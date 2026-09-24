package com.example.repository

import com.example.data.EthicsDao
import com.example.data.PersonalReflectionEntity
import com.example.data.PlayerProfileEntity
import com.example.data.PlayerProgressEntity
import com.example.data.UnlockedCardEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EthicsRepository(private val dao: EthicsDao) {

    val allProgress: Flow<List<PlayerProgressEntity>> = dao.getAllProgress()

    val unlockedCardIds: Flow<Set<String>> = dao.getAllUnlockedCards().map { list ->
        list.map { it.cardId }.toSet()
    }

    val playerProfile: Flow<PlayerProfileEntity?> = dao.getProfile()

    val reflection: Flow<PersonalReflectionEntity?> = dao.getReflection()

    suspend fun recordChoice(
        scenarioId: String,
        chapterId: Int,
        choiceId: String,
        decisionType: String,
        scoreDelta: Int,
        cardToUnlock: String?
    ) {
        val progress = PlayerProgressEntity(
            scenarioId = scenarioId,
            chapterId = chapterId,
            selectedChoiceId = choiceId,
            decisionType = decisionType,
            scoreDelta = scoreDelta,
            completedAt = System.currentTimeMillis()
        )
        dao.saveProgress(progress)

        if (!cardToUnlock.isNullOrBlank()) {
            dao.unlockCard(UnlockedCardEntity(cardId = cardToUnlock))
        }

        // Recompute ethics score based on all progress
        // Baseline 50, clamped [0, 100]
        // Fetch current records to update profile
    }

    suspend fun updateProfileScore(newScore: Int, completedCount: Int) {
        dao.saveProfile(
            PlayerProfileEntity(
                id = 1,
                currentScore = newScore.coerceIn(0, 100),
                completedScenariosCount = completedCount
            )
        )
    }

    suspend fun unlockKnowledgeCard(cardId: String) {
        dao.unlockCard(UnlockedCardEntity(cardId = cardId))
    }

    suspend fun saveReflections(q1: String, q2: String, q3: String) {
        dao.saveReflection(
            PersonalReflectionEntity(
                id = 1,
                q1Answer = q1,
                q2Answer = q2,
                q3Answer = q3,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun resetAllData() {
        dao.clearProgress()
        dao.clearCards()
        dao.clearReflections()
        dao.saveProfile(PlayerProfileEntity(id = 1, currentScore = 50, completedScenariosCount = 0))
    }
}
