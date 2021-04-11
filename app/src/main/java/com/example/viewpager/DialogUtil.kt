package com.example.viewpager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

/**
 * DialogUtil Class, this class is use to display and Manage  all the Fragment calls, and manage the button calls for
 * display the edit Fragment  and update the dp
 * delete  the delete Fragment and update the dp
 */
class DialogUtil {

    companion object{

        fun displayUpdateContactDialog(activity: AppCompatActivity, afterUpdateCallBack: AfterUpdateCallBack, contact : contact) {

            val updateContactDialog = EditFragment(afterUpdateCallBack,activity,contact)
            updateContactDialog.show(activity.supportFragmentManager,"Update Contact Modal")
        }

        fun displayProfileContactDialog(activity: AppCompatActivity, afterUpdateCallBack: AfterUpdateCallBack, contact : contact) {

            val profileContactDialog = ProfileFragment(afterUpdateCallBack,activity,contact)
            profileContactDialog.show(activity.supportFragmentManager,"Profile Contact Modal")
        }

        fun displayDeleteContactDialog(activity: AppCompatActivity, afterUpdateCallBack: AfterUpdateCallBack, contact : contact) {

            val deleteContactDialog = DeleteFragment(afterUpdateCallBack,activity,contact)
             deleteContactDialog.show(activity.supportFragmentManager,"Delete Contact Modal")
        }
    }
}