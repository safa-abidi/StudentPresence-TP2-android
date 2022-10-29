package com.gl4.studentspresence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class StudentListAdapter(private val data: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() , Filterable {
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
        if (data[position].gender == "m") {
            holder.imageView.setImageResource(R.drawable.boss)
        } else {
            holder.imageView.setImageResource(R.drawable.businesswoman)
        }
        holder.checkbox.isChecked = data[position].isPresent
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    var dataFilterList = ArrayList<Student>()

    init {
        // INITIAL DATA
        dataFilterList = data
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                    val resultList = ArrayList<Student>()
                    for (student in data) {
                        if (student.isPresent && charSearch == "presents"){
                            resultList.add(student)
                        }else if (!student.isPresent && charSearch == "absents"){
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList

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