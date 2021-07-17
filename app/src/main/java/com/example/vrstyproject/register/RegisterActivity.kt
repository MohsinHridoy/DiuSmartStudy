package com.example.vrstyproject.register

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.vrstyproject.CommonUtil.goToNextActivity
import com.example.vrstyproject.R
import com.example.vrstyproject.home.HomeActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var context: Context
    val STARTUP_DELAY = 300
    val ANIM_ITEM_DURATION = 1000
    val EDITTEXT_DELAY = 300
    val BUTTON_DELAY = 500
    val VIEW_DELAY = 400
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        context=this

        val logoImageView = findViewById<LottieAnimationView>(R.id.img_logo)
       // val text = findViewById<TextView>(R.id.txt1)

        val container = findViewById<ViewGroup>(R.id.container)

        //Code for app logo animation

        //Code for app logo animation
        ViewCompat.animate(logoImageView)
                .translationY(-250f)
                .setStartDelay(STARTUP_DELAY.toLong())
                .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                        DecelerateInterpolator(1.2f)).start()

     //   ViewCompat.animate(text)
              //  .translationY(-250f)
                //.setStartDelay(STARTUP_DELAY.toLong())
               // .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                      //  DecelerateInterpolator(1.2f)).start()

        //Here we are setting animation on displaying content

        //Here we are setting animation on displaying content
        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            var viewAnimator: ViewPropertyAnimatorCompat
            viewAnimator = if (v is EditText) {
                ViewCompat.animate(v)
                        .scaleY(1f).scaleX(1f)
                        .setStartDelay((EDITTEXT_DELAY * i + 500).toLong())
                        .setDuration(500)
            } else if (v is Button) {
                ViewCompat.animate(v)
                        .scaleY(1f).scaleX(1f)
                        .setStartDelay((BUTTON_DELAY * i + 500).toLong())
                        .setDuration(500)
            }


            else {
                ViewCompat.animate(v)
                        .translationY(50f).alpha(1f)
                        .setStartDelay((VIEW_DELAY * i + 500).toLong())
                        .setDuration(1000)
            }
            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }

        login.setOnClickListener {
            goToNextActivity(context,HomeActivity::class.java)
        }
    }
}