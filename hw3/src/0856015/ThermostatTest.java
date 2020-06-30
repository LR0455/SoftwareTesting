import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermostatTest {
    // (curTemp < dTemp - thresholdDiff) || (override && curTemp < overTemp - thresholdDiff)) && (timeSinceLastRun > minLag)
    /*
        1. Test cases
        (1) true case
            curTemp = 30, dTemp = 40, thresholdDiff = 5, override = true, overTemp = 40, timeSinceLastRun = 10, minLag = 5
        (2) false case
            curTemp = 30, dTemp = 40, thresholdDiff = 5, override = true, overTemp = 40, timeSinceLastRun = 5, minLag = 10
     */
    @Test
    void testPC() {
        Thermostat thst = new Thermostat();
        // true case
        ProgrammedSettings pSet_T = new ProgrammedSettings(40);

        thst.setCurrentTemp(30);
        thst.setThresholdDiff(5);
        thst.setOverride(true);
        thst.setOverTemp(40);
        thst.setTimeSinceLastRun(10);
        thst.setMinLag(5);

        assertEquals(true, thst.turnHeaterOn(pSet_T));

        // false case
        thst.setTimeSinceLastRun(5);
        thst.setMinLag(10);

        assertEquals(false, thst.turnHeaterOn(pSet_T));
    }
    // (curTemp < dTemp - thresholdDiff) || (override && curTemp < overTemp - thresholdDiff)) && (timeSinceLastRun > minLag)
    /*
        1. Clause coverage
        (1)
            (curTemp < dTemp - thresholdDiff) = true
                curTemp = 30, dTemp = 40, thresholdDiff = 5
            (curTemp < dTemp - thresholdDiff) = false
                curTemp = 40, dTemp = 30, thresholdDiff = 5
        (2)
            override = true
            override = false
        (3)
            (curTemp < overTemp - thresholdDiff) = true
                curTemp = 30, overTemp = 40, thresholdDiff = 5
            (curTemp < overTemp - thresholdDiff) = true
                curTemp = 40, overTemp = 30, thresholdDiff = 5
        (4)
            (timeSinceLastRun > minLag) = true
                timeSinceLastRun = 10, minLag = 5
            (timeSinceLastRun > minLag) = false
                timeSinceLastRun = 5, minLag = 10
        2. Test cases
        (1) true case
            curTemp = 30, dTemp = 40, thresholdDiff = 5, override = true, overTemp = 40, timeSinceLastRun = 10, minLag = 5
        (2) false case
            curTemp = 40, dTemp = 30, thresholdDiff = 5, override = false, overTemp = 30, timeSinceLastRun = 5, minLag = 10
     */
    @Test
    void testCC() {
        Thermostat thst = new Thermostat();
        // true case
        ProgrammedSettings pSet_T = new ProgrammedSettings(40);

        thst.setCurrentTemp(30);
        thst.setThresholdDiff(5);
        thst.setOverride(true);
        thst.setOverTemp(40);
        thst.setTimeSinceLastRun(10);
        thst.setMinLag(5);

        assertEquals(true, thst.turnHeaterOn(pSet_T));

        // false case
        ProgrammedSettings pSet_F = new ProgrammedSettings(30);

        thst.setCurrentTemp(40);
        thst.setThresholdDiff(5);
        thst.setOverride(false);
        thst.setOverTemp(30);
        thst.setTimeSinceLastRun(5);
        thst.setMinLag(10);

        assertEquals(false, thst.turnHeaterOn(pSet_F));
    }

    // (curTemp < dTemp - thresholdDiff) || (override && curTemp < overTemp - thresholdDiff)) && (timeSinceLastRun > minLag)
    /*
        (timeSinceLastRun > minLag) --> major clause --> T, F
        (curTemp < dTemp - thresholdDiff) --> T
        (override) --> T
        (curTemp < overTemp - thresholdDiff) --> T
     */
    @Test
    void testCACC() {
        Thermostat thst = new Thermostat();
        // true case
        ProgrammedSettings pSet_T = new ProgrammedSettings(40);

        thst.setCurrentTemp(30);
        thst.setThresholdDiff(5);
        thst.setOverride(true);
        thst.setOverTemp(40);
        thst.setTimeSinceLastRun(10);
        thst.setMinLag(5);

        assertEquals(true, thst.turnHeaterOn(pSet_T));

        // false case
        thst.setTimeSinceLastRun(5);
        thst.setMinLag(10);

        assertEquals(false, thst.turnHeaterOn(pSet_T));
    }
}