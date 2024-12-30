package com.pawga.model

import io.micronaut.jms.activemq.artemis.configuration.ActiveMqArtemisConfiguration.CONNECTION_FACTORY_BEAN_NAME
import io.micronaut.jms.annotations.JMSProducer
import io.micronaut.jms.annotations.Queue
import io.micronaut.messaging.annotation.MessageBody

/**
* Created by sivannikov on 30.12.2024 13:18
*/

@JMSProducer(CONNECTION_FACTORY_BEAN_NAME)
interface TextProducer {

 @Queue("messages")
 fun send(@MessageBody body: String)
}