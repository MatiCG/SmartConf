package epitech.eip.smartconf

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.AccountFragment
import epitech.eip.smartconf.Fragments.HomeFragment
import epitech.eip.smartconf.Fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navbar.setOnNavigationItemSelectedListener(this)
        navbar.selectedItemId = R.id.home
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
            Toast.makeText(applicationContext, "Rien pour l'instant", Toast.LENGTH_SHORT).show()
            false
        }
        popup.show()
    }
}
