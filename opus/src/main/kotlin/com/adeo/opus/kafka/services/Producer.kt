package com.adeo.opus.kafka.services

import com.adeo.opus.configurations.OpusConfiguration
import com.adeo.opus.configurations.opusLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class Producer {
    private val logger = opusLogger(javaClass)

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Autowired
    private lateinit var opusConfiguration: OpusConfiguration


    fun send(data: String) {
        logger.info("sending data='{}'", data)
        kafkaTemplate.send(opusConfiguration.topic, data)
    }
}