package ratelimiter;

import java.time.Instant;

public class LeakyBucket {
    // LeakyBucket rate limiter implementation would go here
    private int capacity;
    private Instant lastLeakTimestamp;
    private double leakRate; // Rate at which requests leak out (requests per second)
    private double currentCapacity;

    public LeakyBucket(int capacity, double leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentCapacity = 0;
        this.lastLeakTimestamp = Instant.now();
    }

    public synchronized boolean allowRequest(){
        leak();
        if(currentCapacity+1>capacity)
            return false;

        currentCapacity+=1;
        return true;
    }

    private void leak(){
        Instant now = Instant.now();
        double leakedRequest = (now.toEpochMilli() - lastLeakTimestamp.toEpochMilli()) * leakRate / 1000.0;

        if(leakedRequest>0){
            currentCapacity = Math.max(0, (currentCapacity - leakedRequest));
            lastLeakTimestamp = now;
        }
    }
}