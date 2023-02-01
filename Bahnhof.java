import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class TrainInfo{
        public int ID;
        public String name; 
        public int[] tracks;
        public int minCapacity;
        public int maxCapacity;
        public int waitTime;
        
        TrainInfo(int ID, String name, int[] tracks, int minCapacity, int maxCapacity, int waitTime) {
            this.ID = ID;
            this.name = name;
            this.tracks = tracks;
            this.minCapacity = minCapacity;
            this.maxCapacity = maxCapacity;
            this.waitTime = waitTime;
        }
}

public class Bahnhof extends World
{
    int tick;
   
    // 0=>Open, 1=>Locked, 2=>Ready
    public int[] Intersections = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 0=>Open, 1=>Locked
    public int[] Track = {0, 0, 0};
    // [TrackID, startY, endY, startX, endX, entryX, entryY, UpDown]
    public int[][] Platforms = {{0, 13, 70, 430, 855, 405, 40, 0},  
                            {0, 139, 233, 430, 855, 405, 233, 1},
                            {1, 234, 329, 430, 855, 405, 233, 0},
                            {1, 399, 500, 430, 855, 405, 500, 1}, 
                            {2, 501, 585, 430, 855, 405, 500, 0},
                            {2, 659, 710, 430, 855, 405, 688, 1}};
                            
    public ArrayList<TrainInfo> TrainList = new ArrayList<>();
    
    public LinkedList<Screen> Queue = new LinkedList<Screen>();    
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Bahnhof()
    {    
        super(1280, 720, 1, false); 
        setPaintOrder(Screen.class, Train.class, Person.class, Intersection.class, Track.class, Station.class);
        
        TrainList.add(new TrainInfo(0, "S19 Koblenz", new int[]{0,1}, 15, 20, 900 ));
        TrainList.add(new TrainInfo(1, "IC8 Brig", new int[]{1,2}, 20, 35, 1800 ));
        TrainList.add(new TrainInfo(2, "S2 Buchs", new int[]{2,0}, 25, 30, 1800 ));
        TrainList.add(new TrainInfo(3, "IR13 Chur", new int[]{0,1}, 10, 15, 600 ));
        
        Station station = new Station();
        addObject(station, 215, 360);
        
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
    
    public void newMessage(String text) {
        
        if (Queue.size() >= 1) {
            for (Screen screen : Queue) {
                screen.updateScreen();
            }
        }
        Screen screen = new Screen(getTime() + " " + text);
        Queue.addFirst(screen);
        addObject(screen, 20+screen.getImage().getWidth()/2, 30);
        if (Queue.size() > 5) { 
            removeObject(Queue.getLast());
            Queue.removeLast();
        }
    }
    
    public String getTime() {
        String min = Integer.toString((tick/10)%60);
        if (min.length() <= 1) min = "0" + min;
        String hour = Integer.toString((int)tick/600);
        if (hour.length() <= 1) hour = "0" + hour;
        return hour + ":" + min;
    }
    
    public void spawnTrain(){
        TrainInfo trainInfo = TrainList.get(Greenfoot.getRandomNumber(TrainList.size()));
        for (int tries = 0; tries<9;tries++) {
            int t = trainInfo.tracks[Greenfoot.getRandomNumber(2)]*3+tries%3;
            if (Intersections[t] == 0) {
                lockIntersec(t);
                Train train = new Train(trainInfo, t);
                int position = 0;
                switch (t) {
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
