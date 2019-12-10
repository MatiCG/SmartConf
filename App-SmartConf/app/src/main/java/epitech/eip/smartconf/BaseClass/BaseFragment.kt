package epitech.eip.smartconf.BaseClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.actionbar_home_layout.view.*
import kotlinx.android.synthetic.main.actionbar_return_layout.view.*
import kotlinx.android.synthetic.main.activity_main.*

open class BaseFragment: Fragment() {
    var fragmentId: Int = 0
    lateinit var mAuth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance()
    private var ref = database.getReference("users")

    override fun onStart() {
        super.onStart()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (activity is MainActivity) {
                    val parent = mAuth.currentUser?.uid?.let { dataSnapshot.child(it) }
                    val firebaseMeetings = parent?.child("meetingsId")?.value

                    if (firebaseMeetings != null) {
                        val test: MutableList<String>? = firebaseMeetings as? MutableList<String>
                        if (test != null)
                            getAct().mUser.setMeetingsId(test)
                    }
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mAuth = FirebaseAuth.getInstance()
        getAct().navbar.visibility = View.VISIBLE.takeIf { shouldShowNavBar() } ?: View.GONE
        getAct().actionbar.visibility = View.VISIBLE.takeIf { shouldShowActionBar() } ?: View.GONE
        val actionbarView = LayoutInflater.from(context).inflate(setCustomActionBar(), getAct().actionbar, false)
        actionbarView?.page_title?.text = setPageTitle()
        actionbarView?.back_btn?.setOnClickListener {
            getAct().onBackPressed()
        }
        getAct().actionbar.addView(actionbarView)
        return inflater.inflate(getLayout(), container, false)
    }

    fun getAct(): MainActivity { return activity as MainActivity }

    fun placeFragment(fragment: BaseFragment, view: Int = R.id.root_frag_view,
                      animEnter: Int = 0, animExit: Int = 0,
                      backAnimEnter: Int = 0, backAnimExit: Int = 0) {
        getAct().placeFragment(fragment, view, animEnter, animExit, backAnimEnter, backAnimExit)
    }

    fun setRootFragment(fragment: BaseFragment, view: Int = R.id.root_frag_view) {
        getAct().setRootFragment(fragment, view)
    }

    fun removeFragment(delete_fragment: BaseFragment) {
        getAct().removeFragment(delete_fragment)
    }

    //This functions help you to customize the Fragment
    open fun shouldUseCustomBack(): Boolean { return false }
    open fun shouldShowNavBar(): Boolean { return false }
    open fun shouldShowActionBar(): Boolean { return true }
    open fun onCustomBackPressed() { Toast.makeText(context, getString(R.string.toast_notcongifured), Toast.LENGTH_SHORT).show() }
    open fun setPageTitle(): String { return getString(R.string.app_name) }
    open fun setCustomActionBar(): Int { return R.layout.actionbar_home_layout }
    open fun getLayout(): Int { return R.layout.empty_layout }
}