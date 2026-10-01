# Mock 10 Answers: System Design (All 3 Options)

---

## OPTION A: Rate Limiter (Token Bucket)

### Solution Code

```java
import java.util.*;
import java.util.concurrent.*;

public class RateLimiter {
    private static class UserBucket {
        double tokens;
        long lastRefillTime;
        
        UserBucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }
    }
    
    private final int capacity;
    private final double refillRate;  // tokens per second
    private final Map<String, UserBucket> buckets = 
        new ConcurrentHashMap<>();
    
    public RateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }
    
    public boolean allowRequest(String userId) {
        UserBucket bucket = buckets.computeIfAbsent(userId, 
            k -> new UserBucket(capacity));
        
        synchronized(bucket) {
            // Refill tokens based on time elapsed
            long now = System.currentTimeMillis();
            long elapsed = now - bucket.lastRefillTime;
            double tokensToAdd = (elapsed / 1000.0) * refillRate;
            
            bucket.tokens = Math.min(capacity, 
                bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
            
            // Check if request allowed
            if (bucket.tokens >= 1) {
                bucket.tokens -= 1;
                return true;
            }
            return false;
        }
    }
    
    public double getCurrentTokens(String userId) {
        UserBucket bucket = buckets.get(userId);
        return bucket == null ? 0 : bucket.tokens;
    }
}

// Test
public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(10, 5);  // 10 capacity, 5/sec
        
        // Test 1: Burst up to capacity
        int allowed = 0;
        for (int i = 0; i < 15; i++) {
            if (limiter.allowRequest("user1")) allowed++;
        }
        System.out.println("Burst requests: " + allowed + "/15 allowed");
        // Expected: 10
        
        // Test 2: Wait for refill
        Thread.sleep(1000);
        allowed = 0;
        for (int i = 0; i < 10; i++) {
            if (limiter.allowRequest("user1")) allowed++;
        }
        System.out.println("After 1 second: " + allowed + "/10 allowed");
        // Expected: 5 (refilled)
    }
}
```

### Design Discussion

**Trade-offs**:
- ✅ Fair (burst-friendly)
- ✅ Predictable (consistent limit)
- ❌ Memory overhead per user
- ❌ Doesn't work well distributed (need shared state)

**Improvements for distributed**:
- Use Redis to store buckets centrally
- Trade-off: network latency for accuracy
- Alternative: Sliding window (less fair, better distributed)

---

## OPTION B: Parking Lot System

### Solution Code

