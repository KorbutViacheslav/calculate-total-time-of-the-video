package org.profitsoft;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicDouble {
    private final AtomicReference<Double> value;

    public AtomicDouble(double initialValue) {
        value = new AtomicReference<>(initialValue);
    }

    public double get() {
        return value.get();
    }

    public void set(double newValue) {
        value.set(newValue);
    }

    public double addAndGet(double delta) {
        Double oldValue;
        Double newValue;
        do {
            oldValue = value.get();
            newValue = (oldValue == null ? delta : oldValue + delta);
        } while (!value.compareAndSet(oldValue, newValue));
        return newValue;
    }
}
