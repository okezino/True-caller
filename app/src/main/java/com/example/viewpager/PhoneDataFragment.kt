package com.example.viewpager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.second_fragment.*

/**
 * This is the Fragment for the phone Contact
 */


class PhoneDataFragment : Fragment() {

    var REQUEST_READ_CONTACT = 123




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // this is where permission  request is made

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),arrayOf(android.Manifest.permission.READ_CONTACTS),REQUEST_READ_CONTACT)
        }else  callPhoneContact()


    }

    /**
     * This is my call function that is passed once the permission request is accepted
     */

    private fun callPhoneContact() {

        // declaration of Cursor to get all the data
        var cursor : Cursor? = requireActivity().contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        requireActivity().startManagingCursor(cursor)


        var data = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Photo._ID)

        var add = intArrayOf(android.R.id.text1, android.R.id.text2)

        var simpleAdapter : SimpleCursorAdapter = SimpleCursorAdapter(requireContext(),android.R.layout.simple_list_item_2,cursor,data,add)
        listView.adapter = simpleAdapter
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_READ_CONTACT -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhoneContact()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "The app was not allowed to read your contact",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


}