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
/*
class User(user_token: String, user_email: String, user_fname: String, user_lname: String) {
    private var token: String = user_token
    private var email: String = user_email
    private var first_name: String = user_fname
    private var last_name: String = user_lname
    private var my_meetings: List<String>? = listOf("example_of_meeting_id_:_gufgU69G62RNIZy02wlrj2EQCpW")

    //Getters
    fun getEmail(): String {
        return email
    }

    fun getFirstName(): String {
        return first_name
    }

    fun getLastName(): String {
        return last_name
    }

    fun getMeetings(): List<String>? {
        return my_meetings
    }

    fun getToken(): String {
        return token
    }

    //Setters
    fun setEmail(pEmail: String) {
        email = pEmail
    }
}
 */