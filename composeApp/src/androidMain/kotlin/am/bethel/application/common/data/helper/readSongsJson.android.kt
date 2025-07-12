package am.bethel.application.common.data.helper

// main
actual suspend fun readSongsJson(): String {
    println("loaded from android")
    return "Android don't need this implementation"
}