package winter.music.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import winter.music.adapters.LocalPagerAdapter
import winter.music.R

class LocalFragment : Fragment(){
    private lateinit var localPagerAdapter: LocalPagerAdapter
    private lateinit var localViewPager: ViewPager
    private lateinit var localTabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val localView = inflater.inflate(R.layout.local, container, false)
        localPagerAdapter = LocalPagerAdapter(
            context!!,
            childFragmentManager,
            arrayListOf(
                getText(R.string.tab_artist),
                getText(R.string.tab_album),
                getText(R.string.tab_single),
                getText(R.string.tab_folder)
            )
        )
        localViewPager = localView.findViewById(R.id.local_view_pager)
        localViewPager.adapter = localPagerAdapter // bind them
        // bind TabLayout with ViewPager
        localTabLayout = localView.findViewById(R.id.local_tablayout)
        localTabLayout.setupWithViewPager(localViewPager) // bind pager with tabs

        return localView
    }
}