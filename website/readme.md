# Merchant Website

This application is developed with **Maven** and the package is represented as a WAR file. Resulted file can be found in **target directory**. 

Deploying can be made using **mvn clean install**. When this command is executed will trigger embedded Glassfish server.

In order to update the merchant library, you have to replace the library (resources/merchant.library-1.0.0.jar) with the new version and redeploy the website.

Main URL to access Merchant Website: http://localhost:8080/net.emandates.merchant.website/Home