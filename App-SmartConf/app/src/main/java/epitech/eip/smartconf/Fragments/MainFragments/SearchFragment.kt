package epitech.eip.smartconf.Fragments.MainFragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class SearchFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_search_layout }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Search" }

}