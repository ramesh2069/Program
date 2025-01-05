# Oracle SQL Cheat Sheet

## Data Definition Language (DDL)
### CREATE
**Syntax:**
```sql
CREATE TABLE table_name (
    column1 datatype [constraint],
    column2 datatype [constraint],
    ...
);
```
**Example:**
```sql
CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    hire_date DATE
);
```

### ALTER
**Syntax:**
```sql
ALTER TABLE table_name
ADD column_name datatype;

ALTER TABLE table_name
MODIFY column_name datatype;

ALTER TABLE table_name
DROP COLUMN column_name;
```
**Example:**
```sql
ALTER TABLE employees ADD salary NUMBER;
ALTER TABLE employees MODIFY salary NUMBER(10, 2);
ALTER TABLE employees DROP COLUMN hire_date;
```

### DROP
**Syntax:**
```sql
DROP TABLE table_name;
```
**Example:**
```sql
DROP TABLE employees;
```

### RENAME
**Syntax:**
```sql
RENAME old_table_name TO new_table_name;
```
**Example:**
```sql
RENAME employees TO staff;
```

### TRUNCATE
**Syntax:**
```sql
TRUNCATE TABLE table_name;
```
**Example:**
```sql
TRUNCATE TABLE employees;
```

## Data Manipulation Language (DML)
### INSERT
**Syntax:**
```sql
INSERT INTO table_name (column1, column2, ...)
VALUES (value1, value2, ...);
```
**Example:**
```sql
INSERT INTO employees (emp_id, name, hire_date)
VALUES (1, 'John Doe', SYSDATE);
```

### UPDATE
**Syntax:**
```sql
UPDATE table_name
SET column1 = value1, column2 = value2
WHERE condition;
```
**Example:**
```sql
UPDATE employees
SET salary = 50000
WHERE emp_id = 1;
```

### DELETE
**Syntax:**
```sql
DELETE FROM table_name
WHERE condition;
```
**Example:**
```sql
DELETE FROM employees WHERE emp_id = 1;
```

### SELECT
**Syntax:**
```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```
**Examples:**
- Selecting all columns:
  ```sql
  SELECT * FROM employees;
  ```
- Selecting specific columns:
  ```sql
  SELECT name, salary FROM employees;
  ```
- Column alias:
  ```sql
  SELECT name AS "Employee Name" FROM employees;
  ```

## Data Control Language (DCL)
### GRANT
**Syntax:**
```sql
GRANT privilege_name ON object_name TO user_name;
```
**Example:**
```sql
GRANT SELECT ON employees TO hr_user;
```

### REVOKE
**Syntax:**
```sql
REVOKE privilege_name ON object_name FROM user_name;
```
**Example:**
```sql
REVOKE SELECT ON employees FROM hr_user;
```

### ROLES
**Syntax:**
```sql
GRANT role_name TO user_name;
REVOKE role_name FROM user_name;
```
**Example:**
```sql
GRANT dba TO admin_user;
REVOKE dba FROM admin_user;
```

## SQL SELECT Statements
### ORDER BY Clause
**Syntax:**
```sql
SELECT column1, column2
FROM table_name
ORDER BY column_name [ASC|DESC];
```
**Example:**
```sql
SELECT name, salary FROM employees ORDER BY salary DESC;
```

## Functions
### Single Row Functions
#### Character Functions
**Example:**
```sql
SELECT UPPER(name), LOWER(name) FROM employees;
```
#### Number Functions
**Example:**
```sql
SELECT ROUND(salary, 2), TRUNC(salary, 1) FROM employees;
```
#### Date Functions
**Example:**
```sql
SELECT SYSDATE, ADD_MONTHS(hire_date, 6) FROM employees;
```
#### General Functions
**Example:**
```sql
SELECT NVL(salary, 0) FROM employees;
```

## Constraints
### Adding Constraints
**Syntax:**
```sql
CREATE TABLE table_name (
    column_name datatype [constraint]
);
```
**Example:**
```sql
CREATE TABLE departments (
    dept_id NUMBER PRIMARY KEY,
    dept_name VARCHAR2(50) NOT NULL
);
```
### Dropping a Constraint
**Syntax:**
```sql
ALTER TABLE table_name DROP CONSTRAINT constraint_name;
```

## Joins
### Equijoins
**Example:**
```sql
SELECT e.name, d.dept_name
FROM employees e
JOIN departments d ON e.dept_id = d.dept_id;
```

