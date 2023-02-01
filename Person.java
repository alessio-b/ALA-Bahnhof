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
    public TrainInfo trainInfo;
    private int[] destination = new int[2]; // [X, Y]
    GreenfootImage standing = new GreenfootImage("person/standing.png");
    GreenfootImage walking1 = new GreenfootImage("person/walking1.png");
    GreenfootImage walking2 = new GreenfootImage("person/walking2.png");
    
    int ranmod = Greenfoot.getRandomNumber(2);
    
    public int[][] Platforms;
    public Person(TrainInfo Info) {
        standing.scale(50,50);
        walking1.scale(50,50);
        walking2.scale(50,50);
        setImage(standing);
        
        trainInfo = Info;
        state = "";
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private void moveT(){
        turnTowards(destination[0], destination[1]);
        move(1);
            
        if (time%40==0) {
            setImage(walking1);
        } else if (time%20==0) {
            setImage(walking2);
        }
    }
    
    public void act()
    {
        time++;
        int track = ((Bahnhof) getWorld()).getTrainTrack(trainInfo.name);
        Platforms = ((Bahnhof) getWorld()).Platforms;
        
        int platform = track*2+ranmod;
        if (state == "return") {
            if (getX() > destination[0]) {
                moveT();
            } else {
                setImage(standing);
                state = "";
            }
        }
        if (track != -1) {
            switch (state) {
                default:
                    setImage(standing);
                    destination[0] = Platforms[platform][5];
                    destination[1] = Platforms[platform][6];
                    state = "toEntry";
                    break;
                case "toEntry":
                    moveT();
                    if ( getX() == destination[0] && getY() == destination[1]) {
                        destination[0] = Greenfoot.getRandomNumber(Platforms[platform][4]-Platforms[platform][3])+Platforms[platform][3];
                        destination[1] = Greenfoot.getRandomNumber(Platforms[platform][2]-Platforms[platform][1])+Platforms[platform][1];
                        state = "toTrack";
                    }
                    break;
                case "toTrack":
                    moveT();
                    if ( getX() == destination[0] && getY() == destination[1]) {
                        state = "Enter";
                    }
                    break;
                case "Enter":
                        if (Platforms[platform][7] == 0) {
                            setRotation(90);
                            move(1);
                        } else {
                            setRotation(270);
                            move(1);
                        }
                    break;
            }
        } else {
            if (getX() > 390) {
                destination[0] = Greenfoot.getRandomNumber(380)+10;
                state = "return";
            }
        }
        /*
        if (state == "toEntry" || state == "toTrack") {
            if ( getX() == destination[0] && getY() == destination[1]) {
                if (state == "toEntry") {
                    destination[0] = Greenfoot.getRandomNumber(Platforms[track][4]-Platforms[track][3])+Platforms[track][3];
                    destination[1] = Greenfoot.getRandomNumber(Platforms[track][2]-Platforms[track][1])+Platforms[track][1];
                            
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
            
        }
        if (state == "standing") {
            if ( ((Bahnhof) getWorld()).Track[Platforms[track][0]] == 2 ) {
                if (time%40==0) {
                    setImage(walking1);
                } else if (time%20==0) {
                    setImage(walking2);
                }
                if (Platforms[track][7] == 0) {
                    setRotation(90);
                    move(1);
                } else {
                    setRotation(270);
                    move(1);
                }
            }
        }
        */
    }
}
