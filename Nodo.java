package GATSP;


public class Nodo {
	private String name;
	private double longitudine;
	private double latitudine;
	
	private static final double RAGGIO_TERRESTRE = 6378.1370D;
	private static final double GRADI_RADIANTI = Math.PI/180D;
	private static final double KM_MIGLIA = 0.621371;
	
	
	public Nodo(String name,double lat, double lon) {
		this.longitudine=lon*GRADI_RADIANTI;
		this.latitudine=lat*GRADI_RADIANTI;
		this.name=name;
	}
	
		
	public double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}

	public double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int calcolaDistanza(Nodo nodo) {
		double temp1=nodo.getLongitudine() - this.getLongitudine();
		double temp2=nodo.getLatitudine() - this.getLatitudine();
		double dist = Math.pow(Math.sin(temp1/2D), 2D) +
				Math.cos(this.getLatitudine()) * Math.cos(nodo.getLatitudine()) *
				Math.pow(Math.sin(temp2/2D), 2D);
		return (int)(KM_MIGLIA*RAGGIO_TERRESTRE*2D*Math.atan2(Math.sqrt(dist),Math.sqrt(1D-dist)));
	}
	
	@Override
    public String toString(){
        return getName();
    }

	public String getName() {
		return this.name;
	}
}
