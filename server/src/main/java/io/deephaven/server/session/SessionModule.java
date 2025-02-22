/**
 * Copyright (c) 2016-2022 Deephaven Data Labs and Patent Pending
 */
package io.deephaven.server.session;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.grpc.BindableService;
import io.grpc.ServerInterceptor;

@Module
public interface SessionModule {
    @Binds
    @IntoSet
    BindableService bindSessionServiceGrpcImpl(SessionServiceGrpcImpl sessionServiceGrpc);

    @Binds
    @IntoSet
    ServerInterceptor bindSessionServiceInterceptor(
            SessionServiceGrpcImpl.AuthServerInterceptor sessionServiceInterceptor);

    @Binds
    @IntoSet
    TicketResolver bindSessionTicketResolverServerSideExports(ExportTicketResolver resolver);
}
