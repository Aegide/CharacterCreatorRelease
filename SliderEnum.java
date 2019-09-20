

public enum SliderEnum {

    skin("skin"),
  
    hair("hair"),
  
    metal("metal"),
  
    trim("trim"),

    cloth("cloth"),

    leather("leather");
  

    private String Name;

    SliderEnum(String sliderElementType){
        Name = sliderElementType;
    }

    public String toString(){
        return Name;
    }

  }