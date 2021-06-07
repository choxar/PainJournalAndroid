package com.example.painjournal

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.painjournal.databinding.NewMainBinding
import com.example.painjournal.main.data.PainImageType
import kotlinx.android.synthetic.main.new_main.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class NewRecordActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: NewMainBinding


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


        val painPower = arrayOf(
            "Choose Power of Pain",
            "Pain Bearable",
            "Moderate Pain",
            "Serve Pain",
            "Very Serve Pain",
            "Worst Pain Possible"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, painPower)
        val intent = Intent(this, MainActivity::class.java)


        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {

            //close, go back to the menu

            startActivity(intent)

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

                            binding.painTypeImageView.setImageResource(R.drawable.other_pain)

                        }
                        1 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.back_pain)

                        }

                        2 -> {

                            //binding.painTypeImageView.setImageResource(R.drawable.headache_pain)
                            painTypeImageView.setImageResource(PainImageType.HEADACHE.imagePath)
                            //painTypeImageView.setTag(SAVED_IMAGE, PainImageType.HEADACHE.id)
                            TestApplication.instance.imageId = PainImageType.HEADACHE.id



                        }

                        3 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.neck_pain)


                        }

                        4 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.muscle_pain)


                        }

                        5 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.stomachache_pain)


                        }

                        6 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.chest_pain)


                        }

                        7 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.hip_pain)


                        }

                        8 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.joint_pain)


                        }

                        9 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.nerve_pain)


                        }

                        10 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.sore_throat)


                        }

                        11 -> {

                            binding.painTypeImageView.setImageResource(R.drawable.other_pain)


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when (itemView) {

            //here add navigation to home screen and save action
            R.id.action_save -> finish()



        }

        return false
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






