import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Tank tank = new Tank();
        Telecom telecom = new Telecom();

        MainController controller = new MainController();

        // Set the commands
        controller.setCommand('a', new TankMoveForwardCommand(tank));
        controller.setCommand('b', new TankMoveBackwardCommand(tank));
        controller.setCommand('c', new TelecomConnectCommand(telecom));
        controller.setCommand('d', new TelecomDisconnectCommand(telecom));

        // Set the macro command
        controller.setCommand('m', new MacroCommand(Arrays.asList(
                new TankMoveForwardCommand(tank),
                new TankMoveForwardCommand(tank),
                new TelecomConnectCommand(telecom),
                new TelecomConnectCommand(telecom)
                )));

        // Start the game
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("**** Press a button (a~z) or undo (1) or redo (2) ****");
            String input = scanner.nextLine();
            if (input.equals("1")){
                controller.undo();
            } else if (input.equals("2")){
                controller.redo();
            } else {
                controller.pressButton(input.charAt(0));
            }
        }
    }
}

