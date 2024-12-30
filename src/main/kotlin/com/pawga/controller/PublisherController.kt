package com.pawga.controller

import com.pawga.model.Message
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import com.pawga.service.TextProducer
import io.micronaut.http.HttpResponse.serverError
import io.micronaut.http.annotation.Body
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.serde.ObjectMapper
import org.slf4j.LoggerFactory

/**
 * Created by sivannikov on 30.12.2024 13:29
 */

@Controller("/message")
class PublisherController(
    private val producer: TextProducer,
    private val mapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post("/object", produces=["text/plain"])
    @ExecuteOn(TaskExecutors.BLOCKING)
    fun publish(@Body message: Message): HttpResponse<String> {
        try {
            val jmsMessage = mapper.writeValueAsString(message)
            producer.send(jmsMessage)
            return ok("Message Sent")
        } catch (exception: Exception) {
            logger.error(exception.message)
            return serverError()
        }
    }
}