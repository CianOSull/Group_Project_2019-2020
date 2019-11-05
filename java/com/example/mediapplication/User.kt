package com.example.mediapplication

class User {

    /**
     *
     * @return
     */
    /**
     *
     * @param fname
     */
    var fname: String? = null
    /**
     *
     * @return
     */
    /**
     *
     * @param address
     */
    var address: String? = null
    /**
     *
     * @return
     */
    /**
     *
     * @param lname
     */
    var lname: String? = null
    /**
     *
     * @return
     */
    /**
     *
     * @param password
     */
    var password: String? = null
    /**
     *
     * @return
     */
    /**
     *
     * @param e_mail
     */
    var e_mail: String? = null
    /**
     *
     * @return
     */
    /**
     *
     * @param phoneNumber
     */
    var phoneNumber: Int = 0

    /**
     *
     * @param fname
     * @param address
     * @param lname
     * @param password
     * @param e_mail
     * @param phoneNumber
     */
    constructor(
        fname: String,
        address: String,
        lname: String,
        password: String,
        e_mail: String,
        phoneNumber: Int
    ) {
        this.fname = fname
        this.address = address
        this.lname = lname
        this.password = password
        this.e_mail = e_mail
        this.phoneNumber = phoneNumber
    }

    /**
     *
     */
    constructor() {}
}
