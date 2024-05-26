# Project Documentation

## 1. Project Structure

The project follows a standard Spring Boot application structure:

# Project Structure

## src/main/java/com/apricart/task

### controller
- REST API controllers

### model
- Entity classes

### repository
- Spring Data repositories

### service
- Service classes

### TaskApplication.java
- Main Spring Boot application class

## src/main/resources

### application.properties
- Application configuration properties

## 2. API Endpoints

### Cart Controller
- `POST /api/carts`: Add a product to the cart.
- `PUT /api/carts/{id}`: Update a cart item.
- `DELETE /api/carts/{id}`: Remove a cart item.
- `DELETE /api/carts/clear`: Clear the entire cart.
- `GET /api/carts`: Get all cart items.
- `GET /api/carts/total`: Get the total price of items in the cart.

### Coupon Controller
- `POST /api/coupons/validate`: Validate a coupon code.
- `POST /api/coupons/apply`: Apply a coupon to the order.

### Order Controller
- `POST /api/orders`: Place an order.
- `PUT /api/orders/{id}/status`: Update the status of an order.
- `GET /api/orders/user/{userId}`: Get all orders of a user.
- `GET /api/orders/{id}`: Get an order by ID.
- `DELETE /api/orders/{id}`: Cancel an order.
- `GET /api/orders/{id}/total`: Get the total price of an order.

### Product Controller
- `POST /api/products`: Create a new product.
- `PUT /api/products/{id}`: Update a product.
- `DELETE /api/products/{id}`: Delete a product.
- `GET /api/products/{id}`: Get a product by ID.

### Checkout Controller
- `POST /api/checkout`: Process checkout for placing an order.

### Warehouse Controller
- `POST /api/warehouses`: Create a new warehouse.
- `PUT /api/warehouses/{id}`: Update a warehouse.
- `DELETE /api/warehouses/{id}`: Delete a warehouse.
- `GET /api/warehouses/{id}`: Get a warehouse by ID.
- `GET /api/warehouses`: Get all warehouses.

## 3. Usage Instructions

### 1. Start the Application
- Ensure you have Java Development Kit (JDK) installed on your system.
- Open the project in your preferred Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
- Run the `TaskApplication.java` class to start the Spring Boot application.

### 2. Interact with API Endpoints
- Use appropriate HTTP requests (e.g., using tool like Postman) to interact with the provided API endpoints.
- Refer to the API Endpoints section for details on request and response formats for each endpoint.

### 3. Configure Application Properties

- Open the `application.properties` file located in the `src/main/resources` directory.
- Replace placeholders in the properties file with your actual database information:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/your_database?useSSL=false
  spring.datasource.username=your_username
  spring.datasource.password=your_password

Feel free to review the code, run the application, and provide any feedback.
