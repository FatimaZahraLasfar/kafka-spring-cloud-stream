# 🚀 Kafka & Spring Cloud Stream

### Event-Driven Processing with Spring Boot, Apache Kafka, and Kafka Streams

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Event%20Streaming-231F20?logo=apachekafka)](https://kafka.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker)](https://www.docker.com/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)](https://maven.apache.org/)

## 📌 Overview

**Kafka & Spring Cloud Stream** is a Java-based project exploring event-driven application development with Spring Boot, Apache Kafka, Spring Cloud Stream, and Kafka Streams.

The project demonstrates how applications can produce, consume, and process events through Kafka topics using Spring's functional programming model and stream-processing capabilities.

By combining Spring Cloud Stream's messaging abstractions with Kafka Streams, the application illustrates how to build asynchronous data flows that reduce direct coupling between event producers and consumers.

The project is also an opportunity to explore message-driven architectures, topic-based communication, and the configuration of Kafka infrastructure using Docker Compose.

## 🎯 Objectives

The main objectives of this project are to:

- Understand the principles of event-driven architecture.
- Integrate Apache Kafka with a Spring Boot application.
- Use Spring Cloud Stream to configure messaging bindings.
- Explore functional components for producing and consuming messages.
- Process streams of events using Kafka Streams.
- Understand how Kafka topics connect different stages of a processing pipeline.
- Run Kafka infrastructure locally with Docker Compose.
- Explore asynchronous communication between application components.

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Spring Boot | Application framework |
| Spring Cloud Stream | Messaging abstraction and binding configuration |
| Apache Kafka | Distributed event streaming and message transport |
| Kafka Streams | Stream processing |
| Spring Cloud Function | Functional application components |
| Maven | Dependency management and build automation |
| Docker Compose | Local Kafka and ZooKeeper infrastructure |
| Lombok | Reducing Java boilerplate code |

## 🏗️ Architecture

The application is configured around functional components connected through Kafka topics.

### Main components

**1. Page Event Component**

The application declares a functional component named `pageEventComponent`, associated with the Kafka destination `T2` through its input binding.

**2. Page Event Supplier**

The `pageEventSupplier` component is configured to publish messages to the Kafka topic `T3`. Its producer poller is configured with a fixed delay of 200 milliseconds.

**3. Kafka Streams Function**

The `kStreamFunction` component reads from `T3` and publishes its output to `T4`. This establishes a stream-processing path between the two topics.

**4. Kafka Broker**

Apache Kafka transports messages between the application and its configured topics. The local broker is defined in the Docker Compose configuration.

**5. ZooKeeper**

The Docker Compose configuration includes ZooKeeper for the configured Kafka broker.

### Event flow

1. The Spring Boot application initializes its configured functional components and messaging bindings.
2. The page event supplier is configured to publish messages to `T3` at the specified polling interval.
3. The Kafka Streams function consumes messages from `T3`.
4. The stream-processing function processes the incoming data according to its implementation and sends output to `T4`.
5. The application also declares the `pageEventComponent` input binding for `T2`.

The exact contents and transformations of the events depend on the Java implementation.

## 📂 Project Structure

```text
kafka-spring-cloud-stream/
├── docker/
├── src/
│   ├── main/
│   │   ├── java/                 # Application source code
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Test source code
├── .mvn/
│   └── wrapper/
├── compose.yml                   # Kafka and ZooKeeper services
├── mvnw                          # Maven Wrapper (Unix/Linux)
├── mvnw.cmd                      # Maven Wrapper (Windows)
├── pom.xml                       # Dependencies and build configuration
└── README.md
```

## ⚙️ Prerequisites

Before running the project, make sure you have:

- **JDK 21** installed.
- **Docker Desktop** or Docker Engine installed and running.
- **Docker Compose** available.
- Git installed.
- An IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code (optional).

Verify your installations:

```bash
java -version
docker --version
docker compose version
```

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/FatimaZahraLasfar/kafka-spring-cloud-stream.git
cd kafka-spring-cloud-stream
```

### 2. Start Kafka infrastructure

Start the Kafka broker and ZooKeeper services defined in `compose.yml`:

```bash
docker compose up -d
```

Check the running containers:

```bash
docker compose ps
```

Inspect the service logs if necessary:

```bash
docker compose logs -f broker
```

The configured Kafka broker exposes port `9092` on the local machine.

### 3. Build the application

On Linux or macOS:

```bash
./mvnw clean package
```

On Windows:

```powershell
.\mvnw.cmd clean package
```

If Maven Wrapper cannot be executed on Linux or macOS, grant the wrapper executable permission:

```bash
chmod +x mvnw
```

### 4. Run the Spring Boot application

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application is configured to use port `8080`.

The application and Kafka broker must both be running for the configured messaging flow to operate.

### 5. Stop the infrastructure

When you finish experimenting, stop the services:

```bash
docker compose down
```

To also remove the containers' associated Compose-managed volumes, if any:

```bash
docker compose down -v
```

Use the second command carefully because it removes associated volumes.

## 🔧 Configuration

The main application configuration is located in:

`src/main/resources/application.properties`

The current configuration includes:

```properties
spring.application.name=kafka-spring-cloud-stream
server.port=8080

spring.cloud.stream.bindings.pageEventComponent-in-0.destination=T2

spring.cloud.stream.bindings.pageEventSupplier-out-0.destination=T3

spring.cloud.stream.bindings.kStreamFunction-in-0.destination=T3
spring.cloud.stream.bindings.kStreamFunction-out-0.destination=T4

spring.cloud.function.definition=pageEventComponent;pageEventSupplier;kStreamFunction

spring.cloud.stream.bindings.pageEventSupplier-out-0.producer.poller.fixed-delay=200

spring.cloud.stream.kafka.streams.binder.configuration.commit.interval.ms=5000
```

### Understanding the configuration

| Property | Explanation |
|---|---|
| `spring.application.name` | Sets the application name. |
| `server.port` | Configures the HTTP server port. |
| `pageEventComponent-in-0.destination` | Associates the component's input binding with `T2`. |
| `pageEventSupplier-out-0.destination` | Routes supplier output to `T3`. |
| `kStreamFunction-in-0.destination` | Configures the stream function's input topic as `T3`. |
| `kStreamFunction-out-0.destination` | Configures the stream function's output topic as `T4`. |
| `spring.cloud.function.definition` | Declares the functional components to activate. |
| `producer.poller.fixed-delay` | Configures a 200 ms delay between supplier polling cycles. |
| `commit.interval.ms` | Configures the Kafka Streams commit interval to 5,000 ms. |

**Important:** The configured polling delay does not necessarily represent the time needed to process an event end to end. Actual behavior depends on the implementation, broker activity, and processing workload.

## 📸 Application Visualization

<img width="601" height="401" alt="Screenshot 2026-10-24 143250" src="https://github.com/user-attachments/assets/fbb09d5b-d568-4b81-a98c-63ff5fcef2d0" />

*Figure 1: Visualization of the Kafka messaging pipeline and event-processing flow.*

## 🧪 Testing

The project includes test-related dependencies for Kafka, Spring MVC, and Spring Cloud Stream's test binder.

Run the tests with Maven:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

The test binder can be useful for testing Spring Cloud Stream components without requiring a live Kafka broker for every test. Actual test coverage depends on the tests implemented in the repository.

## 🐛 Troubleshooting

### Kafka connection errors

- Check that Docker is running.
- Verify that the Kafka container is up with `docker compose ps`.
- Inspect the broker logs using `docker compose logs -f broker`.
- Confirm that the application is connecting to the correct broker address.

For an application running directly on the host, the configured external broker address is `localhost:9092`. Applications running inside Docker may need to use the appropriate internal service address instead.

### Port already in use

If port `9092` or `8080` is occupied, stop the conflicting service or adjust the relevant port configuration.

### Maven build errors

- Verify that JDK 21 is installed.
- Check the output of `java -version`.
- Ensure that Maven dependencies can be downloaded.
- Review the complete Maven error message before changing dependency versions.

### Messages are not appearing

- Verify the topic names: `T2`, `T3`, and `T4`.
- Check the application logs.
- Confirm that the functional components are declared and activated.
- Verify that the Kafka Streams function is correctly implemented and connected to the expected bindings.

## 📚 Learning Resources

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Spring Cloud Stream Documentation](https://docs.spring.io/spring-cloud-stream/reference/)
- [Spring Cloud Stream Kafka Binder](https://docs.spring.io/spring-cloud-stream/reference/kafka/kafka-binder/usage.html)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
- [Docker Compose Documentation](https://docs.docker.com/compose/)

## 🌱 Future Improvements

Possible extensions to explore include:

- Adding a Kafka web interface to visualize topics and messages.
- Implementing more advanced event transformations.
- Adding integration tests with a real Kafka broker.
- Exploring consumer groups and partitioning.
- Introducing structured logging and error handling.
- Adding monitoring and observability.
- Containerizing the Spring Boot application alongside Kafka.
- Documenting example event payloads and expected processing results.

These are potential extensions rather than claims about existing functionality.

## 👩‍💻 Author

**Fatima Zahra Lasfar**

- GitHub: [@FatimaZahraLasfar](https://github.com/FatimaZahraLasfar)
- Portfolio: [My Portfolio](https://fatimazahralasfar.github.io/My-Portfolio/)

## 🙏 Acknowledgements

This project was developed as a learning opportunity to explore Java-based event-driven architectures and messaging systems.

Special thanks to the teams behind Apache Kafka, Spring Boot, Spring Cloud Stream, and the open-source community for providing the tools and documentation that make these technologies accessible to developers.

Thank you for visiting this repository and taking the time to explore the project. Your feedback, suggestions, and contributions are always welcome!

**Thanks in advance for your interest and support.** ⭐
