package com.example.painjournal.main.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(record: Record)

    @Query ("SELECT * FROM record_table ORDER BY painDate ASC")
    fun readAllData(): LiveData<List<Record>>

    @Delete
    fun deleteRecord(record: Record)



}