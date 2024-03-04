package com.example.recyclervideojuegos

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclervideojuegos.Entities.Videogame
import com.example.recyclervideojuegos.databinding.ItemVideogameBinding

class VideogameViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

    val binding = ItemVideogameBinding.bind(view)
    private lateinit var videogame : Videogame


    fun render (item: Videogame, onClickListener:(Videogame)->Unit){

        videogame= item

        binding.tvVideogameName.text=item.name
        binding.gameImg.setImageResource(item.image)
        binding.tvYear.setText(item.year.toString())
        binding.tvPlatform.setText(item.platform)
        binding.imgDisponible.setImageResource(item.imgDisponible)

        itemView.setOnClickListener{
            onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(videogame.name)

        menu.add(this.adapterPosition,0,0,"Ver mapa")

    }


}