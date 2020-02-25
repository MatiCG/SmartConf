package epitech.eip.smartconf.Fragments.MainFragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.Form.FormFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_home_layout.*

class HomeFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_home_layout }
    override fun shouldUseCustomBack(): Boolean { return true }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Home" }

    override fun onResume() {
        super.onResume()

        if (getAct().mUser.getMeetingsId().size <= 0) {
            anim_empty.playAnimation()
            anim_empty.visibility = View.VISIBLE
        } else {
            anim_empty.cancelAnimation()
            anim_empty.visibility = View.INVISIBLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = HomeAdapter(getAct().mUser.getMeetingsId(), this@HomeFragment)

        fab.setOnClickListener {
            placeFragment(FormFragment(),
                animEnter = R.transition.fade_in,
                animExit = R.transition.fade_out,
                backAnimEnter = R.transition.fade_in,
                backAnimExit = R.transition.fade_out)
        }
    }
}