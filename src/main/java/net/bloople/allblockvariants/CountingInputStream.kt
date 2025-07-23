package net.bloople.allblockvariants

import java.io.IOException
import java.io.InputStream

// From https://github.com/Devan-Kerman/ARRP/blob/cdb107b112afbaf86000cb0579abe8722e3e53d9/src/main/java/net/devtech/arrp/util/CountingInputStream.java
class CountingInputStream(private val input: InputStream) : InputStream() {
    private var read = 0

    @Throws(IOException::class)
    override fun read(): Int {
        val read = this.input.read()
        if(read != -1) {
            this.read++
        }
        return read
    }

    @Throws(IOException::class)
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val read = this.input.read(b, off, len)
        if(read != -1) {
            this.read += read
        }
        return read
    }

    fun bytes(): Int {
        return this.read
    }
}