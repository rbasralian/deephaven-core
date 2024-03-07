//
// Copyright (c) 2016-2024 Deephaven Data Labs and Patent Pending
//
// ****** AUTO-GENERATED CLASS - DO NOT EDIT MANUALLY
// ****** Run ReplicateTypedHashers or ./gradlew replicateTypedHashers to regenerate
//
// @formatter:off
package io.deephaven.engine.table.impl.by.typed.incopenagg.gen;

import static io.deephaven.util.compare.IntComparisons.eq;
import static io.deephaven.util.compare.LongComparisons.eq;

import io.deephaven.base.verify.Assert;
import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.IntChunk;
import io.deephaven.chunk.LongChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.chunk.util.hashing.IntChunkHasher;
import io.deephaven.chunk.util.hashing.LongChunkHasher;
import io.deephaven.engine.rowset.RowSequence;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.impl.by.IncrementalChunkedOperatorAggregationStateManagerOpenAddressedBase;
import io.deephaven.engine.table.impl.sources.immutable.ImmutableIntArraySource;
import io.deephaven.engine.table.impl.sources.immutable.ImmutableLongArraySource;
import io.deephaven.util.type.TypeUtils;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.util.Arrays;

final class IncrementalAggOpenHasherIntLong extends IncrementalChunkedOperatorAggregationStateManagerOpenAddressedBase {
    private ImmutableIntArraySource mainKeySource0;

    private ImmutableIntArraySource alternateKeySource0;

    private ImmutableLongArraySource mainKeySource1;

    private ImmutableLongArraySource alternateKeySource1;

    public IncrementalAggOpenHasherIntLong(ColumnSource[] tableKeySources,
            ColumnSource[] originalTableKeySources, int tableSize, double maximumLoadFactor,
            double targetLoadFactor) {
        super(tableKeySources, tableSize, maximumLoadFactor);
        this.mainKeySource0 = (ImmutableIntArraySource) super.mainKeySources[0];
        this.mainKeySource0.ensureCapacity(tableSize);
        this.mainKeySource1 = (ImmutableLongArraySource) super.mainKeySources[1];
        this.mainKeySource1.ensureCapacity(tableSize);
    }

    private int nextTableLocation(int tableLocation) {
        return (tableLocation + 1) & (tableSize - 1);
    }

    private int alternateNextTableLocation(int tableLocation) {
        return (tableLocation + 1) & (alternateTableSize - 1);
    }

    protected void build(RowSequence rowSequence, Chunk[] sourceKeyChunks) {
        final IntChunk<Values> keyChunk0 = sourceKeyChunks[0].asIntChunk();
        final LongChunk<Values> keyChunk1 = sourceKeyChunks[1].asLongChunk();
        final int chunkSize = keyChunk0.size();
        for (int chunkPosition = 0; chunkPosition < chunkSize; ++chunkPosition) {
            final int k0 = keyChunk0.get(chunkPosition);
            final long k1 = keyChunk1.get(chunkPosition);
            final int hash = hash(k0, k1);
            final int firstTableLocation = hashToTableLocation(hash);
            int tableLocation = firstTableLocation;
            MAIN_SEARCH: while (true) {
                int outputPosition = mainOutputPosition.getUnsafe(tableLocation);
                if (outputPosition == EMPTY_OUTPUT_POSITION) {
                    final int firstAlternateTableLocation = hashToTableLocationAlternate(hash);
                    int alternateTableLocation = firstAlternateTableLocation;
                    while (alternateTableLocation < rehashPointer) {
                        outputPosition = alternateOutputPosition.getUnsafe(alternateTableLocation);
                        if (outputPosition == EMPTY_OUTPUT_POSITION) {
                            break;
                        } else if (eq(alternateKeySource0.getUnsafe(alternateTableLocation), k0) && eq(alternateKeySource1.getUnsafe(alternateTableLocation), k1)) {
                            outputPositions.set(chunkPosition, outputPosition);
                            break MAIN_SEARCH;
                        } else {
                            alternateTableLocation = alternateNextTableLocation(alternateTableLocation);
                            Assert.neq(alternateTableLocation, "alternateTableLocation", firstAlternateTableLocation, "firstAlternateTableLocation");
                        }
                    }
                    numEntries++;
                    mainKeySource0.set(tableLocation, k0);
                    mainKeySource1.set(tableLocation, k1);
                    outputPosition = nextOutputPosition.getAndIncrement();
                    outputPositions.set(chunkPosition, outputPosition);
                    mainOutputPosition.set(tableLocation, outputPosition);
                    outputPositionToHashSlot.set(outputPosition, mainInsertMask | tableLocation);
                    break;
                } else if (eq(mainKeySource0.getUnsafe(tableLocation), k0) && eq(mainKeySource1.getUnsafe(tableLocation), k1)) {
                    outputPositions.set(chunkPosition, outputPosition);
                    break;
                } else {
                    tableLocation = nextTableLocation(tableLocation);
                    Assert.neq(tableLocation, "tableLocation", firstTableLocation, "firstTableLocation");
                }
            }
        }
    }

