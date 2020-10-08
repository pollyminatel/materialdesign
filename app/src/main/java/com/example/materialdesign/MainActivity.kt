package com.example.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragmentSearch = FragmentSearch()
    val fragmentFavorite = FragmentFavorite()
    val fragmentPerson = FragmentPerson()
    var active = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fab.setOnClickListener {
//            Toast.makeText(this, "FAB tocado", Toast.LENGTH_LONG).show()
//        }

        val fm = supportFragmentManager
        active = fragmentSearch
        fm.beginTransaction().add(R.id.fragment_container, fragmentSearch, "1").commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_busca -> {
                    fm.beginTransaction().hide(active)
                        .show(fragmentSearch).commit()
                    active = fragmentSearch
                }
                R.id.bottom_favoritos -> {
                    if(fm.findFragmentByTag("2") == null)
                        fm.beginTransaction().hide(active)
                            .add(R.id.fragment_container, fragmentFavorite, "2").commit()
                    else
                        fm.beginTransaction().hide(active)
                            .show(fragmentFavorite).commit()
                    active = fragmentFavorite
                }
                R.id.bottom_perfil -> {
                    if(fm.findFragmentByTag("3") == null)
                        fm.beginTransaction().hide(active)
                            .add(R.id.fragment_container, fragmentPerson, "3").commit()
                    else
                        fm.beginTransaction().hide(active)
                            .show(fragmentPerson).commit()
                    active = fragmentPerson
                }
            }
            true
        }
    }
}