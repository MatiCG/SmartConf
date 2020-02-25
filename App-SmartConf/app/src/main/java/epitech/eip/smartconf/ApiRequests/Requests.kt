package tekproject.dev_epicture.epicture.ApiRequests

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.Volley
import com.android.volley.*
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.snackbar.Snackbar
import tekproject.dev_epicture.epicture.Interface.VolleyCallback
import java.lang.Exception

class Requests {
    fun makePostRequest(url: String, context: Context) {
        val queue = Volley.newRequestQueue(context)
        queue.cancelAll(POST)

        try {
            val request = object : JsonObjectRequest(POST, url, null,
                Response.Listener { response ->
//                    callback.onSuccessResponse(response)
//                    Log.d("laissemoitranquil", response.toString())
                    //Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
  //                  callback.onFailedResponse()
                }) {
            }
            queue.add(request)
        } catch (e: Exception) {
        }
    }
}