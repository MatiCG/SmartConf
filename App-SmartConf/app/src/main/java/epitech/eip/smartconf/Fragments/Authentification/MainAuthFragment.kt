package epitech.eip.smartconf.Fragments.Authentification

import android.os.Bundle
import android.view.View
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class MainAuthFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.act_login_layout }
    override fun shouldShowActionBar(): Boolean { return false }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeFragment(OptionsLoginFragment(), R.id.authentification_section)
    }
}