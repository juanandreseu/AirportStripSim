import java.util.Random;
public class Airplane implements Comparable<Airplane>, Updatable{
    private int fuel;
    private States state;
    private enum States {LANDING, TAKINGOFF};
    private Random rand=new Random();
    public Airplane(){
        fuel=rand.nextInt(11)+5;//Randomly assigns a value for fuel between 5 and 15 units.
        if(rand.nextInt(2)==1){ //Randomly selects a state, whether the plane is landing or taking off.
            state=States.LANDING;
        }
        else{
            state=States.TAKINGOFF;
        }
    }
    public int getFuel(){ return fuel;}
    public void setFuel(int f){ fuel=f;}
    public boolean isLanding(){ return state==States.LANDING;}
    public boolean isTakingOff(){ return state==States.TAKINGOFF;}
    @Override
    public int compareTo(Airplane plane){
        if (fuel<plane.getFuel()){//If plane has less fuel than current plane.
            return -1;
        }
        else if(fuel==plane.getFuel()){ //If fuels are the same
            return 0;
        }
        return 1; //If plane has more fuel than current plane.
    }
    @Override
    public void update(){
        fuel--;
    }
}