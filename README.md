## Zookeeper Server Setup Docker Instance Installation

The `ZookeeperServiceConsumerApplication` has been built with `Zookeeper`as a service discovery tool. If you do not have
access to a `Zookeeper`server, please follow the steps as below for getting started quickly.

For development purposes, this can be rapidly setup by utilizing `Docker` to setup the `Zookeeper`container.

Below you will find `Docker` commands for downloading and starting up a local `Zookeeper`instance.

```shell
> docker run  zookeeper
> docker run -p 2181:2181 --name local-zookeeper --restart always -d zookeeper
```

This will startup a `Zookeeper`instance in `Docker` on port `2181` for your local machine.

By navigating to `src/main/resources/bootstrap.properties` you can enable and configure the `Zookeeper`server by
updating the properties.

```properties
# Zookeeper Configuration (Disabled by default)
spring.cloud.zookeeper.enabled=true
spring.cloud.zookeeper.discovery.enabled=true
# Configure to your Zookeeper instance.
spring.cloud.zookeeper.connect-string=localhost:2181
```

---

# Dockerize the EntryAPI Consumer into a Docker Container
## Create Docker Image of EntryAPI
1. First you need to generate a jar of the latest version of the application. This can be done via maven with the following
   command:

```shell
> ./mvnw clean package
```

2. Verify the jar starts up correctly

```shell
> java -jar target/demo<insert version>.jar
```

Example: `java -jar target/demo-0.0.1-SNAPSHOT.jar`.

3. Create the `Docker` Image

```shell
> docker build --tag=entryapiconsumer .
```

4. Pull down additional docker images from the `Docker` hub.
```shell
> docker pull zookeeper 
```

4. Compose `Docker` container
```shell
> docker-compose up
```

5. Verify `Docker` containers are up and running

```shell
> docker ps -a
```

Expected output should be as follows:
```shell 
CONTAINER ID   IMAGE              COMMAND                  CREATED             STATUS                        PORTS                                                  NAMES
3e1a37148a36   entryapi           "java -jar demo-0.0.???"   30 minutes ago      Up 28 seconds                 0.0.0.0:8080->8080/tcp                                 springbootprojecttemplate_entryapi_1
1fb96e094beb   zookeeper          "/docker-entrypoint.???"   30 minutes ago      Up 28 seconds                 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   springbootprojecttemplate_zookeeper_1
b30a9622838a   redis              "docker-entrypoint.s???"   30 minutes ago      Up 28 seconds                 0.0.0.0:6379->6379/tcp                                 springbootprojecttemplate_redis_1
4b9d0bec32a6   entryapiconsumer   "java -jar demo-0.0.???"   About an hour ago   Up 17 seconds                 0.0.0.0:8081->8081/tcp                                 zookeeperserviceconsumer_zookeeperserviceconsumer_1

```

---

# Entry API

Below you will find the API definition for the `Entry API` which has been implemented as part of
the `Spring Boot Project Template`. The `ZookeeperServiceConsumerApplication` interacts with this API using
a `FeignClient`to locate the service and interact directly with this API.

---

## Add Entry

The add `Entry` operation is supported by the following Restful CRUD operation:
> POST /entries/entry

### Request

Request Body - An `EntryActionInput` object:

```json
{
  "entry": {
    "value": "John Lennon"
  }
}
```

Sample Request:

```shell
curl --location --request POST 'http://localhost:8081/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
      "value":"John Lennon"
    }
}'
```

### Response

The following response structure is expected for the add `Entry` operation.

### Success (HTTP Response: 201 Created)

The entry is returned to the user if added successfully.

Sample Successful Response:

```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not added successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 1             | "Entry already exists." | 404 |
| 3             |  "The request body is missing or not well formed." | 400 |

Sample Error Response (Entry Already exists)

```json
{
  "entry": null,
  "error": {
    "code": "1",
    "description": "Entry already exists."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)

```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---

## Delete Entry

The delete `Entry` operation is supported by the following Restful CRUD operation:
> DELETE /entries/entry

### Request

Request Body - An `EntryActionInput` object:

```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  }
}
```

Sample Request:

```shell
curl --location --request DELETE 'http://localhost:8081/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
        "value":"John Lennon"
    }
}'
```

### Response

The following response structure is expected for the delete `Entry` operation.

### Success (HTTP Response: 200 Ok)

The `Entry` which has been removed is returned to the user if deleted successfully.

Sample Successful Response:

```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not deleted successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |
| 3             | "The request body is missing or not well formed." | 400 |

Sample Error Response (HTTP Response 404 - Entry Not Found)

```json
{
  "entry": null,
  "error": {
    "code": "2",
    "description": "Entry could not be found."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)

```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---

## Update Entry

The update `Entry` operation is supported by the following Restful CRUD operation:
> PUT /entries/entry

### Request

Request Body - An `EntryActionInput` object:

```json
{
  "key": "Ringo Star",
  "entry": {
    "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
    "value": "Ringo Starr"
  }
}
```

Sample Request:

```shell
curl --location --request PUT 'http://localhost:8081/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key":"Ringo Star",
    "entry": {
        "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
            "value": "Ringo Starr"
    }
}'
```

### Response

The following response structure is expected for the update `Entry` operation.

#### Success (HTTP Response: 200 Ok)

The `Entry` which has been updated is returned to the user if updated successfully.

Sample Successful Response:

```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not updated successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |
| 3             | "The request body is missing or not well formed." | 400 |

Sample Error Response (HTTP Response 404 - Entry Not Found)

```json
{
  "entry": null,
  "error": {
    "code": "2",
    "description": "Entry could not be found."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)

```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---

## Get Entry

The get `Entry` operation is supported by the following Restful CRUD operation:
> GET /entries/entry/{value}/id/{id}

### Request

Sample Request:

```shell
curl --location --request GET 'http://localhost:8081/entries/entry/John Lennon/id/ddabce27-f6a7-4860-8111-5728b5f4c915'
```

### Response

The following response structure is expected for the get `Entry` operation.

### Success (HTTP Response: 200 Ok)

The `Entry` is returned to the user if found.

Sample Successful Response:

```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not found successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |
| 3             | "The request body is missing or not well formed." | 400 |

Sample Error Response (HTTP Response 404 - Entry Not Found)

```json
{
  "entry": null,
  "error": {
    "code": "2",
    "description": "Entry could not be found."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)

```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---

## Get All Entries

The get all `Entry` operation is supported by the following Restful CRUD operation:
> GET /entries

### Request

Sample Request:

```shell
curl --location --request GET 'http://localhost:8081/entries/entry/John Lennon/id/ddabce27-f6a7-4860-8111-5728b5f4c915'
```

### Response

The following response structure is expected for the get all `Entry` operation.

### Success (HTTP Response: 200 Ok)

An array of `Entry` is returned if more than one `Entry`is found.

Sample Successful Response:

```json
{
  "entries": [
    {
      "id": "d8f79374-b51c-47ef-9937-c44323d54183",
      "value": "Paul McCartney"
    },
    {
      "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
      "value": "Ringo Starr"
    },
    {
      "id": "f0462965-4488-45f5-a52a-5b5bffc5fade",
      "value": "George Harrison"
    },
    {
      "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
      "value": "John Lennon"
    }
  ],
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if not a single `Entry` was found successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |

Sample Error Response (HTTP Response 404 - Entry Not Found)

```json
{
  "entry": null,
  "error": {
    "code": "2",
    "description": "Entry could not be found."
  }
}
```

---