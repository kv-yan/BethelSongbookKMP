package am.bethel.application.common.domain.model

import am.bethel.songbook.Song as SongEntity


data class Song(
    val id: Int = 0,
    val songNumber: String = "0",
    val songWords: String = "Error",
    val isFavorite: Boolean = false
) {
//    fun getWords() = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
    fun getWords() = this.songWords
}

fun SongEntity.toSong() = Song(
    id = this.id.toInt(),
    songNumber = this.songNumber,
    songWords = this.songWords,
)

fun Song.getTitle(): String {
    val result = StringBuilder()
    for (it in this.getWords().split("\n")) {
        if (it.contains("1.")) {
            result.append(it)
        }
    }
    return result.toString()
}
