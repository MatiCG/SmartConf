package epitech.eip.smartconf.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_meetingdesc_layout.*
import kotlinx.android.synthetic.main.fragelem_synthese_layout.view.*

class MeetingDescFragment(private var active: Boolean): BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_meetingdesc_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_content.addView(loadActive().takeIf { active } ?: loadInactive())

    }

    private fun loadInactive(): View{
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragelem_synthese_layout, frag_content, false)
        view.d.text = "Recording meeting"
        return view
    }

    private fun loadActive(): View{
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragelem_synthese_layout, frag_content, false)
        view.d.text = "Bon c'est juste un test mais la en gros c'est quand la synthèse de la reu est dispo !!"
        return view
    }
}