package io.deephaven.queryutil.dataadapter.datafetch.single;

import io.deephaven.engine.table.Table;
import io.deephaven.queryutil.dataadapter.rec.desc.RecordAdapterDescriptor;
import io.deephaven.queryutil.dataadapter.rec.RecordUpdater;
import io.deephaven.engine.table.ColumnSource;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Utility to retrieve a single row of table data and convert it to a record of type {@code T}.
 *
 * @param <T> The data structure used to represent table rows
 */
public class SingleRowRecordAdapter<T> {

    @NotNull
    private final RecordAdapterDescriptor<T> rowRecordAdapterDescriptor;

    @NotNull
    private final ColumnSource<?>[] recordColumnSources;

    /**
     * An array of column adapters (parallel to the array of {@link #recordColumnSources}) used to update a record of
     * type {@code T} with values directly from the {@link #recordColumnSources}.
     */
    @NotNull
    private final ColSourceToRecordAdapter<T, ?>[] rowToRecordAdapters;

    private SingleRowRecordAdapter(@NotNull Table sourceTable,
            @NotNull RecordAdapterDescriptor<T> rowRecordAdapterDescriptor) {
        this.rowRecordAdapterDescriptor = rowRecordAdapterDescriptor;

        final Map<String, RecordUpdater<T, ?>> columnAdapters = rowRecordAdapterDescriptor.getColumnAdapters();

        // Find the column sources corresponding to the column adapters
        recordColumnSources = columnAdapters
                .entrySet()
                .stream()
                // find the corresponding column source. check the type.
                .map(en -> sourceTable.getColumnSource(en.getKey(), en.getValue().getSourceType()))
                .toArray(ColumnSource[]::new);


        final int nCols = recordColumnSources.length;

        // noinspection unchecked
        final RecordUpdater<T, ?>[] recordUpdaters = columnAdapters.values().toArray(new RecordUpdater[0]);

        // noinspection unchecked
        rowToRecordAdapters = new ColSourceToRecordAdapter[nCols];
        for (int i = 0; i < nCols; i++) {
            final RecordUpdater<T, ?> recordUpdater = recordUpdaters[i];
            rowToRecordAdapters[i] = ColSourceToRecordAdapter.getColSourceToRecordAdapter(recordUpdater);
        }
    }

    public static <T> SingleRowRecordAdapter<T> create(@NotNull Table sourceTable,
            RecordAdapterDescriptor<T> rowRecordAdapterDescriptor) {
        return new SingleRowRecordAdapter<>(sourceTable, rowRecordAdapterDescriptor);
    }

    /**
     * Create a record of type {@code T} from the data in the table at index {@code k}.
     *
     * @param k       The index key from which to retrieve data.
     * @param usePrev Whether to use prev values.
     * @param record  The record to store the data in.
     * @return A record containing the data at {@code k}.
     */
    public T retrieveDataSingleKey(long k, boolean usePrev, T record) {
        for (int colIdx = 0; colIdx < recordColumnSources.length; colIdx++) {
            updateRecordFromColumn(colIdx, k, usePrev, record);
        }
        return record;
    }

    @SuppressWarnings("unchecked")
    private <C> void updateRecordFromColumn(int colIdx, long k, boolean usePrev, T record) {
        final ColSourceToRecordAdapter<T, C> adapter = (ColSourceToRecordAdapter<T, C>) rowToRecordAdapters[colIdx];
        adapter.updateRecordFromColumn(
                (ColumnSource<C>) recordColumnSources[colIdx],
                k,
                usePrev,
                record);
    }

}
