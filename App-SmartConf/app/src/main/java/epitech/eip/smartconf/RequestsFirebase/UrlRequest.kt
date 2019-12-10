package epitech.eip.smartconf.RequestsFirebase

val meetings = "https://smartconf-eip-epitech.firebaseio.com/.json"

class UrlRequest {
    fun getAllMeetings(): String {
        return meetings
    }
}