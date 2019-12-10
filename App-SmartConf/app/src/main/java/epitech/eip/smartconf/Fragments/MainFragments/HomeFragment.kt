package epitech.eip.smartconf.Fragments.MainFragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import epitech.eip.smartconf.Adapters.HomeAdapter
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.Form.FormFragment
import epitech.eip.smartconf.Interface.VolleyCallback
import epitech.eip.smartconf.R
import epitech.eip.smartconf.RequestsFirebase.UrlRequest
import kotlinx.android.synthetic.main.frag_home_layout.*
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_home_layout }
    override fun shouldUseCustomBack(): Boolean { return true }
    override fun shouldShowNavBar(): Boolean { return true }
    override fun setPageTitle(): String { return "Home" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = HomeAdapter(getAct().mUser!!.getMeetingsId(), this)

        fab.setOnClickListener {
            placeFragment(FormFragment())
        }
    }
}