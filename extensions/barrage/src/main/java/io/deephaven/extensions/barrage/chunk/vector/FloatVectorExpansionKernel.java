/*
 * ---------------------------------------------------------------------------------------------------------------------
 * AUTO-GENERATED CLASS - DO NOT EDIT MANUALLY - for any changes edit CharVectorExpansionKernel and regenerate
 * ---------------------------------------------------------------------------------------------------------------------
 */
/*
 * Copyright (c) 2016-2021 Deephaven Data Labs and Patent Pending
 */

package io.deephaven.extensions.barrage.chunk.vector;

import io.deephaven.chunk.FloatChunk;
import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.IntChunk;
import io.deephaven.chunk.ObjectChunk;
import io.deephaven.chunk.WritableFloatChunk;
import io.deephaven.chunk.WritableChunk;
import io.deephaven.chunk.WritableIntChunk;
import io.deephaven.chunk.WritableObjectChunk;
import io.deephaven.chunk.attributes.Any;
import io.deephaven.chunk.attributes.ChunkPositions;
import io.deephaven.chunk.sized.SizedFloatChunk;
import io.deephaven.vector.FloatVector;
import io.deephaven.vector.FloatVectorDirect;
import io.deephaven.vector.Vector;

public class FloatVectorExpansionKernel implements VectorExpansionKernel {
    private final static FloatVector ZERO_LEN_VECTOR = new FloatVectorDirect();
    public final static FloatVectorExpansionKernel INSTANCE = new FloatVectorExpansionKernel();

    @Override
    public <A extends Any> WritableChunk<A> expand(
            final ObjectChunk<Vector<?>, A> source, final WritableIntChunk<ChunkPositions> perElementLengthDest) {
        if (source.size() == 0) {
            perElementLengthDest.setSize(0);
            return WritableFloatChunk.makeWritableChunk(0);
        }

        final ObjectChunk<FloatVector, A> typedSource = source.asObjectChunk();
        final SizedFloatChunk<A> resultWrapper = new SizedFloatChunk<>();

        int lenWritten = 0;
        perElementLengthDest.setSize(source.size() + 1);
        for (int i = 0; i < typedSource.size(); ++i) {
            final FloatVector row = typedSource.get(i);
            final int len = row == null ? 0 : row.intSize("FloatVectorExpansionKernel");
            perElementLengthDest.set(i, lenWritten);
            final WritableFloatChunk<A> result = resultWrapper.ensureCapacityPreserve(lenWritten + len);
            for (int j = 0; j < len; ++j) {
                result.set(lenWritten + j, row.get(j));
            }
            lenWritten += len;
            result.setSize(lenWritten);
        }
        perElementLengthDest.set(typedSource.size(), lenWritten);

        return resultWrapper.get();
    }

    @Override
    public <A extends Any> WritableObjectChunk<Vector<?>, A> contract(
            final Chunk<A> source, final IntChunk<ChunkPositions> perElementLengthDest) {
        if (perElementLengthDest.size() == 0) {
            return WritableObjectChunk.makeWritableChunk(0);
        }

        final FloatChunk<A> typedSource = source.asFloatChunk();
        final WritableObjectChunk<Vector<?>, A> result =
                WritableObjectChunk.makeWritableChunk(perElementLengthDest.size() - 1);

        int lenRead = 0;
        for (int i = 0; i < result.size(); ++i) {
            final int ROW_LEN = perElementLengthDest.get(i + 1) - perElementLengthDest.get(i);
            if (ROW_LEN == 0) {
                result.set(i, ZERO_LEN_VECTOR);
            } else {
                final float[] row = new float[ROW_LEN];
                for (int j = 0; j < ROW_LEN; ++j) {
                    row[j] = typedSource.get(lenRead + j);
                }
                lenRead += ROW_LEN;
                result.set(i, new FloatVectorDirect(row));
            }
        }

        return result;
    }
}