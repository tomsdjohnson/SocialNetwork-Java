# "MyFace" Social Network Exersize

# Setup

This project requires at least Java 8.  If you want to run it from the command line then you'll 
need [Maven 3](https://maven.apache.org/) on your `PATH` as well.

You'll need MySQL server and MySQL workbench, this can be downloaded from 
[here](https://dev.mysql.com/downloads/installer/) if you don't aleady have it.  When running 
through the installer you can keep all the default options and choose a sensible root password.

Open mysql workbench, choose the local mysql instance, and log in with your root credentials.
Create a database called socialnetwork and run the `bootstrapDatabase.sql` script to create the tables and insert some test data.

Create a user account that can access the database and update `config.yml` with the username and password.

**Warning: the password will be visible in GitHub**, so make sure it is not shared with anything else.

# Running the application

To run inside IntelliJ, create a run configuration of type `Application`,
the main class is `org.softwire.training.SocialNetworkApplication`.

To run from the command line, first build the jar with `mvn clean install`,
then start the app with `java -jar target/socialnetwork-1.0-SNAPSHOT.jar`.

Once the application has started go to `http://localhost:8080` for the homepage.  The healthcheck
 endpoint `http://localhost:8081/healthcheck` will show if there were issues connecting to the 
 database.  If there are any problems starting the application take a look at the logs first.

# Unit Tests

To run in IntelliJ, right click on the `src/test/java` folder and then choose `Run 'All Tests'`.
From the command line, use `mvn test`.

# Icons

Icons taken from [material.io](https://material.io), licenced under Apache 2.0.
