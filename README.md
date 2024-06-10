# Ecommerce Microservices Application

## Overview
Welcome to our Ecommerce Microservices Application! This application is built using the microservices architecture, leveraging Spring Boot for robustness and efficiency. Each service is designed to handle specific functionalities of an ecommerce platform, promoting modularity and scalability. Below is a breakdown of the services included in this application:

1. **Customer Service:** Manages customer data using MongoDB as the database.
2. **Discovery Server:** Utilizes Eureka for service discovery within the application.
3. **Notification Server:** Handles notifications using Kafka for messaging, email sending, and MongoDB for storage.
4. **Order Service:** Responsible for managing orders.
5. **Payment Service:** Facilitates payment processing, utilizing PostgreSQL for data storage.
6. **Config Server:** Manages configurations for the application.
7. **Product Service:** Handles products and categories, with data stored in PostgreSQL.
8. **API Gateway:** Acts as a common entry point for all services.

## Technologies Used
- Spring Boot: For building microservices.
- Kafka: For asynchronous messaging.
- MongoDB: For customer data and notifications storage.
- Eureka: For service discovery.
- PostgreSQL: For storing product, category, and payment data.
- OpenFeign and Rest Templates: For communication between services.
- API Gateway: For routing requests to appropriate services.

## Installation and Setup
To run this application locally, follow these steps:

1. **Clone the Repository:** Clone this repository to your local machine.
2. **Setup Kafka:** Install and configure Kafka on your machine.
3. **Setup MongoDB:** Install and configure MongoDB on your machine.
4. **Setup PostgreSQL:** Install and configure PostgreSQL on your machine.
5. **Run Config Server:** Start the Config Server.
6. **Run Eureka Server:** Start the Eureka Server for service discovery.
7. **Run each Service:** Start each microservice individually.
8. **Run API Gateway:** Start the API Gateway to handle incoming requests.

## Configuration
- Configuration files for each service are located in the Config Server. Modify these files as needed for environment-specific configurations.
- Update application.properties or application.yml files in each service with appropriate database and messaging configurations.

## Usage
Once the application is up and running, you can interact with it using RESTful endpoints. Below are some common endpoints:

- Customer Service: `/customers`
- Order Service: `/orders`
- Payment Service: `/payments`
- Product Service: `/products`

## Contributing
Contributions are welcome! If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request on GitHub.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For any inquiries or support, please contact YvesHAKIZIMANA at yvhakizimana123@gmail.com

Happy coding! 🚀