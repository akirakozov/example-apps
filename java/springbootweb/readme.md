# Simple spring-boot web application

Simple http web server, it starts on port 8080 and return IP-address of current server machine.

Run web server:
```
mvn exec:java
```

Usage:
```
curl "http://localhost:8080/?name=Petr"

Hi, Petr!
My Ip: 192.168.0.1
```


