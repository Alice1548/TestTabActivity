package scisrc.mobiledev.testtabactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import scisrc.mobiledev.testtabactivity.R
import scisrc.mobiledev.testtabactivity.fragments.Tab1
import scisrc.mobiledev.testtabactivity.fragments.Tab2
import scisrc.mobiledev.testtabactivity.fragments.Tab3
import scisrc.mobiledev.testtabactivity.databinding.ActivityBottomViewBinding

class BottomViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Tab1())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Tab1())
                        .commit()
                    true
                }
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Tab2())
                        .commit()
                    true
                }
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Tab3())
                        .commit()
                    true
                }
                else -> false
            }
        }

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_notifications)
        badge.number = 99
        badge.isVisible = true
    }
}