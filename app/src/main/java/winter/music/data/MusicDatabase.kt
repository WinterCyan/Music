package winter.music.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Audio::class, AudioPlaylistJoin::class, Category::class, Playlist::class], version = 1, exportSchema = false)
abstract class MusicDatabase: RoomDatabase(){
    abstract fun audioDao(): AudioDao
    abstract fun audioPlaylistJoinDao(): AudioPlaylistJoinDao
    abstract fun categoryDao(): CategoryDao
    abstract fun playlistDao(): PlaylistDao

    companion object{
        private const val DATABASE_NAME = "music-database.db"
        @Volatile private var instance: MusicDatabase? = null
        fun getInstance(context: Context): MusicDatabase{
            return instance?: synchronized(this){
                instance?: buildDatabase(context).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context): MusicDatabase {
            return Room.databaseBuilder(context, MusicDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}