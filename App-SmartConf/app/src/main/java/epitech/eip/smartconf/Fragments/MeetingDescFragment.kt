package epitech.eip.smartconf.Fragments

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_readytostart_layout.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MeetingDescFragment(private var active: Boolean): BaseFragment() {
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false

    private lateinit var mAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage

    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadActive().takeIf { active } ?: loadInactive())
        mAuth = FirebaseAuth.getInstance()

        output = context?.getExternalFilesDir(null)?.absolutePath + "/recording.wav"
        //Environment.getExternalStorageDirectory().absolutePath + "/recording.wav"
        Toast.makeText(context, output, Toast.LENGTH_SHORT).show()
        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder?.setAudioEncodingBitRate(16 * 44100)
        mediaRecorder?.setAudioSamplingRate(44100)
        mediaRecorder?.setOutputFile(output)

        button_start_recording.setOnClickListener {
            startRecording()
        }
        button_stop_recording.setOnClickListener {
            stopRecording()
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

    private fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(state){
            try {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                state = false

                storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference.child("Meetings/Sounds/" + createToken())

                val stream = FileInputStream(File(output))

                storageRef.putStream(stream)
                Toast.makeText(context, "Stop!", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) { }
        } else{
            Toast.makeText(context, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }

    fun createToken(): String {
        val chars = ('0'..'9').toList().toTypedArray() + ('a'..'z').toList().toTypedArray()
        return (1..32).map { chars.random() }.joinToString { "" }
    }
}