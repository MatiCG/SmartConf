package epitech.eip.smartconf.Fragments.Form

import android.os.Bundle
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
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }
    override fun getLayout(): Int { return R.layout.frag_formhandling_layout }

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
        content_form.addView(LayoutInflater.from(context).inflate(formLayout[STEP], content_form, false))
        btn_next.setOnClickListener {
            STEP += 1
            //content_form.removeAllViews()
            addData(meetings)
            if (STEP == formTitles.size) {
                putDataToFirebase(meetings)
                removeFragment(this)
                setRootFragment(HomeFragment())
            } else {
                form_title.text = formTitles[STEP]
                content_form.addView(LayoutInflater.from(context).inflate(formLayout[STEP], content_form, false))
            }
        }
    }

    fun addData(meetings: Meetings) {
        meetings.setTitle(title?.text.toString())
        meetings.setSubject(test?.text.toString())
    }

    fun putDataToFirebase(meetings: Meetings) {
        val ref = FirebaseDatabase.getInstance().getReference("meetings")
        val userRef = FirebaseDatabase.getInstance().getReference("users")

        getAct().mUser.addMeetingsId(createToken())
        //Add meeting data
        ref.child(createToken()).setValue(meetings)
        //Update user data
        userRef.child(mAuth.currentUser!!.uid).setValue(getAct().mUser)
        Toast.makeText(context, "Meeting uploaded !", Toast.LENGTH_SHORT).show()
    }

    fun createToken(): String {
        val chars = ('0'..'9').toList().toTypedArray() + ('a'..'z').toList().toTypedArray()
        return (1..32).map { chars.random() }.joinToString("")
    }
}