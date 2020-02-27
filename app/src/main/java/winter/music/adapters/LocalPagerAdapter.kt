package winter.music.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import winter.music.activities.MainActivity
import winter.music.fragments.CardListFragment
import winter.music.fragments.SongListFragment

class LocalPagerAdapter(fm: FragmentManager, private val tabsName: ArrayList<CharSequence>) : FragmentPagerAdapter(fm){
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CardListFragment(MainActivity.getArtists())
            1 -> CardListFragment(MainActivity.getAlbums())
            2 -> SongListFragment(MainActivity.getSingle())
            else -> CardListFragment(MainActivity.getFolders())
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabsName[position]
}