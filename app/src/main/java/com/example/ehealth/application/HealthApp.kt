package com.example.ehealth.application

import android.app.Application
import com.example.ehealth.repository.HealthRepository

class HealthApp: Application() {
    val database by lazy { HealthDatabase.getDatabase(this) }
    val repository by lazy { HealthRepository(database.healthDao()) }
}