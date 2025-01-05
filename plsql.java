Here is Part 1 of your PL/SQL cheat sheet, focusing on the foundational concepts and control structures. I'll split the content into manageable sections. Let me know if you'd like me to continue with the rest!

---

# PL/SQL Cheat Sheet (Part 1)

## PL/SQL Basics

### **Variables**
**Syntax:**
```plsql
DECLARE
    variable_name datatype [NOT NULL] [:= value];
```
**Example:**
```plsql
DECLARE
    v_emp_id NUMBER := 1001;
    v_name VARCHAR2(50);
BEGIN
    v_name := 'John Doe';
    DBMS_OUTPUT.PUT_LINE('Employee ID: ' || v_emp_id || ', Name: ' || v_name);
END;
```

---

### **Identifiers**
- Must start with a letter.
- Can include letters, digits, and underscores.
- Case-insensitive (e.g., `My_Var` is the same as `my_var`).

---

### **Comments**
**Single-line comment:**
```plsql
-- This is a single-line comment
```
**Multi-line comment:**
```plsql
/* This is a
   multi-line comment */
```

---

### **PL/SQL Block Structure**
**Syntax:**
```plsql
DECLARE
    -- Declarations (optional)
BEGIN
    -- Executable statements (mandatory)
EXCEPTION
    -- Exception handling (optional)
END;
```
**Example:**
```plsql
DECLARE
    v_number NUMBER := 10;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Value: ' || v_number);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred.');
END;
```

---

## Control Structures: IF Statements

### **Simple IF Statement**
**Syntax:**
```plsql
IF condition THEN
    -- Statements
END IF;
```
**Example:**
```plsql
DECLARE
    v_salary NUMBER := 5000;
BEGIN
    IF v_salary > 4000 THEN
        DBMS_OUTPUT.PUT_LINE('High Salary');
    END IF;
END;
```

---

### **Compound IF Statement**
**Syntax:**
```plsql
IF condition1 THEN
    -- Statements for condition1
ELSIF condition2 THEN
    -- Statements for condition2
ELSE
    -- Statements for default case
END IF;
```
**Example:**
```plsql
DECLARE
    v_salary NUMBER := 3000;
BEGIN
    IF v_salary > 5000 THEN
        DBMS_OUTPUT.PUT_LINE('Very High Salary');
    ELSIF v_salary > 4000 THEN
        DBMS_OUTPUT.PUT_LINE('High Salary');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Average Salary');
    END IF;
END;
```

---

### **IF-THEN-ELSE Statement**
**Syntax:**
```plsql
IF condition THEN
    -- Statements if condition is true
ELSE
    -- Statements if condition is false
END IF;
```
**Example:**
```plsql
DECLARE
    v_bonus NUMBER;
    v_salary NUMBER := 4500;
BEGIN
    IF v_salary > 4000 THEN
        v_bonus := 1000;
    ELSE
        v_bonus := 500;
    END IF;
    DBMS_OUTPUT.PUT_LINE('Bonus: ' || v_bonus);
END;
```

---

## Loops

### **Basic Loop**
**Syntax:**
```plsql
LOOP
    -- Statements
    EXIT WHEN condition;
END LOOP;
```
**Example:**
```plsql
DECLARE
    v_counter NUMBER := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE('Counter: ' || v_counter);
        v_counter := v_counter + 1;
        EXIT WHEN v_counter > 5;
    END LOOP;
END;
```

---

### **WHILE Loop**
**Syntax:**
```plsql
WHILE condition LOOP
    -- Statements
END LOOP;
```
**Example:**
```plsql
DECLARE
    v_counter NUMBER := 1;
BEGIN
    WHILE v_counter <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE('Counter: ' || v_counter);
        v_counter := v_counter + 1;
    END LOOP;
END;
```

---

### **FOR Loop**
**Syntax:**
```plsql
FOR loop_variable IN lower_bound..upper_bound LOOP
    -- Statements
END LOOP;
```
**Example:**
```plsql
BEGIN
    FOR i IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE('Iteration: ' || i);
    END LOOP;
END;
```

---

## Transaction Control

### **COMMIT**
**Syntax:**
```plsql
COMMIT;
```
**Example:**
```plsql
BEGIN
    INSERT INTO employees (emp_id, name, salary) VALUES (101, 'Alice', 5000);
    COMMIT;
END;
```

