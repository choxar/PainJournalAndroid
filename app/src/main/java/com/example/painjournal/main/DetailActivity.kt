package com.example.painjournal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.painjournal.databinding.DetailMainBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}