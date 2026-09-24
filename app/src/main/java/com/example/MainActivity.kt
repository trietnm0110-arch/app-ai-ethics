package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.screens.ChapterSelectScreen
import com.example.ui.screens.DecisionMapScreen
import com.example.ui.screens.EthicsStatsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.KnowledgeInventoryScreen
import com.example.ui.screens.ReflectionScreen
import com.example.ui.screens.ScenarioPlayScreen
import com.example.ui.screens.TutorialDialog
import com.example.ui.theme.Cyan400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Navy950
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.EthicsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: EthicsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                EthicsQuestApp(viewModel = viewModel)
            }
        }
    }
}

data class NavItem(
    val screen: AppScreen,
    val label: String,
    val icon: ImageVector,
    val tag: String
)

@Composable
fun EthicsQuestApp(viewModel: EthicsViewModel) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val ethicsScore by viewModel.ethicsScore.collectAsStateWithLifecycle()
    val progressList by viewModel.allProgress.collectAsStateWithLifecycle()
    val unlockedCardIds by viewModel.unlockedCardIds.collectAsStateWithLifecycle()
    val reflectionEntity by viewModel.reflection.collectAsStateWithLifecycle()
    val responsibleCount by viewModel.responsibleCount.collectAsStateWithLifecycle()
    val riskyCount by viewModel.riskyCount.collectAsStateWithLifecycle()
    val greyZoneCount by viewModel.greyZoneCount.collectAsStateWithLifecycle()

    var isTutorialVisible by remember { mutableStateOf(false) }

    val navItems = listOf(
        NavItem(AppScreen.HOME, "Trang chủ", Icons.Default.Home, "nav_home"),
        NavItem(AppScreen.CHAPTER_SELECT, "Chương", Icons.Default.CollectionsBookmark, "nav_chapters"),
        NavItem(AppScreen.DECISION_MAP, "Nhánh rẽ", Icons.Default.Timeline, "nav_decision_map"),
        NavItem(AppScreen.KNOWLEDGE_CARDS, "Tri thức", Icons.Default.MenuBook, "nav_inventory"),
        NavItem(AppScreen.REFLECTION, "Phản tư", Icons.Default.Psychology, "nav_reflection")
    )

    // Hide bottom bar when inside active Scenario Play visual novel
    val isPlayingScenario = gameState.currentScreen == AppScreen.SCENARIO_PLAY

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy950),
        bottomBar = {
            if (!isPlayingScenario) {
                NavigationBar(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .testTag("main_bottom_nav"),
                    containerColor = Navy900,
                    tonalElevation = 8.dp
                ) {
                    navItems.forEach { item ->
                        val isSelected = gameState.currentScreen == item.screen
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.navigateTo(item.screen) },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Indigo400,
                                selectedTextColor = Indigo400,
                                indicatorColor = Navy800,
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                            ),
                            modifier = Modifier.testTag(item.tag)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = gameState.currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "screen_transition"
            ) { targetScreen ->
                when (targetScreen) {
                    AppScreen.HOME -> HomeScreen(
                        ethicsScore = ethicsScore,
                        progressList = progressList,
                        unlockedCardsCount = unlockedCardIds.size,
                        responsibleCount = responsibleCount,
                        riskyCount = riskyCount,
                        greyZoneCount = greyZoneCount,
                        onNavigate = { viewModel.navigateTo(it) },
                        onStartScenario = { viewModel.startScenario(it) },
                        onShowTutorial = { isTutorialVisible = true }
                    )

                    AppScreen.CHAPTER_SELECT -> ChapterSelectScreen(
                        ethicsScore = ethicsScore,
                        progressList = progressList,
                        onBack = { viewModel.navigateTo(AppScreen.HOME) },
                        onStartScenario = { viewModel.startScenario(it) },
                        onOpenDecisionMap = { viewModel.openDecisionMapForChapter(it) }
                    )

                    AppScreen.SCENARIO_PLAY -> ScenarioPlayScreen(
                        gameState = gameState,
                        ethicsScore = ethicsScore,
                        onBack = { viewModel.navigateTo(AppScreen.CHAPTER_SELECT) },
                        onAdvanceDialogue = { viewModel.advanceDialogue() },
                        onSelectChoice = { viewModel.selectChoice(it) },
                        onAdvanceToEthicsAnalysis = { viewModel.advanceToEthicsAnalysis() },
                        onCompleteAndContinue = { viewModel.completeScenarioAndContinue() },
                        onDismissCardBanner = { viewModel.dismissCardUnlockBanner() },
                        onReplayScenario = { viewModel.startScenario(it) }
                    )

                    AppScreen.DECISION_MAP -> DecisionMapScreen(
                        currentChapterId = gameState.targetChapterIdForMap,
                        ethicsScore = ethicsScore,
                        progressList = progressList,
                        onSelectChapter = { viewModel.setTargetChapterForMap(it) },
                        onBack = { viewModel.navigateTo(AppScreen.HOME) },
                        onStartScenario = { viewModel.startScenario(it) }
                    )

                    AppScreen.KNOWLEDGE_CARDS -> KnowledgeInventoryScreen(
                        unlockedCardIds = unlockedCardIds,
                        selectedCardDetail = gameState.selectedKnowledgeCardDetail,
                        ethicsScore = ethicsScore,
                        onBack = { viewModel.navigateTo(AppScreen.HOME) },
                        onSelectCard = { viewModel.viewCardDetail(it) }
                    )

                    AppScreen.ETHICS_STATS -> EthicsStatsScreen(
                        ethicsScore = ethicsScore,
                        progressList = progressList,
                        unlockedCardsCount = unlockedCardIds.size,
                        responsibleCount = responsibleCount,
                        riskyCount = riskyCount,
                        greyZoneCount = greyZoneCount,
                        onBack = { viewModel.navigateTo(AppScreen.HOME) },
                        onReplayScenario = { viewModel.startScenario(it) }
                    )

                    AppScreen.REFLECTION -> ReflectionScreen(
                        ethicsScore = ethicsScore,
                        progressList = progressList,
                        unlockedCardsCount = unlockedCardIds.size,
                        reflectionEntity = reflectionEntity,
                        onSaveReflections = { q1, q2, q3 -> viewModel.saveReflections(q1, q2, q3) },
                        onBack = { viewModel.navigateTo(AppScreen.HOME) }
                    )

                    AppScreen.TUTORIAL -> {
                        // Handled via Dialog
                    }
                }
            }

            if (isTutorialVisible) {
                TutorialDialog(onDismiss = { isTutorialVisible = false })
            }
        }
    }
}
