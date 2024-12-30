package com.pawga.service

import com.pawga.model.Message
import com.pawga.model.TextProducer
import io.micronaut.serde.ObjectMapper
import jakarta.inject.Singleton

/**
 * Created by sivannikov on 30.12.2024 13:25
 */

@Singleton
class TextProducerImpl(private val textProducer: TextProducer, private val mapper: ObjectMapper) {

    fun send(message: String) {
        textProducer.send(message)
    }

    fun send(message: Message) {
        val jmsMessage = mapper.writeValueAsString(message)
        textProducer.send(jmsMessage)
    }
}