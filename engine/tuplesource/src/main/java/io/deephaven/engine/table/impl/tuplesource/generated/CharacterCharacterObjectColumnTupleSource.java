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
import io.deephaven.chunk.ObjectChunk;
import io.deephaven.chunk.WritableChunk;
import io.deephaven.chunk.WritableObjectChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.TupleSource;
import io.deephaven.engine.table.WritableColumnSource;
import io.deephaven.engine.table.impl.tuplesource.AbstractTupleSource;
import io.deephaven.engine.table.impl.tuplesource.ThreeColumnTupleSourceFactory;
import io.deephaven.tuple.generated.CharCharObjectTuple;
import io.deephaven.util.type.TypeUtils;
import org.jetbrains.annotations.NotNull;


/**
 * <p>{@link TupleSource} that produces key column values from {@link ColumnSource} types Character, Character, and Object.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CharacterCharacterObjectColumnTupleSource extends AbstractTupleSource<CharCharObjectTuple> {

    /** {@link ThreeColumnTupleSourceFactory} instance to create instances of {@link CharacterCharacterObjectColumnTupleSource}. **/
    public static final ThreeColumnTupleSourceFactory<CharCharObjectTuple, Character, Character, Object> FACTORY = new Factory();

    private final ColumnSource<Character> columnSource1;
    private final ColumnSource<Character> columnSource2;
    private final ColumnSource<Object> columnSource3;

    public CharacterCharacterObjectColumnTupleSource(
            @NotNull final ColumnSource<Character> columnSource1,
            @NotNull final ColumnSource<Character> columnSource2,
            @NotNull final ColumnSource<Object> columnSource3
    ) {
        super(columnSource1, columnSource2, columnSource3);
        this.columnSource1 = columnSource1;
        this.columnSource2 = columnSource2;
        this.columnSource3 = columnSource3;
    }

    @Override
    public final CharCharObjectTuple createTuple(final long rowKey) {
        return new CharCharObjectTuple(
                columnSource1.getChar(rowKey),
                columnSource2.getChar(rowKey),
                columnSource3.get(rowKey)
        );
    }

    @Override
    public final CharCharObjectTuple createPreviousTuple(final long rowKey) {
        return new CharCharObjectTuple(
                columnSource1.getPrevChar(rowKey),
                columnSource2.getPrevChar(rowKey),
                columnSource3.getPrev(rowKey)
        );
    }

    @Override
    public final CharCharObjectTuple createTupleFromValues(@NotNull final Object... values) {
        return new CharCharObjectTuple(
                TypeUtils.unbox((Character)values[0]),
                TypeUtils.unbox((Character)values[1]),
                values[2]
        );
    }

    @Override
    public final CharCharObjectTuple createTupleFromReinterpretedValues(@NotNull final Object... values) {
        return new CharCharObjectTuple(
                TypeUtils.unbox((Character)values[0]),
                TypeUtils.unbox((Character)values[1]),
                values[2]
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <ELEMENT_TYPE> void exportElement(@NotNull final CharCharObjectTuple tuple, final int elementIndex, @NotNull final WritableColumnSource<ELEMENT_TYPE> writableSource, final long destinationRowKey) {
        if (elementIndex == 0) {
            writableSource.set(destinationRowKey, tuple.getFirstElement());
            return;
        }
        if (elementIndex == 1) {
            writableSource.set(destinationRowKey, tuple.getSecondElement());
            return;
        }
        if (elementIndex == 2) {
            writableSource.set(destinationRowKey, (ELEMENT_TYPE) tuple.getThirdElement());
            return;
        }
        throw new IndexOutOfBoundsException("Invalid element index " + elementIndex + " for export");
    }

    @Override
    public final Object exportElement(@NotNull final CharCharObjectTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return tuple.getThirdElement();
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    public final Object exportElementReinterpreted(@NotNull final CharCharObjectTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return tuple.getThirdElement();
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    protected void convertChunks(@NotNull WritableChunk<? super Values> destination, int chunkSize, Chunk<? extends Values> [] chunks) {
        WritableObjectChunk<CharCharObjectTuple, ? super Values> destinationObjectChunk = destination.asWritableObjectChunk();
        CharChunk<? extends Values> chunk1 = chunks[0].asCharChunk();
        CharChunk<? extends Values> chunk2 = chunks[1].asCharChunk();
        ObjectChunk<Object, ? extends Values> chunk3 = chunks[2].asObjectChunk();
        for (int ii = 0; ii < chunkSize; ++ii) {
            destinationObjectChunk.set(ii, new CharCharObjectTuple(chunk1.get(ii), chunk2.get(ii), chunk3.get(ii)));
        }
        destinationObjectChunk.setSize(chunkSize);
    }

    /** {@link ThreeColumnTupleSourceFactory} for instances of {@link CharacterCharacterObjectColumnTupleSource}. **/
    private static final class Factory implements ThreeColumnTupleSourceFactory<CharCharObjectTuple, Character, Character, Object> {

        private Factory() {
        }

        @Override
        public TupleSource<CharCharObjectTuple> create(
                @NotNull final ColumnSource<Character> columnSource1,
                @NotNull final ColumnSource<Character> columnSource2,
                @NotNull final ColumnSource<Object> columnSource3
        ) {
            return new CharacterCharacterObjectColumnTupleSource(
                    columnSource1,
                    columnSource2,
                    columnSource3
            );
        }
    }
}
