# 🐾 Pet Adoption & Care Tracker

A full-stack web application for managing pet adoptions, tracking vaccinations, feeding schedules, and veterinary appointments. Built with Spring Boot (Java), React, and MySQL.

## 📋 Features

### Core Features
- **Pet Adoption Management**
  - Browse available pets for adoption
  - Filter pets by type, breed, age, and location
  - View detailed pet profiles with photos and descriptions
  - Mark pets as adopted

- **Vaccination Tracking**
  - Record vaccination history for each pet
  - Set reminders for upcoming vaccinations
  - Track vaccination status and dates

- **Feeding Schedules**
  - Create and manage feeding schedules
  - Set feeding times and portions
  - Track feeding history and notes

- **Vet Appointments**
  - Schedule veterinary appointments
  - Track appointment history
  - Set reminders for upcoming visits

## 🛠️ Tech Stack

### Backend
- **Framework:** Spring Boot 3.x
- **Language:** Java 17+
- **Database:** MySQL 8.0
- **ORM:** Spring Data JPA / Hibernate
- **API:** RESTful APIs

### Frontend
- **Framework:** React 18
- **UI Library:** Material-UI (MUI) / Custom components
- **State Management:** React Context / Redux
- **HTTP Client:** Axios
- **Build Tool:** Vite

### Development Tools
- **Version Control:** Git
- **IDE:** IntelliJ IDEA / VS Code
- **Database Client:** MySQL Workbench

## 📦 Prerequisites

- Java Development Kit (JDK) 17 or higher
- Node.js 18+ and npm
- MySQL Server 8.0+
- Maven 3.8+

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd pet-adoption-tracker
```

### 2. Backend Setup

#### Configure MySQL Database

Create a new MySQL database:

```sql
CREATE DATABASE pet_adoption_db;
```

#### Update Application Properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pet_adoption_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

#### Run Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:5173`

## 📁 Project Structure

```
pet-adoption-tracker/
├── backend/                    # Spring Boot Application
│   ├── src/main/java/
│   │   └── com/petadoption/
│   │       ├── config/        # Configuration classes
│   │       ├── controller/    # REST Controllers
│   │       ├── entity/        # JPA Entities
│   │       ├── repository/    # Data Repositories
│   │       ├── service/       # Business Logic
│   │       └── dto/            # Data Transfer Objects
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── frontend/                   # React Application
│   ├── src/
│   │   ├── components/        # Reusable components
│   │   ├── pages/             # Page components
│   │   ├── services/          # API services
│   │   ├── context/           # React context
│   │   └── utils/             # Utility functions
│   ├── package.json
│   └── vite.config.js
│
└── README.md
```

## 🔌 API Endpoints

### Pets
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/pets` | Get all pets |
| GET | `/api/pets/{id}` | Get pet by ID |
| POST | `/api/pets` | Create new pet |
| PUT | `/api/pets/{id}` | Update pet |
| DELETE | `/api/pets/{id}` | Delete pet |
| GET | `/api/pets/adoptable` | Get adoptable pets |

### Vaccinations
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/pets/{petId}/vaccinations` | Get pet vaccinations |
| POST | `/api/pets/{petId}/vaccinations` | Add vaccination |
| PUT | `/api/vaccinations/{id}` | Update vaccination |
| DELETE | `/api/vaccinations/{id}` | Delete vaccination |

### Feeding Schedules
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/pets/{petId}/feeding-schedules` | Get feeding schedules |
| POST | `/api/pets/{petId}/feeding-schedules` | Create schedule |
| PUT | `/api/feeding-schedules/{id}` | Update schedule |
| DELETE | `/api/feeding-schedules/{id}` | Delete schedule |

### Vet Appointments
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/pets/{petId}/appointments` | Get appointments |
| POST | `/api/pets/{petId}/appointments` | Create appointment |
| PUT | `/api/appointments/{id}` | Update appointment |
| DELETE | `/api/appointments/{id}` | Delete appointment |

## 🗄️ Database Schema

### Tables

#### pets
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| name | VARCHAR(100) | Pet name |
| species | VARCHAR(50) | Dog, Cat, etc. |
| breed | VARCHAR(100) | Breed |
| age | INT | Age in months |
| gender | VARCHAR(20) | Male/Female |
| description | TEXT | Pet description |
| image_url | VARCHAR(255) | Photo URL |
| is_adopted | BOOLEAN | Adoption status |
| location | VARCHAR(100) | Location |
| created_at | TIMESTAMP | Creation date |
| updated_at | TIMESTAMP | Update date |

#### vaccinations
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| pet_id | BIGINT | Foreign key to pets |
| name | VARCHAR(100) | Vaccine name |
| date_administered | DATE | Date given |
| next_due_date | DATE | Next dose date |
| veterinarian | VARCHAR(100) | Vet name |
| notes | TEXT | Additional notes |

#### feeding_schedules
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| pet_id | BIGINT | Foreign key to pets |
| food_type | VARCHAR(100) | Food type |
| portion_size | VARCHAR(50) | Portion amount |
| feeding_time | TIME | Scheduled time |
| frequency | VARCHAR(50) | Daily/Weekly |
| notes | TEXT | Additional notes |

#### vet_appointments
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| pet_id | BIGINT | Foreign key to pets |
| appointment_date | DATETIME | Scheduled date |
| veterinarian | VARCHAR(100) | Vet name |
| clinic | VARCHAR(100) | Clinic name |
| reason | VARCHAR(255) | Visit reason |
| status | VARCHAR(20) | Scheduled/Completed/Cancelled |
| notes | TEXT | Additional notes |

## 🔧 Environment Variables

### Backend
```env
DB_USERNAME=root
DB_PASSWORD=your_password
SERVER_PORT=8080
```

### Frontend
```env
VITE_API_BASE_URL=http://localhost:8080/api
```

## 📱 Screenshots

> Add screenshots of your application here

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- Thanks to all contributors
- Inspiration from various pet adoption platforms
