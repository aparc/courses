package queue;

public class App {
    public static void main(String[] args) {

        CustomQueue<String> myQueue = new ArrayQueue<String>(5);

        myQueue.enqueue("Dog");
        myQueue.enqueue("Cat");
        myQueue.enqueue("Bird");
        myQueue.enqueue("Fish");
        myQueue.enqueue("Tiger");
        System.out.println(myQueue.dequeue() + " is dequeue");

        System.out.println("------------------------");
        myQueue.enqueue("Blow");
        myQueue.enqueue("World");
        myQueue.enqueue("Peace");

        myQueue.print();

    }
}
