package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

class CountdownCommandParserTest {

    private static final String MESSAGE_INVALID_SWITCH_TAB_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            CountdownCommand.MESSAGE_SWITCH_TAB_USAGE);
    private static final String MESSAGE_INVALID_ADD_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            CountdownCommand.MESSAGE_ADD_COUNTDOWN_USAGE);
    private static final String MESSAGE_INVALID_DELETE_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            CountdownCommand.MESSAGE_DELETE_COUNTDOWN_USAGE);

    private final CountdownCommandParser countdownCommandParser = new CountdownCommandParser();
    private final CountdownCommand countdownCommand = new CountdownCommand();

    @Test
    public void parse_switchCountdownTab_success() {
        assertParseSuccess(countdownCommandParser, "", countdownCommand);
    }


    @Test
    public void parse_switchCountdownTab_failure() {
        assertParseFailure(countdownCommandParser, "countdownsss", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
        assertParseFailure(countdownCommandParser, "countdown hehexd", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
    }

    @Test
    public void parse_addCountdown_success() {
        EventName eventName = new EventName("Halloween Party");
        EventDate eventDate = new EventDate("31-10-2021", false);
        CountdownCommand expected = new CountdownCommand(eventName, eventDate);
        assertParseSuccess(countdownCommandParser, " n/Halloween Party d/31-10-2021", expected);
        assertParseSuccess(countdownCommandParser, " n/Halloween Party d/31-Oct-2021", expected);
    }

    @Test
    public void parse_addCountdown_failure() {
        assertParseFailure(countdownCommandParser, " n/Halloween Party d/31-10-2020", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " n/Halloween Party d/31-Oct-2020", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " n/Hall'ween Party d/31-Oct-2020", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " n/Halloween Party d/31-Oct", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " n/Halloween Party d/The Day", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " d/31-Oct-2021", MESSAGE_INVALID_ADD_FORMAT);
        assertParseFailure(countdownCommandParser, " n/Halloween Party", MESSAGE_INVALID_ADD_FORMAT);
    }

    @Test
    public void parse_deleteCountdown_success() {
        Index index = Index.fromOneBased(1);
        CountdownCommand expected = new CountdownCommand(index, true);
        assertParseSuccess(countdownCommandParser, " del/1", expected);
    }

    @Test
    public void parse_deleteCountdown_failure() {
        assertParseFailure(countdownCommandParser, " del/0", MESSAGE_INVALID_DELETE_FORMAT);
        assertParseFailure(countdownCommandParser, " del/-1", MESSAGE_INVALID_DELETE_FORMAT);
        assertParseFailure(countdownCommandParser, " del/haha", MESSAGE_INVALID_DELETE_FORMAT);
    }

}