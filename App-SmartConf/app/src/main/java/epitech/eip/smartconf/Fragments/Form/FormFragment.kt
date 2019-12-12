package epitech.eip.smartconf.Fragments.Form

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.MainFragments.HomeFragment
import epitech.eip.smartconf.Model.Meetings
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.form_meetingabout_layout.*
import kotlinx.android.synthetic.main.form_meetingbasicdata_layout.*
import kotlinx.android.synthetic.main.frag_formhandling_layout.*

class FormFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_formhandling_layout }
    override fun shouldShowActionBar(): Boolean { return false }
    private lateinit var mView: View
    private var STEP = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formTitles: List<String> = listOf("Basic data", "What's about")
        val formLayout: List<Int> =
            listOf(R.layout.form_meetingbasicdata_layout, R.layout.form_meetingabout_layout)
        val meetings = Meetings()

        meetings.setToken(mAuth.currentUser!!.uid)

        form_title.text = formTitles[STEP]
        form_container.addView(LayoutInflater.from(context).inflate(formLayout[STEP], form_container, false))
        btn_next.setOnClickListener {
            val values = form_input_subject?.text.toString() + form_input_title?.text.toString()
            if (values == "null") {
                form_input_title?.error = "Please, you need to give a title to your meeting"
                form_input_subject?.error = "Explain with keyword the meeting"
                return@setOnClickListener
            }
            STEP += 1
            addData(meetings)
            form_container.removeAllViews()
            if (STEP == formTitles.size) {
                putDataToFirebase(meetings)
                removeFragment(this)
                setRootFragment(HomeFragment())
            } else {
                form_title.text = formTitles[STEP]
                form_container.addView(LayoutInflater.from(context).inflate(formLayout[STEP], form_container, false))
            }
        }
    }

    fun addData(meetings: Meetings) {
        meetings.setTitle(form_input_title?.text.toString())
        meetings.setSubject(form_input_subject?.text.toString())
    }

    fun putDataToFirebase(meetings: Meetings) {
        val ref = FirebaseDatabase.getInstance().getReference("meetings")
        val userRef = FirebaseDatabase.getInstance().getReference("users")
        val token = createToken()

        getAct().mUser.addMeetingsId(token)
        //Add meeting data
        ref.child(token).setValue(meetings)
        //Update user data
        userRef.child(mAuth.currentUser!!.uid).setValue(getAct().mUser)
        Toast.makeText(context, "Meeting uploaded !", Toast.LENGTH_SHORT).show()
    }

    fun createToken(): String {
        val chars = ('0'..'9').toList().toTypedArray() + ('a'..'z').toList().toTypedArray()
        return (1..32).map { chars.random() }.joinToString("")
    }
}