    protected void probe(RowSequence rowSequence, Chunk[] sourceKeyChunks) {
        final IntChunk<Values> keyChunk0 = sourceKeyChunks[0].asIntChunk();
        final LongChunk<Values> keyChunk1 = sourceKeyChunks[1].asLongChunk();
        final int chunkSize = keyChunk0.size();
        for (int chunkPosition = 0; chunkPosition < chunkSize; ++chunkPosition) {
            final int k0 = keyChunk0.get(chunkPosition);
            final long k1 = keyChunk1.get(chunkPosition);
            final int hash = hash(k0, k1);
            final int firstTableLocation = hashToTableLocation(hash);
            boolean found = false;
            int tableLocation = firstTableLocation;
            int outputPosition;
            while ((outputPosition = mainOutputPosition.getUnsafe(tableLocation)) != EMPTY_OUTPUT_POSITION) {
                if (eq(mainKeySource0.getUnsafe(tableLocation), k0) && eq(mainKeySource1.getUnsafe(tableLocation), k1)) {
                    outputPositions.set(chunkPosition, outputPosition);
                    found = true;
                    break;
                }
                tableLocation = nextTableLocation(tableLocation);
                Assert.neq(tableLocation, "tableLocation", firstTableLocation, "firstTableLocation");
            }
            if (!found) {
                final int firstAlternateTableLocation = hashToTableLocationAlternate(hash);
                boolean alternateFound = false;
                if (firstAlternateTableLocation < rehashPointer) {
                    int alternateTableLocation = firstAlternateTableLocation;
                    while ((outputPosition = alternateOutputPosition.getUnsafe(alternateTableLocation)) != EMPTY_OUTPUT_POSITION) {
                        if (eq(alternateKeySource0.getUnsafe(alternateTableLocation), k0) && eq(alternateKeySource1.getUnsafe(alternateTableLocation), k1)) {
                            outputPositions.set(chunkPosition, outputPosition);
                            alternateFound = true;
                            break;
                        }
                        alternateTableLocation = alternateNextTableLocation(alternateTableLocation);
                        Assert.neq(alternateTableLocation, "alternateTableLocation", firstAlternateTableLocation, "firstAlternateTableLocation");
                    }
                }
                if (!alternateFound) {
                    throw new IllegalStateException("Missing value in probe");
                }
            }
        }
    }

    private static int hash(int k0, long k1) {
        int hash = IntChunkHasher.hashInitialSingle(k0);
        hash = LongChunkHasher.hashUpdateSingle(hash, k1);
        return hash;
    }

    private boolean migrateOneLocation(int locationToMigrate) {
        final int currentStateValue = alternateOutputPosition.getUnsafe(locationToMigrate);
        if (currentStateValue == EMPTY_OUTPUT_POSITION) {
            return false;
        }
        final int k0 = alternateKeySource0.getUnsafe(locationToMigrate);
        final long k1 = alternateKeySource1.getUnsafe(locationToMigrate);
        final int hash = hash(k0, k1);
        int destinationTableLocation = hashToTableLocation(hash);
        while (mainOutputPosition.getUnsafe(destinationTableLocation) != EMPTY_OUTPUT_POSITION) {
            destinationTableLocation = nextTableLocation(destinationTableLocation);
        }
        mainKeySource0.set(destinationTableLocation, k0);
        mainKeySource1.set(destinationTableLocation, k1);
        mainOutputPosition.set(destinationTableLocation, currentStateValue);
        outputPositionToHashSlot.set(currentStateValue, mainInsertMask | destinationTableLocation);
        alternateOutputPosition.set(locationToMigrate, EMPTY_OUTPUT_POSITION);
        return true;
    }

    @Override
    protected int rehashInternalPartial(int entriesToRehash) {
        int rehashedEntries = 0;
        while (rehashPointer > 0 && rehashedEntries < entriesToRehash) {
            if (migrateOneLocation(--rehashPointer)) {
                rehashedEntries++;
            }
        }
        return rehashedEntries;
    }