### Left Outer Join
**Example:**
```sql
SELECT e.name, d.dept_name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.dept_id;
```

### Cross Join
**Example:**
```sql
SELECT e.name, d.dept_name
FROM employees e, departments d;
```

## Sequence, View, Index, Synonyms
### Sequence
**Example:**
```sql
CREATE SEQUENCE emp_seq START WITH 1 INCREMENT BY 1;
```
### View
**Example:**
```sql
CREATE VIEW emp_view AS
SELECT name, salary FROM employees;
```
### Index
**Example:**
```sql
CREATE INDEX idx_name ON employees(name);
```
### Synonyms
**Example:**
```sql
CREATE SYNONYM emp_syn FOR employees;
```
Here’s a detailed breakdown of SQL joins for Oracle, with syntax and examples:

---

## SQL Joins in Oracle

### 1. **Equijoins**
**Definition:** Combines rows that have matching values in both tables.
**Syntax:**
```sql
SELECT column1, column2
FROM table1 t1
JOIN table2 t2
ON t1.common_column = t2.common_column;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name
FROM employees
JOIN departments
ON employees.dept_id = departments.dept_id;
```

---

### 2. **Non-Equijoins**
**Definition:** Combines rows that meet a non-equal condition.
**Syntax:**
```sql
SELECT column1, column2
FROM table1 t1
JOIN table2 t2
ON t1.column1 < t2.column2;
```
**Example:**
```sql
SELECT employees.name, salary_grades.grade
FROM employees
JOIN salary_grades
ON employees.salary BETWEEN salary_grades.min_salary AND salary_grades.max_salary;
```

---

### 3. **Self Joins**
**Definition:** Joins a table to itself.
**Syntax:**
```sql
SELECT t1.column1, t2.column2
FROM table_name t1
JOIN table_name t2
ON t1.common_column = t2.common_column;
```
**Example:**
```sql
SELECT e1.name AS Employee, e2.name AS Manager
FROM employees e1
JOIN employees e2
ON e1.manager_id = e2.emp_id;
```

---

### 4. **Left Outer Join**
**Definition:** Retrieves all rows from the left table, with matching rows from the right table or NULL if no match exists.
**Syntax:**
```sql
SELECT column1, column2
FROM table1 t1
LEFT JOIN table2 t2
ON t1.common_column = t2.common_column;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name
FROM employees
LEFT JOIN departments
ON employees.dept_id = departments.dept_id;
```

---

### 5. **Right Outer Join**
**Definition:** Retrieves all rows from the right table, with matching rows from the left table or NULL if no match exists.
**Syntax:**
```sql
SELECT column1, column2
FROM table1 t1
RIGHT JOIN table2 t2
ON t1.common_column = t2.common_column;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name
FROM employees
RIGHT JOIN departments
ON employees.dept_id = departments.dept_id;
```

---

### 6. **Full Outer Join**
**Definition:** Retrieves all rows when there is a match in either table, filling unmatched rows with NULLs.
**Syntax:**
```sql
SELECT column1, column2
FROM table1 t1
FULL OUTER JOIN table2 t2
ON t1.common_column = t2.common_column;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name
FROM employees
FULL OUTER JOIN departments
ON employees.dept_id = departments.dept_id;
```

---

### 7. **Cross Join**
**Definition:** Produces a Cartesian product of two tables.
**Syntax:**
```sql
SELECT column1, column2
FROM table1
CROSS JOIN table2;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name
FROM employees
CROSS JOIN departments;
```

---

### 8. **Natural Join**
**Definition:** Joins tables based on columns with the same name and datatype.
**Syntax:**
```sql
SELECT column1, column2
FROM table1
NATURAL JOIN table2;
```
**Example:**
```sql
SELECT name, dept_name
FROM employees
NATURAL JOIN departments;
```

---

### 9. **Joining Three or More Tables**
**Definition:** Combines three or more tables based on matching conditions.
**Syntax:**
```sql
SELECT column1, column2, column3
FROM table1 t1
JOIN table2 t2 ON t1.common_column = t2.common_column
JOIN table3 t3 ON t2.another_column = t3.another_column;
```
**Example:**
```sql
SELECT employees.name, departments.dept_name, locations.city
FROM employees
JOIN departments ON employees.dept_id = departments.dept_id
JOIN locations ON departments.location_id = locations.location_id;
```

