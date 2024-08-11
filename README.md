# notification-service

## Swagger

## How to use notification service

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
