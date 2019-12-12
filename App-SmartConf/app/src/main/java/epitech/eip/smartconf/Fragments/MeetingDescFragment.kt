package epitech.eip.smartconf.Fragments

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_readytostart_layout.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MeetingDescFragment(private val meetingsId: String, private var active: Boolean): BaseFragment() {
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var IS_RECORDING: Boolean = false
    private var ref = FirebaseDatabase.getInstance().getReference("meetings").child(meetingsId)

    private lateinit var storage: FirebaseStorage

    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadActive().takeIf { active } ?: loadInactive())

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                meeting_title2.text = p0
                    .child("title")
                    .value.toString()
                meeting_desc2.text = p0
                    .child("subject")
                    .value.toString()
            }
        })

        output = context?.getExternalFilesDir(null)?.absolutePath + "/recording.wav"
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
            IS_RECORDING = true
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(IS_RECORDING){
            try {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                IS_RECORDING = false

                storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference.child("Meetings/Sounds/${meetingsId}/" + "recording.wav")

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