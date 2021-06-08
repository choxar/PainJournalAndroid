package com.example.painjournal.main.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "record_table")
data class Record (
    @PrimaryKey(autoGenerate = true)
    val recordId : Int,
    val painDate: String,
    val painTime :String,
    val painType: String,
    val painTypeImage: Int,
    val painPower: String,
    val painNotes: String,


    ):Parcelable