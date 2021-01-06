package com.example.todoapp.utils

import android.annotation.SuppressLint
import com.example.domain.auth.model.UserProfile

@SuppressLint("CommitPrefEdits")
class Session(private val mPreferenceUtil: PreferenceUtil) {

    private val USER_LANGUAGE_KEY = "UserLanguageKey"
    private val USER_TOKEN_KEY = "UsrTokenKey"
    private val USER_KEY = "UserKey"

    var userLanguage: String
        get() = mPreferenceUtil.getStringValue(USER_LANGUAGE_KEY, Language.ARABIC.code)
        set(language) = mPreferenceUtil.setStringValue(USER_LANGUAGE_KEY, language)

    var token: String
        get() = mPreferenceUtil.getStringValue(USER_TOKEN_KEY)
        set(token) = mPreferenceUtil.setStringValue(USER_TOKEN_KEY, token)

    var userDetails: UserProfile
        get() = mPreferenceUtil[USER_KEY, UserProfile::class.java]
        set(userDetails) = mPreferenceUtil.save(USER_KEY, userDetails)

    fun logout() {
        mPreferenceUtil.removeValue(USER_TOKEN_KEY)
        mPreferenceUtil.removeValue(USER_KEY)
    }
}