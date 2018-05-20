package com.adeo.opus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class OpusDataIntegrationApplication

fun main(args: Array<String>) {
    runApplication<OpusDataIntegrationApplication>(*args)
}