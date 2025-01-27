package scisrc.mobiledev.testtabactivity.adapters

import android.content.Context

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

    // Getter methods with default values
    fun isNotificationsEnabled() = sharedPreferences.getBoolean("notifications_enabled", true)
    fun isDarkThemeEnabled() = sharedPreferences.getBoolean("dark_theme_enabled", false)
    fun getUsername() = sharedPreferences.getString("username", "") ?: ""

    // Setter methods
    fun setNotificationsEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("notifications_enabled", enabled).apply()
    }

    fun setDarkThemeEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("dark_theme_enabled", enabled).apply()
    }

    fun setUsername(username: String) {
        sharedPreferences.edit().putString("username", username).apply()
    }
}