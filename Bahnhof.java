import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bahnhof extends World
{
    int tick;
    
    // [startX, endX, startY, endY, entryX, entryY]
    public int[][] Tracks = {{13, 70, 430, 855, 405, 40}, 
                            {139, 329, 430, 855, 405, 233}, 
                            {399, 585, 430, 855, 405, 500}, 
                            {659, 710, 430, 855, 405, 688}};
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Bahnhof()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        
        Intersection topIntersection = new Intersection();
        addObject(topIntersection, 1065, 100);
        
        Intersection middleIntersection = new Intersection();
        addObject(middleIntersection, 1065, 360);
        
        Intersection bottomIntersection = new Intersection();
        addObject(bottomIntersection, 1065, 620);
        
        Track topTrack = new Track();
        addObject(topTrack, 640, 100);
        
        Track middleTrack = new Track();
        addObject(middleTrack, 640, 360);
        
        Track bottomTrack = new Track();
        addObject(bottomTrack, 640, 620);
    }
    
    public void act()
    {
        tick++;
        if (tick%90==0 && Greenfoot.getRandomNumber(100) >= 50) {
            addObject(new Person(Tracks[Greenfoot.getRandomNumber(4)]), Greenfoot.getRandomNumber(380), Greenfoot.getRandomNumber(720));
        }
    }
}
