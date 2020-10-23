package seedu.tr4cker.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.tr4cker.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path tr4ckerFilePath = Paths.get("data" , "tr4cker.json");
    private Path eventsFilePath = Paths.get("data", "events.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setTr4ckerFilePath(newUserPrefs.getTr4ckerFilePath());
        setEventsFilePath(newUserPrefs.getEventsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTr4ckerFilePath() {
        return tr4ckerFilePath;
    }

    public void setTr4ckerFilePath(Path tr4ckerFilePath) {
        requireNonNull(tr4ckerFilePath);
        this.tr4ckerFilePath = tr4ckerFilePath;
    }

    public Path getEventsFilePath() {
        return eventsFilePath;
    }

    public void setEventsFilePath(Path eventsFilePath) {
        requireNonNull(eventsFilePath);
        this.eventsFilePath = eventsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && tr4ckerFilePath.equals(o.tr4ckerFilePath)
                && eventsFilePath.equals(o.eventsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, tr4ckerFilePath, eventsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data (tasks) file location : " + tr4ckerFilePath);
        sb.append("\nLocal data (events) file location : " + eventsFilePath);
        return sb.toString();
    }

}
