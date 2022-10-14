package io.deephaven.clock;

/**
 * Created by rbasralian on 10/13/22
 */
public final class SystemMillisRealTimeClock implements RealTimeClock {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public long currentTimeMicros() {
        return System.currentTimeMillis() * 1000L;
    }

    @Override
    public long currentTimeNanos() {
        return System.currentTimeMillis() * 1_000_000L;
    }
}
