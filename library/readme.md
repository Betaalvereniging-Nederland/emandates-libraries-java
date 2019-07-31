# Merchant Library

This application is developed with **Maven** and the package is represented as a JAR file. Can be created using **mvn clean package** command. Resulted file can be found in **target directory**.

In the pom file it have been set the version of JDK. Also, this project contains sonar plugin.

In order to check sonar analysis these are the following steps:
1. Download SonarQube, Unzip 
2. Start on Windows from <PATH_WHERE_IS_UNZIPPED\bin\windows-x86-XX\StartSonar.bat>
   Start on other operating system: <PATH_WHERE_IS_UNZIPPED/bin/[OS]/sonar.sh
3. In merchant library, run **mvn sonar:sonar**. The output will consist in several info messages, but the most important is the URL to access sonar dashboard.
	

