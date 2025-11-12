package am.bethel.application.common.ext

fun String.removeSongHeader(): String {
    // Find the index where the actual song starts (first "1.")
    val startIndex = this.indexOf("\n1.")
        .takeIf { it >= 0 } // only if "1." found
        ?: this.indexOf("1.") // maybe no newline before "1."

    // If found, cut everything before it
    return if (startIndex >= 0) {
        this.substring(startIndex).trimStart()
    } else {
        this.trimStart() // fallback, return as-is
    }
}


fun String.removeSongFooter(): String{
    // Find the index where the actual song starts (first "1.")
    val startIndex = this.indexOf("\n1.")
        .takeIf { it >= 0 } // only if "1." found
        ?: this.indexOf("1.") // maybe no newline before "1."

    // If found, cut everything before it
    return if (startIndex >= 0) {
        this.substring(startIndex).trimStart()
    } else {
        this.trimStart() // fallback, return as-is
    }
}