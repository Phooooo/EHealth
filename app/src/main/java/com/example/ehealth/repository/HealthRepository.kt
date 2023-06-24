package com.example.ehealth.repository

import com.example.ehealth.dao.HealthDao
import com.example.ehealth.model.Health
import kotlinx.coroutines.flow.Flow

class HealthRepository (private val healthDao:HealthDao) {
    val allHealths: Flow<List<Health>> = healthDao.getAllHealth()

    suspend fun insert(health: Health) {
        healthDao.insertHealth(health)
    }

    suspend fun delete(health: Health) {
        healthDao.deleteHealth(health)
    }

    suspend fun update(health: Health) {
        healthDao.updateHealth(health)
    }
}