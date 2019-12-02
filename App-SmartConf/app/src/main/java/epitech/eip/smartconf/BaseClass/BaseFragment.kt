package epitech.eip.smartconf.BaseClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R

open class BaseFragment: Fragment() {
    var fragmentId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    fun getAct(): MainActivity { return activity as MainActivity }

    //This functions help you to customize the Fragment
    open fun shouldUseCustomBack(): Boolean { return false }
    open fun getLayout(): Int { return R.layout.empty_layout }
    open fun onCustomBackPressed() { Toast.makeText(context, getString(R.string.toast_notcongifured), Toast.LENGTH_SHORT)}
}