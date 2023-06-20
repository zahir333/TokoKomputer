package com.example.tokokomputer.repository

import com.example.tokokomputer.dao.KomputerDao
import com.example.tokokomputer.model.Komputer
import kotlinx.coroutines.flow.Flow

class KomputerRepository(private val komputerDao: KomputerDao) {
    val allKomputer: Flow<List<Komputer>> = komputerDao.getALLKomputer()

    suspend fun insertKomputer(komputer: Komputer) {
        komputerDao.insertKomputer(komputer)
    }
    suspend fun deleteKomputer(komputer: Komputer) {
        komputerDao.deleteKomputer(komputer)
    }

    suspend fun updateKomputer(komputer: Komputer) {
        komputerDao.updateKomputer(komputer)
    }
}