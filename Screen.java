import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;


/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends Actor
{
    
    private GreenfootImage img = new GreenfootImage(390,220);
    private ArrayList<String> Queue = new ArrayList<String>();
    
    public Screen() {
        setImage(img);
        Queue.add("Sir");
        Queue.add("Cumberbatch");
    } 
    
    public void act()
    {
        //getImage().clear();
        String tmp = "";
        String tmp1 = "Test\n ####";
        for (int i = 0; i<Queue.size();i++) {
            tmp = tmp + '\n' + Queue.get(i);
        }
        System.out.println(tmp);
        setImage(new GreenfootImage(tmp, 17, Color.BLACK, Color.WHITE));

        //getImage().drawString(tmp, 0, 10);
    }
}
