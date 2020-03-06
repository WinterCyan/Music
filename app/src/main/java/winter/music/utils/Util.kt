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
        fun initSongTable(context: Context, audios: List<Audio>){
            MusicDatabase.getInstance(context)
                .audioDao()
                .insertAll(audios)
            val inaudios = MusicDatabase.getInstance(context)
                .audioDao()
                .getAudioByAlbum("Bright eyes")
            for (audio in inaudios) println("name: ${audio.name}")
        }

        fun queryArtists(context: Context): List<String> {
            var artists: List<String> = listOf()
            artists = MusicDatabase.getInstance(context)
                .audioDao()
                .getArtists()
            return artists
        }

        fun queryAlbums(context: Context): List<String> {
            var albums: List<String> = listOf()
            albums = MusicDatabase.getInstance(context)
                .audioDao()
                .getAlbums()
            return albums
        }
    }
}