```java
import java.util.*;
import java.util.concurrent.*;

enum VehicleType { CAR, BUS, MOTORCYCLE }
enum SpotSize { COMPACT, REGULAR, LARGE }

abstract class Vehicle {
    private String licensePlate;
    protected VehicleType type;
    protected SpotSize requiredSize;
    
    public Vehicle(String plate, VehicleType type, SpotSize size) {
        this.licensePlate = plate;
        this.type = type;
        this.requiredSize = size;
    }
    
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }
    public SpotSize getRequiredSize() { return requiredSize; }
}

class Car extends Vehicle {
    public Car(String plate) {
        super(plate, VehicleType.CAR, SpotSize.REGULAR);
    }
}

class Bus extends Vehicle {
    public Bus(String plate) {
        super(plate, VehicleType.BUS, SpotSize.LARGE);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String plate) {
        super(plate, VehicleType.MOTORCYCLE, SpotSize.COMPACT);
    }
}

class ParkingSpot {
    private int spotId;
    private SpotSize size;
    private Vehicle parkedVehicle;
    private boolean occupied;
    
    public ParkingSpot(int id, SpotSize size) {
        this.spotId = id;
        this.size = size;
        this.occupied = false;
    }
    
    public synchronized boolean park(Vehicle v) {
        if (occupied || !canFit(v)) return false;
        this.parkedVehicle = v;
        this.occupied = true;
        return true;
    }
    
    public synchronized Vehicle unpark() {
        Vehicle v = parkedVehicle;
        parkedVehicle = null;
        occupied = false;
        return v;
    }
    
    private boolean canFit(Vehicle v) {
        // Large spot fits everything
        if (size == SpotSize.LARGE) return true;
        // Regular fits regular and compact
        if (size == SpotSize.REGULAR) 
            return v.requiredSize != SpotSize.LARGE;
        // Compact fits only compact
        return v.requiredSize == SpotSize.COMPACT;
    }
    
    public boolean isOccupied() { return occupied; }
    public int getSpotId() { return spotId; }
    public SpotSize getSize() { return size; }
}

class Level {
    private int levelNumber;
    private List<ParkingSpot> spots;
    
    public Level(int number, int numSpots) {
        this.levelNumber = number;
        this.spots = new ArrayList<>();
        for (int i = 0; i < numSpots; i++) {
            SpotSize size = i < numSpots/3 ? SpotSize.COMPACT :
                           i < 2*numSpots/3 ? SpotSize.REGULAR :
                           SpotSize.LARGE;
            spots.add(new ParkingSpot(i, size));
        }
    }
    
    public synchronized ParkingSpot findAvailableSpot(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getSize().ordinal() >= v.requiredSize.ordinal()) {
                return spot;
            }
        }
        return null;
    }
    
    public int getAvailableSpotCount() {
        return (int) spots.stream()
            .filter(s -> !s.isOccupied())
            .count();
    }
}

class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private Level level;
    private ParkingSpot spot;
    private long parkTime;
    private long unparkTime;
    private static final double HOURLY_RATE = 5.0;
    
    public Ticket(Vehicle v, Level l, ParkingSpot s) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = v;
        this.level = l;
        this.spot = s;
        this.parkTime = System.currentTimeMillis();
    }
    
    public double calculateFee() {
        if (unparkTime == 0) {
            unparkTime = System.currentTimeMillis();
        }
        long durationMs = unparkTime - parkTime;
        double hours = Math.max(1, durationMs / 3600000.0);
        return hours * HOURLY_RATE;
    }
    
    public String getTicketId() { return ticketId; }
    public ParkingSpot getSpot() { return spot; }
    public Vehicle getVehicle() { return vehicle; }
}

class ParkingLot {
    private List<Level> levels;
    private Map<String, Ticket> activeTickets = 
        new ConcurrentHashMap<>();
    
    public ParkingLot(int numLevels, int spotsPerLevel) {
        this.levels = new ArrayList<>();
        for (int i = 0; i < numLevels; i++) {
            levels.add(new Level(i, spotsPerLevel));
        }
    }
    
    public synchronized Ticket parkVehicle(Vehicle v) {
        for (Level level : levels) {
            ParkingSpot spot = level.findAvailableSpot(v);
            if (spot != null && spot.park(v)) {
                Ticket ticket = new Ticket(v, level, spot);
                activeTickets.put(ticket.getTicketId(), ticket);
                return ticket;
            }
        }
        return null;  // No available spot
    }
    
    public synchronized boolean unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getSpot();
        if (spot.unpark() != null) {
            activeTickets.remove(ticket.getTicketId());
            return true;
        }
        return false;
    }
    
    public double getOccupancyPercentage() {
        int total = 0;
        int occupied = 0;
        for (Level l : levels) {
            // Calculation based on available spots
            occupied += (100 - l.getAvailableSpotCount());
            total += 100;  // Simplified
        }
        return total == 0 ? 0 : (occupied * 100.0) / total;
    }
}

// Test
public class ParkingLotTest {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = new ParkingLot(2, 10);
        
        Car car = new Car("ABC123");
        Ticket t1 = lot.parkVehicle(car);
        System.out.println("Parked car at spot: " + t1.getSpot().getSpotId());
        
        Thread.sleep(100);  // Simulate parking duration
        lot.unparkVehicle(t1);
        System.out.println("Fee: $" + t1.calculateFee());
        
        Bus bus = new Bus("BUS001");
        Ticket t2 = lot.parkVehicle(bus);
        System.out.println("Parked bus: " + (t2 != null ? "success" : "failed"));
    }
}
```

---

## OPTION C: Web Crawler

### Solution Code

