package epitech.eip.smartconf.Fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_home_layout.*

class HomeFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_home_layout }
    override fun shouldUseCustomBack(): Boolean { return true }
    override fun shouldShowNavBar(): Boolean { return true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = HomeAdapter(listOf("Test1", "Test2", "Test3", "Test4", "Test5", "Test6"), this)

        fab.setOnClickListener {
            placeFragment(MeetingCreatorFragment())
        }
    }
}