package com.example.painjournal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.painjournal.adapters.MainActivityAdapter
import com.example.painjournal.databinding.ActivityMainBinding
import com.example.painjournal.main.data.Record
import com.example.painjournal.main.data.RecordViewModel

class MainActivity : AppCompatActivity(), MainActivityAdapter.AdapterClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecordViewModel: RecordViewModel
    private val adapter = MainActivityAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.fab.setOnClickListener {

            val intent = Intent(this, NewRecordActivity::class.java)

            startActivity(intent)

        }

        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mRecordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        mRecordViewModel.readAllData.observe(this, Observer { record ->


            adapter.setData(record)

            toggleEmptyView()


        })


    }

    private fun toggleEmptyView() {

        if (adapter.itemCount == 0) {
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.emptyView.visibility = View.GONE
        }
    }

    override fun onRecordClickListener(record: Record) {
        mRecordViewModel.deleteRecord(record)
    }


}






