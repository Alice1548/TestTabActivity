package scisrc.mobiledev.testtabactivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import scisrc.mobiledev.testtabactivity.fragments.Tab1
import scisrc.mobiledev.testtabactivity.fragments.Tab2
import scisrc.mobiledev.testtabactivity.fragments.Tab3

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1()
            1 -> Tab2()
            2 -> Tab3()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}