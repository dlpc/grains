/*
 * Copyright 2013 Cameron Beccario
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.nullschool.collect.basic;

import net.nullschool.collect.ConstList;
import net.nullschool.reflect.PublicInterfaceRef;
import net.nullschool.util.ArrayTools;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import static net.nullschool.collect.basic.BasicTools.*;
import static net.nullschool.collect.basic.BasicCollections.*;


/**
 * 2013-03-15<p/>
 *
 * @author Cameron Beccario
 */
@PublicInterfaceRef(BasicConstList.class)
final class BasicListN<E> extends BasicConstList<E> {

    private final E[] elements;

    @SuppressWarnings("unchecked")
    BasicListN(Object[] elements) {
        assert elements.getClass() == Object[].class;
        assert elements.length > 1;
        this.elements = (E[])elements;
    }

    @Override public int size() {
        return elements.length;
    }

    @Override public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override public int indexOf(Object o) {
        return ArrayTools.indexOf(o, elements);
    }

    @Override public int lastIndexOf(Object o) {
        return ArrayTools.lastIndexOf(o, elements);
    }

    @Override public E get(int index) {
        return elements[index];
    }

    @Override public Object[] toArray() {
        return elements.clone();
    }

    @Override public <T> T[] toArray(T[] a) {
        int size = elements.length;
        // Cast is safe because Array.newInstance will return an array of type T[].
        @SuppressWarnings("unchecked") T[] result = a.length < size ?
            (T[])Array.newInstance(a.getClass().getComponentType(), size) :
            a;
        // noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, result, 0, size);
        if (result.length > size) {
            result[size] = null;
        }
        return result;
    }

    @Override public ConstList<E> with(E e) {
        return with(elements.length, e);
    }

    @Override public ConstList<E> with(int index, E e) {
        return new BasicListN<>(insert(elements, index, e));
    }

    @Override public ConstList<E> withAll(Collection<? extends E> c) {
        return withAll(elements.length, c);
    }

    @Override public ConstList<E> withAll(int index, Collection<? extends E> c) {
        if (0 <= index && index <= elements.length) {
            return c.isEmpty() ? this : new BasicListN<E>(insertAll(elements, index, c));
        }
        throw new IndexOutOfBoundsException();
    }

    @Override public ConstList<E> replace(int index, E e) {
        return new BasicListN<>(BasicTools.replace(elements, index, e));
    }

    @Override public ConstList<E> without(Object o) {
        int index = ArrayTools.indexOf(o, elements);
        return index < 0 ? this : delete(index);
    }

    @Override public ConstList<E> delete(int index) {
        return condenseToList(BasicTools.delete(elements, index));
    }

    @Override public ConstList<E> withoutAll(Collection<?> c) {
        if (c.isEmpty()) {
            return this;
        }
        Object[] shrunk = deleteAll(elements, c);
        return shrunk.length == size() ? this : BasicCollections.<E>condenseToList(shrunk);
    }

    @Override public ConstList<E> subList(int fromIndex, int toIndex) {
        return condenseToList(Arrays.copyOfRange(elements, fromIndex, toIndex));
    }

    @Override public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
