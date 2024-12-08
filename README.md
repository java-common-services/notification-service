# notification-service

## Swagger

http://localhost:8080/swagger-ui/index.html#/

## Setting up the service locally

1. Create a database called "notifications"
1. Override the database configurations in application.properties. TODO: Write exact steps.

## Deploying the service

TODO

## How to use notification service

### As an admin user, setup notifications

1. Create a template.
   
   Sample body:
   ```json
   {
      "name": "MyTemplate",
      "content": "Hello ${user}",
      "templateType": "EMAIL",
      "templateRenderType": "TEXT",
      "parameters": [
         {
            "name": "user",
            "dataType": "STRING",
            "dataValidationRule": { "minLength": 1, "maxLength": 20}
         }
      ]
   }
   ```

   Note down the template ID, we will be using that in next step.

1. Create a rule

   Sample body:
   ```json
   {
      "eventName": "UserCreated",
      "description": "Rule to be executed when user is created",
      "templateIds": [
         "<previously created template ID>"
      ]
   }
   ```

### In your service, as a service user, send a notification

TBD
