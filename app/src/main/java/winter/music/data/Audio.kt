package winter.music.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audios")
data class Audio(
    @PrimaryKey val id: Long,
    val name: String,
    val artist: String,
    val album: String,
    val duration: Int
)