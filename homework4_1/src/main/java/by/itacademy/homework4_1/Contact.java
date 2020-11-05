package by.itacademy.homework4_1;

public class Contact {
    private String name;
    private String data;
    private boolean isPhone = true;

    public boolean isPhone() { return isPhone; }

    public void setPhone(boolean phone) { this.isPhone = phone; }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

}
