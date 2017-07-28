package com.andrewjoyce.javascripttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.liquidplayer.javascript.JSContext

class MainActivity : AppCompatActivity() {

    val js = JSContext()
    val script = "function convert (type, temp) {\n" +
            "    var convertedTemp\n" +
            "    if (type == \"C\") {\n" +
            "        convertedTemp = temp * 9 / 5 + 32\n" +
            "    } else {\n" +
            "        convertedTemp = (temp -32) * 5 / 9\n" +
            "    }\n" +
            "    return convertedTemp\n" +
            "}\n" +
            "\n" +
            "var converstion = convert(type, temp)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        buttonConvertCelcius.setOnClickListener { convertTemperature("C", editTempreture.text.toString()) }
        buttonConvertFahrenheit.setOnClickListener { convertTemperature("F", editTempreture.text.toString()) }
    }

    private fun convertTemperature(type: String, value: String) {
        with(js) {
            //Set the values for the variables used by the convert function
            evaluateScript("var type = \"$type\"")
            evaluateScript("var temp = $value")
            evaluateScript(script)
            //Get the value returned by the conversion function
            textResult.text = property("converstion").toNumber().toString()
        }
    }
}
