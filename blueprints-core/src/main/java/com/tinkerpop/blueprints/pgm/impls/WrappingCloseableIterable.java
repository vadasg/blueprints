package com.tinkerpop.blueprints.pgm.impls;

import com.tinkerpop.blueprints.pgm.CloseableIterable;

import java.util.Iterator;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class WrappingCloseableIterable<T> implements CloseableIterable<T> {

    private final Iterable<T> iterable;

    public WrappingCloseableIterable(final Iterable<T> iterable) {
        this.iterable = iterable;
    }

    public Iterator<T> iterator() {
        return new WrappingIterator();
    }

    public void close() {
        if (this.iterable instanceof CloseableIterable) {
            ((CloseableIterable) this.iterable).close();
        }
    }

    public String toString() {
        return this.iterable.toString();
    }

    private class WrappingIterator implements Iterator<T> {
        private final Iterator<T> itty = iterable.iterator();

        public void remove() {
            this.itty.remove();
        }

        public T next() {
            return this.itty.next();
        }

        public boolean hasNext() {
            return this.itty.hasNext();
        }
    }
}