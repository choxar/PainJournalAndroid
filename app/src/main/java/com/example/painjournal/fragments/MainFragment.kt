package com.example.painjournal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.painjournal.R
import com.example.painjournal.adapters.MainActivityAdapter
import com.example.painjournal.main.data.RecordViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var mRecordViewModel: RecordViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        //Recyclerview

        val recyclerView = view.recyclerview
        val adapter = MainActivityAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Record View Model

        mRecordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        mRecordViewModel.readAllData.observe(viewLifecycleOwner, Observer { record ->

            adapter.setData(record)

        })

        view.fab.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_newFragment)

        }

        return view
    }
}