package epitech.eip.smartconf.BaseClass

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import epitech.eip.smartconf.R

open class BaseActivity: AppCompatActivity() {

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