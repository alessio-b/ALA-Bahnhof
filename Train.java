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
    private String trainLine;
    private int assignedIntersec;
    private int trainCapacity;
    private int currentAmount;
    
    int tick;
    int sTick = 0;
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Train(int track) {
        setImage("train.png");
        trainCapacity = Greenfoot.getRandomNumber(2)+1;
        
        assignedIntersec = track;
        
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
                if (getX() < 1275) {
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
                if (getY() == 100 || getY() == 360 || getY() == 620) { 
                    int temp = 2;
                } else {
                    switch (assignedIntersec%3) {
                        case 0:
                            if (tick%4==0 ) {
                                if (tick-sTick<35) {
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
                                if (tick-sTick<35) {
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
                state = "loading";
                break;
            case "loading":
                List<Person> obj = getIntersectingObjects(Person.class);
                for (int i=0; i<obj.size(); i++) {
                    getWorld().removeObject(obj.get(0));
                    currentAmount++;
                }
                if (currentAmount >= trainCapacity) {
                    state = "leaving";
                    sTick = tick;
                }
                break;
            case "leaving":
                if (getX() > 1500) {
                    bahnhof.unlockIntersec(assignedIntersec);
                    getWorld().removeObject(this);
                }
                if (getX() >= 1155 && getX() <= 1275) {
                    switch (assignedIntersec%3) {
                        case 0:
                            if (tick%4==0 ) {
                                if (tick-sTick<35) {
                                    setRotation(getRotation()+1);
                                } else {
                                    setRotation(getRotation()-1);
                                }
                                setLocation(getX()+3, getY());
                            }
                            setLocation(getX(), getY()+1);
                            break;
                        case 2:
                            if (tick%4==0 ) {
                                if (tick-sTick<35) {
                                    setRotation(getRotation()-1);
                                } else {
                                    setRotation(getRotation()+1);
                                }
                                setLocation(getX()+3, getY());
                            }
                            setLocation(getX(), getY()-1);
                            break;
                    }
                }
                setLocation(getX()+1, getY());
                break;
        }
       
    }
    
    public int getCapacity() {
        return trainCapacity;
    }
}
