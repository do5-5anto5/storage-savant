package com.do55anto5.storage_savant

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.do55anto5.storage_savant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)

        bind.button.setOnClickListener { saveData() }
        bind.button2.setOnClickListener { getData() }
    }

    private fun saveData () {
        val name = bind.editName.text.toString()
        val lastName = bind.editLastName.text.toString()

        with (sharedPref.edit()) {
            putString("name", name)
            putString("lastName", lastName)
            apply()
        }
    }

    private fun getData () {
        val name = sharedPref.getString("name", "")
        val lastName = sharedPref.getString("lastName", "")

        bind.textView2.text = name + " " + lastName
    }
}