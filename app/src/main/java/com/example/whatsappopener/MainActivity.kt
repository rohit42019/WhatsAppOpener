package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data:String=getString(R.string.zero)
        if(intent.action== Intent.ACTION_PROCESS_TEXT) {
            data = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(data[0] == '+')
        {
            data=data.substring(1)
        }
        if(data.isDigitsOnly())
        {
            openWhatsApp(data)
        }
        else
        {
            Toast.makeText(this,getString(R.string.please_check_your_number),Toast.LENGTH_SHORT).show()
        }

    }
    fun openWhatsApp(num:String)
    {
        val intentWhatsApp=Intent(Intent.ACTION_VIEW)
        intent.setPackage(getString(R.string.whatsapp_package_name))
        var number= if(num.length == 10){
            getString(R.string.nine_one)+num
        }
        else
        {
            num
        }

        intentWhatsApp.data= Uri.parse(getString(R.string.whatsappAPI,number))
        if(intentWhatsApp.resolveActivity(packageManager) != null)
        {
            startActivity(intentWhatsApp)
        }
        else
        {
            Toast.makeText(this,getString(R.string.whatsapp_not_found),Toast.LENGTH_SHORT).show()
        }
    }
}