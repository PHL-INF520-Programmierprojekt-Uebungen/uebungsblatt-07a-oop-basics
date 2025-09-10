package de.phl.programmingproject.employee;

import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the Employee / Polymorphism exercise.
 */
public class EmployeeTest {

    @Test
    void task_1_Employee_class_implemented() {
        /**
         *
         1. An abstract class called `Employee` that has the following attribute:
         - `String name`: the name of the employee.
         The `Employee` class should also have an abstract operation called `calculatePay()` that returns the pay for the employee based on their salary.

         */

        Class<?> employeeClass = TestUtils.getClassForName("Employee", "de.phl.programmingproject.employee");

        TestUtils.assertClassHasFieldOfType(employeeClass, "name", String.class);
        TestUtils.assertClassHasMethod(employeeClass, "calculatePay", double.class);
    }

    @Test
    void task_2_SalariedEmployee_class_implemented() {

        // `SalariedEmployee` that has an attribute `double salary` to store the employee's salary, and a constructor that takes a `name` and `salary` parameter.
        // The class implements the `calculatePay()` operation to return the employee's salary divided by 12 (for monthly pay).

        Class<?> salariedEmployeeClass = TestUtils.getClassForName("SalariedEmployee", "de.phl.programmingproject.employee");

        assertTrue(TestUtils.getClassForName("Employee", "de.phl.programmingproject.employee").isAssignableFrom(salariedEmployeeClass));

        Constructor<?> salariedEmployeeConstructor = TestUtils.getConstructor(salariedEmployeeClass, String.class, double.class);

        TestUtils.assertClassHasFieldOfType(salariedEmployeeClass, "salary", double.class);
        TestUtils.assertClassHasMethod(salariedEmployeeClass, "calculatePay", double.class);

        Object salariedEmployee = TestUtils.createInstance(salariedEmployeeClass, "John Doe", 1000.0);
        assertEquals(1000.0 / 12, TestUtils.invokeMethod(salariedEmployee, "calculatePay"));
    }

    @Test
    void task_2_HourlyEmployee_class_implemented() {
        //     - `HourlyEmployee` that has a constructor that takes a `name`, and `hourlyRate` parameter.
        //     Furthermore, the class has the additional attributes `int hoursWorked` for the hours the employee worked and `double hourlyRate` to store the employee's hourly salary.
        //     Implements the `calculatePay()` operation to return the employee's hourly rate times the hours worked.
        // To add hours that the employee worked, implement the operation `addHoursWorked(int hours)`.
        Class<?> hourlyEmployeeClass = TestUtils.getClassForName("HourlyEmployee", "de.phl.programmingproject.employee");
        TestUtils.assertClassHasFieldOfType(hourlyEmployeeClass, "hourlyRate", double.class);
        TestUtils.assertClassHasFieldOfType(hourlyEmployeeClass, "hoursWorked", int.class);
        TestUtils.assertClassHasMethod(hourlyEmployeeClass, "calculatePay", double.class);
        TestUtils.assertClassHasMethod(hourlyEmployeeClass, "addHoursWorked", void.class, int.class);

        Object hourlyEmployee = TestUtils.createInstance(hourlyEmployeeClass, "John Doe", 10.0);

        TestUtils.invokeMethod(hourlyEmployee, "addHoursWorked", 10);
        try {
            assertEquals(10, TestUtils.getField(hourlyEmployeeClass, "hoursWorked").get(hourlyEmployee));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        assertEquals(100.0, TestUtils.invokeMethod(hourlyEmployee, "calculatePay"));

    }

    @Test
    void task_3_Main_implemented() {
        /**
         *
         * 3. A `Main` class that contains a `main()` operation. In the `main()` operation, create a list of `Employee` objects that includes at least one `SalariedEmployee` and at least one `HourlyEmployee`. Loop through the list and call the `calculatePay()` operation for each object, and print the name of the employee and their pay to the console.
         *
         */

        Class<?> mainClass = TestUtils.getClassForName("Main", "de.phl.programmingproject.employee");

        TestUtils.assertClassHasMethod(mainClass, "main", void.class, String[].class);

        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory("main/java/de/phl/programmingproject/employee/Main.java");
        assertTrue(fileContent.contains("new SalariedEmployee("), "The 'SalariedEmployee' class is not instantiated in the 'main' method of the 'Main' file.");
        assertTrue(fileContent.contains("new HourlyEmployee("), "The 'HourlyEmployee' class is not instantiated in the 'main' method of the 'Main' file.");
        assertTrue(fileContent.contains("calculatePay("), "The 'calculatePay' method is not called in the 'main' method of the 'Main' file.");

        assertTrue(fileContent.contains("List<Employee>"), "The 'main' method does not create a List<Employee>.");

        assertTrue(fileContent.contains("for(") || fileContent.contains("for ("),
                "The 'main' method does not contain a for loop to iterate over the list of employees.");
    }
}
