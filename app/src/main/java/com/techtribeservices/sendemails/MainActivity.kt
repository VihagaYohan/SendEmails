package com.techtribeservices.sendemails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.techtribeservices.sendemails.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.btnSend.setOnClickListener {
            val userAddress = mainBinding.fieldEmail.text.toString()
            val userSubject = mainBinding.fieldSubject.text.toString()
            val userMessage = mainBinding.fieldMessage.text.toString()

            sendMessage(userAddress, userSubject, userMessage)
        }
    }

    // send message
    fun sendMessage(
        userAddress: String,
        userSubject: String,
        userMessage: String
    ) {
        val emailAddresses = arrayOf(userAddress)
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,userSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, userMessage)

        if(emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent,"Choose an application"))
        }

    }
}