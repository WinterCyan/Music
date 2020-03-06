package winter.music.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_row.view.*
import winter.music.R

// adapter which populate list items
class CategoryItemAdapter(private val categories: List<String>?):RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(cardRow: ConstraintLayout):RecyclerView.ViewHolder(cardRow) {
        val categoryName = cardRow.category_name
        val cardRowRecyclerView = cardRow.card_row_recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder{
        val category = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_row, parent, false) as ConstraintLayout
        return CategoryViewHolder(category)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val childLayoutManager = LinearLayoutManager(holder.cardRowRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.cardRowRecyclerView.apply {
            layoutManager = childLayoutManager
            adapter = CardItemAdapter(categories)
        }
        holder.categoryName.text = categories!![position]
    }

    override fun getItemCount() = categories!!.size
}
