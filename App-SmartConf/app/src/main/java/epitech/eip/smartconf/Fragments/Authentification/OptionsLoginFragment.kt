package epitech.eip.smartconf.Fragments.Authentification

import android.os.Bundle
import android.view.View
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.auth_sigbtns_layout.*

class OptionsLoginFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.auth_sigbtns_layout }
    override fun shouldShowActionBar(): Boolean { return false }
    override fun shouldUseCustomBack(): Boolean { return true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            placeFragment(LoginPageFragment(), R.id.authentification_section)
        }
        btn_register.setOnClickListener {
            placeFragment(RegisterPageFragment(), R.id.authentification_section)
        }
    }
}