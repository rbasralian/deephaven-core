//
// Copyright (c) 2016-2024 Deephaven Data Labs and Patent Pending
//
// ****** AUTO-GENERATED CLASS - DO NOT EDIT MANUALLY
// ****** Edit TupleSourceCodeGenerator and run "./gradlew replicateTupleSources" to regenerate
//
// @formatter:off
package io.deephaven.engine.table.impl.tuplesource.generated;

import io.deephaven.chunk.CharChunk;
import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.IntChunk;
import io.deephaven.chunk.WritableChunk;
import io.deephaven.chunk.WritableObjectChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.TupleSource;
import io.deephaven.engine.table.WritableColumnSource;
import io.deephaven.engine.table.impl.tuplesource.AbstractTupleSource;
import io.deephaven.engine.table.impl.tuplesource.ThreeColumnTupleSourceFactory;
import io.deephaven.tuple.generated.IntCharIntTuple;
import io.deephaven.util.type.TypeUtils;
import org.jetbrains.annotations.NotNull;


/**
 * <p>{@link TupleSource} that produces key column values from {@link ColumnSource} types Integer, Character, and Integer.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class IntegerCharacterIntegerColumnTupleSource extends AbstractTupleSource<IntCharIntTuple> {

    /** {@link ThreeColumnTupleSourceFactory} instance to create instances of {@link IntegerCharacterIntegerColumnTupleSource}. **/
    public static final ThreeColumnTupleSourceFactory<IntCharIntTuple, Integer, Character, Integer> FACTORY = new Factory();

    private final ColumnSource<Integer> columnSource1;
    private final ColumnSource<Character> columnSource2;
    private final ColumnSource<Integer> columnSource3;

    public IntegerCharacterIntegerColumnTupleSource(
            @NotNull final ColumnSource<Integer> columnSource1,
            @NotNull final ColumnSource<Character> columnSource2,
            @NotNull final ColumnSource<Integer> columnSource3
    ) {
        super(columnSource1, columnSource2, columnSource3);
        this.columnSource1 = columnSource1;
        this.columnSource2 = columnSource2;
        this.columnSource3 = columnSource3;
    }

    @Override
    public final IntCharIntTuple createTuple(final long rowKey) {
        return new IntCharIntTuple(
                columnSource1.getInt(rowKey),
                columnSource2.getChar(rowKey),
                columnSource3.getInt(rowKey)
        );
    }

    @Override
    public final IntCharIntTuple createPreviousTuple(final long rowKey) {
        return new IntCharIntTuple(
                columnSource1.getPrevInt(rowKey),
                columnSource2.getPrevChar(rowKey),
                columnSource3.getPrevInt(rowKey)
        );
    }

    @Override
    public final IntCharIntTuple createTupleFromValues(@NotNull final Object... values) {
        return new IntCharIntTuple(
                TypeUtils.unbox((Integer)values[0]),
                TypeUtils.unbox((Character)values[1]),
                TypeUtils.unbox((Integer)values[2])
        );
    }

    @Override
    public final IntCharIntTuple createTupleFromReinterpretedValues(@NotNull final Object... values) {
        return new IntCharIntTuple(
                TypeUtils.unbox((Integer)values[0]),
                TypeUtils.unbox((Character)values[1]),
                TypeUtils.unbox((Integer)values[2])
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <ELEMENT_TYPE> void exportElement(@NotNull final IntCharIntTuple tuple, final int elementIndex, @NotNull final WritableColumnSource<ELEMENT_TYPE> writableSource, final long destinationRowKey) {
        if (elementIndex == 0) {
            writableSource.set(destinationRowKey, tuple.getFirstElement());
            return;
        }
        if (elementIndex == 1) {
            writableSource.set(destinationRowKey, tuple.getSecondElement());
            return;
        }
        if (elementIndex == 2) {
            writableSource.set(destinationRowKey, tuple.getThirdElement());
            return;
        }
        throw new IndexOutOfBoundsException("Invalid element index " + elementIndex + " for export");
    }

    @Override
    public final Object exportElement(@NotNull final IntCharIntTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    public final Object exportElementReinterpreted(@NotNull final IntCharIntTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    protected void convertChunks(@NotNull WritableChunk<? super Values> destination, int chunkSize, Chunk<? extends Values> [] chunks) {
        WritableObjectChunk<IntCharIntTuple, ? super Values> destinationObjectChunk = destination.asWritableObjectChunk();
        IntChunk<? extends Values> chunk1 = chunks[0].asIntChunk();
        CharChunk<? extends Values> chunk2 = chunks[1].asCharChunk();
        IntChunk<? extends Values> chunk3 = chunks[2].asIntChunk();
        for (int ii = 0; ii < chunkSize; ++ii) {
            destinationObjectChunk.set(ii, new IntCharIntTuple(chunk1.get(ii), chunk2.get(ii), chunk3.get(ii)));
        }
        destinationObjectChunk.setSize(chunkSize);
    }

    /** {@link ThreeColumnTupleSourceFactory} for instances of {@link IntegerCharacterIntegerColumnTupleSource}. **/
    private static final class Factory implements ThreeColumnTupleSourceFactory<IntCharIntTuple, Integer, Character, Integer> {

        private Factory() {
        }

        @Override
        public TupleSource<IntCharIntTuple> create(
                @NotNull final ColumnSource<Integer> columnSource1,
                @NotNull final ColumnSource<Character> columnSource2,
                @NotNull final ColumnSource<Integer> columnSource3
        ) {
            return new IntegerCharacterIntegerColumnTupleSource(
                    columnSource1,
                    columnSource2,
                    columnSource3
            );
        }
    }
}
