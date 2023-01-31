import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.util.Arrays;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bahnhof extends World
{
    int tick;
    
    // 0=>Open, 1=>Used, 2=>
    public int[] Intersections = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    // [ name,  ]
    public int[] Track = {0, 0, 0};
    // [TrackID, startY, endY, startX, endX, entryX, entryY, UpDown]
    public int[][] Platforms = {{0, 13, 70, 430, 855, 405, 40, 0},  
                            {0, 139, 233, 430, 855, 405, 233, 1},
                            {1, 234, 329, 430, 855, 405, 233, 0},
                            {1, 399, 500, 430, 855, 405, 500, 1}, 
                            {2, 501, 585, 430, 855, 405, 500, 0},
                            {2, 659, 710, 430, 855, 405, 688, 1}};
    // [TrainID, validTrack, minCapacity, maxCapacity, waitTime]                        
    public int[][] Trains = {{0, 1, 50, 150, 1800},
                            {0, 0, 100, 225, 1800},
                            {0, 2, 200, 225, 3600},
                            {0, 2, 100, 225, 1800}};
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Bahnhof()
    {    
        super(1280, 720, 1, false); 
        setPaintOrder(Screen.class, Train.class, Person.class, Intersection.class, Track.class, Station.class);
        
        Station station = new Station();
        addObject(station, 215, 360);
        
        Screen screen = new Screen();
        addObject(screen, 215, 130);
        //screen.addText("Test");
        
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
            spawnPerson();
        }
        if (tick%270==0) {
            spawnTrain();
        }
    }
    
    public void spawnPerson() {
        addObject(new Person(Platforms[Greenfoot.getRandomNumber(5)]), Greenfoot.getRandomNumber(380)+10, Greenfoot.getRandomNumber(700)+10);
    }
    
    public void spawnTrain(){
        int[] trainInfo = Trains[Greenfoot.getRandomNumber(Trains.length)];
        int tracknum;
        for (int tries = 0; tries<9;tries++) {
            tracknum = Greenfoot.getRandomNumber(3)+ trainInfo[1]*3;
            if (Intersections[tracknum] == 0) {
                lockIntersec(tracknum);
                Train train = new Train(tracknum, trainInfo);
                int position = 0;
                switch (tracknum) {
                    case 0:
                        position = 32;
                        break;
                    case 1:
                        position = 100;
                        break;
                    case 2:
                        position = 168;
                        break;
                    case 3:
                        position = 292;
                        break;
                    case 4:
                        position = 360;
                        break;
                    case 5:
                        position = 428;
                        break;
                    case 6:
                        position = 552;
                        break;
                    case 7:
                        position = 620;
                        break;
                    case 8:
                        position = 688;
                        break;
                        
                }
                addObject(train, 1500, position);
                break;
            }
        }
    }
    
    public void lockIntersec(int i) {
        Intersections[i] = 1;
    }
    
    public void unlockIntersec(int i){
        Intersections[i] = 0;
    }
    
        public void lockTrack(int i) {
        Track[i] = 1;
    }
    
    public void unlockTrack(int i){
        Track[i] = 0;
    }
    
    public int getTrack(int i) {
        return Track[i];
    }
    
    public void readyTrack(int i) {
        Track[i] = 2;
    }
}
