package com.alexotero.aisistant.apicore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class ApicoreApplication

fun main(args: Array<String>) {
	runApplication<ApicoreApplication>(*args)
}
