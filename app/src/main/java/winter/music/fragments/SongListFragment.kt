package winter.music.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import winter.music.R
import winter.music.adapters.SongItemAdapter
import winter.music.classes.Audio
import winter.music.classes.AudioList

class SongListFragment(private val audioList: Array<Audio>): Fragment(){
    private lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.song_list, container, false)
        viewAdapter = SongItemAdapter(audioList)
        manager = LinearLayoutManager(context)
        recyclerView = view.findViewById<RecyclerView>(R.id.song_list_recycler).apply {
            adapter = viewAdapter
            layoutManager = manager
        }
        return view
    }
}