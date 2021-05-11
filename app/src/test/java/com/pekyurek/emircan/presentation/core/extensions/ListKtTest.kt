package com.pekyurek.emircan.presentation.core.extensions

import org.junit.Assert.*
import org.junit.Test
import java.lang.NullPointerException

class ListKtTest {


    @Test
    fun next_item_or_first() {
        val list = listOf("a", "c", "e", "b", "w")
        assertEquals(list.nextItemOrFirst("a"), "c")
        assertEquals(list.nextItemOrFirst("c"), "e")
        assertEquals(list.nextItemOrFirst("e"), "b")
        assertEquals(list.nextItemOrFirst("b"), "w")
        assertEquals(list.nextItemOrFirst("w"), "a")

        assertEquals(list.nextItemOrFirst("r"), "a")

        assertNotSame(list.nextItemOrFirst("a"), "b")
        assertNotSame(list.nextItemOrFirst("w"), "b")

        assertNotSame(list.nextItemOrFirst("r"), "s")

        assertThrows(ArithmeticException::class.java) { emptyList<String>().nextItemOrFirst("c") }
        assertThrows(NullPointerException::class.java) { null!!.nextItemOrFirst("") }

    }
}