package am.bethel.application.common.data.helper

import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataWithContentsOfFile

// ios
actual suspend fun readSongsJson(): String {
    println("loaded from ios ")
    val path = NSBundle.mainBundle.pathForResource("songs", "json")!!
    val data = NSData.dataWithContentsOfFile(path)!!
    return NSString.create(data, NSUTF8StringEncoding) as String
}
