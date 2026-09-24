package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerProgressEntity
import com.example.model.Choice
import com.example.model.DecisionType
import com.example.model.Scenario
import com.example.model.ScenarioRepositoryData
import com.example.ui.components.EthicsMeterPill
import com.example.ui.theme.Amber400
import com.example.ui.theme.Cyan400
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

@Composable
fun DecisionMapScreen(
    currentChapterId: Int,
    ethicsScore: Int,
    progressList: List<PlayerProgressEntity>,
    onSelectChapter: (Int) -> Unit,
    onBack: () -> Unit,
    onStartScenario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val chapters = ScenarioRepositoryData.CHAPTERS
    val currentChapter = chapters.find { it.id == currentChapterId } ?: chapters.first()
    val scenarios = ScenarioRepositoryData.getScenariosByChapter(currentChapter.id)
    val progressMap = progressList.associateBy { it.scenarioId }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("decision_map_back_button")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "Decision Map",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Khám phá các nhánh rẽ học thuật",
                            color = Cyan400,
                            fontSize = 12.sp
                        )
                    }
                }

                EthicsMeterPill(score = ethicsScore)
            }
        }

        // Philosophy banner: What if you chose differently?
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Navy800),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Cyan400.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Cyan400.copy(alpha = 0.15f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.AltRoute,
                            contentDescription = null,
                            tint = Cyan400,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "“What if you chose differently?”",
                            color = TextPrimary,
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Mỗi quyết định mở ra một kết cục khác nhau. Nhấn 'Thử nhánh này' để trải nghiệm!",
                            color = TextSecondary,
                            fontSize = 11.5.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // Chapter Tab Selector
        item {
            ScrollableTabRow(
                selectedTabIndex = currentChapterId - 1,
                containerColor = Navy900,
                contentColor = TextPrimary,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[currentChapterId - 1]),
                        color = Indigo400
                    )
                }
            ) {
                chapters.forEach { chapter ->
                    Tab(
                        selected = chapter.id == currentChapterId,
                        onClick = { onSelectChapter(chapter.id) },
                        text = {
                            Text(
                                text = "Chương ${chapter.number}",
                                fontSize = 13.sp,
                                fontWeight = if (chapter.id == currentChapterId) FontWeight.Bold else FontWeight.Normal,
                                color = if (chapter.id == currentChapterId) Indigo400 else TextSecondary
                            )
                        }
                    )
                }
            }
        }

        // Scenario Branch Nodes
        items(scenarios) { scenario ->
            val playerProgress = progressMap[scenario.id]
            ScenarioBranchNodeCard(
                scenario = scenario,
                playerProgress = playerProgress,
                onReplayScenario = { onStartScenario(scenario.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ScenarioBranchNodeCard(
    scenario: Scenario,
    playerProgress: PlayerProgressEntity?,
    onReplayScenario: () -> Unit
) {
    val isDone = playerProgress != null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.5.dp,
                if (isDone) Indigo400.copy(alpha = 0.5f) else Navy700,
                RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Node Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (isDone) Emerald400.copy(alpha = 0.2f) else Navy700)
                    ) {
                        Text(
                            text = "${scenario.number}",
                            color = if (isDone) Emerald400 else TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = scenario.title,
                        color = TextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Surface(
                    color = if (isDone) Emerald400.copy(alpha = 0.15f) else Navy900,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (isDone) "Đã khám phá" else "Chưa chơi",
                        color = if (isDone) Emerald400 else TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Branches Tree
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                scenario.choices.forEach { choice ->
                    val isChosen = playerProgress?.selectedChoiceId == choice.id
                    ChoiceBranchPill(
                        choice = choice,
                        isChosen = isChosen
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Replay CTA
            OutlinedButton(
                onClick = onReplayScenario,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("branch_replay_button_${scenario.id}"),
                shape = RoundedCornerShape(10.dp),
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
                    text = if (isDone) "Chơi lại tình huống & chọn nhánh khác" else "Bắt đầu khám phá tình huống này",
                    color = Cyan400,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ChoiceBranchPill(
    choice: Choice,
    isChosen: Boolean
) {
    val branchColor = when (choice.decisionType) {
        DecisionType.RESPONSIBLE -> Emerald400
        DecisionType.RISKY -> Rose400
        DecisionType.GREY_ZONE -> Amber400
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = if (isChosen) Navy900 else Navy800.copy(alpha = 0.6f),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isChosen) branchColor else Navy700.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(branchColor.copy(alpha = 0.2f))
                ) {
                    Text(
                        text = choice.letter,
                        color = branchColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = choice.title,
                    color = if (isChosen) TextPrimary else TextSecondary,
                    fontSize = 12.5.sp,
                    fontWeight = if (isChosen) FontWeight.SemiBold else FontWeight.Normal,
                    maxLines = 1
                )
            }

            if (isChosen) {
                Surface(
                    color = branchColor.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Lựa chọn của bạn",
                        color = branchColor,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            } else {
                Text(
                    text = "Chưa thử",
                    color = TextSecondary.copy(alpha = 0.5f),
                    fontSize = 11.sp
                )
            }
        }
    }
}
