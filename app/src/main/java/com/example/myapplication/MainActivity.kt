package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users/2")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseBody = response.body()?.string().orEmpty()
                    val pers = gson.fromJson(responseBody, Person::class.java)
                    println("!!! ${pers.data}")
                } catch (e: Exception) {
                    println(response)
                    e.printStackTrace()
                }
            }
        })
    }
}