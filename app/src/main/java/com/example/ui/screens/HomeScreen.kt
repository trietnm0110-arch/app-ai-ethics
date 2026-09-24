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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.data.PlayerProgressEntity
import com.example.model.ScenarioRepositoryData
import com.example.ui.components.DetailedEthicsMeterCard
import com.example.ui.theme.Amber400
import com.example.ui.theme.Cyan400
import com.example.ui.theme.Emerald400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.Indigo500
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Purple400
import com.example.ui.theme.Purple500
import com.example.ui.theme.Rose400
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.AppScreen

@Composable
fun HomeScreen(
    ethicsScore: Int,
    progressList: List<PlayerProgressEntity>,
    unlockedCardsCount: Int,
    responsibleCount: Int,
    riskyCount: Int,
    greyZoneCount: Int,
    onNavigate: (AppScreen) -> Unit,
    onStartScenario: (String) -> Unit,
    onShowTutorial: () -> Unit,
    modifier: Modifier = Modifier
) {
    val completedScenarios = progressList.size
    val totalScenarios = ScenarioRepositoryData.SCENARIOS.size

    // Determine the next scenario to play
    val completedIds = progressList.map { it.scenarioId }.toSet()
    val nextScenario = ScenarioRepositoryData.SCENARIOS.firstOrNull { it.id !in completedIds }
        ?: ScenarioRepositoryData.SCENARIOS.first()

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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(listOf(Indigo500, Purple500))
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "EthicsQuest Logo",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "EthicsQuest",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Đạo đức học thuật & Trí tuệ Nhân tạo",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                IconButton(
                    onClick = onShowTutorial,
                    modifier = Modifier.testTag("tutorial_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = "Hướng dẫn",
                        tint = Cyan400,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Hero Banner Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        1.5.dp,
                        Brush.horizontalGradient(listOf(Indigo400, Purple400, Cyan400)),
                        RoundedCornerShape(24.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = Navy900),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Indigo500.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Indigo400.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = "Visual Novel • Decision Simulator",
                                color = Indigo300(),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = "Tiến độ: $completedScenarios/$totalScenarios",
                            color = Cyan400,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Học thông qua Quyết định & Hệ quả",
                        color = TextPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Đứng trước deadline áp lực, cạm bẫy trích dẫn ma và kỳ thi take-home, mọi quyết định của bạn đều dẫn đến những bước ngoặt thực tế.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Main Action CTA Button
                    Button(
                        onClick = { onStartScenario(nextScenario.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("continue_journey_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Indigo500
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (completedScenarios == 0) "Bắt đầu Chương 1" else "Tiếp tục: ${nextScenario.title}",
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
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
        }

        // Ethics Meter Detailed Card
        item {
            DetailedEthicsMeterCard(
                score = ethicsScore,
                responsibleCount = responsibleCount,
                riskyCount = riskyCount,
                greyZoneCount = greyZoneCount
            )
        }

        // Quick Navigation Grid
        item {
            Text(
                text = "Các tính năng chính",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Chapter Selection
                QuickFeatureCard(
                    title = "5 Chương",
                    subtitle = "15 Tình huống",
                    icon = Icons.Default.CollectionsBookmark,
                    iconColor = Indigo400,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppScreen.CHAPTER_SELECT) }
                )

                // Decision Map (What if)
                QuickFeatureCard(
                    title = "Decision Map",
                    subtitle = "Nhánh rẽ & Replay",
                    icon = Icons.Default.Timeline,
                    iconColor = Cyan400,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppScreen.DECISION_MAP) }
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Knowledge Cards
                QuickFeatureCard(
                    title = "Thẻ Tri thức",
                    subtitle = "$unlockedCardsCount/8 Đã mở khóa",
                    icon = Icons.Default.MenuBook,
                    iconColor = Purple400,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppScreen.KNOWLEDGE_CARDS) }
                )

                // Reflection
                QuickFeatureCard(
                    title = "Phản tư cá nhân",
                    subtitle = "Hành trình & Góc nhìn",
                    icon = Icons.Default.Psychology,
                    iconColor = Emerald400,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppScreen.REFLECTION) }
                )
            }
        }

        // University Disclaimer Footer Card
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Navy800.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Amber400,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Quy định cụ thể có thể khác nhau giữa từng trường, khoa và môn học. Hãy luôn kiểm tra syllabus, quy chế đào tạo hoặc hỏi trực tiếp giảng viên trước khi sử dụng AI.",
                        color = TextSecondary,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun QuickFeatureCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, Navy700, RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .testTag("quick_feature_${title}"),
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconColor.copy(alpha = 0.18f))
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                color = TextSecondary,
                fontSize = 11.5.sp
            )
        }
    }
}

@Composable
private fun Indigo300(): Color = Color(0xFFA5B4FC)
