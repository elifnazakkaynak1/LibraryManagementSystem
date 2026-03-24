# 📚 Library Management System (Part II)

## 📌 Overview

This project is a Java-based library management system developed for SENG 271.
It extends the first phase by supporting multiple material types using **Object-Oriented Programming concepts** such as inheritance, polymorphism, abstract classes, and interfaces.

---

## 🎯 Features

* Supports different material types:

    * Book
    * Magazine
    * DVD
* Borrowing and returning materials
* Reservation system (for books)
* Polymorphic operations using a single collection
* Menu-driven console application

---

## 🧠 OOP Concepts Used

* Inheritance (Material → Book, Magazine, DVD)
* Polymorphism
* Abstract Classes
* Interfaces (Borrowable, Reservable)
* Method Overriding
* Type Casting & instanceof

---

## 🧩 Structure

```
Material (abstract class)
 ├── Book (Borrowable, Reservable)
 ├── Magazine (Borrowable)
 └── DVD

Library → manages all materials (ArrayList<Material>)
Main → user interaction (menu system)
```

---

## ⚙️ Functionalities

* Add new materials
* List all materials
* Filter materials by type
* Borrow / return items
* Reserve / cancel reservation
* Save and load data from file

---

## 🖥️ Example Menu

```
1. Add Book
2. Add Magazine
3. Add DVD
4. List All Materials
5. List by Type
6. Borrow Material
7. Return Material
8. Reserve Material
0. Exit
```

---

## 🚀 Technologies

* Java
* OOP (Inheritance, Polymorphism)
* ArrayList
* File Handling

---

## 👩‍💻 Author

Elif Akkaynak
