
import java.awt.Color;

public enum ColorEnum {

    info(),

    red(0, "Red", 10, 200, 190, new Color(150,0,0,255)),
    green(1, "Green", 430, 480, 46, new Color(0,150,0,255)),
    blue(2, "Blue", 710, 760, 46, new Color(0,0,150,255));

    public int ID;
    public String Name;
    public int TextPositionX;
    public int SliderPositionX;
    public int WidthText;
    public Color TextColor;

    public int Max = 3;

    ColorEnum(int id, String colorName, int textPositionX, int sliderPositionX, int widthText, Color textColor){
        ID = id;
        Name = colorName;
        TextPositionX = textPositionX;
        SliderPositionX = sliderPositionX;
        WidthText = widthText;
        TextColor = textColor;
    }

    ColorEnum(){
    }

    public String toString(){
        return Name;
    }

  }