//
// Copyright (c) 2016-2024 Deephaven Data Labs and Patent Pending
//
package io.deephaven.client.impl;

import io.grpc.ManagedChannel;

public interface ClientChannelFactory {
    static ClientChannelFactory defaultInstance() {
        return ChannelHelper::channel;
    }

    ManagedChannel create(ClientConfig clientConfig);
}
