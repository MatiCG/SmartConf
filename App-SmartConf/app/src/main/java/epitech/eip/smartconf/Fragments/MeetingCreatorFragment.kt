package epitech.eip.smartconf.Fragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class MeetingCreatorFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_addevent_layout }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

}