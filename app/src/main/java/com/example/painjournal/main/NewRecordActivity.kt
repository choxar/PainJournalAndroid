package com.example.painjournal

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.painjournal.databinding.NewMainBinding
import com.example.painjournal.main.data.PainImageType
import com.example.painjournal.main.data.Record
import com.example.painjournal.main.data.RecordViewModel
import kotlinx.android.synthetic.main.detail_main.*
import kotlinx.android.synthetic.main.new_main.*
import kotlinx.android.synthetic.main.new_main.painTypeImageView
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class NewRecordActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: NewMainBinding
    private lateinit var mRecordViewModel: RecordViewModel
    private lateinit var arrayAdapter: ArrayAdapter<String>


    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRecordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)

        val painPower = arrayOf(
            "Choose Power of Pain",
            "Pain Bearable",
            "Moderate Pain",
            "Serve Pain",
            "Very Serve Pain",
            "Worst Pain Possible"

        )
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, painPower)
        val intent = Intent(this, MainActivity::class.java)


        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {

            //close, go back to the menu

            startActivity(intent)

        }

        binding.fab2.setOnClickListener {

            insertDataToDatabase()


        }

        val cal = Calendar.getInstance()
        savedDay = cal.get(Calendar.DAY_OF_MONTH)
        savedMonth = cal.get(Calendar.MONTH)
        savedYear = cal.get(Calendar.YEAR)

        savedHour = cal.get(Calendar.HOUR_OF_DAY)
        savedMinute = cal.get(Calendar.MINUTE)

        //val localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")


        binding.datePickerEditText.text = ZonedDateTime.now().format(dateFormatter)

        //binding.datePickerEditText.setText("$savedDay-$savedMonth-$savedYear")

        binding.timePickerEditText.text = ZonedDateTime.now().format(timeFormatter)


        pickDate()
        pickTime()


        binding.spinnerPainPower.adapter = arrayAdapter
        binding.spinnerPainPower.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }


        binding.spinnerPainType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    //Setting Images

                    when (position) {

                        0 -> {

                            painTypeImageView.setImageResource(PainImageType.OTHER.imagePath)
                            TestApplication.instance.imageId = PainImageType.OTHER.id

                        }
                        1 -> {
                            painTypeImageView.setImageResource(PainImageType.BACK_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.BACK_PAIN.id

                        }

                        2 -> {

                            painTypeImageView.setImageResource(PainImageType.HEADACHE.imagePath)
                            TestApplication.instance.imageId = PainImageType.HEADACHE.id


                        }

                        3 -> {

                            painTypeImageView.setImageResource(PainImageType.NECK_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.NECK_PAIN.id


                        }

                        4 -> {

                            painTypeImageView.setImageResource(PainImageType.MUSCLE_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.MUSCLE_PAIN.id


                        }

                        5 -> {

                            painTypeImageView.setImageResource(PainImageType.STOMATCHACHE.imagePath)
                            TestApplication.instance.imageId = PainImageType.STOMATCHACHE.id


                        }

                        6 -> {

                            painTypeImageView.setImageResource(PainImageType.CHEST_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.CHEST_PAIN.id


                        }

                        7 -> {

                            painTypeImageView.setImageResource(PainImageType.HIP_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.HIP_PAIN.id


                        }

                        8 -> {

                            painTypeImageView.setImageResource(PainImageType.JOINT_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.JOINT_PAIN.id

                        }

                        9 -> {

                            painTypeImageView.setImageResource(PainImageType.NERVE_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.NERVE_PAIN.id

                        }

                        10 -> {

                            painTypeImageView.setImageResource(PainImageType.SORE_THROAT.imagePath)
                            TestApplication.instance.imageId = PainImageType.SORE_THROAT.id

                        }

                        11 -> {


                            painTypeImageView.setImageResource(PainImageType.OTHER_PAIN.imagePath)
                            TestApplication.instance.imageId = PainImageType.OTHER_PAIN.id

                        }
                    }

                    //Setting Toast Message

                    if (parent != null) {
                        Toast.makeText(
                            this@NewRecordActivity,
                            "${parent.getItemAtPosition(position).toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {


                }


            }


    }


    private fun insertDataToDatabase() {
        Log.i("test", "test")
        val painDate = datePickerEditText.text.toString()
        val painTime = timePickerEditText.text.toString()
        val painType = spinnerPainType.selectedItem.toString()
        val painTypeImage = TestApplication.instance.imageId
        val painPower = spinnerPainPower.selectedItem.toString()
        val notes = notes.text.toString()

        if (inputCheck(painDate, painTime, painType, painTypeImage, painPower, notes)) {

            val record = Record(0, painDate, painTime, painType, painTypeImage, painPower, notes)

            mRecordViewModel.addRecord(record)
            Toast.makeText(
                this,
                "Record Successfully added to Journal",
                Toast.LENGTH_LONG
            ).show()
            finish()
        } else {

            Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_LONG)
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
        val check = !TextUtils.isEmpty(painDate) && !TextUtils.isEmpty(painTime)
                && spinnerPainType.selectedItemPosition != 0
                && spinnerPainPower.selectedItemPosition != 0
                && notes.isNotEmpty()
        Log.i("Test", "Test")
        return check

    }

    private fun getDateCalendar() {

        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)


    }

    private fun getTimeCalendar() {

        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)


    }


    private fun pickDate() {

        binding.datePickerEditText.setOnClickListener() {

            getDateCalendar()

            DatePickerDialog(this, this, year, month, day).show()

        }

    }

    private fun pickTime() {

        binding.timePickerEditText.setOnClickListener() {

            getTimeCalendar()

            TimePickerDialog(this, this, hour, minute, true).show()

        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateCalendar()

        //TimePickerDialog(this, this,hour,minute, true).show()
        binding.datePickerEditText.setText("$savedDay-$savedMonth-$savedYear")

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        savedHour = hourOfDay
        savedMinute = minute

        binding.timePickerEditText.setText("$savedHour:$savedMinute")
    }


}






