package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EthicsDao {
    @Query("SELECT * FROM player_progress ORDER BY completedAt ASC")
    fun getAllProgress(): Flow<List<PlayerProgressEntity>>

    @Query("SELECT * FROM player_progress WHERE scenarioId = :scenarioId LIMIT 1")
    suspend fun getProgress(scenarioId: String): PlayerProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: PlayerProgressEntity)

    @Query("SELECT * FROM unlocked_cards ORDER BY unlockedAt ASC")
    fun getAllUnlockedCards(): Flow<List<UnlockedCardEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unlockCard(card: UnlockedCardEntity)

    @Query("SELECT * FROM player_profile WHERE id = 1 LIMIT 1")
    fun getProfile(): Flow<PlayerProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: PlayerProfileEntity)

    @Query("SELECT * FROM personal_reflections WHERE id = 1 LIMIT 1")
    fun getReflection(): Flow<PersonalReflectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReflection(reflection: PersonalReflectionEntity)

    @Query("DELETE FROM player_progress")
    suspend fun clearProgress()

    @Query("DELETE FROM unlocked_cards")
    suspend fun clearCards()

    @Query("DELETE FROM personal_reflections")
    suspend fun clearReflections()
}
