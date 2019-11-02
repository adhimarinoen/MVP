package dev.meetap.mvp.home.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import dev.meetap.mvp.R
import dev.meetap.mvp.home.model.RumahItem
import dev.meetap.mvp.inflate
import kotlinx.android.synthetic.main.item_rumah_list_recycler.view.*

class RumahListAdapter(private val arrRumahUpdates: List<RumahItem>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<RumahListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_rumah_list_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(arrRumahUpdates[position], listener)
    }

    override fun getItemCount(): Int {
        return arrRumahUpdates.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rumahItem: RumahItem, listener: (Int) -> Unit) = with(itemView) {
            textView_title.text = rumahItem.harga
            textView_author.text = rumahItem.nama
            textView_description.text = rumahItem.alamat
            itemView.setOnClickListener{listener(adapterPosition)}
        }
    }
}