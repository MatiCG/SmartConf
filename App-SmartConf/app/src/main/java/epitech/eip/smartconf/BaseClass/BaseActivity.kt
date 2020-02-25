package epitech.eip.smartconf.BaseClass

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import epitech.eip.smartconf.Model.User
import epitech.eip.smartconf.R

open class BaseActivity: AppCompatActivity() {
    lateinit var mUser: User
    lateinit var mAuth: FirebaseAuth

    override fun onResume() {
        super.onResume()
        mAuth = FirebaseAuth.getInstance()
    }

    fun setRootFragment(new_fragment: BaseFragment, view_root: Int = R.id.root_frag_view) {
        val fragmentId = new_fragment.fragmentId.takeIf { it > 0 } ?: view_root
        try {
            if (new_fragment.fragmentId == 0) {
                supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } else {
                supportFragmentManager.popBackStackImmediate(new_fragment.fragmentId, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        } catch (e:Throwable) {
            return
        }
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(fragmentId, new_fragment)
        fragment.commit()
    }

    fun placeFragment(new_fragment: BaseFragment, view_location: Int = R.id.root_frag_view,
                      animEnter: Int = 0, animExit: Int = 0,
                      backAnimEnter: Int = 0, backAnimExit: Int = 0) {
        val fragment = supportFragmentManager.beginTransaction()

        if (animEnter + animExit != 0)
            fragment.setCustomAnimations(animEnter, animExit, backAnimEnter, backAnimExit)
        fragment.replace(view_location, new_fragment, new_fragment.javaClass.name)
        fragment.addToBackStack(new_fragment.tag)
        fragment.commit()
    }

    fun removeFragment(delete_fragment: BaseFragment) {
        val fragment = supportFragmentManager.beginTransaction()

        fragment.remove(delete_fragment)
        fragment.commit()

    }

    override fun onBackPressed() {
        if (getCurrentFragment()?.shouldUseCustomBack() == true) {
            getCurrentFragment()?.onCustomBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    private fun getCurrentFragment(): BaseFragment? {
        val find = supportFragmentManager.findFragmentById(R.id.root_frag_view) as? BaseFragment
        return find ?: supportFragmentManager.findFragmentById(R.id.root_frag_view) as? BaseFragment
    }
}