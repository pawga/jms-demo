package com.pawga.service

import com.fasterxml.jackson.core.type.TypeReference
import com.pawga.model.Message
import io.micronaut.jms.activemq.artemis.configuration.ActiveMqArtemisConfiguration.CONNECTION_FACTORY_BEAN_NAME
import io.micronaut.jms.annotations.JMSListener
import io.micronaut.messaging.annotation.MessageBody
import java.util.*
import kotlin.collections.ArrayList
import io.micronaut.jms.annotations.Queue
import io.micronaut.serde.ObjectMapper
import org.slf4j.LoggerFactory

/**
 * Created by sivannikov on 30.12.2024 20:32
 */
@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
class TextConsumer(private val mapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(this::class.java)
    val messages: MutableList<Message> = Collections.synchronizedList(ArrayList())

    @Queue(value = "messages")
    fun receive(@MessageBody body: String) {
        logger.info("Received message: {}", body)
        try {
            val message = mapper.readValue(body, Message::class.java)
            messages.add(message)
        } catch (exception: Exception) {
            logger.error(exception.message, exception)
        }
    }
}