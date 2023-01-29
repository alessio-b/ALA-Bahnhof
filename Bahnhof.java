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
    
    //
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
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Bahnhof()
    {    
        super(1280, 720, 1, false); 
        setPaintOrder(Train.class, Person.class, Intersection.class, Track.class, Station.class);
        
        Station station = new Station();
        addObject(station, 210, 360);
        
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
        for (int tries = 0; tries<9;tries++) {
            int random = Greenfoot.getRandomNumber(9);
            if (Intersections[random] == 0) {
                lockIntersec(random);
                Train train = new Train(random);
                int loc = 0;
                switch (random) {
                    case 0:
                        loc = 32;
                        break;
                    case 1:
                        loc = 100;
                        break;
                    case 2:
                        loc = 168;
                        break;
                    case 3:
                        loc = 292;
                        break;
                    case 4:
                        loc = 360;
                        break;
                    case 5:
                        loc = 428;
                        break;
                    case 6:
                        loc = 552;
                        break;
                    case 7:
                        loc = 620;
                        break;
                    case 8:
                        loc = 688;
                        break;
                        
                }
                addObject(train, 1500, loc);
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
