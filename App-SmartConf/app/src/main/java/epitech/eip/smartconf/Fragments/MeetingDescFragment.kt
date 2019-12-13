package epitech.eip.smartconf.Fragments

import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import epitech.eip.smartconf.ApiRequests.Requests
import epitech.eip.smartconf.ApiRequests.UrlRequests
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_readytostart_layout.*
import org.json.JSONArray
import org.json.JSONObject
import tekproject.dev_epicture.epicture.Interface.VolleyCallback
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
        if (!active) {
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

            output = context?.getExternalFilesDir(null)?.absolutePath + "/recording.mp3"
            mediaRecorder = MediaRecorder()
            Toast.makeText(context, output, Toast.LENGTH_LONG).show()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mediaRecorder?.setAudioEncodingBitRate(96200)
            mediaRecorder?.setAudioSamplingRate(128000)
            mediaRecorder?.setOutputFile(output)




            button_start_recording.setOnClickListener {
                Snackbar.make(view, "Recording...", Snackbar.LENGTH_SHORT).show()
                startRecording()
            }
            button_stop_recording.setOnClickListener {
                Snackbar.make(view, "Stop recording...", Snackbar.LENGTH_SHORT).show()
                stopRecording()
                Handler().postDelayed({
                    Toast.makeText(context, "REQUEST...", Toast.LENGTH_SHORT).show()
                    Requests().makeGetRequest(object : VolleyCallback {
                        override fun onSuccessResponse(result: JSONArray) {
                            Toast.makeText(context, "RESULT = " + result.toString(), Toast.LENGTH_SHORT).show()
                        }

                        override fun onSuccessResponse(result: JSONObject) {
                            Toast.makeText(context, "RESULT = " + result.toString(), Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailedResponse() {
                            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                    }, UrlRequests().adefinir(meetingsId), context!!)
                }, 500)
            }
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
                val storageRef = storage.reference.child("Meetings/Sounds/${meetingsId}/" + "recording.mp3")
                val uri = Uri.fromFile(File(output)) //!!.replace("recording.wav", "recordingtest.wav")))

                val test = StorageMetadata.Builder()
                    .setContentType("audio/mp3")
                    .build()

                storageRef.putFile(uri, test)
                    .addOnCompleteListener {
                        Toast.makeText(context, "ON SUCCESS", Toast.LENGTH_LONG).show()
                    }
//                val stream = FileInputStream(File(output))

  //              storageRef.putStream(stream)
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