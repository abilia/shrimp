package com.abilia.shrimp.io

import com.abilia.shrimp.models.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class Connection {

    private val storage = "http://www.my-abilia.se/shrimp/"
    private var persons = mutableListOf<Person>()
    private var companies = mutableListOf<Company>()
    private var occupations = mutableListOf<Occupation>()
    private val objectMapper = jacksonObjectMapper()

    enum class JSON(val filename: String) {
        persons("persons"),
        companies("companies"),
        occupations("occupations")
    }

    fun getPersons(forceReload: Boolean = false): List<Person> {
        if (persons.isEmpty() || forceReload) {
            val data = readUrl(JSON.persons)
            persons = objectMapper.readValue(data)
        }
        return persons
    }

    fun getCompanies(forceReload: Boolean = false): List<Company> {
        if (companies.isEmpty() || forceReload) {
            val data = readUrl(JSON.companies)
            companies = objectMapper.readValue(data)
        }
        return companies
    }

    fun getOccupations(forceReload: Boolean = false): List<Occupation> {
        if (occupations.isEmpty() || forceReload) {
            val data = readUrl(JSON.occupations)
            occupations = objectMapper.readValue(data)
        }
        return occupations
    }

    private fun readUrl(url: JSON): ByteArray = URL("$storage$url.json").readBytes()
}
