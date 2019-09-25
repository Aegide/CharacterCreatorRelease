

public enum ColorEnum {

    red("Red"),
  
    green("Green"),
  
    blue("Blue");

    private String Name;

    ColorEnum(String color){
        Name = color;
    }

    public String toString(){
        return Name;
    }

  }