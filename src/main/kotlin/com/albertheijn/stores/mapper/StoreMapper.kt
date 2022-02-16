package com.albertheijn.stores.mapper

import com.albertheijn.stores.dto.AddressDto
import com.albertheijn.stores.dto.AddressPeriodDto
import com.albertheijn.stores.dto.StoreDto
import com.albertheijn.stores.model.Address
import com.albertheijn.stores.model.AddressPeriod
import com.albertheijn.stores.model.Store
import java.time.LocalDateTime

class StoreMapper : Mapper<StoreDto, Store> {
    /*
    *   convert Entity to Dto
    */
    override fun toDto(storeEntity : Store): StoreDto {
        val addressPeriodDto = mutableListOf<AddressPeriodDto>()

        storeEntity.addressPeriod.forEach {
            addressPeriodDto.add(
                AddressPeriodDto(
                    dateValidFrom = it.dateValidFrom,
                    dateValidUntil = it.dateValidUntil,
                    addressDto = AddressDto(
                        street = it.storeAddress.street,
                        houseNumber = it.storeAddress.houseNumber,
                        houseNumberSuffix = it.storeAddress.houseNumberSuffix,
                        postalCode = it.storeAddress.postalCode,
                        city = it.storeAddress.city,
                        country = it.storeAddress.country
                    )
                )
            )
        }
        return StoreDto(
            storeId = storeEntity.storeId,
            name = storeEntity.name,
            status = storeEntity.status,
            createdAt = storeEntity.createdAt,
            updatedAt = storeEntity.updatedAt,
            addressPeriodDto
        )
    }


    /*
    *   convert Dto to Entity
    *   if update = true then will update updatedAt field to localDateTime.now()
    */
    override fun toEntity(storeDto: StoreDto, update : Boolean): Store {

        val addressPeriod = mutableListOf<AddressPeriod>()

        storeDto.addressPeriodDto.forEach {
            addressPeriod.add(
                AddressPeriod(
                    dateValidFrom = it.dateValidFrom,
                    dateValidUntil = it.dateValidUntil,
                    storeAddress = Address(
                        street = it.addressDto.street,
                        houseNumber = it.addressDto.houseNumber,
                        houseNumberSuffix = it.addressDto.houseNumberSuffix,
                        postalCode = it.addressDto.postalCode,
                        city = it.addressDto.city,
                        country = it.addressDto.country
                    )
                )
            )
        }

        return Store(
            storeId = storeDto.storeId,
            name = storeDto.name,
            status = storeDto.status,
            createdAt = storeDto.createdAt,
            updatedAt = if(update) LocalDateTime.now() else storeDto.updatedAt,
            addressPeriod = addressPeriod
        )
    }
}