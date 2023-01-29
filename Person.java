import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
{
    private int time;
    private String state; // "walking", "waiting", "leaving"
    private int[] destination = new int[2]; // [X, Y]
    private int[] targets;
    private boolean readyEnter;
    GreenfootImage standing = new GreenfootImage("person/standing.png");
    GreenfootImage walking1 = new GreenfootImage("person/walking1.png");
    GreenfootImage walking2 = new GreenfootImage("person/walking2.png");
    
    public Person(int[] target) {
        standing.scale(50,50);
        walking1.scale(50,50);
        walking2.scale(50,50);
        setImage(standing);
        
        targets = target;
        
        //set first Destination to Entry Point
        destination[0] = targets[5];
        destination[1] = targets[6];
        
        readyEnter = false;
        state = "toEntry";
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        time++;
        if (state == "toEntry" || state == "toTrack") {
            if ( getX() == destination[0] && getY() == destination[1]) {
                if (state == "toEntry") {
                    destination[0] = Greenfoot.getRandomNumber(targets[4]-targets[3])+targets[3];
                    destination[1] = Greenfoot.getRandomNumber(targets[2]-targets[1])+targets[1];
                            
                    state = "toTrack";
                    return;
                }
                if (state == "toTrack") {
                    setImage(standing);
                    state = "standing";
                    return;
                }
            }
            
            //walk
            turnTowards(destination[0], destination[1]);
            move(1);
            
            // walking animation
            if (time%40==0) {
                setImage(walking1);
            } else if (time%20==0) {
                setImage(walking2);
            }
        }
        if (state == "standing") {
            if ( ((Bahnhof) getWorld()).Track[targets[0]] == 2 ) {
                if (time%40==0) {
                    setImage(walking1);
                } else if (time%20==0) {
                    setImage(walking2);
                }
                if (targets[7] == 0) {
                    setRotation(90);
                    move(1);
                } else {
                    setRotation(270);
                    move(1);
                }
            }
        }
    }
}
