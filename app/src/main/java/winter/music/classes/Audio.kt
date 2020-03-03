package winter.music.classes
import android.net.Uri

data class Audio(val id: Long,
                 val name: String,
                 val artist: String,
                 val album: String,
                 val duration: Int,
                 val location: String,
                 val coverName: String?
)
