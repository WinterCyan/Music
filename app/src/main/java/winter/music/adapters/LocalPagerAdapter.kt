package winter.music.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import winter.music.activities.MainActivity
import winter.music.application.MusicApplication
import winter.music.fragments.ArtistAlbumListFragment
import winter.music.fragments.CardListFragment
import winter.music.fragments.SongListFragment

class LocalPagerAdapter(context: Context, fm: FragmentManager, private val tabsName: ArrayList<CharSequence>) : FragmentPagerAdapter(fm){
    val app = context.applicationContext as MusicApplication
    private val single = app.getSingle()
    private val albums = app.getAlbums()
    private val artists = app.getArtists()
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ArtistAlbumListFragment(artists)
            1 -> ArtistAlbumListFragment(albums)
            2 -> SongListFragment(single)
            else -> SongListFragment(single)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabsName[position]
}