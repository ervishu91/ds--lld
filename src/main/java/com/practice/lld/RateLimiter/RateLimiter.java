package com.practice.lld.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Per-user token-bucket rate limiter.
 *
 * A bucket starts full, each accepted request consumes one token, and tokens are
 * replenished continuously at {@code refillRate} tokens per second. A bucket
 * never grows past {@code maxTokens}, so a user may burst by at most that amount.
 */
public final class RateLimiter {
    // Concurrent map allows different users to be checked concurrently.
    private final Map<Integer, Bucket> usageQuota = new ConcurrentHashMap<>();
    private final int maxTokens;
    private final int refillRate;

    public RateLimiter(int maxTokens, int refillRate) {
        if (maxTokens < 1) {
            throw new IllegalArgumentException("maxTokens must be positive");
        }
        if (refillRate < 1) {
            throw new IllegalArgumentException("refillRate must be positive");
        }
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
    }

    /**
     * Returns true and consumes one token when the user's request is allowed.
     * Returns false when that user has no token available.
     */
    public boolean isRequestAllowed(int userId) {
        // computeIfAbsent both creates and stores a bucket exactly once per user.
        Bucket bucket = usageQuota.computeIfAbsent(userId,
                ignored -> new Bucket(maxTokens, refillRate));
        return bucket.tryConsume();
    }

    private static final class Bucket {
        private final int maxTokens;
        private final int refillRate;
        private double tokens;
        private long lastRefillTimeNanos;

        private Bucket(int maxTokens, int refillRate) {
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.tokens = maxTokens; // A new user is allowed an initial burst.
            this.lastRefillTimeNanos = System.nanoTime();
        }

        /** Synchronization is per user, rather than locking every user's request. */
        private synchronized boolean tryConsume() {
            refill();
            if (tokens < 1.0) {
                return false;
            }
            tokens--; // An allowed request must always consume exactly one token.
            return true;
        }

        private void refill() {
            long now = System.nanoTime();
            long elapsedNanos = now - lastRefillTimeNanos;

            // double preserves partial-token refill progress between requests.
            double tokensToAdd = elapsedNanos * (double) refillRate / 1_000_000_000.0;
            tokens = Math.min(maxTokens, tokens + tokensToAdd);
            lastRefillTimeNanos = now;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(2, 1);
        int userId = 101;

        System.out.println(limiter.isRequestAllowed(userId)); // true: token 1 of 2
        System.out.println(limiter.isRequestAllowed(userId)); // true: token 2 of 2
        System.out.println(limiter.isRequestAllowed(userId)); // false: bucket is empty

        Thread.sleep(1_100); // Refill roughly one token at one token per second.
        System.out.println(limiter.isRequestAllowed(userId)); // true
    }
}
