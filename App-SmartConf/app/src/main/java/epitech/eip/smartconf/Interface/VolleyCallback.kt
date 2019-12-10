package epitech.eip.smartconf.Interface

import org.json.JSONArray
import org.json.JSONObject

interface VolleyCallback {
    fun onSuccessResponse(result: JSONArray)
    fun onSuccessResponse(result: JSONObject)

    fun onFailedResponse()
}
