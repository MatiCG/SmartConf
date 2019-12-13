package epitech.eip.smartconf.ApiRequests

private const val myUrl  =   "http://134.209.191.169:5000/{{meetingID}}/recording.mp3"

class UrlRequests {
    fun adefinir(meetingID: String): String {
        return myUrl
            .replace("{{meetingID}}", meetingID)
    }
}
