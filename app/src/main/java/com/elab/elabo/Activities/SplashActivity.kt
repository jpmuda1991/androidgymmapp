package com.elab.elabo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.elab.elabo.DbHelpers.Dbhelper
import com.elab.elabo.MainActivity
import com.elab.elabo.Models.CategoriesModel
import com.elab.elabo.Models.ExercisesModel
import com.elab.elabo.R
import com.google.gson.Gson
import java.util.ArrayList

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val db: Dbhelper = Dbhelper(this);



        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}