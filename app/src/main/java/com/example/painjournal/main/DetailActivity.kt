package com.example.painjournal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.painjournal.databinding.DetailMainBinding
import com.example.painjournal.main.data.Record
import com.example.painjournal.main.data.getPainImageType


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record = intent.extras!!.getParcelable<Record>("key")

        binding.painTime.text = record!!.painTime
        binding.painTypeImageViewDetail.setImageResource(getPainImageType(record.painTypeImage)!!.imagePath)
        binding.painDate.text = record!!.painDate
        binding.painTypeLabel.text = record!!.painType
        binding.painPower.text = record!!.painPower
        binding.notesDetail.text = record!!.painNotes
        binding.painPowerSecond.text = record!!.painPower


        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {

            //close, go back to the menu

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }


    }

}