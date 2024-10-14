package com.example.recyclerview

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.widget.Button
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnHTTP)
        button.setOnClickListener{
            logConnection()
        }
    }

    private fun logConnection(){
        val stringUrl = "https://api.flickr.com/services/rest/?method=flickr.photos." +
                "search&api_key=ff49fcd4d4a08aa6aafb6ea3de8264" +
                "64&tags=cat&format=json&nojsoncallback=1"

        HttpGetRequest().execute(stringUrl)
    }
}

class HttpGetRequest : AsyncTask<String, Void, String>(){
    override fun doInBackground(vararg params: String?): String {
        var text: String = ""
        val url: URL = URL(params[0])
        val urlConn: HttpURLConnection = url.openConnection() as HttpURLConnection
        text = urlConn.inputStream.bufferedReader().readText()

        return text;
    }

    override fun onPostExecute(result: String) {
        Log.println(Log.DEBUG, "Flickr cats", result)
    }

}