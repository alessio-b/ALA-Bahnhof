import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Station here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Station extends Actor
{
    /**
     * Act - do whatever the Station wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            ((Bahnhof) getWorld()).spawnPerson();
        }
    }
}
