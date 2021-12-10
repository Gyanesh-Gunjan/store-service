package com.AlbertHeijn.storeservice.configuration

const val BASE_URI : String = "/store-service/v1"
const val STORES_DETAILS_END_POINT="$BASE_URI/stores"
const val STORE_BY_ID_END_POINT="$STORES_DETAILS_END_POINT/{storeId}"
const val STORE_DETAILS_ADD_END_POINT="$STORES_DETAILS_END_POINT/add"