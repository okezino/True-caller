package com.example.viewpager

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contact_layout.*
import kotlinx.android.synthetic.main.recyclerview_adapter.*
import kotlinx.android.synthetic.main.update_layout.*
import java.lang.StringBuilder

class FirebaseFragment : Fragment(),OnNodeClick,AfterUpdateCallBack {
    /**
     * Declaration of Variables,
     * database arraylistof Contact class which is pass  as parameter for RecylerAdapter instance
     * Myadapter instance to define the profileList(RecyclerView Id) adapter
     */

    var database = arrayListOf<contact>()
    lateinit var activity : AppCompatActivity
    val Myadapter = RecyclerAdapter(database)
    var databaseRef = FirebaseDatabase.getInstance().getReference("contact")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    /**
     *  OnCreateView this define the view and inflate it with recyclerview_adapter layout
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.recyclerview_adapter, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    /**
     * populateContact function helps
     *     to get data from the firebase
     *     to update the local database for the recyclerView to update the on real Time
     */


    private fun populateContacts(databaseRef: DatabaseReference) {

        var getdata = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {

                database.clear()

                for (i in p0.children) {
                    var conname = i.child("fullname").getValue().toString()
                    var phoneNumber = i.child("phone").getValue().toString()
                    var idnumber = i.child("id").getValue().toString()
                    var newContact = contact(conname, phoneNumber, idnumber)
                    database.add(newContact)
                }

                profile_list.adapter = Myadapter
                profile_list.layoutManager = LinearLayoutManager(requireContext())
                Myadapter.notifyDataSetChanged()


            }
        }

        databaseRef.addValueEventListener(getdata)
        Myadapter.listner = this



    }

    /**
     * The populateContact function is initiatated on the OnActivityCreate State
     */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity = requireActivity() as AppCompatActivity

        populateContacts(databaseRef)

    }

    /**
     * Declaration of onclickIcon function override from the onNodeClick interface
     * declaration of DialogUtil class instance and passing the display function
     */

    override fun onclickIcon(view: View, position: Int) {

        val dataToUpdate = contact(database[position].fullname,database[position].phone,database[position].id)

        when(view.id){
            R.id.edit_image -> DialogUtil.displayUpdateContactDialog(activity,this,dataToUpdate)
            R.id.delete_image -> DialogUtil.displayDeleteContactDialog(activity,this,dataToUpdate)
            R.id.textViewId ->DialogUtil.displayProfileContactDialog(activity,this,dataToUpdate)
        }
    }

    /**
     * Overridden function from the AfterUpdateCallBack Class
     */
    override fun displayListAfterContactUpdate(isUpdated: Boolean) {

        if(isUpdated){
            Toast.makeText(requireContext(),"Updated Successfully",Toast.LENGTH_LONG).show()
            var databaseRef = FirebaseDatabase.getInstance().getReference("contact")
            populateContacts(databaseRef)

        }
    }

    override fun displayListAfterContactDelete(isDeleted: Boolean) {
        if(isDeleted){
            Toast.makeText(requireContext(),"Deleted Successfully",Toast.LENGTH_LONG).show()
            var databaseRef = FirebaseDatabase.getInstance().getReference("contact")
            populateContacts(databaseRef)

        }
    }


}