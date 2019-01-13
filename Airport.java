import java.util.Random;
public class Airport {
        private LinkedPriorityQueue<Airplane> airplanesLanding;
        private LinkedQueue<Airplane> airplanesTakingOff;
        private int takeOffCounter, landingCounter,takeOffTotalTime, landingTotalTime,planesCrashed, longestLanding, longestTakeOff;
        public Airport() {
            airplanesLanding=new LinkedPriorityQueue<>();
            airplanesTakingOff=new LinkedQueue<>();
            reset();
        }
        public void simulate(int duration, double planeProbability){//Simulates the airport generating a plane depending on the probability
            reset();
            int runwayTimeLeft=0, tempLongestLanding=0, tempLongestTakeOff=0; //Time counter for the usage of a runway;
            Airplane currentAirplane=new Airplane();//Variable for current airplane. It will be overwritten in the for loop. It is only initialized for compiling reasons.(May have not been the best solution)
            for(int clock=0;clock<duration;clock++) {
                if (Math.random() < planeProbability) { //Uses a poisson distribution where lambda=1/6.
                    Airplane nextAirplane = new Airplane();
                    if (nextAirplane.isLanding()) {
                        airplanesLanding.add(nextAirplane);
                    } else {
                        airplanesTakingOff.enqueue(nextAirplane);
                    }
                }
                if(runwayTimeLeft>0){
                    runwayTimeLeft--;
                    if(clock>=(duration-runwayTimeLeft)){//If the simulation is about to end while the runway is in use, the simulation will end after the runway is emptyy.
                        clock=(duration-runwayTimeLeft)+1;
                    }
                    if(currentAirplane.isLanding()){//update fuel if current plane is landing and increment landing wait time
                        currentAirplane.update();
                        airplanesLanding.updateValues();
                        landingTotalTime++;
                        longestLanding++;
                        if (!airplanesTakingOff.isEmpty()) { //If planes are waiting to take off increment the taking off waiting time.
                            takeOffTotalTime++;
                            longestTakeOff++;
                        }
                    } else{
                        takeOffTotalTime++;
                        longestTakeOff++;
                        if (!airplanesLanding.isEmpty()) { //If planes are waiting to land increment the landing waiting time and update fuel.
                            airplanesLanding.updateValues();
                            landingTotalTime++;
                            longestLanding++;
                        }
                    }
                    if(runwayTimeLeft==0){
                        if(currentAirplane.isLanding()){
                            if(currentAirplane.getFuel()==0){
                                longestLanding=0;
                                planesCrashed++;
                                System.out.println("Plane crashed!!!!");
                            }else {
                                landingCounter++;
                                if(longestLanding>tempLongestLanding){
                                    tempLongestLanding=longestLanding;
                                }
                                longestLanding=0;
                                System.out.println("Plane landed.");
                            }
                        } else{
                            takeOffCounter++;
                            if(longestTakeOff>tempLongestTakeOff){
                                tempLongestTakeOff=longestTakeOff;
                            }
                            longestTakeOff=0;
                            System.out.println("Plane took off");
                        }
                    }
                } else if(!airplanesLanding.isEmpty()){
                    currentAirplane=airplanesLanding.remove();
                    airplanesLanding.updateValues();
                    runwayTimeLeft=2;
                    if(!airplanesTakingOff.isEmpty()){//If planes are waiting to take off increment the taking off waiting time.
                        takeOffTotalTime++;
                        longestTakeOff++;
                    }
                    landingTotalTime++;
                    longestLanding++;
                    System.out.println("Plane is landing");

                }
                else if(!airplanesTakingOff.isEmpty()){
                    currentAirplane=airplanesTakingOff.dequeue();
                    if (!airplanesLanding.isEmpty()) { //If planes are waiting to land increment the landing waiting time and update fuel.
                        airplanesLanding.updateValues();
                        landingTotalTime++;
                        longestLanding++;
                    }
                    takeOffTotalTime++;
                    longestTakeOff++;
                    runwayTimeLeft=3;
                    System.out.println("Plane is taking off");
                }
            }
            longestTakeOff=tempLongestTakeOff;
            longestLanding=tempLongestLanding;


        }
        public final void reset(){
            airplanesLanding.clear();
            airplanesTakingOff.clear();
            takeOffCounter=0;
            takeOffTotalTime=0;
            landingCounter=0;
            landingTotalTime=0;
            planesCrashed=0;
            longestTakeOff=0;
            longestLanding=0;
        }
        public void displayResults(){
            System.out.println("\nJuan Andres' Airport Statistics: \n");
            System.out.println("Total planes generated: "+(takeOffCounter+landingCounter+planesCrashed+"\n"));
            System.out.println("Number of planes that took off: "+takeOffCounter);
            System.out.println("Longest time for a plane to take off: "+longestTakeOff+" units of time");
            System.out.println("Average waiting time for take off: "+String.format( "%.2f", ((double)(takeOffTotalTime)/takeOffCounter)));
            System.out.println("Number of planes that landed: "+landingCounter);
            System.out.println("Longest time for a plane to land: "+longestLanding+" units of time");
            System.out.println("Average waiting time for landing: "+String.format( "%.2f", ((double)(landingTotalTime)/landingCounter)));
            System.out.println("Number of planes crashed: "+planesCrashed);
        }


}
