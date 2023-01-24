import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Train here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Train extends Actor
{
    
    private String trainLine;
    private int desiredTrack;
    private int trainCapacity;
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Train() {
        trainCapacity = Greenfoot.getRandomNumber(3);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public int getCapacity() {
        return trainCapacity;
    }
}
