package com.example.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(private val context: Context) {

    private val ADMIN = "admin"
    private val NAME = "name"
    private val LOGIN = "login"
    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences(
            "shared",
            Context.MODE_PRIVATE
        )
    }

    fun putIsLogin(loginorout: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(LOGIN, loginorout)
        edit.commit()
    }

    fun getIsLogin(): Boolean {
        return app_prefs.getBoolean(LOGIN, false)
    }

    fun putName(name: String) {
        val edit = app_prefs.edit()
        edit.putString(NAME, name)
        edit.commit()
    }

    fun getNames(): String? {
        return app_prefs.getString(NAME, "")
    }

    fun putIsAdmin(isAdmin: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(ADMIN, isAdmin)
        edit.commit()
    }

    fun getIsAdmin(): Boolean? {
        return app_prefs.getBoolean(ADMIN, false)
    }

}