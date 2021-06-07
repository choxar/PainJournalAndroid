package com.example.painjournal

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.painjournal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false



//        binding.fab.setOnClickListener {
//
//            val intent = Intent(this, NewRecordActivity::class.java)
//
//            startActivity(intent)
//
//        }


        //binding.viewModel = viewModel
        //binding.lifecycleOwner = this

        binding.detailsButton.setOnClickListener {

            //All actions preformed when pressing More details Button

            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)

        }



//        if (dataset.isEmpty()) {
//            binding.mainLayout.visibility = View.GONE
//            binding.emptyView.visibility = View.VISIBLE
//        } else {
//            binding.mainLayout.visibility = View.VISIBLE
//            binding.emptyView.visibility = View.GONE
//        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        TODO("Not yet implemented")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        TODO("Not yet implemented")
    }

}






