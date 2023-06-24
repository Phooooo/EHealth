package com.example.ehealth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ehealth.model.Health
import com.example.ehealth.repository.HealthRepository
import kotlinx.coroutines.launch

class HealthViewModel(private val repository: HealthRepository): ViewModel() {
    val allHealths: LiveData<List<Health>> = repository.allHealths.asLiveData()

    fun insert(health: Health) = viewModelScope.launch {
        repository.insert(health)
    }

    fun delete(health: Health) = viewModelScope.launch {
        repository.delete(health)
    }

    fun update(health: Health) = viewModelScope.launch {
        repository.update(health)
    }
}

class HealthViewModelFactory(private val repository: HealthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((HealthViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return HealthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


