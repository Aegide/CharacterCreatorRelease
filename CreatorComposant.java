import java.awt.image.BufferedImage;

public class CreatorComposant {


    public int XOffset;
    public int YOffset;
    public BufferedImage BI;
    public int IJmax;

    public CreatorComposant(BufferedImage bi , int ijmax){
        XOffset = 0;
        YOffset = 0;
        BI = bi;
        IJmax = ijmax;
    }

}