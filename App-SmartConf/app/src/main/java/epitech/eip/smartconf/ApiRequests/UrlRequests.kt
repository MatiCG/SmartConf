package tekproject.dev_epicture.epicture.ApiRequests

private const val api = "http://134.209.232.212:5000/{{meetingID}}/{{textSpeech}}"

class UrlRequests {
    fun adefinir(meetingID: String, textSpeech: String) :String {
        return api
            .replace("{{meetingID}}", meetingID)
            .replace("{{textSpeech}}", textSpeech)
    }
}
