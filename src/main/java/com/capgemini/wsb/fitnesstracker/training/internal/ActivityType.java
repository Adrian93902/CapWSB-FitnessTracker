package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing different types of activities in the fitness tracker application.
 * Each activity type has a display name associated with it.
 */
public enum ActivityType {

    /**
     * Running activity.
     */
    RUNNING("Running"),

    /**
     * Cycling activity.
     */
    CYCLING("Cycling"),
    /**
     * Walking activity.
     */
    WALKING("Walking"),
    /**
     * Swimming activity.
     */
    SWIMMING("Swimming"),
    /**
     * Tennis activity.
     */
    TENNIS("Tenis");
    /**
     * Constructor to set the display name of the activity type.
     *
     * @param displayName the display name of the activity type.
     */
    private final String displayName;
    /**
     * Gets the display name of the activity type.
     *
     * @return the display name of the activity type.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }
    /**
     * Gets the display name of the activity type.
     *
     * @return the display name of the activity type.
     */
    public String getDisplayName() {
        return displayName;
    }

}
