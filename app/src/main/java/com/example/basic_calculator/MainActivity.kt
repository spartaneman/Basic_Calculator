package com.example.basic_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //This is the textview that displays the user input and calculation result
    private var inputView: TextView? = null
    var lastDot: Boolean = false
    var lastNumeric: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputView = findViewById(R.id.inputView)
    }

    fun onButtonClick(view:View)
    {
        inputView?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View)
    {
        inputView?.text = ""
    }

    fun onDot(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            inputView?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View)
    {
        //because inputView can be null and the text can be empty with must
        //have assign safely
        //let returns the text as it
        inputView?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                inputView?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    //Main purpose is to see if there is an operator used within the string
    //With the first value being the exception
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-"))
        {
            false
        }else
        {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }
}