import javax.swing.JSlider;


public class CreatorSlider {

    int WidthSlider = 215;
    int HeightSlider = 38;
    JSlider Jslider;

    //Basic Slider
    public CreatorSlider(int value, int x, int y, String sliderName){
        Jslider = new JSlider();
        Jslider.setValue(value);
        Jslider.setMajorTickSpacing(64);
        Jslider.setPaintTicks(true);
        Jslider.setPaintLabels(true);
        Jslider.setMaximum(255);
        Jslider.setBounds(x, y, WidthSlider, HeightSlider);
        Jslider.setName(sliderName);
    }

}