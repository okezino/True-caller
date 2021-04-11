package com.example.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_delete.*

class DeleteFragment(val afterUpdateCallBack: AfterUpdateCallBack, val activity: AppCompatActivity, val profile: contact) : DialogFragment() {

    /**
     * This is a Delete Dialog Fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.Theme_AppCompat_Light_Dialog)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    /**
     * call the on deleteUserProfile on the onViewCreated State
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_delete.setOnClickListener {
            deleteUserProfile()
        }

        btn_cancel.setOnClickListener(){
            dismiss()
        }
    }

    /**
     * This is the delete function that is  declared and initiated in the onViewCreated state,
     * Note this function  displayListAfterContactDelete by  updating the database
     */
    private fun deleteUserProfile(){

        var databaseRef = FirebaseDatabase.getInstance().getReference("contact").child(profile.id)
        databaseRef.removeValue()
        afterUpdateCallBack.displayListAfterContactDelete(true)
        dismiss()
    }

}