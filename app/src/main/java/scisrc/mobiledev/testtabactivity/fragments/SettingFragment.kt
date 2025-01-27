package scisrc.mobiledev.testtabactivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import scisrc.mobiledev.testtabactivity.R
import scisrc.mobiledev.testtabactivity.adapters.PreferencesManager
import scisrc.mobiledev.testtabactivity.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    lateinit var notificationSwitch: SwitchCompat
    lateinit var themeSwitch: SwitchCompat
    lateinit var usernameInput: EditText

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingUI()

        // Initialize PreferencesManager
        preferencesManager = PreferencesManager(requireContext())

        // Load saved preferences
        loadSavedPreferences(notificationSwitch, themeSwitch, usernameInput)

        // Set up listeners to save preferences when changed
        setupPreferenceListeners(notificationSwitch, themeSwitch, usernameInput)
    }

    fun bindingUI() {
        // Initialize UI elements
        notificationSwitch = binding.notificationSwitch
        themeSwitch = binding.themeSwitch
        usernameInput = binding.usernameInput
    }

    private fun loadSavedPreferences(
        notificationSwitch: SwitchCompat,
        themeSwitch: SwitchCompat,
        usernameInput: EditText
    ) {
        // Read values with defaults if not found
        notificationSwitch.isChecked = preferencesManager.isNotificationsEnabled()
        themeSwitch.isChecked = preferencesManager.isDarkThemeEnabled()
        usernameInput.setText(preferencesManager.getUsername())
    }

    private fun setupPreferenceListeners(
        notificationSwitch: SwitchCompat,
        themeSwitch: SwitchCompat,
        usernameInput: EditText
    ) {
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferencesManager.setNotificationsEnabled(isChecked)

            // Optionally update app behavior immediately
            updateNotificationSettings(isChecked)
        }

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferencesManager.setDarkThemeEnabled(isChecked)

            // Apply theme change
            updateAppTheme(isChecked)
        }

        // Save username when focus is lost
        usernameInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                preferencesManager.setUsername(
                    usernameInput.text.toString()
                )
            }
        }
    }

    private fun updateNotificationSettings(enabled: Boolean) {
        // Implement notification settings update

    }

    private fun updateAppTheme(isDarkTheme: Boolean) {
        // Implement theme change
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}