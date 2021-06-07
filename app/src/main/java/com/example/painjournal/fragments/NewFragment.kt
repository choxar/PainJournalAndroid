package com.example.painjournal.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.painjournal.R
import com.example.painjournal.TestApplication
import com.example.painjournal.main.data.Record
import com.example.painjournal.main.data.RecordViewModel
import kotlinx.android.synthetic.main.fragment_new.*
import kotlinx.android.synthetic.main.fragment_new.view.*


class NewFragment : Fragment() {

    private lateinit var mRecordViewModel: RecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new, container, false)

        mRecordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)

        view.save.setOnClickListener {

            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {

        val painDate = datelabel.text.toString()
        val painTime = timelabel.text.toString()
        val painType = painTypelabel.text.toString()
        val painTypeImage = TestApplication.instance.imageId
        val painPower = painPowerlabel.text.toString()
        val notes = notes.text.toString()

        if (inputCheck(painDate, painTime, painType, painTypeImage, painPower, notes)) {

            val record = Record(0, painDate, painTime, painType, painTypeImage, painPower, notes)

            mRecordViewModel.addRecord(record)
            Toast.makeText(
                requireContext(),
                "Record Successfully added to Journal",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_newFragment_to_mainFragment)
        } else {

            Toast.makeText(requireContext(), "All fields must be filled in", Toast.LENGTH_LONG)
                .show()


        }

    }


    private fun inputCheck(
        painDate: String,
        painTime: String,
        painType: String,
        painTypeImage: Int,
        painPower: String,
        notes: String
    ): Boolean {

        return !(TextUtils.isEmpty(painDate) && TextUtils.isEmpty(painTime) && TextUtils.isEmpty(
            painType
        ) && TextUtils.isEmpty(painPower) && notes.isEmpty())

    }

}