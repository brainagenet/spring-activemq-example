# Spring ActiveMQ Example

## Application Overview

| name | teyp | description |
| --- | --- | --- |
| spring-activemq-example-lib | jar | Application 전체에서 사용할 Core 기능과 Consumer / Producer에서 공통으로 사용할 비지니스 로직을 포함하고 있습니다. |
| spring-activemq-example-producer | war | 간단한 CRUD Application 입니다. Google Guava 기반의 Event 방식으로 MQ에 요청을 합니다. |
| spring-activemq-example-consumer | war | MQ에 요청된 것을 처리하고 상태를 조회할 수 있는 API를 제공합니다. |

## ActiveMQ 구동하기 (Start ActiveMQ Message Broker)

[ActiveMQ Homepage](http://activemq.apache.org/)에서 [Download](http://activemq.apache.org/activemq-5140-release.html) 한 후, `env` 설정을 한 후, start 해 줍니다.

```bash
$ mkdir -p /d/poc/mq/src
$ cd /d/poc/mq/src

# -- 다운로드
$ wget wget http://archive.apache.org/dist/activemq/5.14.0/apache-activemq-5.14.0-bin.tar.gz

# -- 압축 해제
$ tar xvzf apache-activemq-5.14.0-bin.tar.gz

# -- 디렉토리 이동 및 Symbolic Link 생성
$ mv apache-activemq-5.14.0 .. && cd .. && ln -s ./apache-activemq-5.14.0 apache-activemq-5

# -- 설정
# env 파일의 110 line에 JAVA_HOME을 설정
$ cd apache-activemq-5
$ vi ./bin/env
...
106 # Location of the java installation
107 # Specify the location of your java installation using JAVA_HOME, or specify the
108 # path to the "java" binary using JAVACMD
109 # (set JAVACMD to "auto" for automatic detection)
110 JAVA_HOME="/usr/java/jdk1.7.0"
111 JAVACMD="auto"
112

# -- 구동
$ cd apache-activemq-5
$ bin/activemq start
INFO: Loading '/cygdrive/d/poc/mq/apache-activemq-5.14.0/bin/env'
INFO: Using java '/usr/java/jdk1.7.0/bin/java'
INFO: Starting - inspect logfiles specified in logging.properties and log4j.properties to get details
INFO: pidfile created : '/cygdrive/d/poc/mq/apache-activemq-5.14.0/data/activemq.pid' (pid '4448')

```

### Monitoring 하기
 
#### 내장 Web Console 사용하기

Standalone로 구동하면 내장된 Web Console(`http://localhost:8161/admin/` with credentials `admin/admin`)을 통해 상태를 확인할 수 있습니다.

![activemq-web-console.png](/files/213)

#### hawtio 사용하기

> 참고문서
> * [Running Apache ActiveMQ and hawtio in Standalone Mode](http://www.bennet-schulz.com/2016/07/apache-activemq-and-hawtio.html)

ActiveMQ의 내장 Web Console도 좋기는 하지만 제공해 주는 것 이외에 다른 정보들을 확인하기 어려운 단점이 있습니다. 개인적으로 생각하는 제일 좋은 대안은 JMXTrans + InfluxDB + Grafana 입니다만 설치나 관리의 복잡도가 올라가니 여기서는 JBoss Community의 Open Source Web Console 인 hawtio를 설치하고 사용해 보도록 하겠습니다.

이미 Apache ActiveMQ는 설치했으므로 여기서는 hawtio(offline war version)만 [다운로드](http://hawt.io/getstarted/index.html) 받아서 설치하고 확인해 보도록 하겠습니다.

```bash
# -- 다운로드
$ cd /d/poc/mq/src
$ wget https://oss.sonatype.org/content/repositories/public/io/hawt/hawtio-default-offline/1.4.65/hawtio-default-offline-1.4.65.war

# -- 압축 해제
$ unzip hawtio-default-offline-1.4.65.war -d hawtio

# -- ActiveMQ의 webapps 디렉토리로 이동
$ mv -v hawtio /d/poc/mq/apache-activemq-5/webapps
‘hawtio’ -> ‘/d/poc/mq/apache-activemq-5/webapps/hawtio’

# -- jetty에서 context 등록
$ cd /d/poc/mq/apache-activemq-5/conf
$ vi jetty
...
 61         <bean id="secHandlerCollection" class="org.eclipse.jetty.server.handler.HandlerCollection">
 62                 <property name="handlers">
 63                         <list>
 64                     <ref bean="rewriteHandler"/>
 65                                 <bean class="org.eclipse.jetty.webapp.WebAppContext">
 66                                         <property name="contextPath" value="/admin" />
 67                                         <property name="resourceBase" value="${activemq.home}/webapps/admin" />
 68                                         <property name="logUrlOnStart" value="true" />
 69                                 </bean>
 70                                 <bean class="org.eclipse.jetty.webapp.WebAppContext">
 71                                         <property name="contextPath" value="/hawtio" />
 72                                         <property name="resourceBase" value="${activemq.home}/webapps/hawtio" />
 73                                         <property name="logUrlOnStart" value="true" />
 74                                 </bean>
 75                                 <bean class="org.eclipse.jetty.webapp.WebAppContext">
 76                                         <property name="contextPath" value="/api" />
 77                                         <property name="resourceBase" value="${activemq.home}/webapps/api" />
 78                                         <property name="logUrlOnStart" value="true" />
 79                                 </bean>
...
## 70-74 line에 hawtio에 대해 등록

# -- hawtio의 권한을 activemq의 권한을 사용하도록 하기 위해 activemq/bin/env 파일에 아래의 내용 추가
# -- env파일에서 `ACTIVEMQ_OPTS`를 찾아서 아래의 3개의 설정을 추가한다.
$ cd /d/poc/mq/apache-activemq-5/bin
$ vi env
39
40 ACTIVEMQ_OPTS="${ACTIVEMQ_OPTS} -Dhawtio.realm=activemq"
41 ACTIVEMQ_OPTS="${ACTIVEMQ_OPTS} -Dhawtio.role=admins"
42 ACTIVEMQ_OPTS="${ACTIVEMQ_OPTS} -Dhawtio.rolePrincipalClasses=org.apache.activemq.jaas.GroupPrincipal"
43 
```
ActiveMQ를 구동하고 `http://localhost:8161/hawtio`로 접속하면 다음과 같은 화면을 확인할 수 있다. 

![activemq-hawtio-web-console.png](/files/214)
 
## 참고문서

  * [Spring 4 MVC + JMS + ActiveMQ annotation based Example](http://websystique.com/springmvc/spring-4-mvc-jms-activemq-annotation-based-example/)
    - [download web-shop application](http://websystique.com/?smd_process_download=1&download_id=2567), [download inventory application](http://websystique.com/?smd_process_download=1&download_id=2569)
  * [Spring Boot Example of Spring Integration and ActiveMQ](https://springframework.guru/spring-boot-example-of-spring-integration-and-activemq/)
  * [Configure a Spring JMS application with Spring Boot and annotation support](https://www.javacodegeeks.com/2015/04/configure-a-spring-jms-application-with-spring-boot-and-annotation-support.html)
    - [github - spring-integration/spring-jms/jms-boot-javaconfig/](https://github.com/xpadro/spring-integration/tree/master/spring-jms/jms-boot-javaconfig)
  * [Using Spring to Receive JMS Message](http://bsnyderblog.blogspot.kr/2010/02/using-spring-to-receive-jms-messages.html)
    - [github - spring-jms-examples](https://github.com/bsnyder/spring-jms-examples)
  * [How to configure embedded ActiveMQ Broker URL with Spring Boot](http://stackoverflow.com/questions/38750843/how-to-configure-embedded-activemq-broker-url-with-spring-boot)
  * [Spring Boot - 32. Messaging](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-messaging.html)
  * [Testing improvements in Spring Boot 1.4](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4)
  * [Spring JMS, Message Automatic Conversion, JMS Template](https://dzone.com/articles/spring-jms-message-automatic)

### Event 기반

  * [Annotation-driven event listeners in Spring 4.2](https://www.javacodegeeks.com/2015/10/annotation-driven-event-listeners-in-spring-4-2-2.html)
  
### Guava 관련

  * [Baeldung - guava](http://www.baeldung.com/category/guava/)
    - [Guava - Lists](http://www.baeldung.com/guava-lists)
    - [Filtering and Transforming Collections in Guava](http://www.baeldung.com/guava-filter-and-transform-a-collection)
    
