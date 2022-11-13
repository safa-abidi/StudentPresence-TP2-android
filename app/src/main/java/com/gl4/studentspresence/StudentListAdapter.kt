package com.gl4.studentspresence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import android.os.Handler
import android.util.Log
import kotlin.collections.ArrayList

class StudentListAdapter(private val data: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() , Filterable {
    var dataFilterList = ArrayList<Student>()
    var choice = "0"

    init {
        dataFilterList = data
    }

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
        holder.textView.text = dataFilterList[position].name
        if (dataFilterList[position].gender == "m") {
            holder.imageView.setImageResource(R.drawable.boss)
        } else {
            holder.imageView.setImageResource(R.drawable.businesswoman)
        }
        holder.checkbox.isChecked = dataFilterList[position].isPresent

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            dataFilterList[position].isPresent = isChecked
            Handler().postDelayed({
                filter.filter(choice)
            }, 300)
        }
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var present = constraint.toString() == "1"
                var absent = constraint.toString() == "2"
                choice = constraint.toString()

                dataFilterList = if (!absent && !present) {
                    data
                } else {
                    var resultList = arrayListOf<Student>()
                    if(present){
                        for(i in data) {
                            if(i.isPresent)
                            {
                                resultList.add(i)
                            }
                        }
                    }
                    if(absent)
                    {
                        for(i in data) {
                            if(!i.isPresent)
                            {
                                resultList.add(i)
                            }
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }

    }
}