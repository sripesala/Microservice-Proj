# Microservice-Proj
Spring boot service with in-memory database

##Spring service demo is to add or customer transactions. This service built in spring boot and spring jpa frameworks. ##Mockito is used for unit testing and in-memory database h2 is used as back end.

##IDE - STS

#Available functionalities Enquiry:[GET] http://localhost:8888/api/transaction/customer/{customerid}

Creation:[POST] http://localhost:8888/api/transactions Request body: {"amount":200.0,"timestamp":"1531243438052","customer":"srini"}

Deletion:[DELETE] http://localhost:8888/api/transaction/delete/{transactionid}

update:[POST] http://localhost:8888/api/transaction/update Request body: {"amount":205.0,"timestamp":"1531243438052","customer":"srini","transaction_id":2}
