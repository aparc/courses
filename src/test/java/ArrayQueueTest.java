import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest {

    @Test
    public void testEnqueue_validParams_addItemToQueue() throws NoSuchFieldException {
        //setup
        ArrayQueue queue = new ArrayQueue<String>(1);
        String var = "Test";

        // when
        queue.enqueue(var);

        // then
        Assertions.assertTrue(queue.getElem() == 1);
    }

    @Test
    public void testEnqueue_validParams_expandQueue() {
        //setup
        int initialLength = 3;
        ArrayQueue queue = new ArrayQueue<String>(initialLength);
        String var1 = "Test1";
        String var2 = "Test2";
        String var3 = "Test3";
        String var4 = "Test4";

        //when
        queue.enqueue(var1);
        queue.enqueue(var2);
        queue.enqueue(var3);
        queue.enqueue(var4);

        //then
        Assertions.assertTrue(queue.getArray().length > initialLength);
    }

    @Test
    public void testDequeue_validParams_removeItemFromQueue() {
        // setup
        int initialLength = 3;
        ArrayQueue queue = new ArrayQueue<String>(initialLength);
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");

        // when
        queue.dequeue();
        queue.dequeue();

        //then
        Assertions.assertTrue(queue.getElem() == 1);

    }

}
