package winter.music.application

import android.app.Application
import android.util.Log
import winter.music.classes.Audio
import winter.music.utils.Util

class MusicApplication : Application() {
    companion object {
        private lateinit var single: Array<Audio>
        private lateinit var artists: Array<String>
        private lateinit var albums: Array<String>
        private lateinit var folders: Array<String>
    }
    override fun onCreate() {
        super.onCreate()
        single = Util.queryAll(this)
        Util.initSongTable(this, single)

        val names = Util.queryNames(this)
        artists = Util.queryArtists(this)
        albums = Util.queryAlbums(this)
        folders = Util.queryFolders(this)
    }
    fun getSingle(): Array<Audio> = single
    fun getAlbums():Array<String> = albums
    fun getArtists():Array<String> = artists
    fun getFolders():Array<String> = folders

}