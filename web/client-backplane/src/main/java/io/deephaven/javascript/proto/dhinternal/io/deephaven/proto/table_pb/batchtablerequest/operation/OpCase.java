/**
 * Copyright (c) 2016-2022 Deephaven Data Labs and Patent Pending
 */
package io.deephaven.javascript.proto.dhinternal.io.deephaven.proto.table_pb.batchtablerequest.operation;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(
        isNative = true,
        name = "dhinternal.io.deephaven.proto.table_pb.BatchTableRequest.Operation.OpCase",
        namespace = JsPackage.GLOBAL)
public class OpCase {
    public static int AS_OF_JOIN,
            APPLY_PREVIEW_COLUMNS,
            COMBO_AGGREGATE,
            CREATE_INPUT_TABLE,
            CROSS_JOIN,
            DROP_COLUMNS,
            EMPTY_TABLE,
            EXACT_JOIN,
            FETCH_PANDAS_TABLE,
            FETCH_TABLE,
            FILTER,
            FLATTEN,
            HEAD,
            HEAD_BY,
            LAZY_UPDATE,
            LEFT_JOIN,
            MERGE,
            NATURAL_JOIN,
            OP_NOT_SET,
            RUN_CHART_DOWNSAMPLE,
            SELECT,
            SELECT_DISTINCT,
            SNAPSHOT,
            SORT,
            TAIL,
            TAIL_BY,
            TIME_TABLE,
            UNGROUP,
            UNSTRUCTURED_FILTER,
            UPDATE,
            UPDATE_VIEW,
            VIEW;
}
