package epitech.eip.smartconf.Fragments.MainFragments

import android.os.Bundle
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_account_layout.*

class AccountFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_account_layout }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Account" }

    private var ref = FirebaseDatabase.getInstance().getReference("meetings")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_profile_name?.text = getAct().mUser.getEmail()
    }
}