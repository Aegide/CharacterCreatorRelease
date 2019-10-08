public enum SliderEnum {
    //TODO : rename "ElementEnum"
    
        info(),
    
        hair(0, "Hair", 11),
        skin(1, "Skin", 60),
        metal(2, "Metal", 109),
        trim(3, "Trim", 158),
        cloth(4, "Cloth", 207),
        leather(5, "Leather", 256),
        offset(6, "Offset", 0);
      
        public int ID;
        public String Name;
        public int PositionY;
    
        public int Max = 6;
    
        SliderEnum(int id, String sliderElementType, int positionY){
            ID = id;
            Name = sliderElementType;
            PositionY = positionY;
        }
    
        SliderEnum(){
        }
    
        public String toString(){
            return Name;
        }
    
      }