package scisrc.mobiledev.testtabactivity.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import scisrc.mobiledev.testtabactivity.R
import scisrc.mobiledev.testtabactivity.adapters.ViewPagerAdapter

class TabViewActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tab_view)

        // Initialize views
        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        tabLayout = findViewById(R.id.tabs)

        // Set up adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Connect TabLayout and ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Profile"
                2 -> "Settings"
                else -> null
            }

            // Optional: Add icons
            when (position) {
                0 -> R.drawable.sharp_cottage_24
                1 -> R.drawable.baseline_face_5_24
                2 -> R.drawable.baseline_admin_panel_settings_24
                else -> null
            }?.let {
                tab.setIcon(
                    it
                )
            }
        }.attach()

        // Optional: Add page change callback
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}