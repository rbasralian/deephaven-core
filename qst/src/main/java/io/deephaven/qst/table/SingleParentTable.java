//
// Copyright (c) 2016-2024 Deephaven Data Labs and Patent Pending
//
package io.deephaven.qst.table;

public interface SingleParentTable extends TableSpec {

    TableSpec parent();
}
