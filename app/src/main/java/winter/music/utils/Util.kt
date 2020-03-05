package winter.music.utils

import android.content.Context
import android.provider.MediaStore
import winter.music.data.Audio
import winter.music.data.MusicDatabase

class Util(){
    companion object{
        // get all audio files from MediaStore
        fun queryAll(context: Context): List<Audio>{
            var audioList = listOf<Audio>()
            val projection = arrayOf(
                MediaStore.Audio.Media._ID, // id
                MediaStore.Audio.Media.DISPLAY_NAME, // name
                MediaStore.Audio.Media.ARTIST, // artist
                MediaStore.Audio.Media.ALBUM, // album
                MediaStore.Audio.Media.DURATION // duration
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

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val artist = cursor.getString(artistColumn)
                    val album = cursor.getString(albumColumn)
                    val duration = cursor.getInt(durationColumn)
                    audioList += Audio(id, name, artist, album, duration)
                }
            }
            return audioList
        }

        // initialize songs table
        suspend fun initSongTable(context: Context, audios: List<Audio>){
            MusicDatabase.getInstance(context)
                .audioDao()
                .insertAll(audios)
            val songCount = MusicDatabase.getInstance(context)
                .audioDao()
                .getCount()
            println("songs table initialized, $songCount songs added.")
        }

        // query names
        fun queryNames(context: Context): Array<String> {
            var names: Array<String> = arrayOf()
//            AsyncTask.execute {
//            }
            names = MusicDatabase.getInstance(context)!!
                .songDao()
                .getNames()
            return names
        }

        // query album
        fun queryAlbums(context: Context): Array<String> {
            var albums: Array<String> = arrayOf()
//            AsyncTask.execute {
//            }
            albums = MusicDatabase.getInstance(context)!!
                .songDao()
                .getArtists()
            return albums
        }

        fun queryArtists(context: Context): Array<String> {
            var artists: Array<String> = arrayOf()
//            AsyncTask.execute {
//            }
            artists = MusicDatabase.getInstance(context)!!
                .songDao()
                .getArtists()
            return artists
        }

        fun queryFolders(context: Context): Array<String> {
            var folders: Array<String> = arrayOf()
//            AsyncTask.execute {
//            }
            folders = MusicDatabase.getInstance(context)!!
                .songDao()
                .getArtists()
            return folders
        }
    }
}
