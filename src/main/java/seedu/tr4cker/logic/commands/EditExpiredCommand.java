package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_EXPIRED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

public class EditExpiredCommand extends EditCommand {

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditExpiredCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        super(index, editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredExpiredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        //model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.updateFilteredTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredExpiredTaskList(PREDICATE_SHOW_EXPIRED_TASKS);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }
}