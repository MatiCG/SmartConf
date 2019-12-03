package epitech.eip.smartconf.Fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_home_layout.*

class HomeFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_home_layout }
    override fun shouldUseCustomBack(): Boolean { return true }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = HomeAdapter(listOf<String>("Test1", "Test2", "Test3"), this)
    }
}