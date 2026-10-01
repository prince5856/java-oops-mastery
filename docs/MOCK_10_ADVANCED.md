# Mock 10: Advanced - System Design (Choose 1 of 3)

**Duration**: 60 minutes | **Difficulty**: Advanced | **Topics**: System Design, Architecture, Scalability, Edge Cases, SOLID Principles

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): Explain the difference between vertical and horizontal scaling. When use each?

**Q2** (3 min): What are the SOLID principles? Give a real-world example violating each.

**Q3** (2 min): Describe the CAP theorem. Why can't you have all three (Consistency, Availability, Partition Tolerance)?

**Q4** (2 min): What's the difference between stateless and stateful services? Which is easier to scale?

---

## Section 2: System Design Problem (40 min)

### Choose ONE of these 3 options:

---

## **OPTION A: Rate Limiter (Token Bucket Algorithm)**

**Problem**: Design a rate limiter that enforces per-user request limits.

**Requirements**:
1. Token bucket algorithm:
   - Each user has bucket of N tokens
   - Refill R tokens per second
   - Request costs 1 token
   - Deny if no tokens available

2. API interface:
```java
class RateLimiter {
    // Constructor: capacity, refill rate (tokens/sec)
    RateLimiter(int capacity, double refillRate);
    
    // Returns true if request allowed, false if denied
    boolean allowRequest(String userId);
    
    // Get current tokens for user
    double getCurrentTokens(String userId);
}
```

3. Functional requirements:
   - Support unlimited users
   - Different limits per user (configurable)
   - Handle high concurrency (1000+ req/sec)
   - Precise timing (millisecond accuracy)

4. Test code:
```java
RateLimiter limiter = new RateLimiter(10, 5);  // 10 capacity, 5 tokens/sec

// 10 requests allowed (capacity)
for (int i = 0; i < 10; i++) {
    assertTrue(limiter.allowRequest("user1"));
}

// 11th denied
assertFalse(limiter.allowRequest("user1"));

// After 1 second, 5 new tokens
Thread.sleep(1000);
for (int i = 0; i < 5; i++) {
    assertTrue(limiter.allowRequest("user1"));
}
```

5. Bonus: 
   - Per-user limits (user1: 100 req/min, user2: 1000 req/min)
   - Sliding window algorithm
   - Metrics tracking (rejected requests, active users)

---

## **OPTION B: Parking Lot System (Object-Oriented Design)**

**Problem**: Design a parking lot system for a multi-level garage.

**Requirements**:
1. Class hierarchy:
   - `ParkingLot` (system manager)
   - `Level` (each floor)
   - `ParkingSpot` (individual spot)
   - `Vehicle` (base: Car, Bus, Motorcycle)
   - `Ticket` (parking receipt)

2. Core operations:
```java
Ticket parkVehicle(Vehicle v);      // Returns ticket
boolean unparkVehicle(Ticket t);    // Frees spot, calculates fee
List<ParkingSpot> findAvailableSpots(Vehicle v);
```

3. Requirements:
   - Multiple levels (e.g., 5 floors)
   - Multiple spot sizes (Compact, Regular, Large for Bus)
   - Track occupancy (% full)
   - Calculate parking fee ($5/hour base)
   - Prevent double-parking
   - Thread-safe concurrent operations

4. Test code:
```java
ParkingLot lot = new ParkingLot(5, 100);  // 5 levels, 100 spots each

Vehicle car = new Car("ABC123");
Ticket t1 = lot.parkVehicle(car);
System.out.println("Parked: " + t1.getSpotId());

Thread.sleep(3600000);  // 1 hour
boolean success = lot.unparkVehicle(t1);
System.out.println("Unparked, fee: $" + t1.calculateFee());
```

5. Bonus:
   - Reservation system (pre-book spots)
   - Discount validation (e.g., monthly pass)
   - Entrance/exit gates with ticket scanning
   - Full occupancy alert

---

## **OPTION C: Web Crawler (Distributed Crawling)**

**Problem**: Design a web crawler that crawls a website and builds a sitemap.

**Requirements**:
1. Main class:
```java
class WebCrawler {
    WebCrawler(int maxDepth, int numThreads, Set<String> allowedDomains);
    
    void crawl(String startUrl);
    
    Map<String, PageInfo> getSitemap();  // URL → title, links
}
```

2. Features:
   - Fetch pages using HTTP
   - Extract URLs using regex or HTML parser
   - Avoid duplicate crawls (visited set)
   - Respect robots.txt
   - Max depth limit
   - Thread pool for parallel crawling

3. Functional requirements:
   - Thread-safe URL queue
   - Avoid infinite loops (cycle detection)
   - Respect rate limiting (delay between requests)
   - Handle errors gracefully (404, timeouts)
   - Only crawl same domain (prevent external redirects)

4. Test code:
```java
WebCrawler crawler = new WebCrawler(2, 5, 
    Set.of("example.com"));  // 2 depth, 5 threads, only example.com

crawler.crawl("https://example.com");

Map<String, PageInfo> sitemap = crawler.getSitemap();
sitemap.forEach((url, info) -> {
    System.out.println(url + " -> " + info.getTitle());
});
```

5. Bonus:
   - Respect robots.txt crawl delay
   - Sitemap.xml generation
   - Detect cycles (canonical URLs)
   - Page rank/priority
   - Metadata extraction

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: How would you evolve your system from single-server to distributed? What breaks?

**Q6** (5 min): Describe a design decision in your system. What trade-offs did you make? Alternatives?

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| System Design (Architecture + Implementation + Testing) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**Grading System Design**:
- **25 pts**: Architecture (class hierarchy, clear separation of concerns)
- **25 pts**: Implementation (complete, working, handles edge cases)
- **10 pts**: Testing (demonstrates correctness, thread-safety)

**90+**: Ready for senior roles | **75-89**: Good foundation | **<75**: Review system design concepts

---

See answers: `MOCK_10_ANSWERS.md`

---

## Strategy Tips

1. **Start with architecture** - diagram your classes/components first
2. **Handle edge cases** - null checks, empty collections, concurrent access
3. **Write clean code** - meaningful names, single responsibility
4. **Test thoroughly** - verify core behavior, concurrency, boundaries
5. **Discuss trade-offs** - show you understand why you chose X over Y
