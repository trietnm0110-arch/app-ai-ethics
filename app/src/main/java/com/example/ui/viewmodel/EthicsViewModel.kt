package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.PersonalReflectionEntity
import com.example.data.PlayerProgressEntity
import com.example.model.Choice
import com.example.model.DecisionType
import com.example.model.KnowledgeCard
import com.example.model.KnowledgeCardCatalog
import com.example.model.Scenario
import com.example.model.ScenarioRepositoryData
import com.example.repository.EthicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    CHAPTER_SELECT,
    SCENARIO_PLAY,
    DECISION_MAP,
    KNOWLEDGE_CARDS,
    ETHICS_STATS,
    REFLECTION,
    TUTORIAL
}

data class EthicsGameState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val currentScenario: Scenario? = null,
    val dialogueIndex: Int = 0,
    val isDilemmaActive: Boolean = false,
    val selectedChoice: Choice? = null,
    val isShowingConsequence: Boolean = false,
    val isShowingEthicsAnalysis: Boolean = false,
    val newlyUnlockedCard: KnowledgeCard? = null,
    val selectedKnowledgeCardDetail: KnowledgeCard? = null,
    val targetChapterIdForMap: Int = 1
)

class EthicsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EthicsRepository =
        EthicsRepository(AppDatabase.getDatabase(application).ethicsDao())

    private val _gameState = MutableStateFlow(EthicsGameState())
    val gameState: StateFlow<EthicsGameState> = _gameState.asStateFlow()

    val allProgress: StateFlow<List<PlayerProgressEntity>> = repository.allProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val unlockedCardIds: StateFlow<Set<String>> = repository.unlockedCardIds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    val reflection: StateFlow<PersonalReflectionEntity?> = repository.reflection
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Calculated dynamic ethics score starting from 50
    val ethicsScore: StateFlow<Int> = allProgress.combine(unlockedCardIds) { progressList, _ ->
        var score = 50
        progressList.forEach { score += it.scoreDelta }
        score.coerceIn(0, 100)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 50)

    val responsibleCount: StateFlow<Int> = allProgress.combine(unlockedCardIds) { list, _ ->
        list.count { it.decisionType == DecisionType.RESPONSIBLE.name }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val riskyCount: StateFlow<Int> = allProgress.combine(unlockedCardIds) { list, _ ->
        list.count { it.decisionType == DecisionType.RISKY.name }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val greyZoneCount: StateFlow<Int> = allProgress.combine(unlockedCardIds) { list, _ ->
        list.count { it.decisionType == DecisionType.GREY_ZONE.name }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun navigateTo(screen: AppScreen) {
        _gameState.value = _gameState.value.copy(
            currentScreen = screen,
            selectedKnowledgeCardDetail = null
        )
    }

    fun startScenario(scenarioId: String) {
        val scenario = ScenarioRepositoryData.getScenario(scenarioId) ?: return
        _gameState.value = _gameState.value.copy(
            currentScreen = AppScreen.SCENARIO_PLAY,
            currentScenario = scenario,
            dialogueIndex = 0,
            isDilemmaActive = false,
            selectedChoice = null,
            isShowingConsequence = false,
            isShowingEthicsAnalysis = false,
            newlyUnlockedCard = null
        )
    }

    fun advanceDialogue() {
        val scenario = _gameState.value.currentScenario ?: return
        val nextIndex = _gameState.value.dialogueIndex + 1
        if (nextIndex < scenario.initialDialogues.size) {
            _gameState.value = _gameState.value.copy(dialogueIndex = nextIndex)
        } else {
            // Dialogue completed -> Show choice dilemma!
            _gameState.value = _gameState.value.copy(
                dialogueIndex = nextIndex,
                isDilemmaActive = true
            )
        }
    }

    fun selectChoice(choice: Choice) {
        val scenario = _gameState.value.currentScenario ?: return
        val cardIdToUnlock = choice.unlockedCardId ?: scenario.associatedCardId
        val cardObj = KnowledgeCardCatalog.getById(cardIdToUnlock)

        _gameState.value = _gameState.value.copy(
            selectedChoice = choice,
            isDilemmaActive = false,
            isShowingConsequence = true,
            isShowingEthicsAnalysis = false,
            newlyUnlockedCard = cardObj
        )

        // Asynchronously persist choice to Room
        viewModelScope.launch {
            repository.recordChoice(
                scenarioId = scenario.id,
                chapterId = scenario.chapterId,
                choiceId = choice.id,
                decisionType = choice.decisionType.name,
                scoreDelta = choice.scoreDelta,
                cardToUnlock = cardIdToUnlock
            )
            val currentProgress = allProgress.value
            val newScore = (ethicsScore.value + choice.scoreDelta).coerceIn(0, 100)
            repository.updateProfileScore(newScore, currentProgress.size + 1)
        }
    }

    fun advanceToEthicsAnalysis() {
        _gameState.value = _gameState.value.copy(
            isShowingConsequence = false,
            isShowingEthicsAnalysis = true
        )
    }

    fun dismissCardUnlockBanner() {
        _gameState.value = _gameState.value.copy(newlyUnlockedCard = null)
    }

    fun completeScenarioAndContinue() {
        val current = _gameState.value.currentScenario ?: run {
            navigateTo(AppScreen.CHAPTER_SELECT)
            return
        }

        // Check if there is next scenario in this chapter
        val currentScenarios = ScenarioRepositoryData.SCENARIOS
        val currentIndex = currentScenarios.indexOfFirst { it.id == current.id }
        if (currentIndex in 0 until currentScenarios.size - 1) {
            val nextScenario = currentScenarios[currentIndex + 1]
            // If still in the same chapter or continuing smoothly
            startScenario(nextScenario.id)
        } else {
            // Finished all 15 scenarios! Navigate to Reflection
            navigateTo(AppScreen.REFLECTION)
        }
    }

    fun openDecisionMapForChapter(chapterId: Int) {
        _gameState.value = _gameState.value.copy(
            currentScreen = AppScreen.DECISION_MAP,
            targetChapterIdForMap = chapterId
        )
    }

    fun setTargetChapterForMap(chapterId: Int) {
        _gameState.value = _gameState.value.copy(targetChapterIdForMap = chapterId)
    }

    fun viewCardDetail(card: KnowledgeCard?) {
        _gameState.value = _gameState.value.copy(selectedKnowledgeCardDetail = card)
    }

    fun saveReflections(q1: String, q2: String, q3: String) {
        viewModelScope.launch {
            repository.saveReflections(q1, q2, q3)
        }
    }

    fun resetJourney() {
        viewModelScope.launch {
            repository.resetAllData()
            _gameState.value = EthicsGameState(currentScreen = AppScreen.HOME)
        }
    }
}
