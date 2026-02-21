package ratelimiter;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
//Sliding Window Log
public class SlidingWindow {
    // SlidingWindow rate limiter implementation would go here
    private final long windowSizeInSeconds;
    private final long maxRequestsPerWindow;
    private final Queue<Long> requestTimestamps;

    public SlidingWindow(long windowSizeInSeconds, long maxRequestsPerWindow) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = Instant.now().getEpochSecond();
        long windowStart = now - windowSizeInSeconds;

        // Remove timestamps that are outside the current window
        while (!requestTimestamps.isEmpty() && requestTimestamps.peek() <= windowStart) {
            requestTimestamps.poll();
        }

        if (requestTimestamps.size() < maxRequestsPerWindow) {
            requestTimestamps.add(now);
            return true; // Request allowed
        } 
        
        return false; // Request denied
        
    }
}