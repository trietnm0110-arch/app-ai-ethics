package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.Amber400
import com.example.ui.theme.Cyan400
import com.example.ui.theme.Emerald400
import com.example.ui.theme.Indigo400
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Rose400
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

fun getScoreThemeColor(score: Int): Color = when {
    score >= 75 -> Emerald400
    score >= 50 -> Cyan400
    score >= 35 -> Amber400
    else -> Rose400
}

fun getScoreTitle(score: Int): String = when {
    score >= 80 -> "Liêm chính gương mẫu"
    score >= 65 -> "Người học có trách nhiệm"
    score >= 50 -> "Cân bằng & Đang khám phá"
    score >= 35 -> "Cảnh báo rủi ro học thuật"
    else -> "Nguy cơ vi phạm nghiêm trọng"
}

@Composable
fun EthicsMeterPill(
    score: Int,
    modifier: Modifier = Modifier
) {
    val themeColor = getScoreThemeColor(score)

    Surface(
        modifier = modifier,
        color = Navy800,
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, themeColor.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Balance,
                contentDescription = "Ethics Meter",
                tint = themeColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Ethics: ",
                color = TextSecondary,
                fontSize = 12.sp
            )
            Text(
                text = "$score/100",
                color = themeColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailedEthicsMeterCard(
    score: Int,
    responsibleCount: Int,
    riskyCount: Int,
    greyZoneCount: Int,
    modifier: Modifier = Modifier
) {
    val themeColor = getScoreThemeColor(score)
    val statusTitle = getScoreTitle(score)
    val animatedProgress by animateFloatAsState(
        targetValue = (score / 100f).coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 600),
        label = "score_progress"
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Navy800,
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, themeColor.copy(alpha = 0.4f))
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(themeColor.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = themeColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Hệ thống Ethics Meter",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = statusTitle,
                            color = themeColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "$score/100",
                    color = themeColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Gauge bar
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = themeColor,
                trackColor = Navy700
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Decision Metrics Summary
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricChip(
                    label = "Có trách nhiệm",
                    count = responsibleCount,
                    color = Emerald400
                )
                MetricChip(
                    label = "Rủi ro học thuật",
                    count = riskyCount,
                    color = Rose400
                )
                MetricChip(
                    label = "Vùng xám",
                    count = greyZoneCount,
                    color = Amber400
                )
            }
        }
    }
}

@Composable
private fun MetricChip(
    label: String,
    count: Int,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$count",
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 11.sp
        )
    }
}
