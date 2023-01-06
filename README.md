# aws-rds-sample

This project is a basic sample of using [Terraform](https://www.terraform.io) to create an [RDS](https://aws.amazon.com/free/database) database in [AWS](https://aws.amazon.com) and using [Spring Boot](https://spring.io/projects/spring-boot) to create an API that connects with it.

## Terraform

In order to execute the script of Terraform, first you need to install and configure:

* Install [AWS-CLI](https://docs.aws.amazon.com/cli/index.html)
* Configure the [credentials](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-envvars.html)
* Install [Terraform](https://developer.hashicorp.com/terraform/downloads)

After you have the environment configured, you should initialize terraform:

```
cd terraform
terraform init
```

### Creating structure

Now to create the database you should run the command below:

```
terraform apply
```

This execution will prompt the user to inform:
* Database name: the name of the database
* Database password: the password that will be used to access the database
* Database username: the username that will be used to access the database

After informing all the values, terraform will show what will be applied in the AWS infrastructure and is asking you if it's can be performed.
Answer `yes` and wait for the execution got finished.

The output of the script will be the `URL` of the database created. This will be necessary to use to configure the project to access the database.

### Destroying structure

For any reason, if you want to destroy the database created, you should run the command below:

```
terraform destroy
```

The execution will ask you the same values that are necessary to create the database, but here you can just inform dummy values.

## Application

The application needs some tools installed and configured:

* [Java](https://www.oracle.com/java/technologies/downloads/)
* [Gradle](https://gradle.org/install/)

In order to execute the application, first you have to configure the database URL and credentials.

Open the file `src\main\resources\application.properties` and change the values below for the ones that you inform in the execution of terraform script:
* <DATABASE_URL> - this value is the output of the execution of terraform
* <DATABASE_NAME> - the value informed by you
* <DATABASE_USERNAME> - the value informed by you
* <DATABASE_PASSWORD> - the value informed by you

Now to run the application, just execute the command below:

```
./gradlew bootRun
```

## Database Schema

## Swagger

## Postman

## Unit Test

The project contains some unit tests.

In order to execute all the unit tests, run the command below:

```
./gradlew test
```
