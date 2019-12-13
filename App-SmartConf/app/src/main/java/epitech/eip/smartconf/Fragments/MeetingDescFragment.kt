package epitech.eip.smartconf.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.text.TextUtils
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
import tekproject.dev_epicture.epicture.ApiRequests.Requests
import tekproject.dev_epicture.epicture.ApiRequests.UrlRequests

class MeetingDescFragment(private val meetingID: String): BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    private lateinit var speechRecognizerViewModel: SpeechRecognizerViewModel
    var ref = FirebaseDatabase.getInstance().getReference("meetings")
    private var active = false
    private var test: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadInactive())
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                result?.text = "MEETING RESULT = " + p0
                    .child(meetingID)
                    .child("speech")
                    .value.toString()
            }
        })
        button_start_recording?.setOnClickListener {
            if (speechRecognizerViewModel.isListening) {
                speechRecognizerViewModel.stopListening()
            } else {
                speechRecognizerViewModel.startListening()
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

        textTV?.text = uiOutput.spokenText

        button_start_recording?.background  = if (uiOutput.isListening) {
            context!!.resources.getDrawable(R.drawable.ic_mic_off, null)
        } else {
            if (!TextUtils.isEmpty(uiOutput.spokenText)) {
                saveData(uiOutput.spokenText)
            }
            context!!.resources.getDrawable(R.drawable.ic_mic, null)
        }
    }

    private fun saveData(spokenText: String) {
        Requests().makePostRequest(UrlRequests().adefinir(meetingID, spokenText), context!!)
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