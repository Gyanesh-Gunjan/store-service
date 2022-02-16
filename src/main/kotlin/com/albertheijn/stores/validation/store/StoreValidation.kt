package com.albertheijn.stores.validation.store

import com.albertheijn.stores.model.Store

/*
*   return true if store will Valid
*   else return false
*/
fun isValidStore(store : Store): Boolean {
    val isValidCreateUpdate : Boolean= store.createdAt <= store.updatedAt
    val containsInvalidAddressPeriod : Boolean = store.addressPeriod.any { it.dateValidUntil != null && it.dateValidUntil!! < it.dateValidFrom }
    return isValidCreateUpdate && !containsInvalidAddressPeriod
}