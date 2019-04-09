package com.abilia.shrimp.service

import com.abilia.shrimp.io.Connection
import com.abilia.shrimp.models.Person
import kotlin.random.Random

class GreetingService {

    fun getGreetingPerson(): Person = Connection().getPersons().random()

}
