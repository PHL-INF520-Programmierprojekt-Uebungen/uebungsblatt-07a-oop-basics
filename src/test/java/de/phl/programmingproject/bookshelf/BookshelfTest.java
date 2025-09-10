package de.phl.programmingproject.bookshelf;

import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the BookShelf exercise.
 */
public class BookshelfTest {

    @Test
    void task_1_Bookshelf_class_implemented(){
        /**
         * Create a `Bookshelf` class that represents a collection of books. The `Bookshelf` class should have the following properties:
         *
         * - A set of books (use an `HashSet` to store the books)
         * - A maximum capacity (number of books that the bookshelf can hold)
         *
         * The `Bookshelf` class should also have the following operations:
         *
         * - An operation to add a book to the bookshelf. If the bookshelf is already at maximum capacity, the operation should throw an `IllegalStateException` and not add the book.
         * - An operation to remove a book from the bookshelf. If the bookshelf is empty or does not contain the book, the operation should throw an `IllegalStateException`.
         * - An operation to get the total number of pages of all the books on the bookshelf.
         */

        Class<?> bookshelfClass = TestUtils.getClassForName("Bookshelf", "de.phl.programmingproject.bookshelf");

        Map<String, Class<?>> expectedFields = new HashMap(){{
            put("books", Set.class);
            put("capacity", int.class);
        }};
        TestUtils.assertClassHasFieldsOfType(bookshelfClass, expectedFields);

        Constructor<?> bookshelfConstructor = TestUtils.getConstructor(bookshelfClass, int.class);

        Class<?> bookClass = TestUtils.getClassForName("Book", "de.phl.programmingproject.bookshelf");

        TestUtils.assertClassHasMethod(bookshelfClass, "addBook", void.class, bookClass);
        TestUtils.assertClassHasMethod(bookshelfClass, "removeBook", void.class, bookClass);
        TestUtils.assertClassHasMethod(bookshelfClass, "getBooks", Set.class);
        TestUtils.assertClassHasMethod(bookshelfClass, "getTotalNumberOfPages", int.class);

        Object bookshelf = TestUtils.createInstance(bookshelfClass, 3);

        Method addBookMethod = TestUtils.getMethod(bookshelfClass, "addBook", bookClass);
        assertThrows(Exception.class, () -> {
            Object innerBookshelf = TestUtils.createInstance(bookshelfClass, 2);
            for (int i = 0; i < 4; i++) {
                Object book = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337+i);
                addBookMethod.invoke(innerBookshelf,  book);
                System.out.println("Number of books: " + ((Set) TestUtils.invokeMethod(innerBookshelf, "getBooks")).size());
            }
        }, "The 'addBook' method of the Bookshelf class does not throw an IllegalStateException if the bookshelf is already at maximum capacity.");

        Method removeBookMethod = TestUtils.getMethod(bookshelfClass, "removeBook", bookClass);
        assertThrows(Exception.class, () -> {
            Object innerBookshelf = TestUtils.createInstance(bookshelfClass, 3);
            Object book = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337);
            removeBookMethod.invoke(innerBookshelf, book);
        }, "The 'removeBook' method of the Bookshelf class does not throw an IllegalStateException if the bookshelf is empty or does not contain the book.");

        assertEquals(0, ((Set) TestUtils.invokeMethod(bookshelf, "getBooks")).size(),
                "The 'getBooks' method of the Bookshelf class does not return an empty set if no books have been added.");

        Object book = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337);

        TestUtils.invokeMethod(bookshelf, "addBook", book);
        assertEquals(1, ((Set) TestUtils.invokeMethod(bookshelf, "getBooks")).size(),
                "The 'getBooks' method of the Bookshelf class does not return the correct number of books.");

        TestUtils.invokeMethod(bookshelf, "removeBook", book);

        assertEquals(0, ((Set) TestUtils.invokeMethod(bookshelf, "getBooks")).size(),
                "The 'getBooks' method of the Bookshelf class does not return an empty set if all books have been removed.");

        TestUtils.invokeMethod(bookshelf, "addBook", book);

        Object hobbit = TestUtils.createInstance(bookClass, "The Hobbit", "J. R. R. Tolkien", 300);
        TestUtils.invokeMethod(bookshelf, "addBook", hobbit);
        assertEquals(1337+300, TestUtils.invokeMethod(bookshelf, "getTotalNumberOfPages"),
                "The 'getTotalNumberOfPages' method of the Bookshelf class does not return 0 if no books have been added.");

    }

    @Test
    void task_2_Book_implemented(){
        /**
         * Create a `Book` class that represents a book. The `Book` class should have the following properties:
         *
         * - A title
         * - An author
         * - A number of pages
         *
         * The `Book` class should also have the following operations:
         *
         * - A constructor that takes in the title, author, and number of pages as arguments.
         * - Getter operations for the title, author, and number of pages properties.
         */
        Class<?> bookClass = TestUtils.getClassForName("Book", "de.phl.programmingproject.bookshelf");

        Map<String, Class<?>> expectedFields = new HashMap(){{
            put("author", String.class);
            put("title", String.class);
            put("pages", int.class);
        }};

        TestUtils.assertClassHasFieldsOfType(bookClass, expectedFields);
        TestUtils.assertClassHasMethod(bookClass, "getAuthor", String.class);
        TestUtils.assertClassHasMethod(bookClass, "getTitle", String.class);
        TestUtils.assertClassHasMethod(bookClass, "getPages", int.class);

        Constructor<?> bookConstructor = TestUtils.getConstructor(bookClass, String.class, String.class, int.class);

        Object book = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337);

        assertEquals("The Lord of the Rings", TestUtils.invokeMethod(book, "getTitle"),
                "The 'getTitle' method of the Book class does not return the correct title.");

        assertEquals("J. R. R. Tolkien", TestUtils.invokeMethod(book, "getAuthor"),
                "The 'getAuthor' method of the Book class does not return the correct author.");

        assertEquals(1337, TestUtils.invokeMethod(book, "getPages"),
                "The 'getPages' method of the Book class does not return the correct number of pages.");
    }

    @Test
    void task_3_Main_implemented(){
        /**
         * Finally, create in the `main` operation a `Bookshelf` object and add several `Book` objects to it. The `main` operation should also call the operation of the `Bookshelf` object to get the total number of pages and print this number.
         */

        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory("main/java/de/phl/programmingproject/bookshelf/Main.java");

        assertTrue(fileContent.contains("new Bookshelf"), "The 'Main' class does not instantiate a 'Bookshelf' object.");
        assertTrue(fileContent.contains("new Book"), "The 'Main' class does not instantiate a 'Book' object.");
        assertTrue(fileContent.contains("addBook"), "The 'Main' class does not call the 'addBook' method of the 'Bookshelf' class.");
        assertTrue(fileContent.contains("getTotalNumberOfPages"), "The 'Main' class does not call the 'getTotalNumberOfPages' method of the books to compute the total number of pages.");
    }

    @Test
    void task_bonus_Book_implements_equals(){
        // Implement an operation in the `Book` class that checks whether two books are equal. Two books are considered equal if they have the same title, author, and number of pages.
        Class<?> bookClass = TestUtils.getClassForName("Book", "de.phl.programmingproject.bookshelf");

        TestUtils.assertClassHasMethod(bookClass, "equals", boolean.class, Object.class);

        Object book1 = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337);
        Object book2 = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1337);
        Object book3 = TestUtils.createInstance(bookClass, "The Lord of the Rings", "J. R. R. Tolkien", 1338);

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
    }
}
