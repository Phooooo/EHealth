package com.example.ehealth.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ehealth.model.Health
import kotlinx.coroutines.flow.Flow


@Dao
interface HealthDao {
    @Query("SELECT * FROM health_table ORDER BY id ASC")
    fun getAllHealth(): Flow<List<Health>>

    @Insert
    suspend fun insertHealth(health: Health)

    @Delete
    suspend fun deleteHealth(health: Health)

    @Update
    fun updateHealth(health: Health)
}