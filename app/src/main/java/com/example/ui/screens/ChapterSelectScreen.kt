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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Timer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerProgressEntity
import com.example.model.Chapter
import com.example.model.Scenario
import com.example.model.ScenarioRepositoryData
import com.example.ui.components.EthicsMeterPill
import com.example.ui.theme.Cyan400
import com.example.ui.theme.Emerald400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.Indigo500
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Purple400
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ChapterSelectScreen(
    ethicsScore: Int,
    progressList: List<PlayerProgressEntity>,
    onBack: () -> Unit,
    onStartScenario: (String) -> Unit,
    onOpenDecisionMap: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val progressMap = progressList.associateBy { it.scenarioId }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("back_button")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "Danh sách Chương",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "5 chương • 15 tình huống học thuật",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                EthicsMeterPill(score = ethicsScore)
            }
        }

        // Chapters List
        items(ScenarioRepositoryData.CHAPTERS) { chapter ->
            val scenarios = ScenarioRepositoryData.getScenariosByChapter(chapter.id)
            val completedCount = scenarios.count { progressMap.containsKey(it.id) }

            ChapterCardItem(
                chapter = chapter,
                scenarios = scenarios,
                completedCount = completedCount,
                progressMap = progressMap,
                onStartScenario = onStartScenario,
                onOpenDecisionMap = { onOpenDecisionMap(chapter.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ChapterCardItem(
    chapter: Chapter,
    scenarios: List<Scenario>,
    completedCount: Int,
    progressMap: Map<String, PlayerProgressEntity>,
    onStartScenario: (String) -> Unit,
    onOpenDecisionMap: () -> Unit
) {
    val isAllCompleted = completedCount == scenarios.size
    val chapterIcon = getChapterIcon(chapter.iconName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.5.dp,
                if (isAllCompleted) Emerald400.copy(alpha = 0.5f) else Navy700,
                RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Chapter Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Indigo500.copy(alpha = 0.2f))
                            .border(1.dp, Indigo400, CircleShape)
                    ) {
                        Icon(
                            imageVector = chapterIcon,
                            contentDescription = null,
                            tint = Indigo400,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Chương ${chapter.number}: ${chapter.title}",
                            color = TextPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = chapter.subtitle,
                            color = Cyan400,
                            fontSize = 12.sp
                        )
                    }
                }

                Surface(
                    color = if (isAllCompleted) Emerald400.copy(alpha = 0.15f) else Navy900,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "$completedCount/${scenarios.size} Hoàn thành",
                        color = if (isAllCompleted) Emerald400 else TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = chapter.description,
                color = TextSecondary,
                fontSize = 12.5.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Scenarios inside this Chapter
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                scenarios.forEach { scenario ->
                    val progress = progressMap[scenario.id]
                    val isDone = progress != null

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Navy900)
                            .border(1.dp, if (isDone) Emerald400.copy(alpha = 0.3f) else Navy700, RoundedCornerShape(12.dp))
                            .clickable { onStartScenario(scenario.id) }
                            .padding(12.dp)
                            .testTag("scenario_item_${scenario.id}"),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = if (isDone) Emerald400 else Indigo400,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "${scenario.number}. ${scenario.title}",
                                    color = TextPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = scenario.subtitle,
                                    color = TextSecondary,
                                    fontSize = 11.sp,
                                    maxLines = 1
                                )
                            }
                        }

                        Surface(
                            color = if (isDone) Emerald400.copy(alpha = 0.15f) else Indigo500.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (isDone) "Chơi lại" else "Bắt đầu",
                                color = if (isDone) Emerald400 else Indigo400,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action: View Decision Map for this chapter
            OutlinedButton(
                onClick = onOpenDecisionMap,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chapter_decision_map_button_${chapter.id}"),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Cyan400.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Default.Timeline,
                    contentDescription = null,
                    tint = Cyan400,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Khám phá Decision Map Chương ${chapter.number}",
                    color = Cyan400,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

private fun getChapterIcon(iconName: String): ImageVector = when (iconName) {
    "Timer" -> Icons.Default.Timer
    "AutoAwesome" -> Icons.Default.AutoAwesome
    "Search" -> Icons.Default.Search
    "Quiz" -> Icons.Default.Quiz
    "Balance" -> Icons.Default.Balance
    else -> Icons.Default.Timer
}
