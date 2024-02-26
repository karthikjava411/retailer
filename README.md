Retailer Project
-----------------------
Micro service for calculating the rewards. Below is Logic for the reward calculation:
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction  (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Project Configurations
------------------------
SpringBoot 3.2, Java 21, Oracle Database

Below is the description of the project.
-----------------------------------------
  customer
  ---------
    We have customer save endpoint(localhost:8080/customer/save) for creating new customers. 
    And we have below endpoints to retrieve customers
    For retrieving all customers - localhost:8080/customer/getAllCustomers
    For retrieving customer by id - localhost:8080/customer/customerById/1
  itemdetails
  ------------
  This itemdetails is used for creating and retreving the products.
  For creating the product - localhost:8080/itemdetails/save
  For retreving the all the products - localhost:8080/itemdetails/getAllItemDetails

  orderdetails
  -------------
  In this retailer application orderdetails is the main part. This is used getting the customer transactions and rewardpoints
  User can create an order by using localhost:8080/orderdetails/save. While saving ordertails it will calculate the rewardpoints based on the amount used in the transaction. I have assumed this application as shopping site.
  
  User can retrieve his rewards in two ways - One is in the form of Map - http://localhost:8080/orderdetails/getCustomerRewardsByMonths?customerId=1 
  another is in the form of list - localhost:8080/orderdetails/getCustomerRewards?customerId=1

  User can retrieve his transactions by using endpoint - localhost:8080/orderdetails/getCustomerTransactionByPeriod?customerId=1&noOfMonths=3
  noOfMonths represents the months of transaction to retrieve and if we what to get all transactions we need to pass -1 as noOfMonths

  ordereditems
  -------------
  This is to store what products and how many quantities of the product were purchase during the order.

And i have attached all api endpoint's postman collections in resource.

Just for visualization purpose i have created a small Angular UI project (https://github.com/karthikjava411/retailer-app)
---------------------------------------------------------------------------------------------------
For customer transactions -- http://localhost:4200/orderdetails
For customer rewardpoints -- http://localhost:4200/rewardpoints

And attached the sql files in the resources folder.
  
  
  
