package winter.music.activities

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
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

class MainActivity : AppCompatActivity(){
    private lateinit var mainPagerAdapter: MainPagerAdapter // adapter controls which fragment to inflate in each page
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

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
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }
    }
}