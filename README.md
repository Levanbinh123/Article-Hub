# Social Media Project

## Giới thiệu
Đây là một ứng dụng REST API xây dựng bằng Spring Boot, cho phép quản lý bài viết, danh mục, và người dùng ứng dụng. Hệ thống hỗ trợ tạo tài khoản, đăng nhập, cập nhật trạng thái, và quản lý nội dung.
---
##  Công nghệ sử dụng
- **Java 17+** – Ngôn ngữ chính  
- **Spring Boot** – Framework backend  
- **JPA / Hibernate** – ORM Mapping  
- **H2 / MySQL** – Cơ sở dữ liệu  
- **Lombok** – Tự động generate code  
- **Maven** – Quản lý thư viện  
- **Postman** – Test API  
- **JWT** – Spring Security
- **SLF4J (Ghi log)**
---
## Cấu trúc thư mục
```bash
├── src/main/java
│   └── com/example
│       ├── controller/
│       ├── model/
│       ├── repository/
│       └── service/
├── src/main/resources
│   └── application.properties
├── PTTK/                  # Chứa sơ đồ phân tích thiết kế và tài liệu REST-API
├── pom.xml                # File cấu hình Maven
└── README.md              # File này
## Tải dự án
https://github.com/Levanbinh123/Article-Hub.git
cd social_media
## cấu hình database
Tạo database tên: Article_Hub_Project
Mở file src/main/resources/application.properties và chỉnh sửa username và password tương ứng với MySQL của bạn:
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/Article_Hub_Project
spring.datasource.username=spring
spring.datasource.password=spring
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
## start dự án
Mở project bằng IDE (IntelliJ hoặc Eclipse)
Chạy class chính (có @SpringBootApplication) – ví dụ: SocialMediaPjApplication.java
