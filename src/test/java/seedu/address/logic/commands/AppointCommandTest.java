package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AppointCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AppointCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeUndoableCommand_throwsCommandException() throws Exception {
        AppointCommand appointCommand = prepareCommand(INDEX_FIRST_PERSON, VALID_APPOINTMENT);
        assertCommandFailure(appointCommand, model, String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), VALID_APPOINTMENT));
    }

    @Test
    public void equals() {
        final AppointCommand standardCommand = new AppointCommand(INDEX_FIRST_PERSON, APPOINTMENT_DESC);

        // same values -> returns true
        String copyDescriptor = new String(APPOINTMENT_DESC);
        AppointCommand commandWithSameValues = new AppointCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AppointCommand(INDEX_SECOND_PERSON, VALID_APPOINTMENT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AppointCommand(INDEX_FIRST_PERSON, "random descriptor")));
    }

    private AppointCommand prepareCommand(Index index, String appointmentDetails) {
        AppointCommand appointCommand = new AppointCommand(index, appointmentDetails);
        appointCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return appointCommand;
    }
}
