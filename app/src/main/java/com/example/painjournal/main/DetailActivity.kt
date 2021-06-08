package com.example.painjournal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.painjournal.databinding.DetailMainBinding
import com.example.painjournal.main.data.Record


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record = intent.extras!!.getParcelable<Record>("key")

        binding.painTime.text = record!!.painTime


    }

}