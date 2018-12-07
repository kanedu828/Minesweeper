/***************************************************************************
*Square.java
*
*Represents each square of a minesweeper map.
*
*
*
*
*
****************************************************************************/




import java.util.Random;
public class Square{
    Random rand = new Random();
    private boolean safe;
    private String visual;

    public Square(){
        safe = true;
        visual = "* ";
    }
    public void setVisual(String visual){
        this.visual = visual;
    }

    public String getVisual(){
        return visual;
    }

    public void setSafe(boolean safe){
        this.safe = safe;
    }

    public boolean getSafe(){
        return safe;
    }



    
        
}






