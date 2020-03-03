package winter.music.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_item.view.*
import winter.music.R
import winter.music.classes.Audio

// adapter which populate list items
class SongItemAdapter(private val audioList: Array<Audio>):RecyclerView.Adapter<SongItemAdapter.ListViewHolder>() {
    class ListViewHolder(songItem: ConstraintLayout):RecyclerView.ViewHolder(songItem) {
        val songName: TextView = songItem.song_item_name
        val songAuthor: TextView = songItem.song_item_author
        val songOperateBtn: ImageButton = songItem.song_item_button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val songItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.song_item, parent, false) as ConstraintLayout
        return ListViewHolder(songItem)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val audio = audioList[position]
        holder.songName.text = audio.name
        holder.songAuthor.text = audio.artist
    }

    override fun getItemCount() = audioList.size
}