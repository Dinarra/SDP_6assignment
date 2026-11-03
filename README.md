# Visitor Pattern - In-Memory File System

Assignment #6, ShP-2216 Software Design Patterns (Java 17, Maven, JUnit 5).

## Description
An in-memory file system (files and directories) with three independent operations
implemented as visitors: total size, search, and statistics.

## Structure
src/main/java/filesystem/
- Element, Visitor - interfaces
- FileNode, DirectoryNode - elements
- TotalSizeVisitor, SearchVisitor, StatisticsVisitor - operations
- Main - demo

src/test/java/filesystem/ - JUnit 5 tests

## Run the tests
    mvn test

## Run the application
    mvn compile
    java -cp target/classes filesystem.Main

(Or run Main in IntelliJ IDEA.)