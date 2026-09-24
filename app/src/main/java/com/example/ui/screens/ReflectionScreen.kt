package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PersonalReflectionEntity
import com.example.data.PlayerProgressEntity
import com.example.model.DecisionType
import com.example.model.KnowledgeCardCatalog
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
fun ReflectionScreen(
    ethicsScore: Int,
    progressList: List<PlayerProgressEntity>,
    unlockedCardsCount: Int,
    reflectionEntity: PersonalReflectionEntity?,
    onSaveReflections: (String, String, String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalScenarios = ScenarioRepositoryData.SCENARIOS.size
    val completedCount = progressList.size
    val riskyCount = progressList.count { it.decisionType == DecisionType.RISKY.name }
    val conceptsLearned = unlockedCardsCount

    var q1Text by remember(reflectionEntity) { mutableStateOf(reflectionEntity?.q1Answer ?: "") }
    var q2Text by remember(reflectionEntity) { mutableStateOf(reflectionEntity?.q2Answer ?: "") }
    var q3Text by remember(reflectionEntity) { mutableStateOf(reflectionEntity?.q3Answer ?: "") }
    var isSavedToastVisible by remember { mutableStateOf(false) }

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
                    IconButton(onClick = onBack, modifier = Modifier.testTag("reflection_back_button")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "Hành trình & Phản tư",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Personal Reflection & Academic Integrity",
                            color = Cyan400,
                            fontSize = 12.sp
                        )
                    }
                }

                EthicsMeterPill(score = ethicsScore)
            }
        }

        // YOUR AI ETHICS JOURNEY Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.5.dp, Indigo400, RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = Navy900),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "YOUR AI ETHICS JOURNEY",
                        color = Indigo400,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tổng kết Hành trình Đạo đức Học thuật",
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        JourneyStatItem(
                            value = "$completedCount/$totalScenarios",
                            label = "Tình huống đã qua",
                            color = Cyan400
                        )
                        JourneyStatItem(
                            value = "$conceptsLearned/8",
                            label = "Khái niệm đã học",
                            color = Emerald400
                        )
                        JourneyStatItem(
                            value = "$riskyCount",
                            label = "Quyết định cần xem lại",
                            color = Amber400
                        )
                        JourneyStatItem(
                            value = "$unlockedCardsCount",
                            label = "Thẻ tri thức mở",
                            color = Purple400
                        )
                    }
                }
            }
        }

        // Philosophical Introduction
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Navy800,
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Sự phản tư - Giá trị cao nhất của việc học",
                        color = Purple400,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Trò chơi không nhằm phán xét bạn là 'đạo đức' hay 'thiếu đạo đức'. Mục tiêu lớn nhất là giúp bạn tự phản tỉnh (Self-Reflection), nhận diện các rủi ro tiềm ẩn và hình thành thói quen tư duy độc lập khi sử dụng công nghệ.",
                        color = TextSecondary,
                        fontSize = 12.5.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Prompt 1
        item {
            ReflectionQuestionCard(
                number = "1",
                question = "Trong những tình huống nào bạn cảm thấy khó đưa ra quyết định nhất?",
                hint = "Ví dụ: Áp lực thời gian 23:00, bài thi take-home, hay khi cả nhóm ỷ lại vào AI...",
                textValue = q1Text,
                onTextChanged = { q1Text = it },
                testTag = "reflection_input_q1"
            )
        }

        // Prompt 2
        item {
            ReflectionQuestionCard(
                number = "2",
                question = "Bạn nghĩ ranh giới giữa AI assistance (hỗ trợ học tập) và AI-generated work (làm hộ) nằm ở đâu?",
                hint = "Ví dụ: Khi AI gợi mở cấu trúc tư duy so với khi AI viết thay 100% nội dung...",
                textValue = q2Text,
                onTextChanged = { q2Text = it },
                testTag = "reflection_input_q2"
            )
        }

        // Prompt 3
        item {
            ReflectionQuestionCard(
                number = "3",
                question = "Nếu không chắc một hành vi có được phép hay không, bạn sẽ kiểm tra thông tin ở đâu?",
                hint = "Ví dụ: Đọc Syllabus môn học, hỏi trực tiếp giảng viên/trợ giảng, kiểm tra sổ tay sinh viên...",
                textValue = q3Text,
                onTextChanged = { q3Text = it },
                testTag = "reflection_input_q3"
            )
        }

        // Save Button
        item {
            Button(
                onClick = {
                    onSaveReflections(q1Text, q2Text, q3Text)
                    isSavedToastVisible = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("save_reflections_button"),
                colors = ButtonDefaults.buttonColors(containerColor = Indigo500),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Lưu Nhật ký Phản tư",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (isSavedToastVisible) {
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Emerald400.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Emerald400)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Emerald400,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Đã lưu thành công suy nghĩ của bạn vào cơ sở dữ liệu!",
                            color = Emerald400,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun JourneyStatItem(
    value: String,
    label: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 10.5.sp,
            maxLines = 2
        )
    }
}

@Composable
private fun ReflectionQuestionCard(
    number: String,
    question: String,
    hint: String,
    textValue: String,
    onTextChanged: (String) -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Navy800),
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(Indigo500.copy(alpha = 0.2f))
                        .border(1.dp, Indigo400, CircleShape)
                ) {
                    Text(
                        text = number,
                        color = Indigo400,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = question,
                    color = TextPrimary,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = hint,
                color = TextSecondary,
                fontSize = 11.5.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = textValue,
                onValueChange = onTextChanged,
                placeholder = {
                    Text(
                        text = "Nhập suy nghĩ và chiêm nghiệm của bạn...",
                        color = TextSecondary.copy(alpha = 0.5f),
                        fontSize = 13.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(testTag),
                minLines = 3,
                maxLines = 6,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Indigo400,
                    unfocusedBorderColor = Navy700,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = Cyan400
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
