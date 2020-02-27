package winter.music.room

import androidx.room.*

@Entity(tableName = "song")
data class Song(
    @PrimaryKey(autoGenerate = true) val songId: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(defaultValue = "Unknown") val author: String?,
    @ColumnInfo val duration: Int,
    @ColumnInfo val location: String,
    @ColumnInfo(defaultValue = "Unknown") val album: String?,
    @ColumnInfo val coverName: String?
)

@Entity(tableName = "playlist")
data class Playlist(
    @PrimaryKey(autoGenerate = true) val playlistId: Int,
    @ColumnInfo val playlistName: String
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class SongPlaylistCrossRef(
    val playlistId: Int,
    val songId: Int
)

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(SongPlaylistCrossRef::class)
    )
    val songs: List<Song>
)

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        associateBy = Junction(SongPlaylistCrossRef::class)
    )
    val songs: List<Playlist>
)

@Dao
interface SongDao{ // used for accessing database
    @Query("SELECT * FROM song")
    fun getAll():List<Song>

    @Query("SELECT * FROM song WHERE songId IN (:songIds)")
    fun loadAllById(songIds: IntArray):List<Song>

    @Transaction // return playlists to which the song belongs
    @Query("SELECT * FROM song WHERE songId = (:songId)")
    fun getListsInSong(songId: Int): SongWithPlaylists

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(songs: Array<Song>) {
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(song: Song)

    @Delete
    fun delete(song: Song)
}

@Dao
interface PlaylistDao{
    @Query("SELECT * FROM playlist")
    fun getAll():List<Playlist>

    @Query("SELECT * FROM playlist WHERE playlistId IN (:playlistIds)")
    fun loadAllById(playlistIds: IntArray): List<Playlist>

    @Transaction // get songs in a playlist
    @Query("SELECT * FROM playlist WHERE playlistId = (:playlistId)")
    fun getSongsInList(playlistId: Int): PlaylistWithSongs

    @Insert
    fun insertAll(vararg lists: Playlist)

    @Delete
    fun delete(playlist: Playlist)
}

@Dao
interface SongPlaylistCrossRefDao{
    @Insert // insert a single song-playlist reference
    fun insert(ref: SongPlaylistCrossRef)
}

@Database(entities = [Song::class, Playlist::class, SongPlaylistCrossRef::class], version = 2)
abstract class MusicDatabase: RoomDatabase(){ // abstract class extends RoomDatabase
    abstract fun songDao(): SongDao // abstract method returns class @Dao
    abstract fun listDao(): PlaylistDao
    abstract fun refDao(): SongPlaylistCrossRefDao
}
