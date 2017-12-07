package mysqlpackage.domain;

public class Producer {

    private int producerId;
    private String name;
    private String address;

    public Producer(int producerId, String name, String address) {
        this.producerId = producerId;
        this.name = name;
        this.address = address;
    }

    public int getProducerId() {
        return producerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "producerId=" + producerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
