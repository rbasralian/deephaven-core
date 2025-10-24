//
// Copyright (c) 2016-2025 Deephaven Data Labs and Patent Pending
//
package io.deephaven.grpc.compression;

import io.grpc.Codec;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;

/**
 *
 * Created by rbasralian on 9/12/25
 */
public final class DhCodecRegistry {

    public static final CompressorRegistry COMPRESSOR_REGISTRY;

    static {
        COMPRESSOR_REGISTRY = CompressorRegistry.newEmptyInstance();
        COMPRESSOR_REGISTRY.register(new Codec.Gzip());
        COMPRESSOR_REGISTRY.register(Codec.Identity.NONE);
        COMPRESSOR_REGISTRY.register(new LZ4Codec());
        COMPRESSOR_REGISTRY.register(new ZstdCodec());
    }

    public static final DecompressorRegistry DECOMPRESSOR_REGISTRY;

    static {
        DECOMPRESSOR_REGISTRY = DecompressorRegistry.emptyInstance()
                .with(new Codec.Gzip(), true)
                .with(new LZ4Codec(), true)
                .with(new ZstdCodec(), true)
                .with(Codec.Identity.NONE, false);
    }

    private DhCodecRegistry() {}

}
