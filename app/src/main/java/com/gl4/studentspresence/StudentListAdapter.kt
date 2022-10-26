package com.gl4.studentspresence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter(private val data: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val imageView: ImageView
        val checkbox: CheckBox

        init {
            textView = itemView.findViewById(R.id.textView)
            imageView = itemView.findViewById(R.id.imageView)
            checkbox = itemView.findViewById(R.id.checkBox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position].name
        if(data[position].gender == "m"){
            holder.imageView.setImageResource(R.drawable.boss)
        } else {
            holder.imageView.setImageResource(R.drawable.businesswoman)
        }

        if(data[position].isPresent){
            holder.checkbox.isChecked
        }else{
            !holder.checkbox.isChecked
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}