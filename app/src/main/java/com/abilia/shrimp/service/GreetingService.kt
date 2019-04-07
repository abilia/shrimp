package com.abilia.shrimp.service

import android.util.Log
import com.abilia.shrimp.io.GetGreetingTask
import com.abilia.shrimp.models.Person
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*
import java.util.concurrent.ExecutionException

class GreetingService {

    fun getGreetingPerson(): Person {
        val task = GetGreetingTask().execute()
        var data: String? = null
        try {
            data = task.get()
        } catch (e: ExecutionException) {
            Log.e("", "Exception when get data from server", e)
        } catch (e: InterruptedException) {
            Log.e("", "Exception when get data from server", e)
        }

        val mapper = jacksonObjectMapper()
        val persons: List<Person> = mapper.readValue(data!!)
        return persons.get(Random().nextInt(persons.size))
    }
}