---

### **ROLLBACK**
**Syntax:**
```plsql
ROLLBACK;
```
**Example:**
```plsql
BEGIN
    DELETE FROM employees WHERE emp_id = 101;
    ROLLBACK;
END;
```

---

### **SAVEPOINT**
**Syntax:**
```plsql
SAVEPOINT savepoint_name;
ROLLBACK TO savepoint_name;
```
**Example:**
```plsql
BEGIN
    INSERT INTO employees (emp_id, name) VALUES (102, 'Bob');
    SAVEPOINT sp1;

    INSERT INTO employees (emp_id, name) VALUES (103, 'Charlie');
    ROLLBACK TO sp1;

    COMMIT;
END;
```

---

Here’s **Part 2** of your PL/SQL cheat sheet, focusing on DML operations, cursors, and exceptions.

---

# PL/SQL Cheat Sheet (Part 2)

## DML Operations Using PL/SQL

### **INSERT**
**Syntax:**
```plsql
BEGIN
    INSERT INTO table_name (column1, column2, ...)
    VALUES (value1, value2, ...);
END;
```
**Example:**
```plsql
BEGIN
    INSERT INTO employees (emp_id, name, salary)
    VALUES (104, 'Diana', 6000);
    DBMS_OUTPUT.PUT_LINE('Record inserted.');
END;
```

---

### **UPDATE**
**Syntax:**
```plsql
BEGIN
    UPDATE table_name
    SET column1 = value1, column2 = value2
    WHERE condition;
END;
```
**Example:**
```plsql
BEGIN
    UPDATE employees
    SET salary = 7000
    WHERE emp_id = 104;
    DBMS_OUTPUT.PUT_LINE('Record updated.');
END;
```

---

### **DELETE**
**Syntax:**
```plsql
BEGIN
    DELETE FROM table_name
    WHERE condition;
END;
```
**Example:**
```plsql
BEGIN
    DELETE FROM employees
    WHERE emp_id = 104;
    DBMS_OUTPUT.PUT_LINE('Record deleted.');
END;
```

---

### **MERGE**
**Syntax:**
```plsql
BEGIN
    MERGE INTO target_table t
    USING source_table s
    ON (t.column = s.column)
    WHEN MATCHED THEN
        UPDATE SET t.column1 = s.column1
    WHEN NOT MATCHED THEN
        INSERT (column1, column2, ...)
        VALUES (value1, value2, ...);
END;
```
**Example:**
```plsql
BEGIN
    MERGE INTO employees e
    USING departments d
    ON (e.dept_id = d.dept_id)
    WHEN MATCHED THEN
        UPDATE SET e.salary = e.salary + 1000
    WHEN NOT MATCHED THEN
        INSERT (emp_id, name, salary, dept_id)
        VALUES (105, 'Eve', 5500, d.dept_id);
END;
```

---

## Cursors

### **Types of Cursors**
1. **Implicit Cursor**: Automatically created by Oracle for DML operations.
2. **Explicit Cursor**: Defined explicitly by the programmer for query handling.

---

### **Explicit Cursor Life Cycle**
1. **Declare the Cursor**
   **Syntax:**
   ```plsql
   CURSOR cursor_name IS SELECT_statement;
   ```
   **Example:**
   ```plsql
   CURSOR emp_cursor IS SELECT emp_id, name FROM employees;
   ```

2. **Open the Cursor**
   **Syntax:**
   ```plsql
   OPEN cursor_name;
   ```

3. **Fetch Data**
   **Syntax:**
   ```plsql
   FETCH cursor_name INTO variable1, variable2, ...;
   ```

4. **Close the Cursor**
   **Syntax:**
   ```plsql
   CLOSE cursor_name;
   ```

---

### **Explicit Cursor Example**
```plsql
DECLARE
    CURSOR emp_cursor IS SELECT emp_id, name FROM employees;
    v_emp_id employees.emp_id%TYPE;
    v_name employees.name%TYPE;
BEGIN
    OPEN emp_cursor;
    LOOP
        FETCH emp_cursor INTO v_emp_id, v_name;
        EXIT WHEN emp_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Employee ID: ' || v_emp_id || ', Name: ' || v_name);
    END LOOP;
    CLOSE emp_cursor;
END;
```

