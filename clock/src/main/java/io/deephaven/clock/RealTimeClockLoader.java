package io.deephaven.clock;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by rbasralian on 10/13/22
 */
class RealTimeClockLoader {

    private static final String REAL_TIME_CLOCK_SOURCE_PROPERTY = "RealTimeSource";

    /**
     * If you re
     */
    private static final String REAL_TIME_CLOCK_SOURCE = System.getProperty(REAL_TIME_CLOCK_SOURCE_PROPERTY, "jdk-internals").toLowerCase();

    private static final String JDK_INTERNALS_CLOCK_CLASS_NAME = "io.deephaven.clock.impl.JdkInternalsRealTimeClock";

    static RealTimeClock loadImpl() {
        final RealTimeClock impl;
        switch (REAL_TIME_CLOCK_SOURCE) {
            case "jdk-internals":
                final Iterator<RealTimeClock> it = ServiceLoader.load(RealTimeClock.class).iterator();
                if (!it.hasNext()) {
                    throw new RuntimeException("Could not load " + RealTimeClock.class.getSimpleName() + " service");
                }
                impl = it.next();
                if (it.hasNext()) {
                    throw new IllegalStateException("Found multiple implementations for " + RealTimeClock.class.getSimpleName());
                }

                final String implClassName = impl.getClass().getName();
                if (!implClassName.equals("")) {
                    throw new RuntimeException("Expected " + RealTimeClock.class.getSimpleName() + " of type " + JDK_INTERNALS_CLOCK_CLASS_NAME + " but found " + implClassName + " instead");
                }

                break;
            case "instant":
                impl = new JavaInstantRealTimeClock();
                break;
            case "millis":
                impl = new SystemMillisRealTimeClock();
                break;
            default:
                throw new RuntimeException("Unsupported clock source: " + REAL_TIME_CLOCK_SOURCE);
        }

        System.out.println(RealTimeClockLoader.class.getSimpleName() + ": Loaded clock of type " + impl.getClass().getName());
        return impl;
    }

}
