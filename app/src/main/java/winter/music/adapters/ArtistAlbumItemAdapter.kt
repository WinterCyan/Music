package winter.music.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.artist_album_item.view.*
import kotlinx.android.synthetic.main.song_item.view.*
import winter.music.R

// adapter which populate list items
class ArtistAlbumItemAdapter(private val artistAlbum: List<String>):RecyclerView.Adapter<ArtistAlbumItemAdapter.ListViewHolder>() {
    class ListViewHolder(artistAlbumItem: ConstraintLayout):RecyclerView.ViewHolder(artistAlbumItem) {
        val name: TextView = artistAlbumItem.artist_album_name
        init {
            artistAlbumItem.setOnClickListener {
                
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val artistAlbumItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.artist_album_item, parent, false) as ConstraintLayout
        return ListViewHolder(artistAlbumItem)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.name.text = artistAlbum[position]
    }

    override fun getItemCount() = artistAlbum.size
}
