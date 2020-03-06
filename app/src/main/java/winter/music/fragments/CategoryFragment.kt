package winter.music.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import winter.music.R
import winter.music.adapters.CategoryItemAdapter

class CategoryFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    val data: List<String> = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.category, container, false)
        viewAdapter = CategoryItemAdapter(data)
        manager = LinearLayoutManager(context)
        recyclerView = view.findViewById<RecyclerView>(R.id.category_recycler).apply{
            adapter = viewAdapter
            layoutManager = manager
        }
        return view
    }
}