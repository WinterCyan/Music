package winter.music.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "audio_playlist_join",
    primaryKeys = ["audio_id", "playlist_id"],
    foreignKeys = [
        ForeignKey(entity = Audio::class, parentColumns = ["id"], childColumns = ["audio_id"]),
        ForeignKey(entity = Playlist::class, parentColumns = ["id"], childColumns = ["playlist_id"])
    ]
)
data class AudioPlaylistJoin(
    val audio_id: Long,
    val playlist_id: Long
)