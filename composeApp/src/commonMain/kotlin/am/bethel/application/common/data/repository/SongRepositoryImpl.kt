package am.bethel.application.common.data.repository

import am.bethel.application.common.data.helper.SongJsonLoader
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.model.toSong
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.songbook.BethelDatabase
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class SongRepositoryImpl(
    private val database: BethelDatabase
) : SongRepository {

    override suspend fun insertAll() {
        SongJsonLoader().load().collect { list ->
            list.onEach {
                database.songsEntityQueries.insertSong(
                    it.id.toLong(),
                    songNumber = it.songNumber,
                    songWords = it.songWords,
                )
            }
        }
    }

    override fun getAll(): Flow<List<Song>> =
        database.songsEntityQueries.getAllSongs()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toSong() } }


    override fun search(query: String): Flow<List<Song>> =
        database.songsEntityQueries.searchByText(query)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toSong() } }

    override fun getByNumber(songNumber: String): Flow<Song?> = flow {
        val song = database.songsEntityQueries.getBySongNumber(songNumber)
            .executeAsOneOrNull()
            ?.toSong()
        emit(song)
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun addToFavorites(song: Song) {
        val currentTime = Clock.System.now().toEpochMilliseconds()
        database.songsEntityQueries.addToFavorites(
            songId = song.id.toLong(),
            addedAt = currentTime
        )
    }

    override suspend fun removeFromFavorites(song: Song) {
        database.songsEntityQueries.removeFromFavorites(song.id.toLong())
    }

    override fun getFavoriteSongs(): Flow<List<Song>> =
        database.songsEntityQueries.getFavorites()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toSong() } }

    override fun isFavorite(song: Song): Flow<Boolean> =
        database.songsEntityQueries.isFavorite(song.id.toLong())
            .asFlow()
            .map { it.executeAsOne() }
}
