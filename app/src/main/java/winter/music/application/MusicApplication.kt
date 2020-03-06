package winter.music.application

import android.app.Application
import winter.music.data.Audio
import winter.music.utils.Util

class MusicApplication : Application() {
    companion object {
        private lateinit var single: List<Audio>
        private lateinit var artists: List<String>
        private lateinit var albums: List<String>
        private lateinit var folders: List<String>
    }
    override fun onCreate() {
        super.onCreate()
        single = Util.queryAll(this)
        Util.initSongTable(this, single)
        artists = Util.queryArtists(this)
        albums = Util.queryAlbums(this)
    }
    fun getSingle(): List<Audio> = single
    fun getAlbums(): List<String> = albums
    fun getArtists(): List<String> = artists
    fun getFolders(): List<String> = listOf()

}