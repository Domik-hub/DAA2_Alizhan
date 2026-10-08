# DAA Assignment 2

Alizhan Alikhanov 

## Overview
This project implements fundamental data structures from scratch (without using built-in Java collections), collects low-level performance metrics (`steps`, `moves`, `comparisons`, `time_ms`), and provides a comprehensive comparative analysis of their efficiency and Big-O complexity.

## Project Structure
* `pom.xml` - Maven configuration file
* `src/main/java/DynamicArray.java` - Dynamic array implementation with automatic capacity doubling
* `src/main/java/MyLinkedList.java` - Doubly linked list implementation with optimized bidirectional traversal
* `src/main/java/MinHeap.java` - Minimum heap implementation based on an array
* `src/main/java/Benchmark.java` - Performance benchmarking script and metrics exporter
* `src/test/java/DataStructuresTest.java` - JUnit 5 unit tests for correctness verification
* `results/results.csv` - Collected raw benchmark data

---

## Prerequisites
* Java Development Kit (JDK) 17 or higher
* Apache Maven

---

## Build and Execution Guide

### 1. Run Unit Tests (JUnit 5)
To verify correctness and test all edge cases across data structures, run:
```bash
mvn clean test
