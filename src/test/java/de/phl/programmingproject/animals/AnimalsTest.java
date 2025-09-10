package de.phl.programmingproject.animals;

import de.phl.programmingproject.TestBase;
import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Animals exercise
 */
public class AnimalsTest extends TestBase {


    static Class<?> getAnimalClass() {
        return TestUtils.getClassForName("Animal", "de.phl.programmingproject.animals");
    }


    @Test
    void part_1_task_1_Animal_class_with_properties_implemented() {
        Class<?> animalClass = TestUtils.getClassForName("Animal", "de.phl.programmingproject.animals");

        TestUtils.assertClassHasFieldOfType(animalClass, "name", String.class);
        TestUtils.assertClassHasMethod(animalClass, "speak", void.class);
        TestUtils.assertClassHasMethod(animalClass, "getName", String.class);

        final String name = "Pika";
        Object animal = TestUtils.createInstance(animalClass, name);

        String output = TestUtils.runActionAndGetSystemOut(() -> {
            try {
                TestUtils.getMethod(animalClass, "speak").invoke(animal);
            } catch (Exception e) {
                System.err.println(e);
                fail("The 'speak' method of the Animal class could not be invoked.");
            }
        });
        try {
            TestUtils.getMethod(animalClass, "speak").invoke(animal);
        } catch (Exception e) {
            System.err.println(e);
            fail("The 'speak' method of the Animal class could not be invoked.");
        }

        assertTrue(output.contains(name),
                "The 'speak' method of the Animal class does not print the name of the animal.");
    }

    @Test
    void part_1_task_2_Mammal_class_with_properties_implemented() {
        // 2. Create a `Mammal` class that inherits from `Animal` and adds a `breed` field of type `String`. Override the `speak` operation to print also the breed. Provide also a getter operation for the breed.
        Class<?> mammalClass = TestUtils.getClassForName("Mammal", "de.phl.programmingproject.animals");
        // test if the class inherits from Animal
        assertTrue(getAnimalClass().isAssignableFrom(mammalClass),
                "The Mammal class does not inherit from the Animal class.");

        TestUtils.assertClassHasFieldOfType(mammalClass, "breed", String.class);
        TestUtils.assertClassHasMethod(mammalClass, "getBreed", String.class);

        final Object mammal = TestUtils.createInstance(mammalClass, "Pika", "Mouse");

        String output = TestUtils.runActionAndGetSystemOut(() -> {
            TestUtils.invokeMethod(mammal, "speak");
        });
        assertTrue(output.contains("Mouse"), "The 'speak' method of the Mammal class does not print the breed.");
    }

    @Test
    void part_1_task_3_4_5_Cat_class_implements_meow() {
        Class<?> catClass = TestUtils.getClassForName("Cat", "de.phl.programmingproject.animals");
        Class<?> mammalClass = TestUtils.getClassForName("Mammal", "de.phl.programmingproject.animals");
        assertTrue(mammalClass.isAssignableFrom(catClass),
                "The Cat class does not inherit from the Mammal class.");

        Object cat = TestUtils.createInstance(catClass, "Garfield", "Cat");

        TestUtils.assertClassHasMethod(catClass, "meow", void.class);

        String output = TestUtils.runActionAndGetSystemOut(() -> {
            TestUtils.invokeMethod(cat, "speak");
            TestUtils.invokeMethod(cat, "meow");
        });
        assertTrue(output.toLowerCase().contains("meow"), "The 'meow' method of the Cat class does not print 'Meow'.");
    }

    @Test
    void part_1_task_3_4_5_Dog_class_with_properties_implemented() {
        // 3. Create a `Dog` class that inherits from `Mammal` and overrides the `speak` operation to print also "Woof".
        Class<?> dogClass = TestUtils.getClassForName("Dog", "de.phl.programmingproject.animals");
        Class<?> mammalClass = TestUtils.getClassForName("Mammal", "de.phl.programmingproject.animals");
        assertTrue(mammalClass.isAssignableFrom(dogClass),
                "The Dog class does not inherit from the Mammal class.");

        Object dog = TestUtils.createInstance(dogClass, "Scooby Doo", "Dog");

        String output = TestUtils.runActionAndGetSystemOut(() -> {
            TestUtils.invokeMethod(dog, "speak");
            TestUtils.invokeMethod(dog, "bark");
        });
        assertTrue(output.toLowerCase().contains("woof"), "The 'speak' method of the Dog class does not print 'Woof'.");
    }

    @Test
    void part_2_task_1_Zoo_class_with_properties_implemented() {
        Class<?> zooClass = TestUtils.getClassForName("Zoo", "de.phl.programmingproject.animals");

        Object zoo = TestUtils.createInstance(zooClass);
        Field animalsField = TestUtils.getField(zooClass, "animals");

        try {
            Object animals = animalsField.get(zoo);
            assertTrue(Set.class.isAssignableFrom(animals.getClass()),
                    String.format("The 'animals' field of the Zoo class is not of type Set. Expected %s, but was %s. ", Set.class, animalsField.getType().getCanonicalName()));
        } catch (IllegalAccessException e) {
            System.err.println(e);
            fail("Failed to access the 'animals' field of the Zoo class.");
        }

        TestUtils.assertClassHasMethod(zooClass, "displayAnimals", void.class);
    }

    @Test
    void part_2_task_2_Zoo_implements_addAnimal_and_removeAnimal() {
        Class<?> zooClass = TestUtils.getClassForName("Zoo", "de.phl.programmingproject.animals");

        Class<?> catClass = TestUtils.getClassForName("Cat", "de.phl.programmingproject.animals");
        Class<?> dogClass = TestUtils.getClassForName("Dog", "de.phl.programmingproject.animals");

        Method addAnimalsMethod = TestUtils.getMethod(zooClass, "addAnimal", getAnimalClass());

        Method removeAnimalsMethod = TestUtils.getMethod(zooClass, "removeAnimal", getAnimalClass());

        Object zoo = TestUtils.createInstance(zooClass);
        Object cat = TestUtils.createInstance(catClass, "Garfield", "Cat");
        Object dog = TestUtils.createInstance(dogClass, "Scooby Doo", "Dog");

        Field animalsField = TestUtils.getField(zooClass, "animals");
        try {
            addAnimalsMethod.invoke(zoo, cat);
            assertEquals(1, ((Set) animalsField.get(zoo)).size(), "The 'addAnimal' method of the Zoo class does not add the animal to the animals set.");
            addAnimalsMethod.invoke(zoo, dog);
            assertEquals(2, ((Set) animalsField.get(zoo)).size(), "The 'addAnimal' method of the Zoo class does not add the animal to the animals set.");
            removeAnimalsMethod.invoke(zoo, cat);
            assertEquals(1, ((Set) animalsField.get(zoo)).size(), "The 'removeAnimal' method of the Zoo class does not remove the animal from the animals set.");
        } catch (Exception e) {
            System.err.println(e);
            fail("Failed to invoke addAnimal or removeAnimal method of Zoo class.");
        }
    }
}
