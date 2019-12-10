package epitech.eip.smartconf.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import epitech.eip.smartconf.SpeechToText.SpeechRecognizerViewModel
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_readytostart_layout.*

class MeetingDescFragment(private var active: Boolean): BaseFragment() {
    private lateinit var speechRecognizerViewModel: SpeechRecognizerViewModel
    var state = false
    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadActive().takeIf { active } ?: loadInactive())
        if (!active) {
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}


        button_start_recording.setOnClickListener {
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

        textTV.text = uiOutput.spokenText
        button_start_recording.background  = if (state == true) {
            context!!.resources.getDrawable(R.drawable.ic_mic_off, null)
        } else {
            context!!.resources.getDrawable(R.drawable.ic_mic, null)
        }
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