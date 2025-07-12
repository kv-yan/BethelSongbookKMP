package am.bethel.application.common.domain.model


data class Song(
    val id: Int = 0,
    val songNumber: String = "0",
    val songWords: String = "Error",
    val isFavorite: Boolean = false
) {
//    fun getWords() = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
    fun getWords() = this.songWords
}