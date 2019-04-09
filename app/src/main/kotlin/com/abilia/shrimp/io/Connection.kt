package com.abilia.shrimp.io

import com.abilia.shrimp.models.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class Connection {

    val storage = "http://www.my-abilia.se/shrimp/"

    enum class JSON(val filename: String) {
        persons("persons"),
        companies("companies"),
        occupations("occupations")
    }

    val objectMapper = jacksonObjectMapper()

    fun getPersons(): List<Person> {
        val data = readUrl(JSON.persons)
        return objectMapper.readValue(data)
    }

    fun getCompanies(): List<Company> {
        val data = readUrl(JSON.companies)
        return objectMapper.readValue(data)
    }

    fun getOccupations(): List<Occupations> {
        val data = readUrl(JSON.occupations)
        return objectMapper.readValue(data)
    }

    fun readUrl(url: JSON): ByteArray  {
        return URL(storage + url + ".json").readBytes()
    }
}
