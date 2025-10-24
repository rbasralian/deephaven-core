//
// Copyright (c) 2016-2025 Deephaven Data Labs and Patent Pending
//
package io.deephaven.grpc.compression;

import io.grpc.Codec;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * Created by rbasralian on 9/12/25
 */
public class LZ4Codec implements Codec {
    @Override
    public String getMessageEncoding() {
        return "lz4";
    }

    // TODO: this just does not work. not sure why. maybe something about knowing when we have a complete frame?
    @Override
    public InputStream decompress(InputStream is) {
        try {
            return new FramedLZ4CompressorInputStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OutputStream compress(OutputStream os) {
        try {
            return new FramedLZ4CompressorOutputStream(os, new FramedLZ4CompressorOutputStream.Parameters(FramedLZ4CompressorOutputStream.BlockSize.K256, false, false, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
