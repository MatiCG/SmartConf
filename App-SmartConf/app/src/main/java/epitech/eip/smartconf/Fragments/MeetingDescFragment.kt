package epitech.eip.smartconf.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import epitech.eip.smartconf.SpeechToText.SpeechRecognizerViewModel
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_readytostart_layout.*
import kotlinx.android.synthetic.main.fragelem_synthese_layout.*
import kotlinx.android.synthetic.main.fragelem_synthese_layout.view.*
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import tekproject.dev_epicture.epicture.ApiRequests.Requests
import tekproject.dev_epicture.epicture.ApiRequests.UrlRequests
import tekproject.dev_epicture.epicture.Interface.VolleyCallback

class MeetingDescFragment(private val meetingID: String): BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    private lateinit var speechRecognizerViewModel: SpeechRecognizerViewModel
    var ref = FirebaseDatabase.getInstance().getReference("meetings")
    private var active = false
    private var test: String? = null
    private lateinit var testvalue: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadInactive())
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                meeting_title2?.text = p0
                    .child(meetingID)
                    .child("title")
                    .value.toString()
                meeting_desc2?.text = p0
                    .child(meetingID)
                    .child("subject")
                    .value.toString()
                meeting_date?.text = p0
                    .child(meetingID)
                    .child("date")
                    .value.toString()
                testvalue = p0
                    .child(meetingID)
                    .child("peech")
                    .value.toString()
            }
        })

        button_start_recording?.setOnClickListener {
            if (speechRecognizerViewModel.isListening) {
                speechRecognizerViewModel.stopListening()
            } else {
                speechRecognizerViewModel.startListening()
                button_start_recording.playAnimation()
            }
        }
        setupSpeechViewModel()
    }

    private fun setupSpeechViewModel() {
        speechRecognizerViewModel = ViewModelProviders.of(this).get(SpeechRecognizerViewModel::class.java)
        speechRecognizerViewModel.getViewState().observe(this, Observer<SpeechRecognizerViewModel.ViewState> { viewState ->
            render(viewState)
        })
    }

    private fun render(uiOutput: SpeechRecognizerViewModel.ViewState?) {
        if (uiOutput == null) return

        if (!uiOutput.isListening) {
            button_start_recording.cancelAnimation()
            button_start_recording.progress = 0F
            if (!TextUtils.isEmpty(uiOutput.spokenText) && !uiOutput.isListening) {
                saveData(uiOutput.spokenText)
            }
        }
    }

    private fun saveData(spokenText: String) {
        button_start_recording.visibility = View.INVISIBLE
        anim.visibility = View.VISIBLE
        anim.playAnimation()
        anim.setMinProgress(0.8F)
        Log.d("REQUEST", "sending request... Sending : " + spokenText)
        Requests().makePostRequest(UrlRequests().adefinir(meetingID, spokenText), context!!)
        Handler().postDelayed({
            anim.visibility = View.INVISIBLE
            anim.cancelAnimation()
            anim.progress = 0F
            frag_content.removeAllViews()
            var tmp = loadActive()
            tmp.result.text = testvalue
            frag_content.addView(tmp)
        }, 3000)
    }

    private fun loadInactive(): View{
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragelem_readytostart_layout, frag_content, false)
        return view
    }

    private fun loadActive(): View{
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragelem_synthese_layout, frag_content, false)
        return view
    }
}