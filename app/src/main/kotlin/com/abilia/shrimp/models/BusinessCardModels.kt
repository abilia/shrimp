package com.abilia.shrimp.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Person(
        val id: Int,
        @get:JsonProperty("full_name") val fullName: String
)

data class Company (
        val id: Int,
        val name: String,
        val city: String
)

data class Occupation (
        @get:JsonProperty("person_id") val personId : Int,
        @get:JsonProperty("company_id") val companyId : Int,
        val occupation : String
)

data class ExtendedPerson (
        val person: Person,
        val company: Company,
        val occupation: Occupation
)