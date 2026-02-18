package src.Homework.medium;

public class Box<T> {

    private T item;

    public Box(T item) {
        this.item = item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void showType() {
        System.out.println("Тип объекта: " + item.getClass().getName());
    }

    @Override
    public String toString() {
        return "Box{item=" + item + "}";
    }
}