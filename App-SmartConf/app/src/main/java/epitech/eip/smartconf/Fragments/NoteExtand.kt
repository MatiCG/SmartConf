package epitech.eip.smartconf.Fragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class NoteExtand(var meeting_name: String): BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_noteopen_layout }
    override fun shouldShowNavBar(): Boolean { return false }
    override fun setPageTitle(): String { return meeting_name }


}