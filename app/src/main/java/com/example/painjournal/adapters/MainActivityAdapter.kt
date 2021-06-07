package com.example.painjournal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.painjournal.R
import com.example.painjournal.main.data.Record
import kotlinx.android.synthetic.main.detail_main.view.*

class MainActivityAdapter: RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>() {

    private var recordsMainActivity = emptyList<Record>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityAdapter.MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentRecord = recordsMainActivity[position]
        holder.itemView.painDate.text = recordsMainActivity.painDate.toString()
//        holder.itemView.painTime.text = recordsMainActivity.painTime.toString()
//        holder.itemView.painPower.text = recordsMainActivity.PainPower.toString()
//        holder.itemView.painTypeLabel.text = recordsMainActivity.painType.toString()
//        holder.itemView.painTypeImageView.text = recordsMainActivity.painTypeImage.toString()



    }

    override fun getItemCount(): Int {

        return recordsMainActivity.size

    }

fun setData(record: List<Record>) {

this.recordsMainActivity = record
    notifyDataSetChanged()

}


}