package com.pawga.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Message(val name: String, val email: String, val website: String)
