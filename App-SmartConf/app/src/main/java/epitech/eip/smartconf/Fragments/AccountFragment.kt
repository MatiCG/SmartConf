package epitech.eip.smartconf.Fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_account_layout.*
import kotlinx.android.synthetic.main.frag_home_layout.*

class AccountFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_account_layout }
    override fun shouldShowNavBar(): Boolean { return true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drop_down_option_menu.setOnClickListener {
            placeFragment(AccountInfoFragment())
        }
    }
}