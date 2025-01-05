Trigger
CREATE [OR REPLACE] TRIGGER trigger_name
{BEFORE | AFTER | INSTEAD OF}
{INSERT [OR] | UPDATE [OR] | DELETE}
[OF column_name] ON table_name
[FOR EACH ROW]
WHEN (condition)
BEGIN
    -- SQL statements
END;
/
CREATE OR REPLACE TRIGGER price_history_trigger
BEFORE UPDATE OF unit_price ON product1
FOR EACH ROW
BEGIN
    INSERT INTO product_price_history 
    VALUES (:OLD.product_id, :OLD.product_name, :OLD.supplier_name, :OLD.unit_price);
END;
/
CREATE OR REPLACE TRIGGER empstatetrigger
BEFORE UPDATE ON emp
BEGIN
    INSERT INTO emp_log VALUES('Statement Level Trigger Fired');
END;
/
CREATE OR REPLACE TRIGGER emprowtrigger
BEFORE UPDATE ON emp
FOR EACH ROW
BEGIN
    INSERT INTO emp_log VALUES('Row Level Trigger Fired');
END;
/
CURSOR
DECLARE
    CURSOR emp_cur IS SELECT eno, fname, lname, sal FROM empl WHERE sal > 25000;
    emprec empl%ROWTYPE;
BEGIN
    OPEN emp_cur;
    FETCH emp_cur INTO emprec;
    DBMS_OUTPUT.PUT_LINE(emprec.eno || ' ' || emprec.fname || ' ' || emprec.sal);
    CLOSE emp_cur;
END;
/
Here are the uncovered or less-detailed topics from your initial list. I'll provide concise cheat sheet entries for them below. 

---

### Additional Topics:

1. **Types of Triggers**
   - **Statement Triggers**: Triggered once for the triggering statement.
   - **Row Triggers**: Triggered for each row affected by the DML statement.

   **Row Trigger Example**:
   ```plsql
   CREATE OR REPLACE TRIGGER row_trigger_example
   AFTER INSERT ON employees
   FOR EACH ROW
   BEGIN
       DBMS_OUTPUT.PUT_LINE('New Employee ID: ' || :NEW.emp_id);
   END;
   ```

   **Statement Trigger Example**:
   ```plsql
   CREATE OR REPLACE TRIGGER statement_trigger_example
   BEFORE DELETE ON employees
   BEGIN
       DBMS_OUTPUT.PUT_LINE('A delete operation is about to happen.');
   END;
   ```

2. **Using Conditional Logic in Triggers**
   - Conditional logic can control what happens within a trigger based on certain conditions.

   **Example**:
   ```plsql
   CREATE OR REPLACE TRIGGER conditional_trigger
   BEFORE UPDATE ON employees
   FOR EACH ROW
   BEGIN
       IF :NEW.salary > 10000 THEN
           DBMS_OUTPUT.PUT_LINE('High salary update detected!');
       END IF;
   END;
   ```

---

3. **Exception Handling Types**
   - **Predefined Exceptions**: System-defined (e.g., `NO_DATA_FOUND`, `TOO_MANY_ROWS`).
   - **User-Defined Exceptions**: Custom exceptions created using the `EXCEPTION` keyword.

   **Predefined Example**:
   ```plsql
   BEGIN
       SELECT salary INTO v_salary FROM employees WHERE emp_id = 9999;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
           DBMS_OUTPUT.PUT_LINE('Employee not found.');
   END;
   ```

   **User-Defined Example**:
   ```plsql
   DECLARE
       custom_exception EXCEPTION;
   BEGIN
       RAISE custom_exception;
   EXCEPTION
       WHEN custom_exception THEN
           DBMS_OUTPUT.PUT_LINE('Custom exception raised.');
   END;
   ```

---

4. **Packages - Usage**
   - Packages group related procedures, functions, variables, and cursors.

   **Example**: Package for managing employee data.
   ```plsql
   CREATE OR REPLACE PACKAGE emp_mgmt IS
       PROCEDURE add_employee(emp_id IN NUMBER, name IN VARCHAR2);
       FUNCTION get_employee(emp_id IN NUMBER) RETURN VARCHAR2;
   END emp_mgmt;
   ```

   **Body**:
   ```plsql
   CREATE OR REPLACE PACKAGE BODY emp_mgmt IS
       PROCEDURE add_employee(emp_id IN NUMBER, name IN VARCHAR2) IS
       BEGIN
           INSERT INTO employees (emp_id, name) VALUES (emp_id, name);
       END;
       
       FUNCTION get_employee(emp_id IN NUMBER) RETURN VARCHAR2 IS
           v_name VARCHAR2(50);
       BEGIN
           SELECT name INTO v_name FROM employees WHERE emp_id = emp_id;
           RETURN v_name;
       END;
   END emp_mgmt;
   ```

---

5. **Cursor FOR Loops**
   - Simplifies handling cursors by automatically opening, fetching, and closing them.

   **Example**:
   ```plsql
   DECLARE
       CURSOR emp_cursor IS SELECT emp_id, name FROM employees;
   BEGIN
       FOR emp_record IN emp_cursor LOOP
           DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_record.emp_id || ', ' || emp_record.name);
       END LOOP;
   END;
   ```

---

6. **Autonomous Transactions**
   - Autonomous transactions are independent of the main transaction.

   **Syntax**:
   ```plsql
   PRAGMA AUTONOMOUS_TRANSACTION;
   ```

   **Example**:
   ```plsql
   CREATE OR REPLACE PROCEDURE log_error (msg IN VARCHAR2) IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
       INSERT INTO error_log (message, log_date) VALUES (msg, SYSDATE);
       COMMIT;
   END;
   ```

---

7. **Trigger Timing (Before/After)**
   - **BEFORE**: Trigger fires before the DML statement.
   - **AFTER**: Trigger fires after the DML statement.

   **Example**:
   ```plsql
   CREATE OR REPLACE TRIGGER before_insert_trigger
   BEFORE INSERT ON employees
   FOR EACH ROW
   BEGIN
       DBMS_OUTPUT.PUT_LINE('About to insert Employee ID: ' || :NEW.emp_id);
   END;
   ```

---

8. **Nested Blocks**
   - PL/SQL allows blocks within blocks (nested blocks).

   **Example**:
   ```plsql
   BEGIN
       DECLARE
           v_outer VARCHAR2(20) := 'Outer Block';
       BEGIN
           DECLARE
               v_inner VARCHAR2(20) := 'Inner Block';
           BEGIN
               DBMS_OUTPUT.PUT_LINE(v_outer || ' - ' || v_inner);
           END;
       END;
   END;
   ```

---

If there are other missing or specific details you need, let me know!


