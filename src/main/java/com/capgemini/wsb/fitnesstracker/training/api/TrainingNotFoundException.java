package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that a {@link Training} entity was not found.
 *
 * <p>This exception is thrown when an attempt is made to retrieve or manipulate a training session
 * that does not exist in the system.</p>
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {
    /**
     * Constructs a new {@code TrainingNotFoundException} with the specified message.
     *
     * @param message the detail message
     */
    private TrainingNotFoundException(String message) {
        super(message);
    }
    /**
     * Constructs a new {@code TrainingNotFoundException} with the ID of the training session that was not found.
     *
     * @param id the ID of the training session
     */
    public TrainingNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }

}
