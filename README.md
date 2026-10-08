# DAA Assignment 2
Alizhan Alikhanov
#Group: SE-2523

## Project Structure

DAA_Assignment2/
├── pom.xml
├── README.md
├── REPORT.md
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── DynamicArray.java
│   │       ├── MyLinkedList.java
│   │       ├── MinHeap.java
│   │       └── Benchmark.java
│   └── test/
│       └── java/
│           └── DataStructuresTest.java
└── results/
    ├── results.csv
    └── generate_plots.py

## Prerequisites
* Java Development Kit (JDK) version 17 or higher.
* Apache Maven for build automation and dependency management.
* Python 3 with pandas and matplotlib libraries installed (for plot generation).

---

## Build and Execution Guide

### 1. Run Unit Tests (JUnit 5)
To verify correctness across all data structures, run:
mvn clean test

### 2. Run Benchmark & Generate Data
To execute workload simulations and create results.csv, run:
mvn compile exec:java -Dexec.mainClass="Benchmark"
