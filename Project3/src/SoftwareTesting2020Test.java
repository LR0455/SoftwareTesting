import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MypaypalService implements paypalService {
    public String doDonate(){
        return "successed";
    }
}

class SoftwareTesting2020Test {

    @org.junit.jupiter.api.Test
    void test_a() throws InterruptedException {
        SoftwareTesting2020 st2020 = new SoftwareTesting2020();

        Date dateMock = mock(Date.class);
        when(dateMock.getWeekday()).thenReturn(4); // Wednesday
        st2020.date = dateMock;

        Student std = new Student(); // initial temperature is 38.0 -> fever

        Hospital hptMock = mock(Hospital.class);
        when(hptMock.treatment(any(Student.class))).thenReturn("After a long time treatment. The student can get out! ^__^"); // only return string
        st2020.setHospital(hptMock);

        st2020.enterClass(std);
        verify(st2020.hospital, never()).treatment(std);
    }

    @org.junit.jupiter.api.Test
    void test_b() throws InterruptedException {
        SoftwareTesting2020 st2020 = new SoftwareTesting2020();

        Date dateMock = mock(Date.class);
        when(dateMock.getWeekday()).thenReturn(5); // Thursday
        st2020.date = dateMock;

        Student std = new Student(); // initial temperature is 38.0 -> fever

        Hospital hptMock = mock(Hospital.class);
        when(hptMock.treatment(any(Student.class))).thenReturn("After a long time treatment. The student can get out! ^__^"); // only return string
        st2020.setHospital(hptMock);

        assertEquals("After a long time treatment. The student can get out! ^__^", st2020.enterClass(std));
    }

    @org.junit.jupiter.api.Test
    void test_c() throws InterruptedException {
        SoftwareTesting2020 st2020 = new SoftwareTesting2020();

        Date dateMock = mock(Date.class);
        when(dateMock.getWeekday()).thenReturn(5); // Thursday
        st2020.date = dateMock;

        Hospital htpSpy = spy(Hospital.class);
        doNothing().when(htpSpy).isolation(any(Student.class));
        st2020.setHospital(htpSpy);

        Student std1 = new Student("0000123", 37.5);
        Student std2 = new Student("0000456", 38.5);
        Student std3 = new Student("0000789", 39);

        st2020.enterClass(std1);
        st2020.enterClass(std2);
        st2020.enterClass(std3);

        assertEquals(Arrays.asList(new String[] {"0000123", "0000456", "0000789"}), st2020.hospital.getLog());
    }

    @org.junit.jupiter.api.Test
    void test_d() {
        SoftwareTesting2020 st2020Mock = mock(SoftwareTesting2020.class);
        when(st2020Mock.getScore(anyString())).thenReturn(60);
        assertEquals(60, st2020Mock.getScore("0000123"));
    }

    @org.junit.jupiter.api.Test
    void test_e() {
        SoftwareTesting2020 st2020 = new SoftwareTesting2020();
        assertEquals("Thanks you", st2020.donate(new MypaypalService()));
    }
}
