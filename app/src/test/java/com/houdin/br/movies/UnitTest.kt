package com.houdin.br.movies

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

/**
 * @author Matheus Gomes on 08/06/2021.
 */
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
abstract class UnitTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setupTest() {
        initialize()
        Dispatchers.setMain(testDispatcher)
    }

    abstract fun initialize()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
