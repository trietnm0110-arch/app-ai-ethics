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
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerProgressEntity
import com.example.model.DecisionType
import com.example.model.ScenarioRepositoryData
import com.example.ui.components.DetailedEthicsMeterCard
import com.example.ui.components.EthicsMeterPill
import com.example.ui.theme.Amber400
import com.example.ui.theme.Cyan400
import com.example.ui.theme.Emerald400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Purple400
import com.example.ui.theme.Rose400
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun EthicsStatsScreen(
    ethicsScore: Int,
    progressList: List<PlayerProgressEntity>,
    unlockedCardsCount: Int,
    responsibleCount: Int,
    riskyCount: Int,
    greyZoneCount: Int,
    onBack: () -> Unit,
    onReplayScenario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalDecisions = progressList.size
    val riskyProgressItems = progressList.filter { it.decisionType == DecisionType.RISKY.name }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("stats_back_button")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "Hồ sơ Ethics Meter",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Phân tích xu hướng ra quyết định",
                            color = Cyan400,
                            fontSize = 12.sp
                        )
                    }
                }

                EthicsMeterPill(score = ethicsScore)
            }
        }

        // Full Meter Card
        item {
            DetailedEthicsMeterCard(
                score = ethicsScore,
                responsibleCount = responsibleCount,
                riskyCount = riskyCount,
                greyZoneCount = greyZoneCount
            )
        }

        // Four Key Pillar Metrics
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatMetricCard(
                    title = "Decisions Made",
                    value = "$totalDecisions/15",
                    sub = "Quyết định đã đưa ra",
                    color = Indigo400,
                    modifier = Modifier.weight(1f)
                )
                StatMetricCard(
                    title = "Concepts Learned",
                    value = "$unlockedCardsCount/8",
                    sub = "Khái niệm đã tiếp thu",
                    color = Emerald400,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatMetricCard(
                    title = "Responsible",
                    value = "$responsibleCount",
                    sub = "Hành vi có trách nhiệm",
                    color = Cyan400,
                    modifier = Modifier.weight(1f)
                )
                StatMetricCard(
                    title = "Risky Decisions",
                    value = "$riskyCount",
                    sub = "Quyết định có rủi ro",
                    color = Rose400,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Mistakes to Review Section
        item {
            Text(
                text = "Mistakes to Review (Các quyết định nên xem lại):",
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (riskyProgressItems.isEmpty()) {
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Navy800,
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Emerald400.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Emerald400,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (totalDecisions == 0) "Bạn chưa bắt đầu tình huống nào. Hãy bắt đầu Chương 1!" else "Tuyệt vời! Bạn chưa có quyết định rủi ro nào cần xem lại.",
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        } else {
            items(riskyProgressItems) { item ->
                val scenario = ScenarioRepositoryData.getScenario(item.scenarioId)
                if (scenario != null) {
                    val chosenChoice = scenario.choices.find { it.id == item.selectedChoiceId }
                    MistakeReviewCard(
                        scenario = scenario,
                        chosenChoiceTitle = chosenChoice?.title ?: "Lựa chọn rủi ro",
                        academicCategory = chosenChoice?.ethicsAnalysis?.academicCategory ?: "Rủi ro học thuật",
                        onReplay = { onReplayScenario(scenario.id) }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun StatMetricCard(
    title: String,
    value: String,
    sub: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = title,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = sub,
                color = TextSecondary,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun MistakeReviewCard(
    scenario: com.example.model.Scenario,
    chosenChoiceTitle: String,
    academicCategory: String,
    onReplay: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Rose400.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Navy900),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tình huống ${scenario.number}: ${scenario.title}",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = Rose400.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Cần rút kinh nghiệm",
                        color = Rose400,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Bạn đã chọn: $chosenChoiceTitle",
                color = TextSecondary,
                fontSize = 12.5.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Danh mục vi phạm: $academicCategory",
                color = Amber400,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onReplay,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .testTag("replay_mistake_button_${scenario.id}"),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Cyan400)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Cyan400,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Thử lại tình huống này với hướng đi có trách nhiệm",
                    color = Cyan400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
