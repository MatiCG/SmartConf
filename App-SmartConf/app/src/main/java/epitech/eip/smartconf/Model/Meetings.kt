package epitech.eip.smartconf.Model

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
/*
class Meetings(leader_token: String = "not_defined",
               meeting_schedule: String = "not_defined",
               meeting_speech: String = "not_defined",
               meeting_started: Boolean = false,
               meeting_subject: String = "not_defined",
               meeting_title: String = "not_defined") {

    private var mtgs_leader_token: String = leader_token
    private var mtgs_title: String = meeting_title
    private var mtgs_subject: String = meeting_subject
    private var mtgs_schedule: String = meeting_schedule
    private var mtgs_speech: String = meeting_speech
    private var mtgs_status: Boolean = meeting_started

    //Getters
    fun getLeaderToken(): String {
        return mtgs_leader_token
    }

    fun getTitle(): String {
        return mtgs_title
    }

    fun getSubject(): String {
        return mtgs_subject
    }

    fun getSchedule(): String {
        return mtgs_schedule
    }

    fun getSpeech(): String {
        return mtgs_speech
    }

    fun getStatus(): Boolean {
        return mtgs_status
    }

    //Setters
    fun setLeaderToken(token: String) {
        mtgs_leader_token = token
    }

    fun setTitle(title: String) {
        mtgs_title = title
    }

    fun setSubject(subject: String) {
        mtgs_subject = subject
    }

    fun setSchedule(schedule: String) {
        mtgs_schedule = schedule
    }

    fun setSpeech(speech: String) {
        mtgs_speech = speech
    }

    fun setStatus(status: Boolean) {
        mtgs_status = status
    }
}
 */