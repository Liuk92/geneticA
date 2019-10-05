package GATSP;

import java.util.ArrayList;
import java.util.Arrays;

public class test {

	private static final double MIGLIA_KM = 1.609344;
	
	public ArrayList<Nodo> initialRoute = new ArrayList<Nodo>(Arrays.asList(
			 new Nodo("Latina",41.28,12.53),
	        
	         new Nodo("Napoli",40.21,14.15),
	        
	       new Nodo("Bologna",44.30,11.21),
	       
	         new Nodo("Caserta",41.04,14.19),
	        
	         new Nodo("Milano",45.28, 9.11),
	      
	        new Nodo("Firenze",43.46,11.15),
	      
	         new Nodo("Agrigento",37.18,13.36),
	        
	         new Nodo("Ancona",43.37,13.31),
	        
	        new Nodo("Ascoli Piceno",42.51,13.34),
	        
	         new Nodo("Bari",41.07,16.53),
	       
	         new Nodo("Benevento",41.08,14.46),
	        
	       new Nodo("Bergamo",45.42,9.40),
	        
	         new Nodo("Cagliari",39.13,9.07),
	        
	         new Nodo("Brescia",45.32,10.12),
	        
	         new Nodo("Asti",44.53,8.12),
	       
	         new Nodo("Catania",37.30,15.05),
	        
	         new Nodo("Chieti",42.21,14.10),
	       
	       new Nodo("Genova",44.25,8.55),
	       
	         new Nodo("Lecce",40.21,18.11),
	      
	        new Nodo("Livorno",43.33,10.19)
			));
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t = new test();
		Population pop = new Population(GA.POP_SIZE,t.initialRoute);
		pop.ordinaStrade();
		GA ga = new GA(t.initialRoute);
		int genN = 0;
		t.print(genN++);
		t.printPop(pop);
		while(genN < GA.numGen) {
			t.print(genN++);
			pop=ga.evolvePopulation1(pop);
			pop.ordinaStrade();
			t.printPop(pop);
		}
		System.out.println("La migliore soluzione trovata : " +pop.getRoutes().get(0));
		System.out.println("w/ di distanza "+String.format("%.2f",pop.getRoutes().get(0).calcolaDistanza1()/MIGLIA_KM)+" Km");
		
	}

	public void print(int i) {
		
		System.out.println(" Generazione " + i);
		System.out.println("");
		
		
	}

	public void printPop(Population pop) {
		
		pop.getRoutes().forEach(x->{
			System.out.println(Arrays.toString(x.getCities().toArray())+ " | "+
					String.format("%.4f", x.getFitness())+" | "+String.format("%.2f",x.calcolaDistanza1()));
		});
		System.out.println("");
	}

}
