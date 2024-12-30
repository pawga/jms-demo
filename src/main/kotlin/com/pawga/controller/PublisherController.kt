package com.pawga.controller

import com.pawga.model.Message
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.parameters.RequestBody
import com.pawga.service.TextProducerImpl
import io.micronaut.http.HttpResponse.serverError

/**
 * Created by sivannikov on 30.12.2024 13:29
 */

@Controller("/message")
class PublisherController(private val producer: TextProducerImpl) {

    @Post("/object")
    fun publish(@RequestBody message: Message): HttpResponse<String> {
        try {
            producer.send(message)
            return ok("Message Sent")
        } catch (exception: Exception) {
            return serverError()
        }
    }

    @Post("/string")
    fun publish(@RequestBody message: String): HttpResponse<String> {
        try {
            producer.send(message)
            return ok("Message Sent")
        } catch (exception: Exception) {
            return serverError()
        }
    }
}