package seedu.tr4cker.model;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.task.Task;

/**
 * Unmodifiable view of Tr4cker.
 */
public interface ReadOnlyTr4cker {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

}
