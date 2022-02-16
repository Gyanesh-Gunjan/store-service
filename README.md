# store service

## End Points </br>
	Base URI [ "/store-service/v1" ]
	•	/stores	| Get
	•	/stores	| Post
	•	/stores	| Update
	•	/stores	| Put
	•	QueryParams	| Get by storeId
	⁃	refDate 	(Default = current date)
	⁃	futureFlag	(Default = false)
	⁃	storeId	

## Def </br>
	•	Future records 
	⁃	dataValidFrom <= refDate <= dateValidUntil 
	•	Present records 
	⁃	refDate >= dataValidFrom > dataValidUntil 
	•	futureFlag 
	⁃	True - return all future records
	⁃	False - return all current record 


## Store Service 
	•	getAllStores()
	•	FindById()
	•	Save()
	•	Update()



