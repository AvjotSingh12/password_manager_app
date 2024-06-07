package com.example.passwordmanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "passwordManager"
        private const val TABLE_PASSWORDS = "Passwords"
        private const val KEY_SERVICE = "service"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_PASSWORDS + "("
                + KEY_SERVICE + " TEXT PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PASSWORDS")
        onCreate(db)
    }

    fun addNewPassword(service: String, username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_SERVICE, service)
        values.put(KEY_USERNAME, username)
        values.put(KEY_PASSWORD, password)
        db.insert(TABLE_PASSWORDS, null, values)
        db.close()
    }

    fun readPasswords(): List<PasswordModel> {
        val passwordList: MutableList<PasswordModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_PASSWORDS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val service = cursor.getString(0)
                    val username = cursor.getString(1)
                    val password = cursor.getString(2)
                    val passwordModel = PasswordModel(service, username, password)
                    passwordList.add(passwordModel)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }
        return passwordList
    }

    fun deletePassword(username: String) {
        val db = this.writableDatabase
        db.delete(TABLE_PASSWORDS, "$KEY_SERVICE = ?", arrayOf(username))
        db.close()
    }

    fun updatePassword(service: String, username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_SERVICE,service)
        values.put(KEY_USERNAME, username)
        values.put(KEY_PASSWORD, password)
        db.update(TABLE_PASSWORDS, values, "$KEY_SERVICE = ?", arrayOf(service))
        db.close()
    }

   @SuppressLint("Range")
   fun getPassword(service: String): PasswordModel? {
       val db = this.readableDatabase
       val cursor = db.query(
           TABLE_PASSWORDS, arrayOf(KEY_SERVICE, KEY_USERNAME, KEY_PASSWORD),
           "$KEY_SERVICE = ?", arrayOf(service), null, null, null, null
       )
       cursor.use { cursor ->
           if (cursor != null && cursor.moveToFirst()) {
               val service = cursor.getString(cursor.getColumnIndex(KEY_SERVICE))
               val username = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
               val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
               return PasswordModel(service, username, password)
           }
       }
       db.close()
       return null
   }




    }
