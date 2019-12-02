package epitech.eip.smartconf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
}
