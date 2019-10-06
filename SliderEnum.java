

public enum SliderEnum {
//TODO : rename "ElementEnum"

    skin("skin"),
  
    hair("hair"),
  
    metal("metal"),
  
    trim("trim"),

    cloth("cloth"),

    leather("leather"),

    offset("offset");
  

    private String Name;

    SliderEnum(String sliderElementType){
        Name = sliderElementType;
    }

    public String toString(){
        return Name;
    }

  }