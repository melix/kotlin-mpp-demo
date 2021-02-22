package utils

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.*

// Kudos to https://www.nequalsonelifestyle.com/2020/11/16/kotlin-native-file-io/
// and https://twitter.com/neqone/status/1363634263292338176
class Files {
    companion object {
        private const val READ_MODE = "r"
        private const val WRITE_MODE = "w"

        fun readAllText(filePath: String): String {
            val returnBuffer = StringBuilder()
            val file = fopen(filePath, READ_MODE) ?:
            throw IllegalArgumentException("Cannot open input file $filePath")

            try {
                memScoped {
                    val readBufferLength = 64 * 1024
                    val buffer = allocArray<ByteVar>(readBufferLength)
                    var line = fgets(buffer, readBufferLength, file)?.toKString()
                    while (line != null) {
                        returnBuffer.append(line)
                        line = fgets(buffer, readBufferLength, file)?.toKString()
                    }
                }
            } finally {
                fclose(file)
            }

            return returnBuffer.toString()
        }
    }
}
