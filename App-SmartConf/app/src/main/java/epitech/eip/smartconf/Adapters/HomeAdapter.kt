package epitech.eip.smartconf.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.Fragments.MeetingDescFragment
import epitech.eip.smartconf.R

class HomeAdapter(private var titles: List<String>, private var fragment: BaseFragment) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var ref = FirebaseDatabase.getInstance().getReference("meetings")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recy_items_layout, parent, false)
        return HomeViewHolder(view)
    }


    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                holder.meeting_title.text = p0
                    .child(titles[position])
                    .child("title")
                    .value.toString()
                holder.meeting_description.text = p0
                    .child(titles[position])
                    .child("subject")
                    .value.toString()
                holder.meeting_status.text = p0
                    .child(titles[position])
                    .child("token")
                    .value.toString()
            }
        })
        holder.itemView.setOnClickListener {
            fragment.placeFragment(MeetingDescFragment(titles[position], false.takeIf { position == 0 || position == 2} ?: true),
                R.id.root_frag_view,
                R.transition.slide_in_left,
                R.transition.slide_out_left,
                R.transition.slide_out_right,
                R.transition.slide_in_right)
        }
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meeting_title: TextView = itemView.findViewById(R.id.meeting_title)
        val meeting_status: TextView = itemView.findViewById(R.id.meeting_status)
        val meeting_description: TextView = itemView.findViewById(R.id.meeting_desc)
    }
}