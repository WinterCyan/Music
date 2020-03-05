package winter.music.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AudioDao{
    @Query("SELECT * FROM audios WHERE id = :audioId")
    fun getAudio(audioId:Long): LiveData<Audio>

    @Query("SELECT COUNT(*) FROM audios")
    fun getCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(audios: List<Audio>)
}