package com.example.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.update_layout.*


class EditFragment(val afterUpdateCallBack: AfterUpdateCallBack, val activity: AppCompatActivity, val profile: contact): DialogFragment() {

    var databaseRef = FirebaseDatabase.getInstance().getReference("contact").child(profile.id)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.Theme_AppCompat_Light_Dialog)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.update_layout, container, false)
    }

    /**
    * call the on deleteUserProfile on the onViewCreated State
    */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        name_update.setText(profile.fullname)
        phone_update.setText(profile.phone)

        btn_update.setOnClickListener {
            if(name_update.text.toString().isNotEmpty() && phone_update.text.toString().isNotEmpty() ){
                updateUserContact(name_update.text.toString(),phone_update.text.toString())
            } else Toast.makeText(requireActivity(), "Fill in the missing fields", Toast.LENGTH_SHORT).show()

        }
    }

    /**
     * Design and setup of the Fragment layout
     */

    override fun onStart() {
        super.onStart()
        val dialog=dialog
        if(dialog != null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width,height)
        }
    }
    /**
     * This is the Update function that is  declared and initiated in the onViewCreated state,
     * Note this function  displayListAfterContactUpdate by  updating the database
     */

    private fun updateUserContact(text: String, text1: String) {
        var update = contact(text, text1,profile.id)
        databaseRef.setValue(update)
        afterUpdateCallBack.displayListAfterContactUpdate(true)
        dismiss()
    }

}