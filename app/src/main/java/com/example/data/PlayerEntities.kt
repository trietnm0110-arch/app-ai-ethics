package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_progress")
data class PlayerProgressEntity(
    @PrimaryKey val scenarioId: String,
    val chapterId: Int,
    val selectedChoiceId: String,
    val decisionType: String,
    val scoreDelta: Int,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_cards")
data class UnlockedCardEntity(
    @PrimaryKey val cardId: String,
    val unlockedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "player_profile")
data class PlayerProfileEntity(
    @PrimaryKey val id: Int = 1,
    val currentScore: Int = 50,
    val completedScenariosCount: Int = 0,
    val playerName: String = "Sinh viên"
)

@Entity(tableName = "personal_reflections")
data class PersonalReflectionEntity(
    @PrimaryKey val id: Int = 1,
    val q1Answer: String = "",
    val q2Answer: String = "",
    val q3Answer: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)
