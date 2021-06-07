package com.example.painjournal.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class Record (
@PrimaryKey(autoGenerate = true)
    val painDate: String,
    val painTime :String,
    val painType: String,
  //  val painTypeImage: ImageView,
    val painPower: String,
    val painNotes: String,


)