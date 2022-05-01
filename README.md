# KafkaDemo

## Resources
### Videos
* https://youtu.be/SqVfCyfCJqw

### Docs
* https://docs.spring.io/spring-kafka/docs/current/reference/html/#receiving-messages
* https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.kafka.receiving
* https://kafka.apache.org/documentation/#producerconfigs
* https://kafka.apache.org/documentation/#consumerconfigs

### Articles
#### Stackoverflow
* https://stackoverflow.com/search?q=user:1240763+[spring-kafka]
* Listeners and concurrency:
* https://stackoverflow.com/a/68978435/4210068
* https://stackoverflow.com/a/55033589/4210068
* https://stackoverflow.com/q/48254650/4210068
* https://stackoverflow.com/a/64012713/4210068
* https://stackoverflow.com/a/50254280/4210068


#### Others
* https://www.baeldung.com/spring-kafka


## Testing
#### Curl
```javascript
curl --location --request POST 'localhost:8081/user/pushToKafka' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 10000,
    "name": "Katie Jacobs",
    "age": 35
}'
```

<img width="768" alt="image" src="https://user-images.githubusercontent.com/58611230/164837947-3b8ab0ec-3f99-4e04-8e3b-fcac95613370.png">

Verify data is pushed correctly

<img width="1519" alt="image" src="https://user-images.githubusercontent.com/58611230/164837979-9626ec90-a5c2-494e-979c-9f108ad98e17.png">

Verify data reception by consumer

<img width="1039" alt="image" src="https://user-images.githubusercontent.com/58611230/164838079-d027fd2e-e8bc-4f6f-bea3-4be4c6ae46b9.png">


## SETUP
### Kafka, Zookeeper, Docker setup
first check if docker is installed 
**docker -v** 
If not, then install docker first 
**brew install --cask docker**

then create a file called docker-compose.yml in user home directory and paste following content into it

```javascript
version: "3"
services:
  zookeeper:
	image: 'bitnami/zookeeper:latest'
	container_name: zookeeper
	ports:
  	- '2181:2181'
	environment:
  	- ALLOW_ANONYMOUS_LOGIN=yes
  kafka:
	image: 'bitnami/kafka:latest'
	container_name: kakfa
	ports:
  	- '9092:9092'
	environment:
  	- KAFKA_BROKER_ID=1
  	- KAFKA_LISTENERS=PLAINTEXT://:9092
  	- KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
  	- KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
  	- ALLOW_PLAINTEXT_LISTENER=yes
	depends_on:
  	- zookeeper
```

then run
**docker-compose -f docker-compose.yml up**
or run using docker desktop client

<img width="1261" alt="image" src="https://user-images.githubusercontent.com/58611230/164838259-fa2e5070-c51b-4337-973f-4f08c64f3c2f.png">

