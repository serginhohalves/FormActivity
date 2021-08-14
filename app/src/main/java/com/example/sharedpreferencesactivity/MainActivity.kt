package com.example.sharedpreferencesactivity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.content.edit
import com.example.sharedpreferencesactivity.databinding.ActivityMainBinding
import com.example.sharedpreferencesactivity.utils.getText
import com.example.sharedpreferencesactivity.utils.toEditable
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("personal_data", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("Lifecycle","onCreate")


//        with(sharedPreferences.edit()){
//            putString("name","Sergio")
//            putBoolean("isSaved", true)
//            apply()
//        }

//        sharedPreferences.edit(){
//            putString("email","serginho.halves@hotmail.com")
//            putBoolean("xpto",false)
//
//        }
        binding.btSave.setOnClickListener{
            saveUserData(sharedPreferences)
        }

    }

    override fun onResume() {
        super.onResume()
        getUserData()
        Log.i("lifecycle", "onResume")
    }

    private fun getUserData() {
        with(sharedPreferences){
           val name =  getString(KEY_SHARED_NAME,"")
            val email = getString(KEY_SHARED_EMAIL,"")
            val gender = getString(KEY_SHARED_GENDER,"")
            val phone = getString(KEY_SHARED_PHONE,"")
            val date = getString(KEY_SHARED_DATE,"")

            with(binding){
                etName.editText?.text = name.toEditable()
                etEmail.editText?.text = email.toEditable()
                etGender.editText?.text = gender.toEditable()
                etPhone.editText?.text = phone.toEditable()
            }

        }


    }

    private fun saveUserData(sharedPreferences: SharedPreferences) {
        with(binding) {
            val name = etName.getText()
            val email = etEmail.getText()
            val gender = etGender.getText()
            val phone = etPhone.getText()
            val day = etDay.getText()
            val month = etMonth.getText()
            val year = etYear.getText()
            val date = "$day/$month/$year"


            sharedPreferences.edit {
                putString("name", name)
                putString("email", email)
                putString("gender", gender)
                putString("phone", phone)
                putString("date", date)
            }
            Snackbar.make(
                btSave,
                getString(R.string.data_saved_sucessfully),
                Snackbar.LENGTH_SHORT
            ).show()

        }
    }

    companion object{
        const val KEY_SHARED_NAME = "name"
        const val KEY_SHARED_EMAIL = "email"
        const val KEY_SHARED_GENDER = "gender"
        const val KEY_SHARED_PHONE = "phone"
        const val KEY_SHARED_DATE= "date"

    }
}