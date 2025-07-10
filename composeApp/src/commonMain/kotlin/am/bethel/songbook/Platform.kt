package am.bethel.songbook

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform