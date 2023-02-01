import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Font;

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends Actor
{
    //GreenfootImage img;
    
    public Screen(String text) {
        //GreenfootImage img = new GreenfootImage(text, 17, Color.BLACK, Color.WHITE);
        GreenfootImage img = new GreenfootImage(390, 20);
        img.setColor(new Color(96, 112, 244));
        img.fill();
        
        img.setColor(new Color(252, 252, 252));
        img.setFont(new Font("Courier New", 12));
        img.drawString(text, 5, 10);
        setImage(img);
    } 
    
    public void act()
    {

    }
    
    public void updateScreen() {
        setLocation(getX(), getY()+21);
    }
}
