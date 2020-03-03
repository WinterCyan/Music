package winter.music.activities

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import winter.music.adapters.MainPagerAdapter
import winter.music.R
import winter.music.application.MusicApplication
import winter.music.classes.Audio

class MainActivity : AppCompatActivity(){
    private lateinit var app: MusicApplication
    private lateinit var mainPagerAdapter: MainPagerAdapter // adapter controls which fragment to inflate in each page
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        app = application as MusicApplication
        val single = app.getSingle()
        val albums = app.getAlbums()
        val artists = app.getArtists()
        val folders = app.getFolders()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager, arrayListOf(
                getText(R.string.tab_category),
                getText(R.string.tab_local)
            )) // pass default fm, get a adapter
        viewPager = findViewById(R.id.music_view_pager) // get pager UI element
        viewPager.adapter = mainPagerAdapter // bind them
        tabLayout = findViewById(R.id.music_tablayout)
        tabLayout.setupWithViewPager(viewPager) // bind pager with tabs

        val bottomClickLayout = findViewById<LinearLayout>(R.id.music_bottom_click)
        bottomClickLayout.setOnClickListener {
        }

        fun getSingle(): Array<Audio>{
            return app.getSingle()
        }
    }
}