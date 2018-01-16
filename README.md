# "MyFace" Social Network Exersize

# Requirements

This project requires at least Java 8.  If you want to run it from the command line then you'll 
need [Maven 3](https://maven.apache.org/) on your `PATH` as well.

You'll need MySQL server and MySQL workbench, this can be downloaded from 
[here](https://dev.mysql.com/downloads/installer/) if you don't aleady have it.  When running 
through the installer you can keep all the default options and set the password for the `root` 
user as `mysql` (well, you can set it to anything you like, but if you want to use something
else then you must also update the `password` field in `config.yml`).

# Setup

Open mysql workbench, choose the local mysql instance, and log in with the username `root` and 
the password `mysql`.  Create a database called socialnetwork and run the `bootstrapDatabase.sql` 
script to create a new database called socialnetwork and insert a bit of test data.

# Running the application

To run inside intelliJ, create a run configuration of type `Application`, the main class is
`org.softwire.training.SocialNetworkApplication` and the program arguments are `server config.yml`.

To run from the command line, first build the jar with `mvn clean install` then start the app with
 `java -jar target/socialnetwork-1.0-SNAPSHOT.jar server config.yml`.

Once the application has started go to `http://localhost:8080` for the homepage.  The healthcheck
 endpoint `http://localhost:8081/healthcheck` will show if there were issues connecting to the 
 database.  If there are any problems starting the application take a look at the logs first.

# Unit Tests

To run in intelliJ, right click on the `src/test/java` folder and then choose `Run 'All Tests'`.  
From the command line, use `mvn test`.

# Icons

Icons taken from [material.io](https://material.io), licenced under Apache v2.