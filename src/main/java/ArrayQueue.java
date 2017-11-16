public class ArrayQueue<T> implements CustomQueue<T> {

    private Object[] array;
    private int rear;
    private int front;
    private int nElem;
    private int maxSize;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
        rear = -1;
        front = 0;
        nElem = 0;

    }

    public void enqueue(T value) {

        if(!isFull()) {
            rear = (rear == array.length - 1) ? -1 : rear; // cyclic tranfer
            array[++rear] = value;
            nElem++;
        } else {
            Object[] oldArray = array;
            array = new Object[(int)(array.length * 1.5)];
            if(rear < front) {
                System.arraycopy(oldArray, front, array, 0, oldArray.length - front);
                System.arraycopy(oldArray, 0, array, oldArray.length - front, rear + 1);
                front = 0;
                rear = oldArray.length - 1;
                array[++rear] = value;
                nElem++;
            } else {
                System.arraycopy(oldArray, front, array, 0, rear + 1);
                array[++rear] = value;
                nElem++;
            }
        }
    }

    public T dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
        }
        T temp = (T) array[front++];
        front = (front == array.length) ? 0 : front; // cyclic transfer
        nElem--;
        return temp;
    }

    public void print() {

        for(int i = front; i < nElem; i++) {
            System.out.println(array[i]);
        }
    }


    public boolean isFull() { // check queue overflow
        return (nElem == array.length);
    }

    public boolean isEmpty() {
        return (nElem == 0);
    }
}
