package epitech.eip.smartconf.BaseClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.actionbar_home_layout.view.*
import kotlinx.android.synthetic.main.activity_main.*

open class BaseFragment: Fragment() {
    var fragmentId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getAct().navbar.visibility = View.VISIBLE.takeIf { shouldShowNavBar() } ?: View.GONE
        getAct().actionbar?.page_title?.text = setPageTitle()
        getAct().actionbar.visibility = View.VISIBLE.takeIf { shouldShowActionBar() } ?: View.GONE
        getAct().actionbar.addView(LayoutInflater.from(context).inflate(setCustomActionBar(), actionbar, false))
        return inflater.inflate(getLayout(), container, false)
    }

    fun getAct(): MainActivity { return activity as MainActivity }

    fun placeFragment(fragment: BaseFragment, view: Int = R.id.root_frag_view) {
        getAct().placeFragment(fragment, view)
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