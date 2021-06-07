package com.example.painjournal.main.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(record: Record)

    @Query ("SELECT * FROM record_table ORDER BY painDate ASC")
    fun readAllData(): LiveData<List<Record>>



}