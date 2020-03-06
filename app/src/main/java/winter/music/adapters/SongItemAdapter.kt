package winter.music.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_item.view.*
import winter.music.R
import winter.music.data.Audio

// adapter which populate list items
class SongItemAdapter(private val audioList: List<Audio>):RecyclerView.Adapter<SongItemAdapter.ListViewHolder>() {
    class ListViewHolder(songItem: ConstraintLayout):RecyclerView.ViewHolder(songItem) {
        val songName: TextView = songItem.song_item_name
        val songArtistAlbum: TextView = songItem.song_item_author
        val songOperateBtn: ImageButton = songItem.song_item_button
        val songClickArea: LinearLayout = songItem.song_item_click_area
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val songItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.song_item, parent, false) as ConstraintLayout
        return ListViewHolder(songItem)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val audio = audioList[position]
        // Sophie - sailing.mp3 : [Sophie,sailing,(Enter)]
        val nameParts = audio.name.split(" - ", ".mp3", ".flac")
        if (nameParts.size >= 3)
            holder.songName.text = nameParts[1]
        else
            holder.songName.text = audio.name
        holder.songArtistAlbum.text = "${audio.artist} - ${audio.album}"
        holder.songClickArea.setOnClickListener {

        }
    }

    override fun getItemCount() = audioList.size
}