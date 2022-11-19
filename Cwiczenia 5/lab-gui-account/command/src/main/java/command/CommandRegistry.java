package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {

	private final ObservableList<Command> commandStack = FXCollections.observableArrayList();
	private final ObservableList<Command> revertedCommandStack = FXCollections.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
		revertedCommandStack.clear();
	}

	public void redo() {
		if (!revertedCommandStack.isEmpty()) {
			int lastIndex = revertedCommandStack.size() -1;
			Command command = revertedCommandStack.get(lastIndex);
			revertedCommandStack.remove(lastIndex);
			commandStack.add(command);
			command.redo();
		}
	}

	public void undo() {
		if (!commandStack.isEmpty()) {
			int lastIndex = commandStack.size() - 1;
			Command command = commandStack.get(lastIndex);
			commandStack.remove(lastIndex);
			revertedCommandStack.add(command);
			command.undo();
		}
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}
}
