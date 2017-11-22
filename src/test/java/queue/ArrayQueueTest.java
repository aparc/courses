package queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest {

    private ArrayQueue queue;
    private int initialLength;

    @BeforeEach
    public void initializeQueue(){
        initialLength = 3;
        queue = new ArrayQueue(initialLength);
    }

    @Test
    @DisplayName("Добавление элемента в очередь")
    public void testEnqueue_validParams_addItemToQueue() throws NoSuchFieldException {

        String var = "Test";

        queue.enqueue(var);

        Assertions.assertTrue(queue.getElem() == 1);
    }

    @Test
    @DisplayName("Расширение очереди")
    public void testEnqueue_validParams_expandQueue() {

        String var1 = "Test1";
        String var2 = "Test2";
        String var3 = "Test3";
        String var4 = "Test4";

        queue.enqueue(var1);
        queue.enqueue(var2);
        queue.enqueue(var3);
        queue.enqueue(var4);

        Assertions.assertTrue(queue.getArray().length > initialLength);
    }

    @Test
    @DisplayName("Удаление элемента из очереди")
    public void testDequeue_validParams_removeItemFromQueue() {

        queue.enqueue("test1");
        String result = (String) queue.dequeue();

        Assertions.assertTrue(result.equals("test1"));
    }

    @Test
    @DisplayName("Расширение очереди при условии циклического переноса")
    public void testEnqueue_validParams_expandQueueCyclicTransferCase(){

        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");
        queue.dequeue();
        queue.enqueue("test4");
        queue.enqueue("test5");

        Assertions.assertTrue(queue.getArray().length > initialLength);

    }

}
