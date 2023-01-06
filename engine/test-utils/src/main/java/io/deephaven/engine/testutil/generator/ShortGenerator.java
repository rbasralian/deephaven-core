package io.deephaven.engine.testutil.generator;

import io.deephaven.util.QueryConstants;

import java.util.Random;
import java.util.TreeMap;

public class ShortGenerator extends AbstractGenerator<Short> {

    private final short to, from;
    private final double nullFraction;

    public ShortGenerator() {
        this((short) (QueryConstants.NULL_SHORT + 1), Short.MAX_VALUE);
    }

    public ShortGenerator(short from, short to) {
        this(from, to, 0);
    }

    public ShortGenerator(short from, short to, double nullFraction) {
        this.from = from;
        this.to = to;
        this.nullFraction = nullFraction;
    }

    @Override
    public Short nextValue(TreeMap<Long, Short> values, long key, Random random) {
        if (nullFraction > 0) {
            if (random.nextDouble() < nullFraction) {
                return null;
            }
        }
        return (short) (from + random.nextInt(to - from));
    }

    @Override
    public Class<Short> getType() {
        return Short.class;
    }
}
