//Plot class gotten from Lecture. Can be found on Moodle.
import javax.swing.*;

// transformation of user coordinates to screen coordinates
public class Plot extends JComponent
{
    // dimensions of plotting area with default values
    public int width = 600, height = 600;
    // dimensions of user-space coordinates with default values
    public double xMin =0,  xMax =1, yMin =0, yMax =1;
    // transformation of coordinates
    public  int scaleX(double x)
    {
        return (int) (width * (x - xMin) / (xMax - xMin));
    }
    public  int scaleY(double y)
    {
        return (int) (height * (yMin - y)/(yMax - yMin)+height);
    }
    public  void setScaleX(double min, double max)
    {
        xMin = min;
        xMax = max;
    }
    public  void setScaleY(double min, double max)
    {
        yMin = min;
        yMax = max;
    }
}
