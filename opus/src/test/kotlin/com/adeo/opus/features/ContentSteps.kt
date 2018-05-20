package com.adeo.opus.features

import com.adeo.opus.configurations.opusLogger
import cucumber.api.java8.En

class ContentSteps : En {

    private val logger = opusLogger(javaClass)

    init {
        Given("I have (\\d+) cukes in my belly", { numberOfCukes: Int ->
            logger.info("Step 1: $numberOfCukes")
        })

        When("I wait (\\d+) hour", { numberOfHours: Int ->
            logger.error("Step 2: $numberOfHours")
        })

        Then("my belly should growl") {
            logger.debug("Step 3")
        }
    }
}

