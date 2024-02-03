import javax.swing.*;

public class MainMars
{
    public static void main(String[] args)
    {
        //creating JFrame and naming it screen
        JFrame screen = new JFrame();
        MarsDisplay marsDisplay = new MarsDisplay();
        //setting size
        screen.setSize(marsDisplay.width + 20, marsDisplay.height + 40);
        //setting title
        screen.setTitle("Mars displayed using Robinson Projection");
        //adding marsDisplay to the JFrame
        screen.add(marsDisplay);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
