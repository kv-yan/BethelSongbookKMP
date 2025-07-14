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
        emit(
            raw.mapIndexed { index, it ->
                val cleanText = cleanHtmlText(it.songWords)
                println("clean text: $cleanText")
                Song(
                    id = index,
                    songNumber = it.songNumber,
                    songWords = cleanText
                )
            }
        )
    }

    private fun cleanHtmlText(input: String): String {
        val decoded = decodeHtmlEntities(input)
        val withNewlines = decoded.replace(Regex("<br\\s*/?>", RegexOption.IGNORE_CASE), "\n")
        val noOtherTags = withNewlines.replace(Regex("<[^>]*>"), "") // just in case there are other tags
        return noOtherTags.trim()
    }

    private fun decodeHtmlEntities(input: String): String {
        return input
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&apos;", "'")
            .replace("&nbsp;", " ")
            .replace(Regex("&#(\\d+);")) { match ->
                val charCode = match.groupValues[1].toIntOrNull()
                charCode?.toChar()?.toString() ?: match.value
            }
    }

    @Serializable
    private data class RawSong(val songNumber: String, val songWords: String)
}
