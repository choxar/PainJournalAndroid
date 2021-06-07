package com.example.painjournal.main.data

import com.example.painjournal.R

const val SAVED_IMAGE = 1

enum class PainImageType(val id: Int, val imagePath: Int) {

    HEADACHE( 1, R.drawable.headache_pain),
    OTHER( 2, R.drawable.other_pain),
    BACK_PAIN(3, R.drawable.back_pain),
    NECK_PAIN(4, R.drawable.neck_pain),
    MUSCLE_PAIN(5, R.drawable.muscle_pain),
    STOMATCHACHE(6, R.drawable.stomachache_pain)


}

fun getPainImageType(id: Int): PainImageType? = PainImageType.values().find { it.id == id }

