package epitech.eip.smartconf.Model

class User {
    private var token: String
    private var email: String
    private var meetings: MutableList<String>

    constructor(pToken: String, pEmail: String) {
        this.token = pToken
        this.email = pEmail
        this.meetings = mutableListOf("Get Started")
    }

    fun getToken(): String {
        return this.token
    }
    fun setToken(token: String) {
        this.token = token
    }

    fun getEmail(): String {
        return this.email
    }
    fun setEmail(email: String) {
        this.email = email
    }

    fun getMeetingsId(): MutableList<String> {
        return this.meetings
    }
    fun setMeetingsId(meetings_id: MutableList<String>) {
        this.meetings = meetings_id
    }
    fun addMeetingsId(meeting_id: String) {
        this.meetings.add(meeting_id)
    }
}