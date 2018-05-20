package com.adeo.opus.kafka.services

import com.adeo.opus.configurations.OpusConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class Producer {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Autowired
    private lateinit var opusConfiguration: OpusConfiguration

    fun send(message: String) {
        kafkaTemplate.send(opusConfiguration.topic, message)
    }
}