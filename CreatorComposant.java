import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CreatorComposant {


    public int XOffset;
    public int YOffset;
    public BufferedImage BI;
    public int IJmax;


    public CreatorComposant(String pathname , int ijmax){

        System.out.println(">> CreatorComposant()");
        
		try {
            BI = ImageIO.read(new File(pathname));
		} catch (IOException ex) {
			System.out.println(">>" + ex);
		}

        XOffset = 0;
        YOffset = 0;

        IJmax = ijmax;
    }


}