---

### **Explicit Cursor Attributes**
- `%FOUND`: Returns TRUE if a row was fetched.
- `%NOTFOUND`: Returns TRUE if no row was fetched.
- `%ROWCOUNT`: Returns the number of rows fetched so far.
- `%ISOPEN`: Returns TRUE if the cursor is open.

---

## Exceptions

### **Exception Block Structure**
**Syntax:**
```plsql
BEGIN
    -- Statements
EXCEPTION
    WHEN exception_name THEN
        -- Exception handling code
    WHEN OTHERS THEN
        -- Handling all other exceptions
END;
```
**Example:**
```plsql
BEGIN
    DELETE FROM employees WHERE emp_id = 9999;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred.');
END;
```

---

### **Types of Exceptions**

1. **Predefined Exceptions:**
   Examples:
   - `NO_DATA_FOUND`: Raised when a SELECT INTO statement returns no rows.
   - `TOO_MANY_ROWS`: Raised when a SELECT INTO statement returns more than one row.
   - `ZERO_DIVIDE`: Raised when dividing by zero.

2. **User-Defined Exceptions:**
   **Syntax:**
   ```plsql
   DECLARE
       exception_name EXCEPTION;
   BEGIN
       RAISE exception_name;
   EXCEPTION
       WHEN exception_name THEN
           -- Exception handling
   END;
   ```
   **Example:**
   ```plsql
   DECLARE
       salary_error EXCEPTION;
   BEGIN
       IF 1 = 1 THEN  -- Simulated condition
           RAISE salary_error;
       END IF;
   EXCEPTION
       WHEN salary_error THEN
           DBMS_OUTPUT.PUT_LINE('Salary error occurred.');
   END;
   ```

---

This part covers DML operations, cursors, and exceptions in PL/SQL. Let me know if you'd like me to proceed with **Part 3** for records, functions, procedures, triggers, and packages!
Here’s **Part 3** of your PL/SQL cheat sheet, focusing on **records**, **functions**, **procedures**, **triggers**, and **packages**.

---

# PL/SQL Cheat Sheet (Part 3)

## Records

### **1. Table-Based Records**
**Definition:** Records based on the structure of a database table.

**Syntax:**
```plsql
DECLARE
    record_name table_name%ROWTYPE;
BEGIN
    SELECT * INTO record_name
    FROM table_name
    WHERE condition;
END;
```

**Example:**
```plsql
DECLARE
    emp_record employees%ROWTYPE;
BEGIN
    SELECT * INTO emp_record
    FROM employees
    WHERE emp_id = 101;
    DBMS_OUTPUT.PUT_LINE('Name: ' || emp_record.name || ', Salary: ' || emp_record.salary);
END;
```

---

### **2. Cursor-Based Records**
**Definition:** Records used with explicit cursors.

**Syntax:**
```plsql
DECLARE
    CURSOR cursor_name IS SELECT_statement;
    record_name cursor_name%ROWTYPE;
BEGIN
    OPEN cursor_name;
    FETCH cursor_name INTO record_name;
    CLOSE cursor_name;
END;
```

**Example:**
```plsql
DECLARE
    CURSOR emp_cursor IS SELECT emp_id, name, salary FROM employees;
    emp_record emp_cursor%ROWTYPE;
BEGIN
    OPEN emp_cursor;
    LOOP
        FETCH emp_cursor INTO emp_record;
        EXIT WHEN emp_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_record.name || ', Salary: ' || emp_record.salary);
    END LOOP;
    CLOSE emp_cursor;
END;
```

---

### **3. Programmer-Defined Records**
**Definition:** Custom record types defined by the programmer.

**Syntax:**
```plsql
DECLARE
    TYPE record_type IS RECORD (
        field1 datatype,
        field2 datatype
    );
    record_name record_type;
BEGIN
    record_name.field1 := value1;
    record_name.field2 := value2;
END;
```

**Example:**
```plsql
DECLARE
    TYPE emp_record_type IS RECORD (
        emp_id NUMBER,
        emp_name VARCHAR2(50)
    );
    emp_record emp_record_type;
BEGIN
    emp_record.emp_id := 101;
    emp_record.emp_name := 'Alice';
    DBMS_OUTPUT.PUT_LINE('Employee ID: ' || emp_record.emp_id || ', Name: ' || emp_record.emp_name);
END;
```

