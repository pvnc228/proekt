
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button0 = findViewById<Button>(R.id.button0)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonMulty = findViewById<Button>(R.id.buttonMulty)
        val buttonDivy = findViewById<Button>(R.id.buttonDIVy)
        val buttonClear = findViewById<Button>(R.id.buttonСlear)

        var num1 = ""
        var num2 = ""
        var operation = ""

        button1.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "1" else "1"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "1" else "1"
                resultTextView.text = num2
            }
        }
        button2.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "5" else "5"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "5" else "5"
                resultTextView.text = num2
            }
        }
        button3.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "2" else "2"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "2" else "2"
                resultTextView.text = num2
            }
        }
        button4.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "4" else "4"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "4" else "4"
                resultTextView.text = num2
            }
        }
        button5.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "7" else "7"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "7" else "7"
                resultTextView.text = num2
            }
        }
        button6.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "3" else "3"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "3" else "3"
                resultTextView.text = num2
            }
        }
        button7.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "6" else "6"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "6" else "6"
                resultTextView.text = num2
            }
        }
        button9.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "8" else "8"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "8" else "8"
                resultTextView.text = num2
            }
        }
        button8.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "9" else "9"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "9" else "9"
                resultTextView.text = num2
            }
        }
        button0.setOnClickListener {
            if (operation.isEmpty()) {
                num1 = if (num1 != "0") num1 + "0" else "0"
                resultTextView.text = num1
            } else {
                num2 = if (num2 != "0") num2 + "0" else "0"
                resultTextView.text = num2
            }
        }
        buttonPlus.setOnClickListener {
            operation = "+"
        }
        buttonMinus.setOnClickListener {
            operation = "-"
        }
        buttonMulty.setOnClickListener {
            operation = "*"
        }
        buttonDivy.setOnClickListener {
            operation = "/"
        }
        buttonEquals.setOnClickListener {
            if (num2.isNotEmpty()) {
                try{
                    val result = when (operation) {
                        "+" -> num1.toFloat() + num2.toFloat()
                        "-" -> num1.toFloat() - num2.toFloat()
                        "*" -> num1.toFloat() * num2.toFloat()
                        "/" -> num1.toFloat() / num2.toFloat()
                        else -> 0.0
                    }
                    resultTextView.text = result.toString()
                    num1 = result.toString()
                } catch (e: ArithmeticException) {
                    resultTextView.text = "Error"
                    num1 = ""
                    num2 = ""
                    operation = ""
                }
            }
        }
        buttonClear.setOnClickListener {
            resultTextView.text = "0"
            num1 = ""
            num2 = ""
            operation = ""
        }
    }

}