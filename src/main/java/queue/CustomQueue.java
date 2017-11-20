package queue;

public interface CustomQueue<T> {

    void enqueue(T value);

    T dequeue();

    void print();

}
