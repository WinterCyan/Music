package winter.music.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.room.Room
import winter.music.room.MusicDatabase
import winter.music.room.Song
import java.nio.file.Path

class Util(){
    companion object{
        fun loadSongFiles(path: Path): ArrayList<Path>?{

            return null
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun dbtest(context: Context){
            val db = Room.databaseBuilder(context, MusicDatabase::class.java, "music_database")
                .fallbackToDestructiveMigration()
                .build()
            val songDao = db.songDao()

            data class Audio(
                val uri: Uri,
                val name: String,
                val duration: Int
            )
            val audioList = mutableListOf<Audio>()
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.RELATIVE_PATH
            )
            val query = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null
            )
            var songs: Array<Song> = arrayOf()
            query?.use { cursor->
                val cursorCount = cursor.count
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val loc = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH)

                while (cursor.moveToNext()){
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val duration = cursor.getInt(durationColumn)
                    val loc = cursor.getString(loc)
                    val contentUri: Uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                    audioList+=Audio(contentUri, name, duration)
                    println("$name Added")
                    val song = Song(0, name, null, duration, loc, null, null)
                    songs += song
                }
                Thread(Runnable {
                    kotlin.run {
                        songDao.insertAll(songs)
                        print("Inserted.")
                    }

                }).start()
            }
            db.close()
        }

//        fun mmrtest(){
//            val mmr = MediaMetadataRetriever()
//            val file = File("/sdcard/Pictures/2.jpg")
//            val path = file.absolutePath
//            print(path)
////            mmr.setDataSource(path)
//            val album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
//            print(album)
//        }
    }
}