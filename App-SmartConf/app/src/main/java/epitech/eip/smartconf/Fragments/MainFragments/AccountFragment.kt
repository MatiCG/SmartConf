package epitech.eip.smartconf.Fragments.MainFragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class AccountFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_account_layout }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Account" }

}