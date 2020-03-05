package winter.music.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface PlaylistDao{
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlaylist(playlistId: Long): LiveData<Playlist>

    @Insert
    fun insert(playlist: Playlist): Long
}