import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import car2.Car;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassCar {

    @Test
    @EnabledOnOs(OS.WINDOWS)               // Я хочу чтобы на этой OC запускался тест
    @EnabledOnJre(JRE.JAVA_16)             // Я хочу чтобы на этой версии JRE запускался тест
    @Execution(ExecutionMode.CONCURRENT)   // Параллельно выполняется тест
    public void getCarYear() {
        Car car = new Car(2000, "blue", 2020, 240);
        assertEquals(2020, car.getYear());
    }

    @Test
    @DisabledOnOs({OS.WINDOWS, OS.LINUX})               // Я хочу чтобы на этих OC не запускался тест
    @DisabledOnJre(JRE.JAVA_16)                         // Я хочу чтобы на этой версии JRE не запускался тест
    @Execution(ExecutionMode.CONCURRENT)
    public void getCarYear2() {
        Car car = new Car(2000, "blue", 2020, 240);
        assertEquals(2020, car.getYear());
    }


    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void getCarWeight(){
        Car car = new Car(2000, "blue", 2020, 240);
        assertEquals(2000, car.getWeight());
    }


    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void getCarSpeed(){
        Car car = new Car(2000, "blue", 2020, 240);
        assertEquals(240, car.getMaxSpeed());
    }
}