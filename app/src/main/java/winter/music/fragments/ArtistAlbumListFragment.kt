package winter.music.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import winter.music.R
import winter.music.adapters.ArtistAlbumItemAdapter

class ArtistAlbumListFragment(private val artistAlbumList: List<String>): Fragment(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.artist_album_list, container, false)
        viewAdapter = ArtistAlbumItemAdapter(artistAlbumList)
        manager = LinearLayoutManager(context)
        recyclerView = view.findViewById<RecyclerView>(R.id.artist_album_list_recycler).apply {
            adapter = viewAdapter
            layoutManager = manager
        }
        return view
    }
}
