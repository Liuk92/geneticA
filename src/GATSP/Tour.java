package GATSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;

public class Tour{

    private ArrayList<Nodo> tour = new ArrayList<Nodo>();
    
    private double fitness = 0;
    
    private boolean FitnessCambiato = true;
    
    
    
    public Tour(GA genetic) {
    	genetic.getInitial().forEach(x-> tour.add(null));  
    	}
    
    public Tour(ArrayList<Nodo> tour){
        this.tour.addAll(tour);
        //Collections.shuffle(this.tour);
        
    }
   
    
    
    public ArrayList<Nodo> getTour() {
    	FitnessCambiato = true;
		return tour;
	}

	public void setTour(ArrayList<Nodo> tour) {
		this.tour = tour;
	}


    public Nodo getCity(int tourPosition) {
        return (Nodo)tour.get(tourPosition);
    }


    public double getFitness() {
    	if (FitnessCambiato == true) {
        
            fitness = (1/(double)calcolaDistanza1())*10000;
            FitnessCambiato = false;
        }
        return fitness;
    }
    

    
    public double calcolaDistanza1() {
    	int nTour= this.tour.size();
    	return (int) (this.tour.stream().mapToDouble(x -> {
    		int iTour = this.tour.indexOf(x);
    		double distanza = 0;
    		if(iTour<nTour-1) 
    			distanza = x.calcolaDistanza(this.tour.get(iTour+1));
    	return distanza;
    }).sum() + this.tour.get(0).calcolaDistanza(this.tour.get(nTour - 1))) ;
    }
    	
 
    public int tourSize() {
        return tour.size();
    }
    
    
    public boolean containsCity(Nodo city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        return Arrays.toString(tour.toArray());
    }

	public ArrayList<Nodo> getCities() {
		
		return tour;
	}
}
