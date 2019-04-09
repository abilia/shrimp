package com.abilia.shrimp.service

import com.abilia.shrimp.io.Connection
import com.abilia.shrimp.models.ExtendedPerson
import com.abilia.shrimp.models.Person
import kotlinx.coroutines.*

class GreetingService {
    private val connection = Connection()

    fun getGreetingPerson(): Person = connection.getPersons().random()

    suspend fun getAllPersonData(): List<ExtendedPerson> {

        val personsTask = GlobalScope.async {
            connection.getPersons()
        }
        val companiesTask = GlobalScope.async {
            connection.getCompanies()
        }
        val occupationsTask = GlobalScope.async {
            connection.getOccupations()
        }

        val persons = personsTask.await()
        val companies = companiesTask.await()
        val occupations = occupationsTask.await()

        return persons
                .mapNotNull { p -> val fo = occupations.find { o -> p.id == o.personId }
                    if (fo != null) Pair(p, fo) else null
                }
                .mapNotNull { po -> val fc = companies.find { c -> c.id == po.second.companyId }
                    if (fc != null) ExtendedPerson(po.first, fc, po.second) else null
                }
    }
}