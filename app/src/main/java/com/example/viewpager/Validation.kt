package com.example.viewpager

/**
 * This is the validation class to validate the name and phoneNumber
 */

class Validation() {


    // This is the name validation to check if name is empty

    fun nameValidation(name: String): Boolean {
        return !name.isEmpty()

    }
     // This is the phoneNumber validation to streamline the input to digit '*' and '#'

    fun phoneNumberValidation(phone :String) : Boolean {

        var valid = true

        if(phone.isEmpty()) valid = false else {
            for(i in phone){
                valid = i.isDigit() || i == '*' || i == '#'
            }
        }


       return valid
    }

}