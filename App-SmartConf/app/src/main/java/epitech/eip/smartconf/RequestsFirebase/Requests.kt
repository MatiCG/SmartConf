package epitech.eip.smartconf.RequestsFirebase

import android.content.Context
import com.android.volley.Request.Method.GET
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import epitech.eip.smartconf.Interface.VolleyCallback
import java.lang.Exception

class Requests {
    fun makeGetRequest(callback: VolleyCallback, url: String, context: Context, header: String = "client") {
        val queue = Volley.newRequestQueue(context)
        queue.cancelAll(GET)

        try {
            val request = object : JsonObjectRequest(GET, url, null,
                Response.Listener { response ->
//                    if (response.get("") is JSONObject) {
                        callback.onSuccessResponse(response.getJSONObject("meetings"))
//                    } else
  //                      callback.onSuccessResponse(response.getJSONArray(""))
                },
                Response.ErrorListener {
                    callback.onFailedResponse()
                }) { }
            queue.add(request)
        } catch (e: Exception) { }
    }
}