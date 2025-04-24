package com.example.movies4U.common.data

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity): Domain
}