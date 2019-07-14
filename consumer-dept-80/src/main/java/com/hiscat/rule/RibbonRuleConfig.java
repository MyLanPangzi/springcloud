package com.hiscat.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class RibbonRuleConfig {

//    @Bean
//    public IRule iRule() {
//        return new RoundRule();
////        return new WeightedResponseTimeRule();
////        return new RandomRule();
//    }

    @Component
    @Slf4j
    public static class RoundRule extends AbstractLoadBalancerRule implements IRule {
        private static final boolean AVAILABLE_ONLY_SERVERS = true;
        private static final boolean ALL_SERVERS = false;

        private AtomicInteger nextServerCyclicCounter;
        private AtomicInteger total = new AtomicInteger();
        private AtomicInteger next = new AtomicInteger() ;

        public RoundRule() {
            nextServerCyclicCounter = new AtomicInteger(0);
        }

        public RoundRule(ILoadBalancer lb) {
            this();
            setLoadBalancer(lb);
        }

        public Server choose(ILoadBalancer lb, Object key) {
            if (lb == null) {
                log.warn("no load balancer");
                return null;
            }

            Server server = null;
            int count = 0;
            while (server == null && count++ < 10) {
                List<Server> reachableServers = lb.getReachableServers();
                List<Server> allServers = lb.getAllServers();
                int upCount = reachableServers.size();
                int serverCount = allServers.size();

                if ((upCount == 0) || (serverCount == 0)) {
                    log.warn("No up servers available from load balancer: " + lb);
                    return null;
                }
                server = allServers.get(next.get());
                if (total.incrementAndGet() >= 5) {
                    total.set(0);
                    if (next.incrementAndGet() >= allServers.size()) {
                        next.set(0);
                    }
                }

                if (server == null) {
                    /* Transient. */
                    Thread.yield();
                    continue;
                }

                if (server.isAlive() && (server.isReadyToServe())) {
                    return (server);
                }

                // Next.
                server = null;
            }

            if (count >= 10) {
                log.warn("No available alive servers after 10 tries from load balancer: "
                        + lb);
            }
            return server;
        }

        /**
         * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
         *
         * @param modulo The modulo to bound the value of the counter.
         * @return The next value.
         */
        private int incrementAndGetModulo(int modulo) {
            for (; ; ) {
                int current = nextServerCyclicCounter.get();
                int next = (current + 1) % modulo;
                if (nextServerCyclicCounter.compareAndSet(current, next))
                    return next;
            }
        }

        @Override
        public Server choose(Object key) {
            return choose(getLoadBalancer(), key);
        }

        @Override
        public void initWithNiwsConfig(IClientConfig clientConfig) {
        }
    }

}
