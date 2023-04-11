import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MainController {

    private Map<Character, Command> commandMap = new HashMap<>();
    public Stack<Command> undoStack = new Stack<>();
    public Stack<Command> redoStack = new Stack<>();

    public MainController(){
        resetButton();
    }

    public void setCommand(char c, Command command) {
        if (commandMap.containsKey(c)){
            commandMap.put(c, command);
        } else {
            throw new IllegalArgumentException("Button " + c + " unsupported.");
        }
    }

    public void pressButton(char button) {
        if (commandMap.containsKey(button)) {
            Command command = commandMap.get(button);
            if (command != null) {
                command.execute();
                undoStack.push(command);
                redoStack.clear();
            }else{
                System.out.println("Button " + button + " is not set.");
            }
        } else {
            throw new IllegalArgumentException("Button " + button + " unsupported.");
        }
    }

    public void resetButton() {
        for (char c = 'a'; c <= 'z'; c++) {
            commandMap.put(c, null);
        }
    }

    public void undo(){
        if (!undoStack.isEmpty()){
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo(){
        if (!redoStack.isEmpty()){
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}
