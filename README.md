# RateLimiter

The Rate Limiter is used to limit the number of times an api can be used by a client. 
For example, Constraints like 5 calls per 20 seconds are only allowed can be implemented using this Rate Limiter.

This is built as a jar. Any api can instantiate the Rate Limiter with the frequency, n and duration, d. 
In the window of "d" number of seconds, only "n" number of calls will return true;

------------------------------

### How to compile :

mvn clean package

------------------------------

### How to Use :

RateLimiter rateLimiter = new RateLimiter(5,20);

//This returns true if in the past duration, there < frequency success calls. Else, it returns false.

rateLimiter.check();

------------------------------

### Test Cases :

A sample Web server which uses the RateLimiter is written in the JUnit TestCases.

