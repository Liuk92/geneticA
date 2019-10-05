package GATSP;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GA {

    /* GA parametri */
    private static final double mutationRate = 0.025;//0.015
    public static final int POP_SIZE = 20;
    public static final int numGen =10;
    
    private ArrayList<Nodo> initialRoute = null;
    
    /* parametri utility */ 
    private static final int tournamentSize = 3;//5
    public static final int elitism = 1;

    
    public GA( ArrayList<Nodo> initial) {this.initialRoute =initial; }
    
    public  ArrayList<Nodo> getInitial(){return initialRoute; }
    
    public Population evolvePopulation1(Population pop){
    	return mutatePopulation(crossossoverPop(pop));
    }
    
   
    
    
	Population crossossoverPop(Population pop) {
		
		Population crossPop = new Population(pop.getRoutes().size(),this);
		IntStream.range(0,elitism).forEach(x -> crossPop.getRoutes().set(x,pop.getRoutes().get(x)));
		IntStream.range(elitism, crossPop.getRoutes().size()).forEach(x->{
			Tour t1 = tournamentSelection(pop).getRoutes().get(0);
			Tour t2 = tournamentSelection(pop).getRoutes().get(0);
			crossPop.getRoutes().set(x, crossoverTour(t1,t2));
		
		});
		return crossPop;
		
	}

	Tour crossoverTour(Tour parent1, Tour parent2) {
 
	  Tour cross=new Tour(this);
	  Tour temp1=parent1;
	  Tour temp2=parent2;
	  if (Math.random()<0.5) {
		  temp1=parent2;
		  temp2=parent1;
	  }
	  for (int x=0;x<cross.getCities().size()/2;x++)
		  cross.getCities().set(x, temp1.getCities().get(x));
	  return FillNullCrossTour(cross,temp2);
	
	}



    // Seleziona il Tour candidato per il crossover
    Population tournamentSelection(Population pop) {
        Population tPop = new Population(tournamentSize,this);
        IntStream.range(0,tournamentSize).forEach(
        	x-> tPop.getRoutes().set(x,pop.getRoutes().get((int)(Math.random()*pop.getRoutes().size()))));
        	tPop.ordinaStrade();;
        	return tPop;
        	
        }
    	
    	
    
    Population mutatePopulation(Population pop) {
    	pop.getRoutes().stream().filter(x -> pop.getRoutes().indexOf(x) >= 1).forEach(x ->mutateTour(x));
    	return pop;	
    }

	Tour mutateTour(Tour y) {
		
		y.getCities().stream().filter(x -> Math.random() < mutationRate).forEach(nodox->{
			
			int z = (int) (y.getCities().size()*Math.random());
			Nodo citta= y.getCities().get(z);
			y.getCities().set(y.getCities().indexOf(nodox), citta);
			y.getCities().set(z, nodox);
				
		});
		return y;
	}
    

	// Funzione filtro
	private Tour FillNullCrossTour(Tour crossT, Tour t1) {
	
		t1.getCities().stream().filter(x-> !crossT.getCities().contains(x)).forEach(cx->{
			
			for(int j=0;j<t1.getCities().size();j++) {
				if(crossT.getCities().get(j)==null) {
					crossT.getCities().set(j, cx);
					break;
				}
			}
		});
	return crossT;
	}}
	