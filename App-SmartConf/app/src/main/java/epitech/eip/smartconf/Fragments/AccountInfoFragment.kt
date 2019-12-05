package epitech.eip.smartconf.Fragments

import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class AccountInfoFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_account_settings_layout }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setCustomActionBar(): Int { return R.layout.actionbar_return_layout }

}