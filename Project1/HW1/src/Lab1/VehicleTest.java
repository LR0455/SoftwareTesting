package Lab1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    Vehicle veh;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        veh = new Vehicle();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        // finalize only exec 1 time -> total - 1 * finalize = 4 - 1 = 3
        assertEquals(3, Vehicle.totalVehicle());
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        veh.setSpeed(1300);
        assertEquals(1300, veh.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        veh.setDir("east");
        assertEquals("east", veh.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, veh.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north", veh.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(4, Vehicle.totalVehicle());
        // exec finalize()
        System.gc();
    }
}