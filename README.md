# KafkaDemo

## Resources
### Videos
* https://youtu.be/SqVfCyfCJqw

### Docs
* https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.kafka.receiving

### Articles
#### Stackoverflow
* https://stackoverflow.com/search?q=user:1240763+[spring-kafka]
* https://stackoverflow.com/questions/48254650/spring-kafka-trying-to-understand-how-things-work-behind-the-scenes?rq=1


#### Others
* https://www.baeldung.com/spring-kafka


#### Curl
`curl --location --request POST 'localhost:8081/user/pushToKafka' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 10000,
    "name": "Katie Jacobs",
    "age": 35
}'`
<img width="768" alt="image" src="https://user-images.githubusercontent.com/58611230/164837947-3b8ab0ec-3f99-4e04-8e3b-fcac95613370.png">

Verify data
<img width="1519" alt="image" src="https://user-images.githubusercontent.com/58611230/164837979-9626ec90-a5c2-494e-979c-9f108ad98e17.png">
