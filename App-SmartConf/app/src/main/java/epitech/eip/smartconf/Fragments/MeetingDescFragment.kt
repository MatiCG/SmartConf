package epitech.eip.smartconf.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*

class MeetingDescFragment(private var active: Boolean): BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}