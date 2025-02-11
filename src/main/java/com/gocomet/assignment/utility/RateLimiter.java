package com.gocomet.assignment.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class RateLimiter {

    private final int MAX_REQUESTS = 5; // Maximum API requests per time window
    private final long TIME_WINDOW = 60 * 1000; // Time window in milliseconds (1 minute)

    private final Map<Long, UserRequestInfo> userRequestMap = new ConcurrentHashMap<>();

    /**
     * Checks if the given user is allowed to make a request within the configured rate limit window.
     * @param userId the ID of the user making the request
     * @return true if the user is allowed to make a request, false otherwise
     */
    public boolean isAllowed(Long userId) {
        long currentTime = System.currentTimeMillis();

        // Check if the user already exists in the userRequestMap
        UserRequestInfo requestInfo = userRequestMap.getOrDefault(userId, new UserRequestInfo(0, currentTime));

        // Check if the current time has passed the time window
        if (currentTime - requestInfo.getStartTime() > TIME_WINDOW) {
            // Reset the counter and start a new time window
            requestInfo.setStartTime(currentTime);
            requestInfo.setRequestCount(0);
        }

        // If request limit is exceeded, deny the request
        if (requestInfo.getRequestCount() >= MAX_REQUESTS) {
            return false;
        }

        // Otherwise, increment the request count and allow the request
        requestInfo.incrementRequestCount();
        userRequestMap.put(userId, requestInfo);
        return true;
    }
    // Inner class to hold user request data
    @Setter
    @Getter
    private static class UserRequestInfo {
        private int requestCount; // Number of API requests made by the user
        private long startTime; // Start time of the current time window

        public UserRequestInfo(int requestCount, long startTime) {
            this.requestCount = requestCount;
            this.startTime = startTime;
        }

        public void incrementRequestCount() {
            this.requestCount++;
        }
    }
}
