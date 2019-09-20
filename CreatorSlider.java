import javax.swing.JSlider;


public class CreatorSlider {

    int WidthSlider = 215;
    int HeightSlider = 38;

    JSlider Jslider;

    //Basic Slider
    public CreatorSlider(int x, int y, SliderEnum name){
        Jslider = new JSlider();
        Jslider.setValue(64);
        Jslider.setMajorTickSpacing(64);
        Jslider.setPaintTicks(true);
        Jslider.setPaintLabels(true);
        Jslider.setMaximum(255);
        Jslider.setBounds(x, y, WidthSlider, HeightSlider);
        
        Jslider.setName(name.toString());//NEW
    }

    public JSlider getJslider(){
        return Jslider;
    }

}