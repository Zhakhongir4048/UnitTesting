import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    public void createCar() {
        car = new Car("Skoda", "ABC-1234", 2019, "Dmitrijs Finashkins");
    }

    @Test
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Test
    void getNumber() {
        assertEquals("ABC-1234", car.getNumber());
    }

    @Test
    void setNumber() {
        car.setNumber("ABCD-1234");
        assertEquals("ABCD-1234", car.getNumber());
    }

    @ParameterizedTest                                         // нужен для метода тестирования у которого параметр
    @ValueSource(strings = {"ABC-123", "DEF-456", "DFG-434"})
    // number берёт отсюда, можем подавать в метод только один параметр
    @NullSource                                                // number берёт Null, проверяем как тут себя поведёт
    @EmptySource
        // number берёт пустое значение
    void testSetNumberMultipleValues(String number) {          // можем использовать только один параметр
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @ParameterizedTest
    @CsvSource({"'ABCD-123', 'ABCD-123'", "'DEF-456', 'DEF-456'"})
        // Первое идёт в number, второе идёт в expectations
    void testSetNumberMultipleValues(String number, String expectations) {
        car.setNumber(number);
        assertEquals(expectations, car.getNumber());
    }

    @ParameterizedTest
    @CsvSource({"1,5", "8,12", "32, 36"})
    public void testInt(int input, int output) {
        assertEquals(output, car.testInt(input));
    }

    @Test
    void getYear() {
        assertEquals(2019, car.getYear());
    }

    @Test
    void getOwner() {
        assertEquals("Dmitrijs Finashkins", car.getOwner());
    }

    @Test
    void setOwner() {
        car.setOwner("Andrej Kirilov");
        assertEquals("Andrej Kirilov", car.getOwner());
    }

    @Test
    void getListOfOwners() {
        assertArrayEquals(new String[]{"Dmitrijs Finashkins"}, car.getOwners().toArray());
    }

    @Test
    void getListOfTwoOwners() {
        car.setOwner("Andrej Kirilov");
        assertArrayEquals(new String[]{"Dmitrijs Finashkins", "Andrej Kirilov"}, car.getOwners().toArray());
    }

    @Test
    public void testPrivateMethod() {
        try {
            // Получаем private метод - getDeclaredMethod
            Method method = Car.class.getDeclaredMethod("testMethod", null);
            // Делаем его доступным - setAccessible
            method.setAccessible(true);
            // Запускаем метод - invoke
            assertEquals(method.invoke(car).toString(), "abc");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Такого метода нет в классе Car");
        }

    }

    @Test
    public void TestPrivateMethodWithArgument() {
        try {
            // Получаем private метод - getDeclaredMethod
            Method method = Car.class.getDeclaredMethod("testMethod", String.class);
            // Делаем его доступным - setAccessible
            method.setAccessible(true);
            // Запускаем метод - invoke
            assertEquals(method.invoke(car, "abd").toString(), "abd");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Такого метода нет в классе Car");
        }
    }


    @ParameterizedTest
    @DisplayName("Test demostrates how test could be loaded from file")
    @CsvFileSource(resources = "test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void testNumbers(String input, String expected) {
        car.setNumber(input);
        assertEquals(expected, car.getNumber());
    }


}