package winter.music.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import winter.music.R
import winter.music.adapters.CardItemAdapter

class CardListFragment(val cardNames: List<String>?): Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.card_list, container, false)
        viewAdapter = CardItemAdapter(cardNames)
        manager = GridLayoutManager(context, 3)
        recyclerView = view.findViewById<RecyclerView>(R.id.card_list_recycler).apply{
            adapter = viewAdapter
            layoutManager = manager
        }
        return view
    }
}
