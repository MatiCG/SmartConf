package epitech.eip.smartconf.Form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_formhandling_layout.*

class FormFragment: BaseFragment() {
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }
    override fun getLayout(): Int { return R.layout.frag_formhandling_layout }

    private var STEP = 0
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startStep(STEP)
        btn_next.setOnClickListener {
            startStep(STEP)
        }
    }

    private fun startStep(step: Int) {
        STEP += 1
        content_form.removeAllViews()
        when(step) {
            0 -> {
                form_title.text = "Basic data"
                mView = LayoutInflater.from(context).inflate(R.layout.form_meetingbasicdata_layout, content_form, false)
            }
            1 -> {
                form_title.text = "What's about ?"
                mView = LayoutInflater.from(context).inflate(R.layout.form_meetingabout_layout, content_form, false)
            }
            else -> {
                Toast.makeText(context, "FINISH", Toast.LENGTH_SHORT).show()
            }
        }
        content_form.addView(mView)
    }
}