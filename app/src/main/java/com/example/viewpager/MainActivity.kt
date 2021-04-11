package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

 open class MainActivity : AppCompatActivity() {

    /**
     * Declaration of instance of the ViewpagerAdapter
     */

     protected val myAdapter =  MyViewPagerAdapter(supportFragmentManager)

      var Seven =   2

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            /**
             * Set-up of FragmentViewPager and Tab layout
             */
        myAdapter.addFragment(AddProfileFragment(), "ADD")
        myAdapter.addFragment(FirebaseFragment(), "ONLINE")
        myAdapter.addFragment(PhoneDataFragment(), "HOME")
        viewpager.adapter = myAdapter
        Tab.setupWithViewPager(viewpager)
    }

}

class  Mainsomething  {

    var seveny = MainActivity().Seven

}
/**
 * Declaration of the FragmentLayout PagerAdapter Class
 */


class MyViewPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager){

    private val fragmentList : MutableList<Fragment> = mutableListOf()
    private val title         : MutableList<String>  = mutableListOf()


    override fun getItem(position: Int): Fragment {
        return   fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
    // Add function to Add Fragment to the FragmentPagerAdapter
    fun addFragment (fragment : Fragment, titleName : String){
        fragmentList.add(fragment)
        title.add(titleName)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]

    }

}