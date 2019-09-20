import javax.swing.JSlider;


public class CreatorSlider {

    int WidthSlider = 215;
    int HeightSlider = 38;

    JSlider Jslider;

    //Basic Slider
    public CreatorSlider(int x, int y){
        Jslider = new JSlider();
        Jslider.setValue(64);
        Jslider.setMajorTickSpacing(64);
        Jslider.setPaintTicks(true);
        Jslider.setPaintLabels(true);
        Jslider.setMaximum(255);

        Jslider.setBounds(x, y, WidthSlider, HeightSlider);
    }

    public JSlider getJslider(){
        return Jslider;
    }

}