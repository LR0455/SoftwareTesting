import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* Step 1: Identify - (a)
* BoundedQueue (int capacity) - set maximum size of queue and initialize parameters(constructor)
*   Exception: IllegalArgumentException -> if capacity is negative number
* void enQueue (Object o) - add new object in queue
*   Exception: NullPointerException -> if o is null
*   Exception: IllegalStateException -> if queue is full
*
* Object deQueue () - return first element in queue
*   Exception: IllegalStateException -> if queue is empty
*
* boolean isEmpty() - return true if queue is empty
*
* boolean isFull() - return true if queue is full
*
* String toString() - return all elements in queue (ex: [1, 2, 3])
*
* Parameters:
*   Object[] - store all elements in queue -- (1)
*   size - total number of elements        -- (2)
*   front - index of first element         -- (3)
*   back - index of last element           -- (4)
*   capacity - maximum limit of size       -- (5)
* */

/* Step2: Develop Characteristics - (b)
* Method       Params            Returns      Values           Exception                  ChID      Characteristic              Covered by
* enQueue      [1, 2, 4, 5]                                                                C1       add non-null object
*                                                              NullPointerException                                                 C1
*                                                              IllegalStateException       C2       constraint satisfied
* deQueue      [1, 2, 3, 5]      Object       Object                                       C3       return non-null object
*                                                              IllegalStateException                                                C2
* isEmpty      [2]               boolean      true, false                                  C4       return true
* isFull       [2, 5]            boolean      true, false                                                                           C4
* toString     [1, 2, 3, 5]      String       String                                                                                C3
* */

/* Step3: Design a partitioning - (c)
* ID      Characteristic              enQueue()      deQueue()      isEmpty()      isFull()      toString()
* C1      add non-null object            O
* C2      constraint satisfied           O              O
* C3      return non-null object                        O                                            O
* C4      return boolean                                               O              O
* */

/* Step4: Define Test Requirements - (d)
* Method        Characteristics      Test Requirements      Infeasible TRs      Revised TRs    Number of TRs
* enQueue       C1, C2               {TT, FT, TF}                FT              FT -> FF           3
* deQueue       C2, C3               {TT, FT, TF}                FT              FT -> FF           3
* isEmpty       C4                   {T, F}                                                         2
* isFull        C4                   {T, F}                                                         2
* toString      C3                   {T, F}                                                         2
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

    // toString
    @Test
    void test_toString_T(){
        assertEquals("[1, cat]", bq.toString());
    }
    @Test
    void test_toString_F(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            bq.deQueue(); bq.deQueue(); // empty queue
            bq.enQueue(null); // add null object
            bq.toString();
        });
    }
}