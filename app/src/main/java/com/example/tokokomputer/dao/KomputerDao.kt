package com.example.tokokomputer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tokokomputer.model.Komputer
import kotlinx.coroutines.flow.Flow

@Dao
interface KomputerDao {
    @Query("SELECT * FROM komputer_table ORDER BY name ASC")
    fun getALLKomputer(): Flow<List<Komputer>>

    @Insert
    suspend fun insertKomputer(komputer: Komputer)

    @Delete
    suspend fun deleteKomputer(komputer: Komputer)

    @Update fun updateKomputer(komputer: Komputer)
}