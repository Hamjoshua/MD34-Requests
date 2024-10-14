package com.example.recyclerview

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonHttp = findViewById<Button>(R.id.btnHTTP)
        buttonHttp.setOnClickListener{
            logConnection()
        }

        val buttonOkHttp = findViewById<Button>(R.id.btnOkHTTP)
        buttonOkHttp.setOnClickListener{
            logOkConnection()
        }
    }

    private fun logConnection(){
        val stringUrl = "https://api.flickr.com/services/rest/?method=flickr.photos." +
                "search&api_key=ff49fcd4d4a08aa6aafb6ea3de8264" +
                "64&tags=cat&format=json&nojsoncallback=1"

        HttpGetRequest().execute(stringUrl)
    }

    private fun logOkConnection(){
        val stringUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search" +
                "&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
        OkGetRequest().execute(stringUrl)
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

class OkGetRequest : AsyncTask<String, Void, String>() {
    private val client = OkHttpClient()

    @Throws(IOException::class)
    override fun doInBackground(vararg params: String): String? {
        val url = params[0]
        var result: String?

        val request = Request.Builder().url(url).build()
        try {
            client.newCall(request).execute()
                .use { response -> result = response.body?.string() }
        } catch (e: IOException) {
            result = e.message
        }

        return result;
    }

    override fun onPostExecute(result: String?) {
        Log.println(Log.INFO, "Flickr cats", result.toString());
    }
}