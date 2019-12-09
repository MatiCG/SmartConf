package epitech.eip.smartconf

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.MainFragments.AccountFragment
import epitech.eip.smartconf.Fragments.Authentification.MainAuthFragment
import epitech.eip.smartconf.Fragments.MainFragments.HomeFragment
import epitech.eip.smartconf.Fragments.MainFragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragment: BaseFragment
    private lateinit var mAuth: FirebaseAuth
    private var USER_CONNECTED: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        authHandle()
    }

    private fun authHandle() {
        USER_CONNECTED = true.takeIf { mAuth.currentUser != null } ?: false

        if (!USER_CONNECTED) {
            setRootFragment(MainAuthFragment())
        } else {
            navbar.setOnNavigationItemSelectedListener(this)
            navbar.selectedItemId = R.id.home
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        fragment = when (item.itemId) {
            R.id.home -> {
                HomeFragment()
            }
            R.id.search -> {
                SearchFragment()
            }
            R.id.account -> {
                AccountFragment()
            }
            else -> {
                return false
            }
        }
        setRootFragment(fragment)
        return true
    }

    fun popUpMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.actionbar_popup_menu, popup.menu)

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    mAuth.signOut()
                    authHandle()
                }
            }
            true
        }
        popup.show()
    }
}
