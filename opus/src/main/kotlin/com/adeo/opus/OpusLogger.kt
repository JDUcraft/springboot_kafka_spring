package com.adeo.opus

import org.slf4j.LoggerFactory

fun <T> opusLogger(javaClass: Class<T>) = LoggerFactory.getLogger(javaClass)!!