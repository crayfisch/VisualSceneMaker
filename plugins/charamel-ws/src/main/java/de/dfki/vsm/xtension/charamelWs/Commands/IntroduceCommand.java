package de.dfki.vsm.xtension.charamelWs.Commands;

public class IntroduceCommand extends ActionCommand {

    public IntroduceCommand(int number) {
        super("humanoid/Presentation/introduce/",
                String.format("Introduce%02d.glb", number));
        if (number > 6 || number < 3) {
            throw new IllegalArgumentException("Only valid between 03-06");
        }
    }
}