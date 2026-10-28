package proje;

public class Memory {

    private String storedData;

    public void write(String data) {
        storedData = data;
    }

    public String read() {
        return storedData;
    }

    public boolean isEmpty() {
        return storedData == null;
    }
}