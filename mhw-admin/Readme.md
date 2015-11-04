# cosplay-admin

## install
1. import your IDE as maven project.
2. download & install in your IDE [lombok](https://projectlombok.org/).
3. run com.cosplay.app.CosplayApplication.
4. access [here](http://localhost:8080) on browser.

```
$ rm cosplay-web/src/main/java/com/cosplay/tool/Generator.java
$ mvn package
$ cd cosplay-web/target
$ java -jar cosplay-web-0.0.1-SNAPSHOT.jar --server.port=${port} --spring.profiles.active=${profile}
```
