package com.numero.github.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.facebook.android.crypto.keychain.AndroidConceal
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain
import com.facebook.crypto.CryptoConfig
import com.facebook.crypto.Entity

class UserRepository(private val context: Context) : IUserRepository {

    private val preferences: SharedPreferences = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE)

    override var name: String? = null
        get(){
            if (field != null) {
                return field
            }
            val s = preferences.getString(KEY_USER_NAME, "")
            return if (s.isEmpty()) {
                null
            } else {
                decrypt(context, KEY_USER_NAME, s)
            }
        }
        set(value) {
            value?: return
            if (value.isEmpty()) {
                return
            }
            val encrypted: String? = encrypt(context, KEY_USER_NAME, value)
            preferences.edit().putString(KEY_USER_NAME, encrypted).apply()
        }

    override var token: String? = null
        get() {
            if (field != null) {
                return field
            }
            val s = preferences.getString(KEY_TOKEN, "")
            return if (s.isEmpty()) {
                null
            } else {
                decrypt(context, KEY_TOKEN, s)
            }
        }
        set(value) {
            value?: return
            if (value.isEmpty()) {
                return
            }
            val encrypted: String? = encrypt(context, KEY_TOKEN, value)
            preferences.edit().putString(KEY_TOKEN, encrypted).apply()
        }

    override val hasToken: Boolean
        get() = token != null

    // TODO クリア処理をいれる

    private fun encrypt(context: Context, key: String, value: String): String? {
        val crypto = AndroidConceal.get().createDefaultCrypto(SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256))
        if (!crypto.isAvailable) {
            return null
        }

        var encrypted: String? = null
        try {
            val bytes: ByteArray = crypto.encrypt(value.toByteArray(), Entity.create(key))
            encrypted = Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encrypted
    }

    private fun decrypt(context: Context, key: String, encryptedValue: String): String? {
        val crypto = AndroidConceal.get().createDefaultCrypto(SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256))
        if (!crypto.isAvailable) {
            return null
        }
        var value: String? = null
        try {
            val decrypted = crypto.decrypt(Base64.decode(encryptedValue, Base64.DEFAULT), Entity.create(key))
            value = String(decrypted)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return value
    }

    companion object {
        private const val USER_PREFERENCE = "USER_PREFERENCE"

        private const val KEY_USER_NAME = "KEY_USER_NAME"

        private const val KEY_TOKEN = "KEY_TOKEN"
    }
}