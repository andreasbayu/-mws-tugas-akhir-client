package com.andbayu.mws_tugas_akhir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.model.MenuModel
import com.andbayu.mws_tugas_akhir.ui.view.master.UpdateDeleteMenuActivity
import com.andbayu.mws_tugas_akhir.utils.Constants
import com.bumptech.glide.Glide

class MenuAdapter(private val listMenuModels: ArrayList<MenuModel>) : RecyclerView.Adapter<MenuAdapter.VHMenu>() {
    inner class VHMenu(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nama: TextView      = itemView.findViewById(R.id.tv_nama)
        val status: TextView    = itemView.findViewById(R.id.tv_status)
        val gambar: ImageView   = itemView.findViewById(R.id.img_menu)
        val harga: TextView     = itemView.findViewById(R.id.tv_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHMenu {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return VHMenu(layoutInflater)
    }

    override fun onBindViewHolder(holder: VHMenu, position: Int) {
        val item = listMenuModels[position]

        holder.nama.text = item.nama
        holder.status.text = item.status
        holder.harga.text = item.harga

        if (item.gambar != "") {
            val imageUrl = "${Constants.BASE_URL}/uploads/foto-menu/${item.gambar}"
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .centerCrop()
                .into(holder.gambar)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, UpdateDeleteMenuActivity::class.java)
            intent.putExtra("DATA", item)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int = listMenuModels.size
}