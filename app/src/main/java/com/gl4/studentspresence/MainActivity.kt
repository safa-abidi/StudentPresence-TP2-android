package com.gl4.studentspresence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val recyclerView : RecyclerView by lazy { findViewById(R.id.recyclerView)  }
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}