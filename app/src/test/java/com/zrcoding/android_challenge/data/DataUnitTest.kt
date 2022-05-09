package com.zrcoding.android_challenge.data

import com.zrcoding.android_challenge.data.remote.helpers.createMovieRequestQueryMap
import com.zrcoding.android_challenge.data.remote.helpers.createMovieRequestUrl
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataUnitTest {
    @Test
    fun apiHelpersTest() {
        assertEquals(createMovieRequestUrl(""), "movie/popular")
        assertEquals(createMovieRequestUrl("anime"), "search/movie")

        Assert.assertFalse(createMovieRequestQueryMap("", 1).containsKey("query"))
        assert(createMovieRequestQueryMap("anime", 2).containsKey("query"))
    }
}