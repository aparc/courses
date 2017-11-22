package queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import queue.ArrayQueue;

public class ArrayQueueTest {

    private final ArrayQueue queue = new ArrayQueue(3);


    @Test
    @DisplayName("Добавление элемента в очередь")
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
    @DisplayName("Расширение очереди")
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
    @DisplayName("Удаление элемента из очереди")
    public void testDequeue_validParams_removeItemFromQueue() {
        // setup
        int initialLength = 1;
        ArrayQueue queue = new ArrayQueue<String>(initialLength);
        queue.enqueue("test1");

        // when
        String result = (String) queue.dequeue();

        //then
        Assertions.assertTrue(result.equals("test1"));
    }

    @Test
    @DisplayName("Расширение очереди при условии циклического переноса")
    public void testEnqueue_validParams_expandQueueCyclicTransferCase(){
        //setup
        int initialLength = 3;
        ArrayQueue queue = new ArrayQueue(initialLength);

        //when
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");
        queue.dequeue();
        queue.enqueue("test4");
        queue.enqueue("test5");

        //then
        Assertions.assertTrue(queue.getArray().length > initialLength);

    }

}
