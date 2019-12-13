package epitech.eip.smartconf.ApiRequests

import android.content.Context
import android.widget.Toast
import com.android.volley.toolbox.Volley
import com.android.volley.*
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import tekproject.dev_epicture.epicture.Interface.VolleyCallback
import java.lang.Exception

class Requests {
    fun makeGetRequest(callback: VolleyCallback, url: String, context: Context) {
        val queue = Volley.newRequestQueue(context)
        queue.cancelAll(POST)
        
        try {
            val request = object : JsonObjectRequest(POST, url, null,
                Response.Listener { response ->
                    if (response.get("data") is JSONObject) {
                        callback.onSuccessResponse(response.getJSONObject("data"))
                    } else
                        callback.onSuccessResponse(response.getJSONArray("data"))
                },
                Response.ErrorListener {
                    Toast.makeText(context, "ERROR = " + it.message, Toast.LENGTH_LONG).show()
                    callback.onFailedResponse()
                }) {
            }
            queue.add(request)
        } catch (e: Exception) {
            //error
        }
    }
}