---

## Functions

### **Creating a Function**
**Syntax:**
```plsql
CREATE OR REPLACE FUNCTION function_name (arg_name datatype)
RETURN datatype
IS
BEGIN
    -- Logic
    RETURN value;
END function_name;
```

**Example:**
```plsql
CREATE OR REPLACE FUNCTION get_salary (emp_id NUMBER)
RETURN NUMBER
IS
    v_salary NUMBER;
BEGIN
    SELECT salary INTO v_salary
    FROM employees
    WHERE emp_id = emp_id;
    RETURN v_salary;
END;
```

---

### **Executing a Function**
**Using SQL:**
```sql
SELECT function_name(arguments) FROM DUAL;
```

**Using PL/SQL:**
```plsql
DECLARE
    result datatype;
BEGIN
    result := function_name(arguments);
END;
```

---

### **Dropping a Function**
**Syntax:**
```sql
DROP FUNCTION function_name;
```

---

## Procedures

### **Creating a Procedure**
**Syntax:**
```plsql
CREATE OR REPLACE PROCEDURE procedure_name (arg_name IN/OUT datatype)
IS
BEGIN
    -- Logic
END procedure_name;
```

**Example:**
```plsql
CREATE OR REPLACE PROCEDURE update_salary (emp_id IN NUMBER, increment IN NUMBER)
IS
BEGIN
    UPDATE employees
    SET salary = salary + increment
    WHERE emp_id = emp_id;
END;
```

---

### **Executing a Procedure**
**Syntax:**
```plsql
BEGIN
    procedure_name(arguments);
END;
```

**Example:**
```plsql
BEGIN
    update_salary(101, 500);
END;
```

---

### **Dropping a Procedure**
**Syntax:**
```sql
DROP PROCEDURE procedure_name;
```

---

## Triggers

### **Creating a Trigger**
**Syntax:**
```plsql
CREATE OR REPLACE TRIGGER trigger_name
BEFORE/AFTER INSERT/UPDATE/DELETE
ON table_name
FOR EACH ROW
BEGIN
    -- Logic
END trigger_name;
```

**Example:**
```plsql
CREATE OR REPLACE TRIGGER trg_salary_check
BEFORE INSERT OR UPDATE ON employees
FOR EACH ROW
BEGIN
    IF :NEW.salary < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salary cannot be negative');
    END IF;
END;
```

---

### **Dropping a Trigger**
**Syntax:**
```sql
DROP TRIGGER trigger_name;
```

---

## Packages

### **Creating a Package Specification**
**Syntax:**
```plsql
CREATE OR REPLACE PACKAGE package_name IS
    -- Declarations
END package_name;
```

**Example:**
```plsql
CREATE OR REPLACE PACKAGE emp_pkg IS
    PROCEDURE update_salary(emp_id IN NUMBER, increment IN NUMBER);
    FUNCTION get_salary(emp_id IN NUMBER) RETURN NUMBER;
END emp_pkg;
```

---

### **Creating a Package Body**
**Syntax:**
```plsql
CREATE OR REPLACE PACKAGE BODY package_name IS
    -- Implementations
END package_name;
```

**Example:**
```plsql
CREATE OR REPLACE PACKAGE BODY emp_pkg IS
    PROCEDURE update_salary(emp_id IN NUMBER, increment IN NUMBER) IS
    BEGIN
        UPDATE employees
        SET salary = salary + increment
        WHERE emp_id = emp_id;
    END;
    
    FUNCTION get_salary(emp_id IN NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT salary INTO v_salary
        FROM employees
        WHERE emp_id = emp_id;
        RETURN v_salary;
    END;
END emp_pkg;
```

---

### **Executing a Package**
**Syntax:**
```plsql
BEGIN
    package_name.procedure_name(arguments);
END;
```

**Example:**
```plsql
BEGIN
    emp_pkg.update_salary(101, 500);
END;
```

---

### **Dropping a Package**
**Syntax:**
```sql
DROP PACKAGE package_name;
```

---

This concludes Part 3 of the PL/SQL cheat sheet. Let me know if you need additional details or other topics!
