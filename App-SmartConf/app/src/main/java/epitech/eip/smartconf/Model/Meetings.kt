package epitech.eip.smartconf.Model

import com.google.firebase.database.DataSnapshot

class Meetings {
    private lateinit var leader_token: String
    private lateinit var meeting_title: String
    private lateinit var meeting_subject: String

    constructor(pLeaderToken: String, pMeetingTitle: String, pMeetingSubject: String) {
        this.leader_token = pLeaderToken
        this.meeting_title = pMeetingTitle
        this.meeting_subject = pMeetingSubject
    }

    constructor() {}

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