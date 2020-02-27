package winter.music.activities

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import winter.music.adapters.MainPagerAdapter
import winter.music.R
import winter.music.classes.SongList
import winter.music.utils.Util

class MainActivity : AppCompatActivity(){
    // song lists
    private lateinit var playing: SongList
    private lateinit var single: SongList
    // category lists
    private lateinit var artist: Array<String> // array of artist's name, toggle and select songs from db.
    private lateinit var album: Array<String>
    private lateinit var folder: Array<String>
    // category type
    private lateinit var categories: Array<SongList>

    // activity has a adapter(class) and a pager(UI element)
    private lateinit var mainPagerAdapter: MainPagerAdapter // adapter controls which fragment to inflate in each page
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        mainPagerAdapter = MainPagerAdapter(
            supportFragmentManager,
            arrayListOf(
                getText(R.string.tab_category),
                getText(R.string.tab_local)
            )
        ) // pass default fm, get a adapter
        viewPager = findViewById(R.id.music_view_pager) // get pager UI element
        viewPager.adapter = mainPagerAdapter // bind them
        // bind TabLayout with ViewPager
        tabLayout = findViewById(R.id.music_tablayout)
        tabLayout.setupWithViewPager(viewPager) // bind pager with tabs

        val bottomClickLayout = findViewById<LinearLayout>(R.id.music_bottom_click)
        bottomClickLayout.setOnClickListener {
//            val intent = Intent(this, PlayActivity::class.java)
//            startActivity(intent)
            Util.dbtest(this)
        }
    }

    companion object {
        fun initLists(){

        }

        fun getPlayings(): SongList?{

            return null
        }

        fun getSingle(): SongList?{

            return null
        }

        fun getAlbums():Array<String>?{

            return null
        }

        fun getArtists():Array<String>?{

            return null
        }

        fun getFolders():Array<String>?{

            return null
        }
    }

}