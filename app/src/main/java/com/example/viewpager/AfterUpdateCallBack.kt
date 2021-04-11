package com.example.viewpager

/**
 * Declaration of the AfterUpdateCallBack interface
 */

interface AfterUpdateCallBack {

    fun displayListAfterContactUpdate(isUpdated:Boolean)
    fun displayListAfterContactDelete(isDeleted:Boolean)
}