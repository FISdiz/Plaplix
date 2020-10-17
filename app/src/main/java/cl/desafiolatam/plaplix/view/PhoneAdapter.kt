package cl.desafiolatam.plaplixtest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.plaplix.R
import cl.desafiolatam.plaplix.model.pojo.Phone
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_phone_list.view.*

class PhoneAdapter(private var phoneDataset : MutableList<Phone>) : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone_list, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneDataset.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.phoneName.text = phoneDataset.get(position).name
        holder.phonePrice.text = phoneDataset.get(position).price.toString()

        Picasso.get().load(phoneDataset.get(position).image).into(holder.phoneImage)
    }

    fun updateItems (it: List<Phone>) {
        phoneDataset.clear()
        phoneDataset.addAll(it)
        notifyDataSetChanged()
    }
    class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var phoneName = itemView.text_name
        var phonePrice = itemView.text_price
        var phoneImage = itemView.image_phone
    }
}