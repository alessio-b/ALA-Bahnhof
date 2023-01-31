import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Train here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Train extends Actor
{
    private String state;
    private int assignedIntersec;
    private int currentAmount;
    private int trainCapacity;
    
    private int waitTime;
    private int tick;
    private int sTick = 0;
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Train(int track, int[] trainInfo) {
        setImage("train.png");
        
        assignedIntersec = track;
        trainCapacity = Greenfoot.getRandomNumber(trainInfo[3]-trainInfo[2])+trainInfo[2];
        waitTime = trainInfo[4];
        
        state = "incoming";
    }
    
    public void act()
    {
        tick++;
        Bahnhof bahnhof = ((Bahnhof)getWorld());
        int track = (int)Math.floor(assignedIntersec/3);
        switch(state) {
            case "incoming":
                setLocation(getX()-2, getY());
                if (getX() < 1225) {
                    state = "waiting";
                }
                break;
            case "waiting":
                if (bahnhof.getTrack(track) == 0) {
                    bahnhof.lockTrack(track);
                    state = "routing";
                    sTick = tick;
                }
                break;
            case "routing":
                if (getX() >= 1105) { 
                    switch (assignedIntersec%3) {
                        case 0:
                            if (tick%4==0 ) {
                                if (tick-sTick<36) {
                                    setRotation(getRotation()-1);
                                } else {
                                    setRotation(getRotation()+1);
                                }
                                setLocation(getX()-3, getY());
                            }
                            setLocation(getX(), getY()+1);
                            break;
                        case 2:
                            if (tick%4==0 ) {
                                if (tick-sTick<36) {
                                    setRotation(getRotation()+1);
                                } else {
                                    setRotation(getRotation()-1);
                                }
                                setLocation(getX()-3, getY());
                            }
                            setLocation(getX(), getY()-1);
                            break;
                    }
                }
                setLocation(getX()-1, getY());
                if (getX() < 640) {
                    state = "ready";
                }
                break;
            case "ready":
                bahnhof.readyTrack(track);
                sTick = tick;
                state = "loading";
                break;
            case "loading":
                List<Person> obj = getIntersectingObjects(Person.class);
                for (int i=0; i<obj.size(); i++) {
                    getWorld().removeObject(obj.get(0));
                    currentAmount++;
                }
                if (currentAmount >= trainCapacity || tick-sTick >= waitTime) {
                    bahnhof.lockTrack(track);
                    state = "prepleave";
                    
                }
                break;
            case "prepleave":
                setLocation(getX()+1, getY());
                if (getX() >= 785) {
                    state = "leaving";
                    sTick = tick;
                }
                break;
            case "leaving":
                if (getX() <= 905) {
                    switch (assignedIntersec%3) {
                        case 0:
                            if (tick%4==0 ) {
                                if (tick-sTick<36) {
                                    setRotation(getRotation()-1);
                                } else {
                                    setRotation(getRotation()+1);
                                }
                                setLocation(getX()+3, getY());
                            }
                            setLocation(getX(), getY()-1);
                            break;
                        case 2:
                            if (tick%4==0 ) {
                                if (tick-sTick<36) {
                                    setRotation(getRotation()+1);
                                } else {
                                    setRotation(getRotation()-1);
                                }
                                setLocation(getX()+3, getY());
                            }
                            setLocation(getX(), getY()+1);
                            break;
                    }
                }
                setLocation(getX()+1, getY());                
                if (getX() > 1500) {
                    bahnhof.unlockIntersec(assignedIntersec);
                    bahnhof.unlockTrack(track);
                    getWorld().removeObject(this);
                }
                break;
        }
       
    }
    
    public int getCapacity() {
        return trainCapacity;
    }
}
