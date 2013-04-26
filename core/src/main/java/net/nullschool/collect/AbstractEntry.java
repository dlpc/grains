package net.nullschool.collect;

import java.util.Map;
import java.util.Objects;


/**
 * 2013-02-14<p/>
 *
 * An implementation of {@link Map.Entry} that satisfies the contract constraints for {@code equals}
 * and {@code hashCode}.
 *
 * @author Cameron Beccario
 */
public abstract class AbstractEntry<K, V> implements Map.Entry<K, V> {

    /**
     * Returns the hashCode for the specified objects as if they were a map entry, exactly as
     * described by {@link Map.Entry#hashCode}.
     *
     * @param key the key
     * @param value the value
     * @return the two objects' hashCodes, xor'd together.
     */
    public static int hashCode(Object key, Object value) {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }


    private boolean equals(Map.Entry<?, ?> that) {
        return
            Objects.equals(this.getKey(), that.getKey()) &&
            Objects.equals(this.getValue(), that.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean equals(Object that) {
        return this == that || that instanceof Map.Entry && equals((Map.Entry<?, ?>)that);
    }

    /**
     * {@inheritDoc}
     */
    @Override public int hashCode() {
        return hashCode(getKey(), getValue());
    }

    /**
     * Returns a String representation of this entry equivalent to Objects.toString(getKey()) + '=' +
     * Objects.toString(getValue()).
     */
    @Override public String toString() {
        return Objects.toString(getKey()) + '=' + Objects.toString(getValue());
    }
}
