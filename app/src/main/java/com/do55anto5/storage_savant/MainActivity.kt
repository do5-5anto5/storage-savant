package com.do55anto5.storage_savant

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.do55anto5.storage_savant.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    private lateinit var sharedPref: SharedPreferences

    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)

        userManager = UserManager(this)

        initListeners()
    }

    private fun initListeners(){
        bind.button.setOnClickListener { saveDataWithSharedPreferences() }
        bind.button2.setOnClickListener { getDataWithSharedPreferences() }

        bind.button3.setOnClickListener { saveDataWithDataStore() }
    }

    private fun saveDataWithDataStore() {
        val name = bind.editText3.text.toString()
        val age = bind.editText4.text.toString().toInt()
        val isAuthenticated = bind.checkBox.isChecked

        lifecycleScope.launch {
        userManager.saveUserData(name, age, isAuthenticated)
        }

    }

    private fun saveDataWithSharedPreferences () {
        val name = bind.editName.text.toString()
        val lastName = bind.editLastName.text.toString()

        with (sharedPref.edit()) {
            putString("name", name)
            putString("lastName", lastName)
            apply()
        }
    }

    private fun getDataWithSharedPreferences () {
        val name = sharedPref.getString("name", "")
        val lastName = sharedPref.getString("lastName", "")

        bind.textView2.text = name + " " + lastName
    }
}