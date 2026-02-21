package ratelimiter;

public class Main {
    public static void main(String[] args) {
        // TokenBucket tokenBucket = new TokenBucket(10, 1);

        // for (int i = 0; i < 15; i++) {
        //     boolean allowed = tokenBucket.allowRequest(1);
        //     System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Denied"));
        //     try {
        //         Thread.sleep(1);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }

   /*     SlidingWindow slidingWindow = new SlidingWindow(60, 3);

        for (int i = 0; i < 5; i++) {
            boolean allowed = slidingWindow.allowRequest();
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Denied"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            Thread.sleep(61000);
        }catch(InterruptedException e){
            e.printStackTrace();
            }
         // Wait for the sliding window to reset
        boolean allowed = slidingWindow.allowRequest();
        System.out.println("Request after wait: " + (allowed ? "Allowed" : "Denied"));*/

        //Test Leaky Bucket
        LeakyBucket leakyBucket = new LeakyBucket(3, 1);

        for(int i=0;i<10;i++){
            boolean allowed = leakyBucket.allowRequest();
            System.out.println("Leaky Bucket Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Denied"));
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
