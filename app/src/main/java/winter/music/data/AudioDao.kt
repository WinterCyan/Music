package winter.music.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AudioDao{
    @Query("SELECT * FROM audios WHERE id = :audioId")
    fun getAudio(audioId:Long): Audio

    @Query("SELECT COUNT(*) FROM audios")
    fun getCount(): Int

    @Query("SELECT DISTINCT album FROM audios ORDER BY album ASC")
    fun getAlbums(): List<String>

    @Query("SELECT DISTINCT artist FROM audios ORDER BY artist ASC")
    fun getArtists(): List<String>

    @Query("SELECT * FROM audios WHERE album = :album ORDER BY name ASC")
    fun getAudioByAlbum(album: String): List<Audio>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(audios: List<Audio>)
}