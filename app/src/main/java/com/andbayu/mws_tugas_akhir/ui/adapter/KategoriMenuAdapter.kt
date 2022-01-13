package com.andbayu.mws_tugas_akhir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.model.KategoriMenuModel
import com.andbayu.mws_tugas_akhir.ui.view.master.UpdateDeleteKategoriMenuActivity
import com.andbayu.mws_tugas_akhir.utils.Constants.BASE_URL
import com.bumptech.glide.*

class KategoriMenuAdapter(val listKategoriMenuModels: ArrayList<KategoriMenuModel>)
    : RecyclerView.Adapter<KategoriMenuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        val imgKategoriMenu: ImageView = itemView.findViewById(R.id.img_kategori_menu)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KategoriMenuAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_kategori_menu, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: KategoriMenuAdapter.ViewHolder, position: Int) {
        val item = listKategoriMenuModels[position]
        holder.tvNama.text = item.nama

        if (item.gambar != "") {
            val imageUrl = "$BASE_URL/uploads/kategori-menu/${item.gambar}"
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .centerCrop()
                .into(holder.imgKategoriMenu)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, UpdateDeleteKategoriMenuActivity::class.java)
            intent.putExtra("ID", item.id)
            intent.putExtra("NAMA", item.nama)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = listKategoriMenuModels.size
}