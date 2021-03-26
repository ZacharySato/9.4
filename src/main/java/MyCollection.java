import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection<E> implements Collection<E> {

    private int size;

    //magic number 1
    private Object[] elementData = new Object[10];

    @Override
    public final boolean add(final E e) {
        if (size == elementData.length) {
            //magic number 2
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean isEmpty() {
        return size == 0;
    }

    @Override
    public final Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public final boolean contains(final Object o) {
        for (E e : this) {
            if (e == null && o == null) {
                return true;
            }
            if (e != null && e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T> T[] toArray(final T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        } else {
            for (int i = 0; i < size; i++) {
                a[i] = (T) elementData[i];
            }
        }
        return a;
    }

    @Override
    public final boolean remove(final Object o) {
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E e = i.next();
            if (e != null) {
                if (e.equals(o)) {
                    i.remove();
                    return true;
                }
            } else {
                if (o == null) {
                    i.remove();
                    return true;
                }
            }

        }
        return false;
    }


    @Override
    public final boolean containsAll(final Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean addAll(final Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public final boolean removeAll(final Collection<?> c) {
        boolean flag = false;
        //при удалении элемента размер коллекции уменьшается, поэтому с конца:
        for (int i = size - 1; i >= 0; i--) {
            Object o = elementData[i];
            if (c.contains(o)) {
                remove(o);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public final boolean retainAll(final Collection<?> c) {
        boolean flag = false;
        for (int i = size - 1; i >= 0; i--) {
            Object o = elementData[i];
            if (!c.contains(o)) {
                remove(o);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public final void clear() {
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
        }
    }

    private class MyIterator<T> implements Iterator<T> {

        private boolean removed = true;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            removed = false;
            return (T) elementData[cursor++];
        }

        @Override
        public void remove() {
            if (removed) {
                throw new IllegalStateException();
            }
            System.arraycopy(elementData, cursor, elementData, cursor - 1, size - cursor);
            elementData[--size] = null;
            cursor--;
            removed = true;
        }
    }
}