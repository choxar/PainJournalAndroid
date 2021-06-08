package com.example.painjournal.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.painjournal.R
import com.example.painjournal.TestApplication
import com.example.painjournal.databinding.FragmentNewBinding
import com.example.painjournal.main.data.Record
import com.example.painjournal.main.data.RecordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_new.*


class NewFragment : Fragment() {

    private lateinit var mRecordViewModel: RecordViewModel

    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewBinding.inflate(inflater, container, false)

        mRecordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener {
            Log.i("test", "test")
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        Log.i("test", "test")
        val painDate = painDate.text.toString()
        val painTime = painTime.text.toString()
        val painType = painTypelabel.text.toString()
        val painTypeImage = TestApplication.instance.imageId
        val painPower = painPower.text.toString()
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