/**
 * Copyright (c) 2016-2022 Deephaven Data Labs and Patent Pending
 */
package io.deephaven.vector;

import io.deephaven.base.verify.Assert;
import io.deephaven.base.verify.Require;
import io.deephaven.util.datastructures.LongSizedDataStructure;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static io.deephaven.base.ClampUtil.clampLong;
import static io.deephaven.vector.Vector.clampIndex;

@Deprecated
public class BooleanVectorSlice extends BooleanVector.Indirect {

    private static final long serialVersionUID = 1L;

    private final BooleanVector innerArray;
    private final long offsetIndex;
    private final long length;
    private final long innerArrayValidFromInclusive;
    private final long innerArrayValidToExclusive;

    private BooleanVectorSlice(@NotNull final BooleanVector innerArray, final long offsetIndex, final long length, final long innerArrayValidFromInclusive, final long innerArrayValidToExclusive) {
        Assert.geqZero(length, "length");
        Assert.leq(innerArrayValidFromInclusive, "innerArrayValidFromInclusive", innerArrayValidToExclusive, "innerArrayValidToExclusive");
        this.innerArray = innerArray;
        this.offsetIndex = offsetIndex;
        this.length = length;
        this.innerArrayValidFromInclusive = innerArrayValidFromInclusive;
        this.innerArrayValidToExclusive = innerArrayValidToExclusive;
    }

    public BooleanVectorSlice(@NotNull final BooleanVector innerArray, final long offsetIndex, final long length) {
        this(innerArray, offsetIndex, length,
                clampLong(0, innerArray.size(), offsetIndex),
                clampLong(0, innerArray.size(), offsetIndex + length));
    }

    @Override
    public Boolean get(final long index) {
        return innerArray.get(clampIndex(innerArrayValidFromInclusive, innerArrayValidToExclusive, index + offsetIndex));
    }

    @Override
    public BooleanVector subVector(final long fromIndexInclusive, final long toIndexExclusive) {
        Require.leq(fromIndexInclusive, "fromIndexInclusive", toIndexExclusive, "toIndexExclusive");
        final long newLength = toIndexExclusive - fromIndexInclusive;
        final long newOffsetIndex = offsetIndex + fromIndexInclusive;
        return new BooleanVectorSlice(innerArray, newOffsetIndex, newLength,
                clampLong(innerArrayValidFromInclusive, innerArrayValidToExclusive, newOffsetIndex),
                clampLong(innerArrayValidFromInclusive, innerArrayValidToExclusive, newOffsetIndex + newLength));
    }

    @Override
    public BooleanVector subVectorByPositions(final long[] positions) {
        return innerArray.subVectorByPositions(Arrays.stream(positions).map(p -> clampIndex(innerArrayValidFromInclusive, innerArrayValidToExclusive, p + offsetIndex)).toArray());
    }

    @Override
    public Boolean[] toArray() {
        if (innerArray instanceof BooleanVectorDirect && offsetIndex >= innerArrayValidFromInclusive && offsetIndex + length <= innerArrayValidToExclusive) {
            return Arrays.copyOfRange(innerArray.toArray(), LongSizedDataStructure.intSize("toArray", offsetIndex), LongSizedDataStructure.intSize("toArray",offsetIndex + length));
        }
        final Boolean[] result = new Boolean[LongSizedDataStructure.intSize("toArray", length)];
        for (int ii = 0; ii < length; ++ii) {
            result[ii] = get(ii);
        }
        return result;
    }

    @Override
    public long size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public BooleanVectorDirect getDirect() {
        return new BooleanVectorDirect(toArray());
    }
}
