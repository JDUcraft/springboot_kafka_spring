package com.adeo.opus.kafka.services

import com.adeo.opus.configurations.OpusConfiguration
import com.adeo.opus.opusLogger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class Consumer {
    private val logger = opusLogger(javaClass)
    private lateinit var opusConfiguration: OpusConfiguration

    @KafkaListener(topics = ["#{opusConfiguration.topic}"], groupId = "#{opusConfiguration.groupId}")
    fun processMessage(content: String) {
        logger.error("received content = '{}'", content)
    }
}