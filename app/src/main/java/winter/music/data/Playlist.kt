package winter.music.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "playlists",
    // "id" from categories, "categoryId" from here.
    foreignKeys = [
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])
    ])
data class Playlist(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val categoryId: Long // foreign key
)