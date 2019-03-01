/* This ImmutableQueue is the implementation of the first assignment. */

import java.util.ArrayList;
import java.util.List;

public final class ImmutableQueue<T> implements Queue<T> {
    private List<T> list;

    public ImmutableQueue() {
        list = new ArrayList<T>();
    }

    private ImmutableQueue(List<T> list) {
        this.list = list;
    }

    @Override
    public ImmutableQueue<T> enQueue(T t) {
        List<T> newList = new ArrayList<T>(list);
        newList.add(t);
        return new ImmutableQueue<T>(newList);
    }

    @Override
    public ImmutableQueue<T> deQueue() {
        if (list.isEmpty()) {
            return this; // undefined behavior according to the requirement.
        }
        List<T> newList = new ArrayList<T>(list.subList(1, list.size()));
        return new ImmutableQueue<T>(newList);
    }

    @Override
    public T head() {
        if (list.isEmpty()) {
            return null; // undefined behavior according to the requirement.
        }
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
