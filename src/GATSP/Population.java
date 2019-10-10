package GATSP;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

   
	private ArrayList<Tour> strade = new ArrayList<Tour>(GA.POP_SIZE);
	
   
    public Population(int populationSize, ArrayList<Nodo> initialRoute) {
        IntStream.range(0,populationSize).forEach(x-> strade.add(new Tour(initialRoute)));
        }
    
    public Population(int populationSize, GA genetic) {
        IntStream.range(0,populationSize).forEach(x-> strade.add(new Tour(genetic.getInitial())));
        }
    
      
    public void ordinaStrade() {
    	strade.sort((strada1, strada2) ->{
    		int flag = 0;
    		if(strada1.getFitness() > strada2.getFitness())
    			flag = -1;
    		else if(strada1.getFitness() < strada2.getFitness())
    			flag = 1;
    		return flag;
    	});
    }
    
    
    public ArrayList<Tour> getRoutes(){return strade;}
    
    
}
