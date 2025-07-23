package net.bloople.allblockvariants

import java.io.OutputStream
import java.lang.AutoCloseable
import kotlin.math.max

// From https://github.com/Devan-Kerman/ARRP/blob/cdb107b112afbaf86000cb0579abe8722e3e53d9/src/main/java/net/devtech/arrp/util/UnsafeByteArrayOutputStream.java
class UnsafeByteArrayOutputStream @JvmOverloads constructor(size: Int = 128) : OutputStream(), AutoCloseable {
    private var buf: ByteArray
    private var index: Int = 0

    init {
        require(size >= 0) { "Negative initial size: $size" }
        this.buf = ByteArray(size)
    }

    override fun write(b: Int) {
        this.ensureCapacity(this.index + 1)
        this.buf[this.index++] = b.toByte()
    }

    private fun ensureCapacity(minCapacity: Int) {
        val len = this.buf.size
        if(minCapacity > len) {
            val size = max(minCapacity, len / 2 + len)
            this.buf = this.buf.copyOf(size)
        }
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        if((off < 0) || (off > b.size) || (len < 0) || ((off + len) - b.size > 0)) {
            throw IndexOutOfBoundsException()
        }
        this.ensureCapacity(this.index + len)
        System.arraycopy(b, off, this.buf, this.index, len)
        this.index += len
    }

    val bytes: ByteArray
        get() = this.buf.copyOf(this.index)
}