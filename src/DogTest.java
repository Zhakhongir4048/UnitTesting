import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    static Dog dog;

//    // Этот метод выполняется перед каждым UnitTest-ом, @BeforeEach для этого служит
//    @BeforeEach
//    void prepareData(){
//        dog = new Dog("Alberts",2);
//    }

    // Этот метод выполняется один раз перед всеми тестами, @BeforeAll для этого служит, нужно сделать статичным поле, метод
    @BeforeAll
    static void prepareData(){
        dog = new Dog("Alberts",2);
    }

    @Test
    void testGetDogNameMethod() {
        assertEquals("Alberts", dog.getName());
    }

    @Test
    void testSetDogNameMethod() {
        dog.setName("Roman");
        assertEquals("Alberts", dog.getName());
    }

    @Test
    void testSetDogNameMethodIfEmpty() {
        Dog dog = new Dog("", 2);
        dog.setName("Roman");
        assertEquals("Roman", dog.getName());
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
    }

}