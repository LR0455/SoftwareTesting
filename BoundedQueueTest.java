import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
* ID      Characteristic
* C1      positive integer of arguments
* C2      add non-null value
* C3      constraint satisfied
* C4      return non-null value
* */

/* Step5: Write Test - (e)
* */
class BoundedQueueTest {

    BoundedQueue bq;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bq = new BoundedQueue(5);
        bq.enQueue(1);
        bq.enQueue("cat");
    }
    // BounedQueue
    @Test
    void testBounedQueue_T() {
        assertNotNull(bq);
    }
    @Test
    void testBounedQueue_F() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bq = new BoundedQueue(-1);
        });
    }

    // enQueue
    @Test
    void test_enQueue_TT(){
        bq.enQueue(3);
        assertEquals(1, bq.deQueue());
        assertEquals("cat", bq.deQueue());
        assertEquals(3, bq.deQueue());
    }
    @Test
    void test_enQueue_FF(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            bq.enQueue(null); // add null object
        });
    }
    @Test
    void test_enQueue_TF(){
        Assertions.assertThrows(IllegalStateException.class, () -> {
            bq.enQueue(1); bq.enQueue(2); bq.enQueue(3); // full queue
            bq.enQueue(4); // over queue capacity
        });
    }

    // deQueue
    @Test
    void test_deQueue_TT(){
        assertEquals(1, bq.deQueue());
        assertEquals("cat", bq.deQueue());
    }
    @Test
    void test_deQueue_FF(){
        Assertions.assertThrows(IllegalStateException.class, () -> {
            bq.deQueue(); bq.deQueue(); // empty queue
            bq.deQueue(); // invalid pop element
        });
    }
    @Test
    void test_deQueue_TF(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            bq.deQueue(); bq.deQueue(); // empty queue
            bq.enQueue(null); // add null object
            assertNull(bq.deQueue());
        });
    }

    // isEmpty
    @Test
    void test_isEmpty_T(){
        bq.deQueue(); bq.deQueue(); // empty queue
        assertEquals(true, bq.isEmpty());
    }
    @Test
    void test_isEmpty_F(){
        assertEquals(false, bq.isEmpty());
    }

    // isFull
    @Test
    void test_isFull_T(){
        bq.enQueue(1); bq.enQueue(2); bq.enQueue(3); // full queue
        assertEquals(true, bq.isFull());
    }
    @Test
    void test_isFull_F(){
        assertEquals(false, bq.isFull());
    }

}