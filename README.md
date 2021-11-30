# store-service

</br>
</br>

## Expose GET Endpoint </br>
- Base URI : ```/store-service/v1```
- Get Endpoint :
  - ```/stores/```    *GET ALL(Query Parameter : refDate)*
  - ```/store/{storeId}```  *GET BY ID*

</br>


## refDate Details </br>
#### Default parameter for refDate will be presentDate </br>

### Future recrod </br>
- dateValidFrom > refDate 
- dataValidUnitl > refDate


### Present recrod </br>
- dateValidFrom <= refDate <= dataValidUntil 


