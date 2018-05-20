package com.adeo.opus.configurations

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
class OpusConfiguration {
    @Value("\${opus.bootstrap.servers}")
    lateinit var bootstrapServers: String

    @Value("\${opus.group.id}")
    lateinit var groupId: String

    @Value("\${opus.topic}")
    lateinit var topic: String
}
