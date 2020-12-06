#Assumptions and Considerations:
------------------------------------
	1) Authentication and Authorization for request can be done by validating the requests against the data in Application.properties file.
	2) All the fields corresponding to USerDetails.java are mandatory.
	3) Need not validate the incoming requests for update transaction and update the non null data based on valid used id.
	4) email id should be valid(Format : {x}@{x}.com.{x}). If not rollback the transaction after the merge.



#Swagger:
---------
	Swagger contract for the API is in the base path - swagger.yaml



#Docker Build:
---------------
	Docker file added to do the build irrespective of dependent softwares available in the machine. Docker installation is sufficient to build the image and run the container

	Commands:
	---------
	Build: docker build -t user-mgmt-service .
	Run: docker run -p 8080:8080 user-mgmt-service
	

#Authentication and Authorization:
----------------------------------------
	Credentials to access the API should ideally be tokens valid on timely basis, If basic auth to be used, atleast the credentials should be retrieved from the Vault to have the security.
	However here to demonstrate the mechanism, I have configured 2 users(1 Read only and 1 Admin Privliged user) in Application.properties file and using the same in the APP.
	
	Read Only : usermgmt-readonly/readonly1234
	Admin : usermgmt-admin/admin1234
	
#PACT:
-------
	Pact location: src/test/resources