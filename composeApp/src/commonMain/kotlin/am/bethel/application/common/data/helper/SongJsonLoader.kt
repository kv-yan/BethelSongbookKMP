package am.bethel.application.common.data.helper

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class SongJsonLoader {
    fun load(): Flow<List<Song>> = flow {
        val json = readSongsJson()
        val raw = Json.decodeFromString<List<RawSong>>(json)
        emit(raw.mapIndexed { index, it ->
            Song(
                id = index,
                songNumber = it.songNumber,
                songWords = cleanHtmlText(it.songWords)
            )
        }
        )
    }

    private fun cleanHtmlText(input: String): String =
        input.replace(Regex("<[^>]*>"), "").replace("&nbsp;", " ").trim()

    @Serializable
    private data class RawSong(val songNumber: String, val songWords: String)
}
