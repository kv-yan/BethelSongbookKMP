package am.bethel.application.common.data.repository

import am.bethel.application.common.data.helper.SongJsonLoader
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SongRepositoryImpl : SongRepository {

    private val songsFlow = MutableStateFlow<List<Song>>(emptyList())

    init {
        getAll()
    }
    override suspend fun insertAll(songs: List<Song>) {
        TODO("Not yet implemented")
    }

    override  fun getAll(): Flow<List<Song>> {
        SongJsonLoader().load().map {
            songsFlow.value = it
            println("loaded :: ${songsFlow.value.size}")
        }.flowOn(Dispatchers.IO)
        return songsFlow
    }

    override fun search(query: String): Flow<List<Song>> = flow {
        songsFlow.map {
            it.filter { song ->
                song.songWords.contains(query)
            }
        }
    }

    override fun getByNumber(songNumber: String): Flow<Song?> = songsFlow.map {
        val index = songNumber.toInt()
        val result = it[index]
        result
    }
}