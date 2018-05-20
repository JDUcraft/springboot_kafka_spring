package com.adeo.opus.unittests

import com.adeo.opus.kafka.services.Producer
import com.adeo.opus.opusLogger
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.listener.MessageListener
import org.springframework.kafka.listener.MessageListenerContainer
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit


@SpringBootTest
@ExtendWith(SpringExtension::class)
@EmbeddedKafka(partitions = 1, topics = ["CONTENT"], controlledShutdown = true)
@DirtiesContext
class OpusDataIntegrationApplicationTests {

    @Autowired
    private lateinit var kafkaListenerEndpointRegistry: KafkaListenerEndpointRegistry

    @Autowired
    private lateinit var producer: Producer

    private lateinit var container: MessageListenerContainer
    private lateinit var records: BlockingQueue<ConsumerRecord<String, String>>

    @BeforeEach
    fun beforeEach() {
        records = LinkedBlockingQueue()
        container = kafkaListenerEndpointRegistry.getListenerContainer(kafkaListenerEndpointRegistry.listenerContainerIds.first())
        container.stop()
        container.setupMessageListener(MessageListener<String, String> { record: ConsumerRecord<String, String> ->
            records.add(record)
        })
        container.start()
        ContainerTestUtils.waitForAssignment(container, 1)
    }

    @Test
    fun `Send a simple message to next broker`() {
        producer.send("{name: 'Jonathan', id: 0}")
        val received: ConsumerRecord<String, String> = records.poll(5, TimeUnit.SECONDS)
        assertThat(received.value()).isEqualTo("{name: 'Jonathan', id: 0}")
    }

    @AfterEach
    fun after() {
        container.stop()
    }
}