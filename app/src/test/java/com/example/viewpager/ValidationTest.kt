package com.example.viewpager

import org.junit.Test

import org.junit.Assert.*

class ValidationTest {

    var validate = Validation()
    var name1 = ""
    var name2 = "abc"
    var phone = ""
    var phone2 = "123aZ"
    var phone3 = "123"

    @Test
    fun nameValidation() {

        assertTrue(validate.nameValidation(name2))
        assertFalse(validate.nameValidation(name1))
    }

    @Test
    fun phoneNumberValidation() {
         assertTrue(validate.phoneNumberValidation(phone3))
        assertFalse(validate.phoneNumberValidation(phone2))
        assertFalse(validate.phoneNumberValidation(phone))


    }
}