package com.example.vrstyproject.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vrstyproject.CommonUtil.goToNextActivity
import com.example.vrstyproject.R
import com.example.vrstyproject.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        context=this

       /// button =findViewById(R.id.button)
        button.setOnClickListener {
            goToNextActivity(context, RegisterActivity::class.java)

         //   val `in` = Intent(context, RegisterActivity::class.java)
          ////  startActivity(`in`)
        }
    }
}