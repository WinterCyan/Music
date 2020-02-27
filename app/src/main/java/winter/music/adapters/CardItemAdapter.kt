package winter.music.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import winter.music.R

class CardItemAdapter(private val cardNames: Array<String>?): RecyclerView.Adapter<CardItemAdapter.CardViewHolder>() {
    class CardViewHolder(cardItem: ConstraintLayout): RecyclerView.ViewHolder(cardItem) {
        val cardImg = cardItem.card_img
        val cardBtn = cardItem.card_btn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder{
        val cardItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_item, parent, false) as ConstraintLayout
        return CardViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    }

    override fun getItemCount() = cardNames!!.size
}
