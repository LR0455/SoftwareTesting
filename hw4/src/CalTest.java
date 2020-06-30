import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalTest {
    // public static int cal (int month1, int day1, int month2,
    //                           int day2, int year)
    public Cal cal_test;
    @Before
    public void setUp() throws Exception
    {
        Cal cal_test = new Cal();
    }

    @After
    public void tearDown() throws Exception
    {
        cal_test = null;
    }
    @Test
    public void testCal()
    {
        assertEquals(62L, (long)Cal.cal(1, 2, 3, 4, 2012));
//        assertEquals(29, cal_test.cal(2, 1, 1, 1, 2020));
//        assertEquals(29, cal_test.cal(2, 1, 3, 1, 2020));
        /*
        for (int y = 2015 ; y <= 2020 ; y ++)
            for (int m1 = 1 ; m1 <= 12 ; m1 ++)
                for (int d1 = 1 ; d1 <= 28 ; d1 ++)
                    for (int m2 = 1 ; m2 <= 12 ; m2 ++)
                        for (int d2 = 1 ; d2 <= 28 ; d2 ++)
                            assertEquals(cal(m1, d1, m2, d2, y), cal_test.cal(m1, d1, m2, d2, y));

         */
    }

    public static int cal (int month1, int day1, int month2,
                           int day2, int year)
    {
        //***********************************************************
        // Calculate the number of Days between the two given days in
        // the same year.
        // preconditions : day1 and day2 must be in same year
        //               1 <= month1, month2 <= 12
        //               1 <= day1, day2 <= 31
        //               month1 <= month2
        //               The range for year: 1 ... 10000
        //***********************************************************
        int numDays;

        if (month2 == month1) // in the same month
            numDays  = day2 - day1;
        else
        {
            // Skip month 0.
            int daysIn[] = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            // Are we in a leap year?
            int m4 = year % 4;
            int m100 = year % 100;
            int m400 = year % 400;
            if ((m4 != 0) || ((m100 == 0) && (m400 != 0)))
                daysIn[2] = 28;
            else
                daysIn[2] = 29;

            // start with days in the two months
            numDays = day2 + (daysIn[month1] - day1);

            // add the days in the intervening months
            for (int i = month1 + 1; i <= month2-1; i++)
                numDays = daysIn[i] + numDays;
        }
        return (numDays);
    }
}