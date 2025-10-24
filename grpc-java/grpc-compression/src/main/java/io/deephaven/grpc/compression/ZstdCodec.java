//
// Copyright (c) 2016-2025 Deephaven Data Labs and Patent Pending
//
package io.deephaven.grpc.compression;

import io.deephaven.configuration.Configuration;
import io.grpc.Codec;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * Created by rbasralian on 9/12/25
 */
public class ZstdCodec implements Codec {

    private static final int LEVEL = Configuration.getInstance().getIntegerWithDefault("ZstdCodec.level", 3);

    @Override
    public String getMessageEncoding() {
        return "zstd";
    }

    @Override
    public InputStream decompress(InputStream is) {
        try {
            return new ZstdCompressorInputStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OutputStream compress(OutputStream os) {
        try {
            return new ZstdCompressorOutputStream(os, LEVEL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
