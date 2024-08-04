# DinoDev Backend

This repository contains the backend of the DinoDev project.

## How to run with Docker

1. Make sure that the Gropius backend is accessible, either by running it locally or by using the deployed version. If
   running local, ensure that the public-api and the login-service are in the `scrum-game-network` network.
2. Edit the docker-compose.yml file to set the correct environment variables:
    - GROPIUS_URL: http://<public-api-container>:8080
    - GROPIUS_FRONTEND_URL: http://localhost:4200
   - For Version 1.2 (and older)
      - GROPIUS_AUTH_SECRET: secret of the login service
        The frontend URL is used to provide a direct link from issues. The given URL is the default URL of the Gropius
        frontend when running locally.
        When using a deployed version, ask the administrator for the secret.
   - Newer versions:
      - GROPIUS_AUTH_PUBLICKEY: <GROPIUS_PUBLIC_KEY>
        The public RSA key, stored at the Gropius Frontend. Encoded as Base64.
3. Run `docker compose up` to start the backend.

## Dev Setup

1. Follow the instructions of the first step in the previous section.
2. Only run the database, i.e., `docker compose up database-scrum-game`.
3. Edit the `application-dev` properties similar to step 2 in the previous section. With the default Gropius setup, only
   the
   secret/public key needs to be changed.
4. Run the backend in the `dev` profile: `./gradlew bootRun --args='--spring.profiles.active=dev'`. Or simply use an
   IntelliJ run configuration, but make sure to set the `dev` profile in the run configuration.

## Package Structure

- `config`: Contains the configuration classes.
- `controller`: Contains the GraphQL controllers.
- `exception`: Code for exception handling.
- `persistance`: Contains the repositories, entities, and mappings.
- `service`: Contains the services.
- `util`: Contains utility classes.


