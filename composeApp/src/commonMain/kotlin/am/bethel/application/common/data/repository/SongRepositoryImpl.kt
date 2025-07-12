package am.bethel.application.common.data.repository

import am.bethel.application.common.data.helper.SongJsonLoader
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class SongRepositoryImpl : SongRepository {
    override suspend fun insertAll(songs: List<Song>) {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Song>> {
        val loader = SongJsonLoader()
        return loader.load()
    }

    override fun search(query: String): Flow<List<Song>> {
        TODO("Not yet implemented")
    }

    override fun getByNumber(songNumber: String): Flow<Song?> {
        TODO("Not yet implemented")
    }
}