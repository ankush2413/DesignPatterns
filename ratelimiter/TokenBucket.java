package ratelimiter;
import java.time.Instant;

public class TokenBucket {
    private final long capacity;
    private final double refillRate; // / Rate at which tokens are added to the bucket (tokens per second)
    private double currentTokens;
    private Instant lastRefillTimestamp; // Last time we refilled the bucket

    public TokenBucket(long capacity, double refillRate){
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentTokens = capacity; // Start with a full bucket
        this.lastRefillTimestamp = Instant.now();
    }

    public synchronized boolean allowRequest(int tokensRequired){
        refillTokens(); // First, add any new tokens based on elapsed time


        if(currentTokens >= tokensRequired){
            currentTokens -= tokensRequired;
            return true; // Request allowed
        } else {
            return false; // Not enough tokens, request denied
        }
    }

    private void refillTokens(){
        Instant now =Instant.now();
        double tokensToAdd = (now.toEpochMilli() - lastRefillTimestamp.toEpochMilli()) * refillRate / 1000.0; // milisecond to second
        this.currentTokens = Math.min(capacity, this.currentTokens + tokensToAdd);  // Add tokens, but don't exceed capacity
        this.lastRefillTimestamp = now;
    }
}