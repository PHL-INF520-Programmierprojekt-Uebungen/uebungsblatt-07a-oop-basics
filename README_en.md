> **Hinweis:** Die aktuelle Aufteilung finden Sie in [README.md](README.md). Diese ältere Übersetzung folgt noch der vorherigen Aufgabenfolge.

# Exercise Sheet
[Link to German Version](./README.md)
In this exercise sheet, you will learn to develop object-oriented source code to solve various tasks.

## Exercise 1: Inheritance (`Animals`)

### Part 1: Inheritance with Animals

Consider the following hierarchy of animal classes:

* Animal
    * Mammal
        * Cat
        * Dog

#### Tasks

Implement the classes in the package `de.phl.programmingproject.animals`.
1. Create an `Animal` class that has a `name` field and a `speak` operation, which prints its name with `System.out.format("I am %s and I am an animal.%n", name);` to the console. Also implement a getter operation for the name.
2. Create a `Mammal` class that inherits from `Animal` and adds a `breed` field of type `String`. Override the `speak` operation to also print the breed. Also implement a getter operation for the breed.
3. Create `Cat` and `Dog` classes that inherit from `Mammal`.
4. Override the `speak` operation in each subclass to print a species-specific message in addition to the output of the parent class. For example, the `speak` operation in the `Cat` class should also print `"Meow!"`.
5. Implement a `meow` operation for `Cat` and a `bark` operation for `Dog` to print `"Meow!"` and `"Woof!"` respectively.

### Part 2: Zoo

Imagine you are creating a zoo simulation. In addition to the above hierarchy of animals, you need a `Zoo` class to manage the animals.

#### Tasks

1. Create a `Zoo` class that has a set (`Set`) of `Animal` objects and a `displayAnimals` operation that allows each animal to speak.
2. Implement an operation to add an animal to the zoo (`addAnimal`), and an operation to remove an animal from the zoo (`removeAnimal`).
3. Uncomment the code in the `main` operation in the `Main` class and run it to see what happens.

## Exercise 1: Bookshelf and Book Class

In this exercise, you will practice object-oriented programming in the context of a bookshelf (`Bookshelf`) and a book management system.
Implement the classes in the package `de.phl.programmingproject.bookshelf`.

### Task 2: The `Bookshelf` Class
Create a `Bookshelf` class that represents a collection (`Set`) of books. The `Bookshelf` class should have the following properties:

- A `Set` of books (`Book`) (use a `HashSet` to store the books)
    - Also implement a corresponding getter `getBooks`
- A `capacity` (maximum number of books the bookshelf can hold)

The `Bookshelf` class should also have the following operations:

- An operation to add a book to the bookshelf (`addBook`). If the bookshelf has already reached its maximum capacity, the operation should throw an `IllegalStateException` and not add the book.
- An operation to remove a book from the bookshelf (`removeBook`). If the bookshelf is empty or does not contain the book, the operation should throw an `IllegalStateException`.
- An operation to retrieve the total number of pages of all books in the bookshelf (`getTotalNumberOfPages`).

### Task 2: The `Book` Class
Create a `Book` class that represents a book. The `Book` class should have the following properties:

- Title (`title`)
- Author (`author`)
- Number of pages (`pages`)

The `Book` class should also have the following operations:

- A constructor that accepts the title, author, and number of pages as arguments.
- Getter operations for the properties title, author, and number of pages.

### Task 3: The `Main` Operation
Finally, in the `main` operation, create a `Bookshelf` object and add several `Book` objects. The `main` operation should also call the operation of the `Bookshelf` object to retrieve the total number of pages and print this number.

### Bonus

Implement an `equals` operation in the `Book` class that checks whether two books are equal. Two books are considered equal if they have the same title, author, and number of pages.

## Exercise 3: Polymorphism (`Employee`)

Create a Java program that demonstrates the use of polymorphism in object-oriented programming in the context of an employee management system.
Implement the classes in the package `de.phl.programmingproject.employee`.

### Tasks

The program should consist of the following classes:

1. An abstract class called `Employee` with the following attribute:
    - `String name`: the name of the employee.
      The `Employee` class should also have an abstract operation called `calculatePay()` that returns the pay of the employee based on their salary.

2. Two subclasses that extend the `Employee` class:
    - `SalariedEmployee`, which has an attribute `double salary` (annual salary) to store the employee's salary, and a constructor that accepts the parameters `name` and `salary`. The class should implement the `calculatePay()` operation to calculate the employee's monthly salary by dividing the annual salary by 12.
    - `HourlyEmployee`, which has a constructor that accepts the parameters `name` and `hourlyRate`. In addition, the class has the additional attributes `int hoursWorked` for the hours worked by the employee and `double hourlyRate` to store the employee's hourly wage. Implement the `calculatePay()` operation to calculate the employee's pay by multiplying the hourly rate by the hours worked. To add the employee's worked hours, implement the operation `addHoursWorked(int hours)`.

3. A `Main` class that contains a `main()` operation. In the `main()` operation, you should create a list of `Employee` objects that contains at least one `SalariedEmployee` and at least one `HourlyEmployee`. Iterate over the list and call the `calculatePay()` operation for each object. Print the name of the employee and their pay to the console.
