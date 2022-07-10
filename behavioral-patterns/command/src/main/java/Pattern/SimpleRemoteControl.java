package Pattern;

public class SimpleRemoteControl {

    private Command slot;  // only one button

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        // set the command the remote will
        // execute
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
