package am.bethel.application.common.data.repository

import am.bethel.application.common.data.helper.SongJsonLoader
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.model.toSong
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.songbook.BethelDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SongRepositoryImpl(
    private val database: BethelDatabase
) : SongRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            insertAll()
        }
    }

    override suspend fun insertAll() {
        SongJsonLoader().load().collect {
            println("insert  started")
            it.onEach {
                database.songsEntityQueries.insertSong(
                    it.id.toLong(),
                    songNumber = it.songNumber,
                    songWords = it.songWords,
                )
            }
            println("insert finished")
        }
    }

    override fun getAll(): Flow<List<Song>> = flow {
        val songs = database.songsEntityQueries.getAllSongs().executeAsList().map { it.toSong() }
        emit(songs)
    }

    override fun search(query: String): Flow<List<Song>> = flow {
        val songs =
            database.songsEntityQueries.searchByText(query).executeAsList().map { it.toSong() }
        emit(songs)
    }

    override fun getByNumber(songNumber: String): Flow<Song?> = flow {
        val song = database.songsEntityQueries.getBySongNumber(songNumber).executeAsOneOrNull()?.toSong()
        emit(song)
    }
}