package com.abilia.shrimp.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Person(val id: Int, @get:JsonProperty("full_name") val fullName: String)
data class Company(val id: Int, val name: String, val city: String)