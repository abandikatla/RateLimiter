# RateLimiter

The Rate Limiter is used to limit the number of times an api can be used by a client. 
For example, Constraints like 5 calls per 20 seconds are only allowed can be implemented using this Rate Limiter.

This is built as a jar. Any api can instantiate the Rate Limiter with the frequency, n and duration, d. 
In the window of "d" number of seconds, only "n" number of calls will return true;

### How to compile :

mvn clean package

### How to Use :

RateLimiter rateLimiter = new RateLimiter(5,20);

//This returns true if in the past duration, there < frequency success calls. Else, it returns false.

rateLimiter.check();

### Test Cases :

A sample Web server which uses the RateLimiter is written in the JUnit TestCases.

### Classes :
1. src/main/java/com/abandikatla/ratelimiter/RateLimiter.java : The RateLimiter which takes frequency and duration in the constructor. The check() returns boolean, indicating if the api call is allowed or not.
2. src/main/java/com/abandikatla/ratelimiter/Util.java : The utility methods are present here.
2. src/test/java/com/abandikatla/ratelimiter/webapp/Server.java : The test web server, using Apache CXF. This class starts the server.
2. src/test/java/com/abandikatla/ratelimiter/webapp/APITest.java : A test web server api, using Apache CXF. The api returns the result indicating if the call is within the limit or not.
3. src/test/java/com/abandikatla/ratelimiter/RateLimiterTest.java : The JUnit test case, which invokes the above test api.


