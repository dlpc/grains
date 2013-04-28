package net.nullschool.collect.basic;

import net.nullschool.collect.ConstList;
import net.nullschool.util.ArrayTools;

import java.util.*;

import static net.nullschool.collect.basic.BasicTools.*;


/**
 * 2013-03-15<p/>
 *
 * @author Cameron Beccario
 */
final class BasicList1<E> extends AbstractBasicConstList<E> {

    private final E e0;

    @SuppressWarnings("unchecked")
    BasicList1(Object e0) {
        this.e0 = (E)e0;
    }

    @Override public int size() {
        return 1;
    }

    @Override public boolean isEmpty() {
        return false;
    }

    @Override public boolean contains(Object o) {
        return Objects.equals(o, e0);
    }

    @Override public int indexOf(Object o) {
        return Objects.equals(o, e0) ? 0 : -1;
    }

    @Override public int lastIndexOf(Object o) {
        return Objects.equals(o, e0) ? 0 : -1;
    }

    @Override public E get(int index) {
        if (index == 0) {
            return e0;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public Object[] toArray() {
        return new Object[] {e0};
    }

    @Override public ConstList<E> with(E e) {
        return BasicConstList.of(e0, e);
    }

    @Override public ConstList<E> with(int index, E e) {
        switch (index) {
            case 0: return BasicConstList.of(e, e0);
            case 1: return BasicConstList.of(e0, e);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public ConstList<E> withAll(Collection<? extends E> c) {
        return c.isEmpty() ? this : BasicConstList.<E>condense(insert(c.toArray(), 0, e0));
    }

    @Override public ConstList<E> withAll(int index, Collection<? extends E> c) {
        if (0 <= index && index <= 1) {
            return c.isEmpty() ? this : BasicConstList.<E>condense(insertAll(new Object[] {e0}, index, c));
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public ConstList<E> replace(int index, E e) {
        if (index == 0) {
            return BasicConstList.of(e);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public ConstList<E> without(Object o) {
        return !contains(o) ? this : BasicConstList.<E>of();
    }

    @Override public ConstList<E> delete(int index) {
        if (index == 0) {
            return BasicConstList.of();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public ConstList<E> withoutAll(Collection<?> c) {
        return !c.contains(e0) ? this : BasicConstList.<E>of();
    }

    @Override public ConstList<E> subList(int fromIndex, int toIndex) {
        ArrayTools.checkRange(fromIndex, toIndex, 1);
        // three possibilities: [0, 0), [0, 1), [1, 1)
        return fromIndex != toIndex ? this : BasicConstList.<E>of();
    }

    private boolean equals(List<?> that) {
        if (that.size() == 1) {
            Object o;
            try {
                o = that.get(0);
            }
            catch (IndexOutOfBoundsException e) {
                return false;
            }
            return Objects.equals(o, e0);
        }
        return false;
    }

    @Override public boolean equals(Object that) {
        return this == that || that instanceof List && equals((List<?>)that);
    }

    @Override public int hashCode() {
        return 31 + Objects.hashCode(e0);
    }
}
