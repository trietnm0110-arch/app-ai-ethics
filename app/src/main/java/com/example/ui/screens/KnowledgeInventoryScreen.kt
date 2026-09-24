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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.model.KnowledgeCard
import com.example.model.KnowledgeCardCatalog
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
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeInventoryScreen(
    unlockedCardIds: Set<String>,
    selectedCardDetail: KnowledgeCard?,
    ethicsScore: Int,
    onBack: () -> Unit,
    onSelectCard: (KnowledgeCard?) -> Unit,
    modifier: Modifier = Modifier
) {
    val allCards = KnowledgeCardCatalog.CARDS

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
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
                        IconButton(onClick = onBack, modifier = Modifier.testTag("inventory_back_button")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Quay lại",
                                tint = TextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text(
                                text = "Thẻ Tri thức (Inventory)",
                                color = TextPrimary,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Đã mở khóa: ${unlockedCardIds.size}/${allCards.size}",
                                color = Cyan400,
                                fontSize = 12.sp
                            )
                        }
                    }

                    EthicsMeterPill(score = ethicsScore)
                }
            }

            // Overview Banner
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Navy800,
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Navy700)
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
                                .background(Purple400.copy(alpha = 0.2f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = Purple400,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Các nguyên tắc cốt lõi về Liêm chính học thuật và Sử dụng AI có trách nhiệm được đúc kết từ các tình huống.",
                            color = TextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Cards Grid
            items(allCards) { card ->
                val isUnlocked = unlockedCardIds.contains(card.id)
                KnowledgeCardItem(
                    card = card,
                    isUnlocked = isUnlocked,
                    onClick = {
                        if (isUnlocked) onSelectCard(card)
                    }
                )
            }
        }

        // Card Detail Modal
        if (selectedCardDetail != null) {
            BasicAlertDialog(
                onDismissRequest = { onSelectCard(null) }
            ) {
                KnowledgeCardDetailDialog(
                    card = selectedCardDetail,
                    onDismiss = { onSelectCard(null) }
                )
            }
        }
    }
}

@Composable
private fun KnowledgeCardItem(
    card: KnowledgeCard,
    isUnlocked: Boolean,
    onClick: () -> Unit
) {
    val cardIcon = getCardIcon(card.iconName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(
                1.dp,
                if (isUnlocked) Indigo400.copy(alpha = 0.5f) else Navy700,
                RoundedCornerShape(18.dp)
            )
            .clickable(enabled = isUnlocked) { onClick() }
            .testTag("knowledge_card_${card.id}"),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) Navy800 else Navy900.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUnlocked) Indigo500.copy(alpha = 0.2f) else Navy700.copy(alpha = 0.4f)
                    )
            ) {
                Icon(
                    imageVector = if (isUnlocked) cardIcon else Icons.Default.Lock,
                    contentDescription = null,
                    tint = if (isUnlocked) Cyan400 else TextSecondary.copy(alpha = 0.5f),
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isUnlocked) card.title else "Thẻ bị khóa",
                        color = if (isUnlocked) TextPrimary else TextSecondary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Surface(
                        color = if (isUnlocked) Emerald400.copy(alpha = 0.15f) else Navy700.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = if (isUnlocked) "Đã mở khóa" else "Chưa mở",
                            color = if (isUnlocked) Emerald400 else TextSecondary.copy(alpha = 0.6f),
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (isUnlocked) card.summary else "Hoàn thành các tình huống tương ứng để mở khóa thẻ tri thức này.",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun KnowledgeCardDetailDialog(
    card: KnowledgeCard,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(2.dp, Indigo400, RoundedCornerShape(24.dp)),
        color = Navy900,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Indigo500.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = card.category,
                        color = Cyan400,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Đóng",
                        tint = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.title,
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = card.englishTerm,
                color = TextSecondary,
                fontSize = 12.5.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Core Principle
            Text(
                text = "Nguyên tắc cốt lõi:",
                color = Purple400,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.corePrinciple,
                color = TextPrimary,
                fontSize = 13.5.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Realistic Example
            Text(
                text = "Ví dụ thực tế:",
                color = Amber400,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.realisticExample,
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Safe Action Guide
            Text(
                text = "Hướng dẫn thực hành an toàn:",
                color = Emerald400,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.safeActionGuide,
                color = TextPrimary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Indigo500),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Đã hiểu",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun getCardIcon(iconName: String): ImageVector = when (iconName) {
    "MenuBook" -> Icons.Default.MenuBook
    "SmartToy" -> Icons.Default.SmartToy
    "WarningAmber" -> Icons.Default.WarningAmber
    "FactCheck" -> Icons.Default.FactCheck
    "Gavel" -> Icons.Default.Gavel
    "Science" -> Icons.Default.Science
    "EditNote" -> Icons.Default.EditNote
    "Groups" -> Icons.Default.Groups
    else -> Icons.Default.AutoAwesome
}
