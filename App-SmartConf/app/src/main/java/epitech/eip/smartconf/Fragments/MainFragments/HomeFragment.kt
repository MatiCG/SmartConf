package epitech.eip.smartconf.Fragments.MainFragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Form.FormFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_home_layout.*

class HomeFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_home_layout }
    override fun shouldUseCustomBack(): Boolean { return true }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Home" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = HomeAdapter(listOf("SmartConf eip", "mockup", "day03", "tarace"), this)

        fab.setOnClickListener {
            placeFragment(FormFragment())
        }
    }
}