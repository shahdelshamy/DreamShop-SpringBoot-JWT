# 🛍️ E-Commerce Project with Spring Boot and Spring Security

This project is a RESTful e-commerce application built using Spring Boot and secured with Spring Security. The application supports two roles: **Admin** and **User**. Admins can manage products and users, while Users can browse products, add items to their cart, and place orders.

## 📋 Table of Contents
- [✨ Features](#-features)
- [🔗 Endpoints](#-endpoints)
  - [📦 Product](#product)
  - [🖼️ Image](#image)
  - [📁 Category](#category)
  - [🛒 Cart Items](#cart-items)
  - [🛍️ Carts](#carts)
  - [📑 Order](#order)
  - [👤 User](#user)
  - [🔐 Login](#login)
- [🛠️ Usage Flow](#usage-flow)
- [🚀 Setup](#setup)
- [🎥 Video Demo](#video-demo)

## ✨ Features
- **User Roles**: Users can register, log in, create a cart, and place orders. Admins can add, update, or delete products.
- **🔒 JWT Authentication**: Users and Admins authenticate via JWT tokens.
- **📦 Product Management**: Admins can manage products by category, brand, and more.
- **🛒 Cart and Order Management**: Users can add items to a cart and place orders. Upon ordering, items in the cart are cleared.
- **🖼️ Image Management**: Product images can be uploaded, downloaded, or deleted.
- **🔗 Modular Endpoints**: Organized endpoints for products, images, categories, cart items, and more.

## 🔗 Endpoints

### 📦 Product
- `GET /products` - Get all products
- `GET /products/name` - Get products by name
- `GET /products/id` - Get product by ID
- `GET /products/category` - Get products by category
- `GET /products/brand` - Get products by brand
- `GET /products/category-brand` - Get products by category and brand
- `GET /products/name-brand` - Get products by name and brand
- `GET /products/count` - Get product count
- `GET /products/count/brand` - Get product count by brand
- `GET /products/count/category` - Get product count by category
- `POST /products` - Add a new product (Admin only)
- `PUT /products` - Update a product (Admin only)
- `DELETE /products` - Delete a product (Admin only)

### 🖼️ Image
- `POST /images` - Upload an image
- `GET /images` - Download image
- `GET /images/product-id` - Get image by product ID
- `DELETE /images` - Delete an image

### 📁 Category
_(Endpoints for managing categories if implemented)_

### 🛒 Cart Items
- `POST /cart-items` - Add item to cart
- `PUT /cart-items` - Update cart item
- `GET /cart-items` - Get cart items
- `DELETE /cart-items` - Delete a cart item

### 🛍️ Carts
- `GET /carts` - Retrieve cart
- `GET /carts/total-price` - Get total price of cart
- `DELETE /carts` - Delete cart
- `DELETE /carts/clear` - Clear cart

### 📑 Order
- `POST /orders` - Place an order
- `GET /orders/id` - Get order by ID
- `GET /orders/user-id` - Get orders by user ID

### 👤 User
- `GET /users/id` - Get user by ID
- `DELETE /users` - Delete user (Admin only)
- `POST /users` - Add new user
- `PUT /users` - Update user

### 🔐 Login
- `POST /login` - User/Admin login

## 🛠️ Usage Flow

1. **Login** 🔐: Users and Admins authenticate by logging in. The server responds with a JWT token.
2. **Product Browsing and Cart Management** 🛒:
   - **User**: Browse products, add desired items to the cart.
   - **Admin**: Can manage products (add, update, delete).
3. **Placing an Order** 📑:
   - The user proceeds to place an order with items in the cart.
   - After the order is placed, items in the cart are automatically cleared.

## 🚀 Setup

1. **Clone the repository**.
2. **Configure Database** in `application.properties`.
3. **Run the application**: `mvn spring-boot:run`.
4. **Access API Documentation**: Use tools like Postman to interact with the endpoints.

## 🎥 Video Demo

To see the project in action, [click here to watch the demo video](https://drive.google.com/file/d/1HcDmFcQnMpsQlGdUIl85bNpk3FhmXE0N/view?usp=drive_link).
