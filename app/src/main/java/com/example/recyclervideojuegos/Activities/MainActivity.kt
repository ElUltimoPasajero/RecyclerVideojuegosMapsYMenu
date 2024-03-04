package com.example.recyclervideojuegos.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclervideojuegos.Entities.Videogame
import com.example.recyclervideojuegos.Entities.VideogameDAO
import com.example.recyclervideojuegos.R
import com.example.recyclervideojuegos.VideogameAdapter
import com.example.recyclervideojuegos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videogameList: List<Videogame>
    private lateinit var videoGameAdapter: VideogameAdapter
    private lateinit var videoGameDao: VideogameDAO
    private lateinit var videogameMutableList:MutableList<Videogame>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoGameDao = VideogameDAO()
        binding.rvVideogames.layoutManager = LinearLayoutManager(this)
        videogameList = videoGameDao.loadList(this)

        binding.rvVideogames.adapter = VideogameAdapter(videogameList) { videogame ->
            onItemSelected(videogame)
        }
    }

    fun onItemSelected(videogame: Videogame) {
        Toast.makeText(
            this,
            "${videogame.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

          override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }



     override fun onOptionsItemSelected(item: MenuItem): Boolean {

            val id = item.itemId

            if (id == R.id.itemDisponible) {
                val lista = mutableListOf<Videogame>()
                for (v in videogameList) {
                    if (v.disponible.toString()=="yes") {
                        lista.add(v)
                    }
                }

                binding.rvVideogames.adapter =
                    VideogameAdapter(lista) { onItemSelected(it) }
                binding.rvVideogames.adapter?.notifyDataSetChanged()

            } else if (id == R.id.itemNoDisponible) {
                val lista = mutableListOf<Videogame>()
                for (v in videogameList) {
                    if (v.disponible.toString()=="no") {
                        lista.add(v)
                    }
                }
                binding.rvVideogames.adapter=
                    VideogameAdapter(lista) { onItemSelected(it) }
                binding.rvVideogames.adapter?.notifyDataSetChanged()

            } else if (id == R.id.itemTodos) {
                binding.rvVideogames.adapter = VideogameAdapter(videogameList) { onItemSelected(it) }
                binding.rvVideogames.adapter?.notifyDataSetChanged()
            }

            return super.onOptionsItemSelected(item)


        }





    override fun onContextItemSelected(item: MenuItem): Boolean {

        //Creamos una variable para traer la comunidad que tenemos afectada
        lateinit var videogameAffected: Videogame

        //Una variable para la vista que vamos a lanzar
        lateinit var miIntent: Intent

        //Nos traemos la lista de comunidades agrupadas por el ID
        videogameAffected = videogameList[item.groupId]

        //Switch para distinguir la opcion del menu contextual pulsada
        when (item.itemId) {

            0 ->{


                lateinit var mapIntent: Intent
                mapIntent = Intent(this, MapViewActivity::class.java)
                mapIntent.putExtra("nombre", videogameAffected.name)
                mapIntent.putExtra("estado", videogameAffected.country)
                mapIntent.putExtra("latitud", videogameAffected.latitude)
                mapIntent.putExtra("longitud", videogameAffected.longitude)
                startActivity(mapIntent)


            }
            else -> return super.onContextItemSelected(item)

        }

return true

        }
}