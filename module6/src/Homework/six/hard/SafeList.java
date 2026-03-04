package Homework.six.hard;
import java.util.*;

public class SafeList<T> implements List<T> {

    private final LinkedList<T> inner = new LinkedList<>();

    @Override
    public boolean add(T element) {
        if (element == null || inner.contains(element)) return false;
        return inner.add(element);
    }

    @Override
    public void add(int index, T element) {
        if (element == null || inner.contains(element)) return;
        if (index < 0 || index > inner.size()) return;
        inner.add(index, element);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= inner.size()) return null;
        return inner.get(index);
    }

    @Override
    public T set(int index, T element) {
        if (element == null || inner.contains(element)) return null;
        if (index < 0 || index >= inner.size()) return null;
        return inner.set(index, element);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= inner.size()) return null;
        return inner.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        return inner.remove(o);
    }

    @Override
    public int size() {
        return inner.size();
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return inner.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return inner.iterator();
    }

    @Override
    public Object[] toArray() {
        return inner.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return inner.toArray(a);
    }

    @Override
    public int indexOf(Object o) {
        return inner.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return inner.lastIndexOf(o);
    }

    @Override
    public List<T> subList(int from, int to) {
        return inner.subList(from, to);
    }

    @Override
    public ListIterator<T> listIterator() {
        return inner.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return inner.listIterator(i);
    }

    @Override
    public void clear() {
        inner.clear();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T el : c) changed |= add(el);
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > inner.size()) return false;
        int i = index;
        boolean changed = false;
        for (T el : c) {
            if (el != null && !inner.contains(el)) {
                inner.add(i++, el);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return inner.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return inner.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return inner.retainAll(c);
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}
