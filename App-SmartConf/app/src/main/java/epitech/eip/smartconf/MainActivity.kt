package epitech.eip.smartconf

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.MainFragments.AccountFragment
import epitech.eip.smartconf.Fragments.Authentification.MainAuthFragment
import epitech.eip.smartconf.Fragments.MainFragments.HomeFragment
import epitech.eip.smartconf.Fragments.MainFragments.SearchFragment
import epitech.eip.smartconf.Model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_home_layout.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragment: BaseFragment
    private var ref = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = mAuth.currentUser
                val userToken: String = user?.uid ?: "error"
                val userEmail: String = user?.email ?: "error"

                mUser = User(userToken, userEmail)
                val parent = mAuth.currentUser?.uid?.let { dataSnapshot.child(it) }
                val firebaseMeetings = parent?.child("meetingsId")?.value
                if (firebaseMeetings != null) {
                    val meetingsList: MutableList<String>? = firebaseMeetings as? MutableList<String>
                    if (meetingsList != null)
                        mUser.setMeetingsId(meetingsList)
                }
                authHandle()
            }
        })
    }

    private fun authHandle() {
        if (mAuth.currentUser == null) {
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
