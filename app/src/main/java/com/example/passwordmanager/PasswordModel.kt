package com.example.passwordmanager

class PasswordModel {

    var service: String = ""
    var username: String = ""
    var passsword: String = ""
    var id: Int = 0

    constructor(service: String, username: String, passsword: String) {
        this.service = service
        this.username = username
        this.passsword = passsword

    }
}