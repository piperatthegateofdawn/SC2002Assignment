# SC2002Assignment
# 📋 Hospital Management System (HMS)

Welcome to the Hospital Management System (HMS) project! This command-line application is designed to streamline hospital operations, providing a seamless and efficient experience for patients, doctors, pharmacists, and administrators. Built with Object-Oriented Programming (OOP) principles, HMS encapsulates complex workflows into modular, maintainable components.

---

## 🚀 Project Objective

Our primary goal is to enhance hospital efficiency by automating routine tasks, including:

- 📅 Appointment Scheduling
- 🧑‍⚕️ Patient Management
- 🏥 Staff Administration
- 💊 Inventory Control

By centralizing these operations, HMS offers a unified platform for managing hospital functionalities effectively.

---

## 🛠 Key Features

### 🎯 Role-Specific Functionalities
1. Patients: Schedule, reschedule, and cancel appointments, view medical records.
2. Doctors: Manage availability, view schedules, update patient records.
3. Pharmacists: Track prescriptions, manage inventory, request replenishments.
4. Administrators: Oversee staff and inventory, approve requests.

### 🔒 Enhanced Security
- Two-Factor Authentication (2FA) ensures secure login using OTP verification.

### ⏰ Flexible Scheduling
- Appointment slots adhere to hospital hours (9 AM to 6 PM, Mon-Sat), with constraints to prevent past or far-future bookings.

---

## 📐 Design Principles

HMS follows the SOLID OOP Principles for scalability and maintainability:

- Single Responsibility Principle (SRP): Role-specific classes focus on dedicated tasks.
- Open-Closed Principle (OCP): Extend functionalities without modifying existing code.
- Liskov Substitution Principle (LSP): Subclasses can replace parent classes without breaking functionality.
- Interface Segregation Principle (ISP): Clients only interact with methods they require.
- Dependency Inversion Principle (DIP): High- and low-level modules depend on abstractions.

---

## 📂 Architecture Overview

- Encapsulation: Core entities manage sensitive data securely through controlled access.
- Abstraction: Complex operations are simplified into service classes.
- Polymorphism: Unified interfaces for role-specific workflows.
- Persistent Data: CSV-based storage ensures seamless runtime updates and data integrity.

---

## 🔍 Testing and Results

Comprehensive testing confirmed the reliability and robustness of HMS functionalities, including:

- 🧑‍⚕️ Doctors: Manage schedules, update patient records.
- 🩺 Patients: Schedule and reschedule appointments.
- 💊 Pharmacists: Dispense prescriptions, track inventory.
- 🏥 Administrators: Oversee staff and inventory.

Minor bugs were identified and resolved during debugging.

---

## 💡 Lessons Learned

- The significance of modular design in complex applications.
- The importance of validation and error handling for robust CLI interactions.
- Strategies for ensuring seamless role transitions.

### 📈 Future Enhancements
- Add billing and reporting modules.
- Integrate a graphical user interface (GUI) for better user experience.
- Implement unit testing for enhanced reliability.

---

## 👩‍💻 Meet the Team

- Tang Yeqing  
- Pahwa Ronak  
- Verma Shireen  
- Ramakrishna Rohan  

Course: SC2002 | Lab Group: SCSD  

For more details, check out the [GitHub Repository](https://github.com/piperatthegateofdawn/SC2002Assignment) and explore our project! 

---

Dive into our HMS project and experience how OOP principles come alive in a real-world application. 🌟
