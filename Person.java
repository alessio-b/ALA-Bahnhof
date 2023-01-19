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
    GreenfootImage standing = new GreenfootImage("person/standing.png");
    GreenfootImage walking1 = new GreenfootImage("person/walking1.png");
    GreenfootImage walking2 = new GreenfootImage("person/walking2.png");
    
    public Person() {
        standing.scale(50,50);
        walking1.scale(50,50);
        walking2.scale(50,50);
        setImage(standing);
        
        String[] randomTable = {"D1", "D2", "D3", "D4"};
        String tmp = randomTable[Greenfoot.getRandomNumber(3)];
        switch(tmp) {
            case "D1":
                destination[0] = Greenfoot.getRandomNumber(855-430)+430;
                destination[1] = Greenfoot.getRandomNumber(70-13)+13;
                break;
            case "D2":
                destination[0] = Greenfoot.getRandomNumber(855-430)+430;
                destination[1] = Greenfoot.getRandomNumber(329-139)+139;
                break;
            case "D3":
                destination[0] = Greenfoot.getRandomNumber(855-430)+430;
                destination[1] = Greenfoot.getRandomNumber(585-399)+399;
                break;
            case "D4":
                destination[0] = Greenfoot.getRandomNumber(855-430)+430;
                destination[1] = Greenfoot.getRandomNumber(710-659)+659;
                break;
        }
        state = "walking";
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        time++;
        if (state == "walking") {
            if ( getX() == destination[0] && getY() == destination[1]) { 
                state = "waiting";
                setImage(standing);
                return;
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
    }
}
