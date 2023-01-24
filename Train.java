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
        trainCapacity = Greenfoot.getRandomNumber(250)+100;
        
        switch(Greenfoot.getRandomNumber(2)){
            case 0:
                desiredTrack = 1;
                break;
            case 1:
                break;
            case 2:
                break;
        }
        
    }
    
    public void act()
    {
        if (isTouching(Intersection.class)) {
            
        }
    }
    
    public int getCapacity() {
        return trainCapacity;
    }
}
