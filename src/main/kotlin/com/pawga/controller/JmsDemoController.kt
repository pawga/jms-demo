package com.pawga.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class JmsDemoController {

    @Get(uri="/", produces=["text/plain"])
    fun index(): String {
        return "The service is alive!"
    }
}