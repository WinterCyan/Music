package winter.music.utils

import android.content.Context
import android.os.AsyncTask
import android.provider.MediaStore
import android.widget.Toast
import androidx.room.Room
import winter.music.classes.Audio
import winter.music.room.MusicDatabase
import winter.music.room.Song

class Util(){
    companion object{
        // get all audio files from MediaStore
        fun queryAll(context: Context): Array<Audio>{
            var audioList = arrayOf<Audio>()
            val projection = arrayOf(
                MediaStore.Audio.Media._ID, // id
                MediaStore.Audio.Media.DISPLAY_NAME, // name
                MediaStore.Audio.Media.ARTIST, // artist
                MediaStore.Audio.Media.ALBUM, // album
                MediaStore.Audio.Media.DURATION, // duration
                MediaStore.Audio.Media.RELATIVE_PATH // location
            )
            val query = context.contentResolver.
                query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                val durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val locationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val duration = cursor.getInt(durationColumn)
                    val album = cursor.getString(albumColumn)
                    val location = cursor.getString(locationColumn)
                    val artist = cursor.getString(artistColumn)
                    audioList += Audio(id, name, artist, album, duration, location, null)
                }
            }
            return audioList
        }

        // initialize songs table
        fun initSongTable(context: Context, audios: Array<Audio>){
            val db = Room.databaseBuilder(context, MusicDatabase::class.java, "music.db")
                .fallbackToDestructiveMigration()
                .build()
            val songDao = db.songDao()
            var songs = arrayOf<Song>()
            for (audio in audios)
                songs += Song(audio.id, audio.name, audio.artist, audio.duration, audio.location, audio.album, null)
            AsyncTask.execute {
                val songCount = songDao.getCount()
                songDao.insertAll(*songs)
                println("songs table initialized, $songCount songs added.")
            }
            db.close()
        }

        // query album
        fun queryAlbums(context: Context): Array<String> {
            val db = Room.databaseBuilder(context, MusicDatabase::class.java, "music.db")
                .fallbackToDestructiveMigration()
                .build()
            val songDao = db.songDao()
            var albums: Array<String> = arrayOf()
            AsyncTask.execute {
                albums = songDao.getAlbums()
            }
            db.close()
            return albums
        }

        fun queryArtists(context: Context): Array<String> {
            val db = Room.databaseBuilder(context, MusicDatabase::class.java, "music.db")
                .fallbackToDestructiveMigration()
                .build()
            val songDao = db.songDao()
            var artists: Array<String> = arrayOf()
            AsyncTask.execute {
                artists = songDao.getArtists()
            }
            db.close()
            return artists
        }

        fun queryFolders(context: Context): Array<String> {
            val db = Room.databaseBuilder(context, MusicDatabase::class.java, "music.db")
                .fallbackToDestructiveMigration()
                .build()
            val songDao = db.songDao()
            var folders: Array<String> = arrayOf()
            AsyncTask.execute {
                folders = songDao.getFolders()
            }
            db.close()
            return folders
        }
    }
}
