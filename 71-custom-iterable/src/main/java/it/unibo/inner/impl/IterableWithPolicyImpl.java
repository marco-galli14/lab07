package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.Predicate;
import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
        
    private List<T> lista;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] init) {
        this(init, new Predicate<T>() {
            public boolean test(T elem) {return true;}
        });
    }

    public IterableWithPolicyImpl(final T[] init, Predicate<T> filter) {
        this.lista = List.of(init);
        this.filter = filter;
    }
    
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> ris = new it();
        return ris;
    }

    private class it implements Iterator<T>{
    
        private int i = 0;

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return lista.get(i++);
        }

        @Override
        public boolean hasNext() {
            boolean ris = false;
            while(ris == false && i < lista.size()) {
                ris = filter.test(lista.get(i));
                if(!ris)
                    i++;
            }
            return ris;
        }
    }

    @Override
    public String toString() {
        return "" + lista;
    }

}
