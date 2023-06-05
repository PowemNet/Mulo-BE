# Mulo Backend

# Build project
Java version: 17
The project is built with gradle. 
Import the project as a gradle project into your IDE, and that should trigger an auto build.
You can alternatively run the command `./gradlew clean build`.

# Run dependencies
All dependencies (only postgres so far) can be setup locally using Docker.

In the root dit, run `docker-compose down && docker-compose up`.

# Run project
`./gradlew bootRun` to run the app using the default `application.yml` file.

# Db seed
Seed data for running on local can be set up using scripts under `resources/seed.sql`
 