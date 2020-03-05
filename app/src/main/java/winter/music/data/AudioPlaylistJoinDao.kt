package winter.music.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AudioPlaylistJoinDao{
    // select from "join" where playlist_id = playlistId, get audio_id, then select from audios where id = audio_id.
    @Query("SELECT * FROM audios INNER JOIN audio_playlist_join ON audios.id = audio_playlist_join.audio_id WHERE audio_playlist_join.playlist_id = :playlistId")
    fun getAudiosFromPlaylist(playlistId: Long): LiveData<List<Audio>>

    @Insert
    fun insertAll(audioPlaylistJoins: List<AudioPlaylistJoin>)
}