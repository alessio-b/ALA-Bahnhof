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
    private int track;
    private int currentAmount;
    private int trainCapacity;
    
    private int waitTime;
    private int tick;
    private int sTick = 0;
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Train(TrainInfo trainInfo, int entryTrack) {
        setImage("train.png");
        
        assignedIntersec = entryTrack;
        track = entryTrack/3;
        trainCapacity = Greenfoot.getRandomNumber(trainInfo.maxCapacity-trainInfo.minCapacity)+trainInfo.minCapacity;
        waitTime = trainInfo.waitTime;
        trainLine = trainInfo.name;
        
        state = "incoming";
    }
    
    public void act()
    {
        Bahnhof bahnhof = ((Bahnhof)getWorld());
        tick++;
        switch(state) {
            case "incoming":
                setLocation(getX()-2, getY());
                if (getX() < 1225) {
                    bahnhof.newMessage(trainLine + " is arriving on Track "+ track);
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
                    bahnhof.newMessage(trainLine + " is leaving from Track " + track);
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
                    bahnhof.removeObject(this);
                }
                break;
        }
       
    }
    
    public int getCapacity() {
        return trainCapacity;
    }
}
