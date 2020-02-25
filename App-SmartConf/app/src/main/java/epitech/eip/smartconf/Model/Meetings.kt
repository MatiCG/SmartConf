package epitech.eip.smartconf.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Meetings {
    private lateinit var leader_token: String
    private lateinit var meeting_title: String
    private lateinit var meeting_subject: String
    private lateinit var meeting_date: String
    private var meeting_active: Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(pLeaderToken: String, pMeetingTitle: String, pMeetingSubject: String) {
        this.leader_token = pLeaderToken
        this.meeting_title = pMeetingTitle
        this.meeting_subject = pMeetingSubject
        this.meeting_date = getDate()
        this.meeting_active = false
    }

    constructor() {}

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)

        return formatted
    }

    fun getToken(): String {
        return this.leader_token
    }
    fun setToken(token: String) {
        leader_token = token
    }

    fun getTitle(): String {
        return this.meeting_title
    }
    fun setTitle(title: String) {
        meeting_title = title
    }

    fun getSubject(): String {
        return this.meeting_subject
    }
    fun setSubject(subject: String) {
        meeting_subject = subject
    }
}