package epitech.eip.smartconf.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.MeetingDescFragment
import epitech.eip.smartconf.R

class HomeAdapter(private var titles: List<String>, private var fragment: BaseFragment) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recy_items_layout, parent, false)

        view.setOnClickListener {

        }
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            fragment.placeFragment(MeetingDescFragment(false), R.id.root_frag_view)
        }
        holder.meeting_title.text = titles[position]
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meeting_title: TextView = itemView.findViewById(R.id.meeting_title)
        val meeting_status: TextView = itemView.findViewById(R.id.meeting_status)
        val meeting_description: TextView = itemView.findViewById(R.id.meeting_desc)
    }
}