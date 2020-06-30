import java.io.IOException;

class StutterTest {
    Stutter stutter;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        stutter = new Stutter();
    }

    @org.junit.jupiter.api.Test
    //
    void testStut1() throws IOException {


        String args[] = {"./src/testfile"};
        stutter.main(args);
    }
    void testcheckDupes(){
    }
    void testisDelimit() {

    }
}