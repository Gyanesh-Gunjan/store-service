package com.albertheijn.stores.mapper

interface Mapper<D, E> {
    fun toDto(storeEntity : E) : D
    fun toEntity(storeDto : D, update : Boolean = false) : E
}