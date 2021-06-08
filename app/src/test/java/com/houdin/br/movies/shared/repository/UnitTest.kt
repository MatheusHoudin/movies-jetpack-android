package com.houdin.br.movies.shared.repository

import org.junit.Before

/**
 * @author Matheus Gomes on 08/06/2021.
 */
abstract class UnitTest {
    @Before
    fun setupTest() {
        initialize()
    }

    abstract fun initialize()
}
