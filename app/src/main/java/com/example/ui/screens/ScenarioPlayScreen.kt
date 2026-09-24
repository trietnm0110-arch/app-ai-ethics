package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Characters
import com.example.model.Choice
import com.example.model.DecisionType
import com.example.model.KnowledgeCard
import com.example.model.Scenario
import com.example.ui.components.CharacterStageView
import com.example.ui.components.ChoiceSelectorCard
import com.example.ui.components.DialogueSpeechBox
import com.example.ui.components.EthicsMeterPill
import com.example.ui.theme.Amber400
import com.example.ui.theme.Cyan400
import com.example.ui.theme.DialogueBoxBg
import com.example.ui.theme.Emerald400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.Indigo500
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Purple400
import com.example.ui.theme.Rose400
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.EthicsGameState

@Composable
fun ScenarioPlayScreen(
    gameState: EthicsGameState,
    ethicsScore: Int,
    onBack: () -> Unit,
    onAdvanceDialogue: () -> Unit,
    onSelectChoice: (Choice) -> Unit,
    onAdvanceToEthicsAnalysis: () -> Unit,
    onCompleteAndContinue: () -> Unit,
    onDismissCardBanner: () -> Unit,
    onReplayScenario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scenario = gameState.currentScenario ?: return

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header: Back, Title, Score Pill
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("scenario_back_button")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Quay lại",
                                tint = TextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text(
                                text = "Chương ${scenario.chapterId} • Tình huống ${scenario.number}/15",
                                color = Cyan400,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = scenario.title,
                                color = TextPrimary,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    EthicsMeterPill(score = ethicsScore)
                }
            }

            // Location & Context Summary Badge
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Navy800.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Cyan400)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = scenario.location,
                            color = Cyan400,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "• ${scenario.subtitle}",
                            color = TextSecondary,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                }
            }

            // Unlocked Card Notification Banner (if any)
            if (gameState.newlyUnlockedCard != null) {
                item {
                    CardUnlockedBanner(
                        card = gameState.newlyUnlockedCard,
                        onDismiss = onDismissCardBanner
                    )
                }
            }

            // ================= PHASE 1: STORY DIALOGUE =================
            if (!gameState.isDilemmaActive && !gameState.isShowingConsequence && !gameState.isShowingEthicsAnalysis) {
                val currentDialogue = scenario.initialDialogues.getOrNull(gameState.dialogueIndex)
                    ?: scenario.initialDialogues.first()
                val currentCharacter = Characters.get(currentDialogue.speaker)

                item {
                    CharacterStageView(
                        character = currentCharacter,
                        emotion = currentDialogue.emotion
                    )
                }

                item {
                    DialogueSpeechBox(
                        dialogue = currentDialogue,
                        onAdvance = onAdvanceDialogue
                    )
                }

                item {
                    // Scenario Context Summary
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Navy800),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "Bối cảnh tình huống:",
                                color = Indigo400,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = scenario.summary,
                                color = TextSecondary,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            // ================= PHASE 2: DILEMMA & CHOICES =================
            if (gameState.isDilemmaActive && !gameState.isShowingConsequence && !gameState.isShowingEthicsAnalysis) {
                item {
                    // Dilemma question hero card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                1.5.dp,
                                Brush.horizontalGradient(listOf(Amber400, Indigo400)),
                                RoundedCornerShape(20.dp)
                            ),
                        colors = CardDefaults.cardColors(containerColor = Navy900),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Amber400,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Đến lượt bạn ra quyết định!",
                                    color = Amber400,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = scenario.dilemmaPrompt,
                                color = TextPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 23.sp
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Chọn phương án hành động của bạn:",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                items(scenario.choices) { choice ->
                    ChoiceSelectorCard(
                        choice = choice,
                        onSelected = { onSelectChoice(choice) }
                    )
                }
            }

            // ================= PHASE 3: CONSEQUENCE SIMULATION =================
            if (gameState.isShowingConsequence && gameState.selectedChoice != null) {
                val choice = gameState.selectedChoice
                val consequence = choice.consequence

                item {
                    ConsequenceHeaderCard(
                        choice = choice,
                        consequence = consequence
                    )
                }

                // Follow-up dialogues in consequence
                items(consequence.followUpDialogues) { dialogue ->
                    val speaker = Characters.get(dialogue.speaker)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, speaker.primaryColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DialogueBoxBg),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(speaker.primaryColor.copy(alpha = 0.2f))
                                    .border(1.dp, speaker.primaryColor, CircleShape)
                            ) {
                                Text(
                                    text = speaker.avatarInitial,
                                    color = speaker.primaryColor,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = speaker.name,
                                    color = speaker.primaryColor,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "“${dialogue.text}”",
                                    color = TextPrimary,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = onAdvanceToEthicsAnalysis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("advance_to_ethics_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = Indigo500),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Xem Phân tích Đạo đức học thuật (AI Ethics)",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // ================= PHASE 4: AI ETHICS ANALYSIS SCREEN =================
            if (gameState.isShowingEthicsAnalysis && gameState.selectedChoice != null) {
                val choice = gameState.selectedChoice
                val analysis = choice.ethicsAnalysis

                item {
                    EthicsAnalysisView(
                        choice = choice,
                        analysis = analysis,
                        onComplete = onCompleteAndContinue,
                        onReplay = { onReplayScenario(scenario.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CardUnlockedBanner(
    card: KnowledgeCard,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, Emerald400, RoundedCornerShape(16.dp)),
        color = Navy800,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Emerald400.copy(alpha = 0.2f))
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = Emerald400,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "ĐÃ MỞ KHÓA THẺ TRI THỨC!",
                        color = Emerald400,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = card.title,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Đóng",
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun ConsequenceHeaderCard(
    choice: Choice,
    consequence: com.example.model.Consequence
) {
    val badgeColor = if (consequence.isPositive) Emerald400 else Rose400

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.5.dp, badgeColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Navy900),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = badgeColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Hệ quả: ${consequence.outcomeBadge}",
                        color = badgeColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = if (choice.scoreDelta > 0) "+${choice.scoreDelta} Điểm" else "${choice.scoreDelta} Điểm",
                    color = if (choice.scoreDelta > 0) Emerald400 else Rose400,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = consequence.title,
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = consequence.narrative,
                color = TextSecondary,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
private fun EthicsAnalysisView(
    choice: Choice,
    analysis: com.example.model.EthicsAnalysis,
    onComplete: () -> Unit,
    onReplay: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Title Banner
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Indigo500.copy(alpha = 0.15f),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, Indigo400)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Indigo400,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "AI ETHICS ANALYSIS",
                        color = Indigo400,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Phân tích Đạo đức & Học thuật",
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Section 1: Your Decision
        AnalysisSectionCard(
            title = "Quyết định của bạn (Your Decision)",
            body = analysis.yourDecision,
            accentColor = Cyan400
        )

        // Section 2: What Happened?
        AnalysisSectionCard(
            title = "Điều gì đã xảy ra? (What Happened?)",
            body = analysis.whatHappened,
            accentColor = if (choice.decisionType == DecisionType.RESPONSIBLE) Emerald400 else Amber400
        )

        // Section 3: Why Does It Matter?
        AnalysisSectionCard(
            title = "Tại sao điều này quan trọng? (Why Does It Matter?)",
            body = analysis.whyDoesItMatter,
            accentColor = Purple400
        )

        // Section 4: Academic Integrity Category
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Navy800),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Danh mục Đạo đức học thuật:",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    color = Rose400.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = analysis.academicCategory,
                        color = Rose400,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Section 5: How Could You Handle It Better?
        AnalysisSectionCard(
            title = "Cách xử lý tốt hơn & có trách nhiệm (How to Handle It Better)",
            body = analysis.howCouldYouHandleItBetter,
            accentColor = Emerald400
        )

        // Mandatory Disclaimer Notice
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Navy900,
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Amber400.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Amber400,
                    modifier = Modifier
                        .size(16.dp)
                        .padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = analysis.disclaimer,
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // CTA: Next scenario or Replay
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Replay Button: "What if you chose differently?"
            OutlinedButton(
                onClick = onReplay,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("replay_scenario_button"),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Cyan400)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Cyan400,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Thử chọn cách khác",
                    color = Cyan400,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Continue Button
            Button(
                onClick = onComplete,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("continue_next_scenario_button"),
                colors = ButtonDefaults.buttonColors(containerColor = Indigo500),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Tiếp tục ▸",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun AnalysisSectionCard(
    title: String,
    body: String,
    accentColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                color = accentColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = body,
                color = TextPrimary,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}
