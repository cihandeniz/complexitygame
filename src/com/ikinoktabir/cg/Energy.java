package com.ikinoktabir.cg;

public class Energy {
    private volatile double absorbed;
    private volatile double emitted;
    private volatile double amount;

    public Energy() {
    }

    public double amount() {
        return amount;
    }

    public synchronized void sync() {
        this.amount += absorbed - emitted;

        absorbed = emitted = 0;
    }

    public void transfer(double amount, Energy energy) {
        willEmit(amount);
        energy.willAbsorb(amount);
    }

    private synchronized void willEmit(double emitted) {
        this.emitted += emitted;
    }

    private synchronized void willAbsorb(double absorbed){
        this.absorbed += absorbed;
    }
}