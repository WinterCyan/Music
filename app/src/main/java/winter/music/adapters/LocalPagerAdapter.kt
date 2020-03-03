package winter.music.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import winter.music.activities.MainActivity
import winter.music.application.MusicApplication
import winter.music.fragments.CardListFragment
import winter.music.fragments.SongListFragment

class LocalPagerAdapter(context: Context, fm: FragmentManager, private val tabsName: ArrayList<CharSequence>) : FragmentPagerAdapter(fm){
    val app = context.applicationContext as MusicApplication
    private val single = app.getSingle()
    private val albums = app.getAlbums()
    private val artists = app.getArtists()
    private val folders = app.getFolders()
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CardListFragment(artists)
            1 -> CardListFragment(albums)
            2 -> SongListFragment(single)
            else -> CardListFragment(folders)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabsName[position]
}