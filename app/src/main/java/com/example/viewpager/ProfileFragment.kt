package com.example.viewpager

import android.Manifest
import android.R.attr.phoneNumber
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment(val afterUpdateCallBack: AfterUpdateCallBack, val activity: AppCompatActivity, val profile: contact) : DialogFragment() {
    var REQUEST_PHONE_CALL = 1
    var PERMISSION_SEND_SMS = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.Theme_AppCompat_Light_Dialog)


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_name.text = profile.fullname
        profile_phone.text = profile.phone


        messages.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(android.Manifest.permission.SEND_SMS),PERMISSION_SEND_SMS)
            } else sendMessage()
        }


        call.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
            }else  makeCall()
        }


        share.setOnClickListener {
            shareProfile()
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog=dialog
        if(dialog != null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width,height)
        }
    }


    private  fun sendMessage(){

        val phoneNumber =profile.phone.trim()
        val uri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "Here goes your message...")
        startActivity(intent)

    }

    private fun makeCall(){
        val numberText =profile.phone.trim()
        val intent= Intent(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:$numberText"))
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(requireContext(),"Permission denied",Toast.LENGTH_LONG).show()

        }
        startActivity(intent)
    }

    private  fun shareProfile(){
        val numberText =profile.phone.trim()
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "$numberText");
        startActivity(Intent.createChooser(shareIntent,"Share file"))

    }



}