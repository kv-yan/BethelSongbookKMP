package am.bethel.application.common.domain.repository

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun insertAll()
    fun getAll(): Flow<List<Song>>
    fun search(query: String): Flow<List<Song>>
    fun getByNumber(songNumber: String): Flow<Song?>
}
