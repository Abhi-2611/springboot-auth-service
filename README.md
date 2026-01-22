# 🏫 School Management System (RLS + SMS)

A backend **School Management System** built using **Spring Boot**, designed with **JWT authentication**, **role-based access control**, and **real-world academic workflows**.

This project started as a **Registration & Login System (RLS)** and was later extended into a **School Management System (SMS)**.

---

## 🚀 Tech Stack

- Java 17  
- Spring Boot  
- Spring Security (JWT)  
- Spring Data JPA  
- PostgreSQL  
- Lombok  
- Maven  

---

## 🔐 Authentication & Authorization

- JWT-based authentication
- Role-based access control enforced at:
  - Controller level
  - Service layer (business rules)

### Supported Roles
- **ADMIN**
- **PRINCIPAL**
- **TEACHER**
- **USER** (base role)

---

## 🧩 Core Modules

### 1️⃣ Registration & Login System (RLS)
- User login using JWT
- Role-based access
- Logout with token invalidation
- Global exception handling

---

### 2️⃣ Subject Management
- Create and update subject master (Admin)
- Active / inactive subject handling

---

### 3️⃣ Teacher–Class–Subject Mapping
- Assign subjects to teachers per class (Admin)
- View mappings:
  - Admin – all mappings
  - Principal – read-only
  - Teacher – own assignments
- Duplicate mapping prevention

---

### 4️⃣ Attendance Module

#### 👨‍🏫 Teacher APIs
- Mark attendance (Present / Absent)
- View class-wise attendance (date-wise)

#### 🎓 Principal APIs
- View attendance by class and date (read-only)

#### 🛠 Admin APIs
- Correct attendance entries

---

### 🔒 Attendance Controls
- Attendance **date locking** to prevent late/backdated updates
- **Automatic attendance day closure** using scheduled jobs
- Teachers and principals cannot modify closed attendance
- Admin retains override capability

---

### 5️⃣ Attendance Reports

- Student Monthly Attendance Report
- Class-wise Monthly Attendance Summary
- Low Attendance Report (configurable threshold)
- Student Attendance History (date-wise)

All reports are:
- Read-only
- Role-restricted
- Derived from attendance data

---

## 🏗 Design Highlights

- Clean separation:
  - Controller → Service → Repository
- Business rules enforced in **service layer**
- Soft delete using `activeFlag`
- Common lifecycle fields via `Auditable` base class
- No hard deletes for academic data
- ID-based APIs (no unnecessary joins)

---

## 📌 Project Status

✔ Subject Mapping – Completed  
✔ Attendance Module – Completed  
✔ Attendance Reports – Completed  
⏳ Pagination – Planned  
⏳ Marks / Exam Module – Planned  

---

## 🧠 Learning Outcomes

- Role-based backend design
- JWT-based authentication
- Real-world school workflows
- Attendance management and reporting
- Clean and maintainable Spring Boot architecture

---

## 🔜 Future Enhancements

- Marks & Exam Management module  
- Promotion eligibility logic  
- Pagination and export (CSV / Excel)  
- Notification system  
- Dashboard aggregation APIs  
- **Frontend implementation using React for Admin, Principal, and Teacher dashboards**

---

## 👤 Author

**Abhishek**  
Backend Developer | Java | Spring Boot
