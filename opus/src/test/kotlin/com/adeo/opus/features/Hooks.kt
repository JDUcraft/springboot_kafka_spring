package com.adeo.opus.features

import com.adeo.opus.opusLogger
import cucumber.api.java.After
import cucumber.api.java.Before

class Hooks {
    private val logger = opusLogger(javaClass)

    @Before
    fun before() {
        logger.info("Before")
    }

    @After
    fun after() {
        logger.info("After")
    }
}