package epitech.eip.smartconf.Fragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class SearchFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_search_layout }
    override fun shouldShowNavBar(): Boolean { return true }

}