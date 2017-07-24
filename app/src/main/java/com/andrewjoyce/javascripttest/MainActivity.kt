package com.andrewjoyce.javascripttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.liquidplayer.javascript.JSContext

class MainActivity : AppCompatActivity() {

    val js = JSContext()
    val script = "function convert (type, degree) {\n" +
            "    var convertedTempt\n" +
            "    if (type == \"C\") {\n" +
            "        convertedTempt = degree * 9 / 5 + 32\n" +
            "    } else {\n" +
            "        convertedTempt = (degree -32) * 5 / 9\n" +
            "    }\n" +
            "    return convertedTempt\n" +
            "}\n" +
            "\n" +
            "var converstion = convert(type, degree)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        setUpView()
    }

    private fun setUpView() {
        buttonConvertCelcius.setOnClickListener { celciusValueChanged(editTempreture.text.toString()) }
        buttonConvertFahrenheit.setOnClickListener { fahrenheitValueChanged(editTempreture.text.toString()) }
    }

    private fun celciusValueChanged(value: String) {
        Log.d("Main Activity", "Fahrenheit Value changed to $value")
        js.evaluateScript("var type = \"C\"")
        js.evaluateScript("var degree = $value")
        js.evaluateScript(script)
        textResult.text = js.property("converstion").toNumber().toString()
    }

    private fun fahrenheitValueChanged(value: String) {
        Log.d("Main Activity", "Fahrenheit Value changed to $value")
        js.evaluateScript("var type = \"F\"")
        js.evaluateScript("var degree = $value")
        js.evaluateScript(script)
        textResult.text = js.property("converstion").toString()
    }
}
