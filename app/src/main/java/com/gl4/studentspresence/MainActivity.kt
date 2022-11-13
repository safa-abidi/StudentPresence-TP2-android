package com.gl4.studentspresence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val recyclerView : RecyclerView by lazy { findViewById(R.id.recyclerView)  }
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    lateinit var myAdapter: StudentListAdapter

    var studentList = arrayListOf(
        Student("jean paul", "m", true),
        Student("abidi safa","f" ,false),
        Student("achour ines","f", false),
        Student("sammari amal","f", true),
        Student("samet rayen","m", true),
        Student("stefan françois", "m", false))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myAdapter = StudentListAdapter(studentList)

        var listes = listOf("all","presents","absents")
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,listes)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                myAdapter.filter.filter(position.toString())
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = myAdapter
        }
    }
}