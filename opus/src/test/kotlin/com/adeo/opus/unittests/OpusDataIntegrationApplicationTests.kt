package com.adeo.opus.unittests

import com.adeo.opus.configurations.opusLogger
import com.adeo.opus.kafka.services.Producer
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.system.measureTimeMillis

@SpringBootTest
@ExtendWith(SpringExtension::class)
class OpusDataIntegrationApplicationTests {

    @Mock
    private lateinit var producer: Producer

    private val logger = opusLogger(javaClass)

    @Before
    fun beforeAll() {
        initMocks(this)
        Mockito.verify(producer, never()).send(any())
    }

    @Test
    fun run() {
        val time = measureTimeMillis {
            for (x in 0..1000) {
                producer.send("{name: 'Jonathan', id: $x}")
            }
        }
        logger.info("Ended in: $time ms")
    }
}