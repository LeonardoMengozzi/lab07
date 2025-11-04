package it.unibo.inner.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private List<T> values;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] values, final Predicate<T> filter) {
        this.values = new ArrayList<T>(List.of(values));
        this.filter = filter;
    }

    public IterableWithPolicyImpl (final T[] values) {
        this(values, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    private class PolicyIterator implements Iterator<T> {
        private int i;

        @Override
        public boolean hasNext() {
            while (this.i < IterableWithPolicyImpl.this.values.size()){
                final T elem = IterableWithPolicyImpl.this.values.get(i);
                if (IterableWithPolicyImpl.this.filter.test(elem)) {
                    return true;
                }
                i++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return IterableWithPolicyImpl.this.values.get(i++);
            }
            throw new NoSuchElementException();
        }
        
    }
}
