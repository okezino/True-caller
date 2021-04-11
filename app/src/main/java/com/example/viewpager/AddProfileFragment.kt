package com.example.viewpager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.phone_layout.*

class AddProfileFragment : Fragment() {
    /**
     *  Declaration(databaseRef) as  Firebase instance with contact as it Reference
     *  Declaration of other add input field
    */



    private lateinit var phone: String
    private lateinit var name: String
    var databaseRef = FirebaseDatabase.getInstance().getReference("contact")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.phone_layout, container, false)
    }

    /**
     * The onViewCreated state Manage the Add_Button
     * the name and phone number is gotten from the input field and pass as parameter to create and instance of contact class
     * the new instance is pass to the onsetvalue method to create a profile on the Firebase DB
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        btn_Add.setOnClickListener {
            name = fullName.text.toString()
            phone = phoneNumber.text.toString()
            val validator = Validation()
            val validateName = validator.nameValidation(name)
            var validatePhone = validator.phoneNumberValidation(phone)

            if (validateName && validatePhone) {
                var id = databaseRef.push().key
                var profile = contact(name, phone, id!!)
                databaseRef.child(id).setValue(profile)

                Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
                fullName.text?.clear()
                phoneNumber.text?.clear()
            } else {
                  if(!validateName) fullName.setError("missing field")
                  if(!validatePhone) phoneNumber.setError("must be a number")

            }
        }

    }

}