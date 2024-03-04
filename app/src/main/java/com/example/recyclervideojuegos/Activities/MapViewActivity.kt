package com.example.recyclervideojuegos.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclervideojuegos.R
import com.example.recyclervideojuegos.databinding.ActivityMapViewBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem

class MapViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapViewBinding
    private lateinit var map: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, this.getPreferences(Context.MODE_PRIVATE))
        binding = ActivityMapViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        map = binding.vMap
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        val mapController = map.controller
        mapController.setZoom(9.5)

        val name = intent.getStringExtra("nombre")
        val capital = intent.getStringExtra("estado")
        val latitude = intent.getDoubleExtra("latitud", 0.0)
        val longitude = intent.getDoubleExtra("longitud", 0.0)

        val items: ArrayList<OverlayItem> = ArrayList()

        items.add(OverlayItem("Estados Unidos", "Sacramento", "Habitantes: 232134213", GeoPoint(37.7749, -122.4194)))
        items.add(OverlayItem("Japón", "Tokio", "Habitantes: 12421342134", GeoPoint(35.6895, 139.6917)))
        items.add(OverlayItem("Estados Unidos", "Sacramento", "Habitantes:124213421", GeoPoint(37.7749, -122.4194)))
        items.add(OverlayItem("Reino Unido", "Londres", "Habitantes:12342314", GeoPoint(51.509865, -0.118092)))
        items.add(OverlayItem("Canadá", "Canatu", "Habitantes:2314124", GeoPoint(45.4215, -75.6993)))
        items.add(OverlayItem("Estados Unidos", "Sacramento", "Habitantes: 2134", GeoPoint(37.7749, -122.4194)))
        items.add(OverlayItem("Japón", "Tokio", "Habitantes: 4324231432", GeoPoint(35.6895, 139.6917)))
        items.add(OverlayItem("Japón", "Tokio", "Habitantes:6788675", GeoPoint(35.6895, 139.6917)))
        items.add(OverlayItem("Reino Unido", "Londres", "Habitantes: 65786578", GeoPoint(51.509865, -0.118092)))
        items.add(OverlayItem("Japón", "Tokio", "Habitantes: 6578658", GeoPoint(35.6895, 139.6917)))
        items.add(OverlayItem("Estados Unidos", "Sacramento", "Habitantes: 5678678657", GeoPoint(37.7749, -122.4194)))


        val mOverlay: ItemizedOverlayWithFocus<OverlayItem> =
            ItemizedOverlayWithFocus(
                items,
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                        return true
                    }

                    override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                        return false
                    }


                }, applicationContext
            )
        mOverlay.setFocusItemsOnTap(true)
        map.overlays.add(mOverlay)
        mapController.setCenter(GeoPoint(latitude, longitude))

    }
    public override fun onResume(){
        super.onResume()
        map.onResume()
    }

    public override fun onPause(){
        super.onPause()
        map.onPause()
    }
}