    @Override
    protected void newAlternate() {
        super.newAlternate();
        this.mainKeySource0 = (ImmutableIntArraySource)super.mainKeySources[0];
        this.alternateKeySource0 = (ImmutableIntArraySource)super.alternateKeySources[0];
        this.mainKeySource1 = (ImmutableLongArraySource)super.mainKeySources[1];
        this.alternateKeySource1 = (ImmutableLongArraySource)super.alternateKeySources[1];
    }

    @Override
    protected void clearAlternate() {
        super.clearAlternate();
        this.alternateKeySource0 = null;
        this.alternateKeySource1 = null;
    }

    @Override
    protected void migrateFront() {
        int location = 0;
        while (migrateOneLocation(location++));
    }

    @Override
    protected void rehashInternalFull(final int oldSize) {
        final int[] destKeyArray0 = new int[tableSize];
        final long[] destKeyArray1 = new long[tableSize];
        final int[] destState = new int[tableSize];
        Arrays.fill(destState, EMPTY_OUTPUT_POSITION);
        final int [] originalKeyArray0 = mainKeySource0.getArray();
        mainKeySource0.setArray(destKeyArray0);
        final long [] originalKeyArray1 = mainKeySource1.getArray();
        mainKeySource1.setArray(destKeyArray1);
        final int [] originalStateArray = mainOutputPosition.getArray();
        mainOutputPosition.setArray(destState);
        for (int sourceBucket = 0; sourceBucket < oldSize; ++sourceBucket) {
            final int currentStateValue = originalStateArray[sourceBucket];
            if (currentStateValue == EMPTY_OUTPUT_POSITION) {
                continue;
            }
            final int k0 = originalKeyArray0[sourceBucket];
            final long k1 = originalKeyArray1[sourceBucket];
            final int hash = hash(k0, k1);
            final int firstDestinationTableLocation = hashToTableLocation(hash);
            int destinationTableLocation = firstDestinationTableLocation;
            while (true) {
                if (destState[destinationTableLocation] == EMPTY_OUTPUT_POSITION) {
                    destKeyArray0[destinationTableLocation] = k0;
                    destKeyArray1[destinationTableLocation] = k1;
                    destState[destinationTableLocation] = originalStateArray[sourceBucket];
                    outputPositionToHashSlot.set(currentStateValue, mainInsertMask | destinationTableLocation);
                    break;
                }
                destinationTableLocation = nextTableLocation(destinationTableLocation);
                Assert.neq(destinationTableLocation, "destinationTableLocation", firstDestinationTableLocation, "firstDestinationTableLocation");
            }
        }
    }

    @Override
    public int findPositionForKey(Object key) {
        final Object [] ka = (Object[])key;
        final int k0 = TypeUtils.unbox((Integer)ka[0]);
        final long k1 = TypeUtils.unbox((Long)ka[1]);
        int hash = hash(k0, k1);
        int tableLocation = hashToTableLocation(hash);
        final int firstTableLocation = tableLocation;
        while (true) {
            final int positionValue = mainOutputPosition.getUnsafe(tableLocation);
            if (positionValue == EMPTY_OUTPUT_POSITION) {
                int alternateTableLocation = hashToTableLocationAlternate(hash);
                if (alternateTableLocation >= rehashPointer) {
                    return UNKNOWN_ROW;
                }
                final int firstAlternateTableLocation = alternateTableLocation;
                while (true) {
                    final int alternatePositionValue = alternateOutputPosition.getUnsafe(alternateTableLocation);
                    if (alternatePositionValue == EMPTY_OUTPUT_POSITION) {
                        return UNKNOWN_ROW;
                    }
                    if (eq(alternateKeySource0.getUnsafe(alternateTableLocation), k0) && eq(alternateKeySource1.getUnsafe(alternateTableLocation), k1)) {
                        return alternatePositionValue;
                    }
                    alternateTableLocation = alternateNextTableLocation(alternateTableLocation);
                    Assert.neq(alternateTableLocation, "alternateTableLocation", firstAlternateTableLocation, "firstAlternateTableLocation");
                }
            }
            if (eq(mainKeySource0.getUnsafe(tableLocation), k0) && eq(mainKeySource1.getUnsafe(tableLocation), k1)) {
                return positionValue;
            }
            tableLocation = nextTableLocation(tableLocation);
            Assert.neq(tableLocation, "tableLocation", firstTableLocation, "firstTableLocation");
        }
    }
}
