package com.adeo.opus.unittests

import com.adeo.opus.opusLogger
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.*


class OpusDataIntegrationApplicationTests {

    private val logger = opusLogger(javaClass)
    private val configFileName: String = "application.properties"

    @Test
    fun producer() {
        val properties = getProperties()
        val producer = KafkaProducer<String, String>(properties, StringSerializer(), StringSerializer())
        for (x in 0..6000000) {
            producer.send(ProducerRecord(properties.getProperty("topic"), "exampleKey $x", "exampleValue $x"))
        }
        producer.close()
        logger.info("Message sent successfully")
    }

    @Test
    fun consumer() {
        val properties = getProperties()
        properties["key.deserializer"] = StringDeserializer::class.java.name
        properties["value.deserializer"] = StringDeserializer::class.java.name
        properties["group.id"] = "opus-client"
        val consumer = KafkaConsumer<String, String>(properties)
        consumer.subscribe(Collections.singletonList(properties.getProperty("topic")))

        val giveUp = 100
        var noRecordsCount = 0
        while (true) {
            val consumerRecords = consumer.poll(1000)
            if (consumerRecords.count() == 0) {
                noRecordsCount++
                if (noRecordsCount > giveUp)
                    break
                else
                    continue
            }
            consumerRecords.forEach { record ->
                logger.info("Consumer Record ${record.key()} ${record.value()}")
            }
            consumer.commitAsync()
        }
        consumer.close()
    }

    private fun getProperties(): Properties {
        val properties = Properties()
        try {
            properties.load(this.javaClass.classLoader.getResourceAsStream(configFileName))
            return properties
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return properties
    }
}
