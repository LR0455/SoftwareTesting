import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PriorityQueueTest {
    @ParameterizedTest
    @MethodSource("provideParameter")
    void testParameter(Object[] org_arr, Object[] sorted_arr) {
        PriorityQueue pq = new PriorityQueue();
        pq.addAll(Arrays.asList(org_arr)); // need collections type

        int index = 0;
        while(!pq.isEmpty())  // sorted
            assertEquals(sorted_arr[index ++], pq.poll());
    }

    static Stream<Arguments> provideParameter() {
        return Stream.of(
                // org_arr, sorted_arr
                arguments((Object) new Object[] { 2,  1,  5 }, (Object) new Object[] {  1,  2,  5 }),
                arguments((Object) new Object[] {-1, 10, -3 }, (Object) new Object[] { -3, -1, 10 }),
                arguments((Object) new Object[] {-4, 0, 0, 10 }, (Object) new Object[] { -4, 0, 0, 10 }),
                arguments((Object) new Object[] {1000, -1000, -3, 12345 }, (Object) new Object[] { -1000, -3, 1000, 12345 }),
                arguments((Object) new Object[] {3, 7, 0, 9, 10 }, (Object) new Object[] { 0, 3, 7, 9, 10 })
        );
    }

    @Test
    void testException() {
        Exception exception1 = assertThrows(NullPointerException.class, () -> {
            PriorityQueue pq = new PriorityQueue();
            pq.offer(null);
        });
        Exception exception2 = assertThrows(NullPointerException.class, () -> {
            PriorityQueue pq = new PriorityQueue();
            pq.addAll(null);
        });
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue pq = new PriorityQueue();
            pq.addAll(pq);
        });
    }
}