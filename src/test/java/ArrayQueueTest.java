import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest {

    @Test
    public void testEnqueue_validParams_addValueToQueue() throws NoSuchFieldException {
        //setup
        ArrayQueue queue = new ArrayQueue<String>(1);
        String var = "Test";

        // when
        queue.enqueue(var);

        // then
        Assertions.assertTrue(queue.getnElem() == 1);
        
    }
}
