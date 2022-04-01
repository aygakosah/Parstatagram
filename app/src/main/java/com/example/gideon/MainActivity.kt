package com.example.gideon


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gideon.fragments.ComposeFragment
import com.example.gideon.fragments.FeedFragment
import com.example.gideon.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager



        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener{
            item ->
            var fragmentToShow: Fragment? = null
            when(item.itemId){
                R.id.action_home->{
                    fragmentToShow = FeedFragment()
                }
                R.id.action_profile->{
                    fragmentToShow = ProfileFragment()
                }
                R.id.action_compose->{
                    fragmentToShow = ComposeFragment()
                }
            }

            if(fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true
        }
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = R.id.action_home




    }


    companion object {
        const val TAG = "MainActivity"

    }


}