```java
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class PageInfo {
    String title;
    Set<String> links;
    
    public PageInfo(String title) {
        this.title = title;
        this.links = new HashSet<>();
    }
}

public class WebCrawler {
    private int maxDepth;
    private ExecutorService executor;
    private Set<String> allowedDomains;
    private Set<String> visited = ConcurrentHashMap.newKeySet();
    private Map<String, PageInfo> sitemap = new ConcurrentHashMap<>();
    private Queue<UrlDepth> queue = new ConcurrentLinkedQueue<>();
    private static final Pattern URL_PATTERN = 
        Pattern.compile("https?://[\\w.-]+(?:/[\\w./-]*)?");
    
    static class UrlDepth {
        String url;
        int depth;
        UrlDepth(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }
    }
    
    public WebCrawler(int maxDepth, int numThreads, 
                      Set<String> allowedDomains) {
        this.maxDepth = maxDepth;
        this.allowedDomains = allowedDomains;
        this.executor = Executors.newFixedThreadPool(numThreads);
    }
    
    public void crawl(String startUrl) {
        queue.add(new UrlDepth(startUrl, 0));
        visited.add(startUrl);
        
        // Submit initial batch of tasks
        for (int i = 0; i < 5; i++) {
            submitNextTask();
        }
        
        // Wait for completion
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void submitNextTask() {
        UrlDepth urlDepth = queue.poll();
        if (urlDepth != null) {
            executor.submit(() -> processUrl(urlDepth));
        }
    }
    
    private void processUrl(UrlDepth urlDepth) {
        try {
            String html = fetchPage(urlDepth.url);
            Set<String> links = extractLinks(html);
            
            String title = extractTitle(html);
            PageInfo info = new PageInfo(title);
            info.links.addAll(links);
            sitemap.put(urlDepth.url, info);
            
            // Queue new links
            if (urlDepth.depth < maxDepth) {
                for (String link : links) {
                    if (!visited.contains(link) && 
                        isAllowedDomain(link)) {
                        visited.add(link);
                        queue.add(new UrlDepth(link, urlDepth.depth + 1));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error crawling " + urlDepth.url + ": " + e);
        } finally {
            submitNextTask();
        }
    }
    
    private String fetchPage(String urlString) throws Exception {
        // Simplified - in production use HttpClient
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(
            conn.getInputStream())) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        }
        return content.toString();
    }
    
    private Set<String> extractLinks(String html) {
        Set<String> links = new HashSet<>();
        Matcher matcher = URL_PATTERN.matcher(html);
        while (matcher.find()) {
            links.add(matcher.group());
        }
        return links;
    }
    
    private String extractTitle(String html) {
        Pattern titlePattern = 
            Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);
        Matcher m = titlePattern.matcher(html);
        return m.find() ? m.group(1) : "Unknown";
    }
    
    private boolean isAllowedDomain(String url) {
        try {
            String domain = new URL(url).getHost();
            return allowedDomains.stream()
                .anyMatch(domain::contains);
        } catch (Exception e) {
            return false;
        }
    }
    
    public Map<String, PageInfo> getSitemap() {
        return new HashMap<>(sitemap);
    }
}

// Test
public class WebCrawlerTest {
    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler(2, 3, 
            Set.of("example.com"));
        
        crawler.crawl("https://example.com");
        
        Map<String, PageInfo> sitemap = crawler.getSitemap();
        System.out.println("Crawled " + sitemap.size() + " pages");
        sitemap.forEach((url, info) -> {
            System.out.println(url + " -> " + info.title);
        });
    }
}
```

---

## Q1: Vertical vs Horizontal Scaling

**Vertical**: Add more resources (CPU, RAM) to single server
- Pros: Simple, no code changes, better consistency
- Cons: Limited by hardware, single point of failure

**Horizontal**: Add more servers
- Pros: Unlimited scalability, fault tolerance
- Cons: Complex (load balancing, data sync), eventual consistency

**When to use**:
- Vertical: Starting out, simple app
- Horizontal: High traffic, distributed system needs

## Q2: SOLID Principles

1. **S**ingle Responsibility: One reason to change
   - Violation: ParkingLot manages spots, payments, gates
   
2. **O**pen/Closed: Open for extension, closed for modification
   - Violation: RateLimiter hard-coded for token bucket only
   
3. **L**iskov Substitution: Subtypes substitute parent
   - Violation: Bus extends Vehicle but can't fit in regular spots
   
4. **I**nterface Segregation: Depend on specific interfaces
   - Violation: Vehicle interface has irrelevant methods
   
5. **D**ependency Inversion: Depend on abstractions
   - Violation: ParkingLot directly creates Level objects

## Q3: CAP Theorem

**Consistency**: All nodes see same data  
**Availability**: System always responsive  
**Partition Tolerance**: System works despite network failures

**Why not all 3?**
- Network partition: Nodes can't communicate
- Choose consistency: Wait for sync (unavailable during partition)
- Choose availability: Serve stale data (inconsistent)

**Real systems**: Most choose AP (Availability + Partition tolerance)

## Q4: Stateless vs Stateful

**Stateless**: No client context stored on server
```java
public String processRequest(Request r) {
    return compute(r.data);  // Depends only on input
}
```
- ✅ Scalable (any server can handle any request)
- ❌ More data sent per request

**Stateful**: Server maintains client context
```java
user.accounts = getUserAccounts(user.id);
```
- ❌ Hard to scale (client must return to same server)
- ✅ Efficient (less data transmission)

**Easier to scale**: Stateless

## Q5: Single-Server to Distributed Evolution

**What breaks**:
1. **Data consistency**: Multiple servers have copies
2. **Timing**: Network delays between servers
3. **Failures**: Server might go down
4. **Coordination**: Need distributed locks, consensus
5. **Testing**: Hard to reproduce timing issues

**Solutions**:
- Load balancer (route requests)
- Shared cache (Redis)
- Database replication
- Message queues (eventual consistency)
- Distributed tracing

## Q6: Design Trade-off Example

**Rate Limiter Choice: Token Bucket**

**Alternatives**:
- Sliding Window (complex, accurate)
- Fixed Window (simple, unfair)
- Leaky Bucket (similar to token)

**Trade-off Chosen**:
- ✅ Fair to bursty traffic
- ✅ Predictable behavior
- ❌ Per-user memory overhead
- ❌ Doesn't work distributed without Redis

**Why**: Interview wanted fairness and simplicity over distributed scalability.

---

**Total Points**: 100  
**Expected Score**: 75+  
**Interview Performance**: This is the final assessment - should show end-to-end system thinking.
