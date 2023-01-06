package io.deephaven.jsoningester;

import io.deephaven.time.DateTime;

/**
 * Interface for providers of message metadata (e.g. timestamps, a {@link #getMsgNo() sequence number}, and a
 * {@link #getMessageId() message ID}).
 */
public interface MessageMetadata {

    /**
     * Gets the time (if available) when this message was published.
     *
     * @return the time (if available) when this message was published.
     */
    DateTime getSentTime();

    /**
     * Gets the time (reported by subscriber) when this message was received.
     *
     * @return the time (reported by subscriber) when this message was received.
     */
    DateTime getReceiveTime();

    /**
     * Gets the time when this message was finished processing by its ingester and was ready to be flushed.
     *
     * @return the time when this message was finished processing by its ingester and was ready to be flushed.
     */
    DateTime getIngestTime();

    /**
     * Gets the ID for this message.
     *
     * @return the ID for this message.
     */
    String getMessageId();

    /**
     * Gets the monotonically-increasing sequential number indicating the sequence this message was received in by the
     * ingester. The message number is used to ensure that
     *
     * @return the sequential number indicating the sequence this message was received in by the ingester.
     */
    long getMsgNo();
}