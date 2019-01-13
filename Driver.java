public class Driver {
    public static void main(String[] args){
        Airport juanAndresAirport=new Airport();
        double probability=(double)(1)/6;
        juanAndresAirport.simulate(3200,probability);
        juanAndresAirport.displayResults();
    }
}
