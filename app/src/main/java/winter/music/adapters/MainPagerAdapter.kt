package winter.music.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import winter.music.fragments.CategoryFragment
import winter.music.fragments.LocalFragment

class MainPagerAdapter(fm: FragmentManager, private val tabsName: ArrayList<CharSequence>) : FragmentPagerAdapter(fm){
    override fun getCount():Int = 2
    override fun getItem(position: Int): Fragment {
        // return different fragment according to page number
        when (position) {
            0 -> return CategoryFragment()
            1 -> return LocalFragment()
        }
        return LocalFragment()
    }

    // set tab names manually, otherwise the name field is empty
    override fun getPageTitle(position: Int): CharSequence? = tabsName[position]
}