
public class Folge {

	private double realZ;
	private double imgZ;
	private double realC;
	private double imgC;

	public Folge() {
	}

	// Apfelmänchen: Z = 0, C =  bildpunkt
	//Julia: Z = bildpunkt, C = beliebig
	
	
	/*
	 * @param int maxItereation: Die Anzahl der maximal durchzuführenden Iterationen (Anzahl der verfügbaren Farben)
	 * return int die durchgeführten iterationen
	 */
	public int iterate(int maxIteration) {
		int steps = 0;
		double border = 2;
		double realN = realZ;
		double imgN = imgZ;
		double realNminus1 = realZ;
		double imgNminus1 = imgZ;

		for (int i = steps; i < maxIteration; i++) {
			realN = (realNminus1 * realNminus1) - (imgNminus1 * imgNminus1);
			imgN = (realNminus1 * imgNminus1) * 2;
			realN += realC;
			imgN += imgC;

			if (Math.abs(Math.sqrt(realN * realN) + (imgN * imgN)) > border) {
				return steps;
			}
			realNminus1 = realN;
			imgNminus1 = imgN;
		}
		return steps;
	}

	public Folge setRealZ(double realC) {
		this.realC = realC;
		return this;
	}

	public Folge setRealC(double realZ) {
		this.realZ = realZ;
		return this;
	}

	public Folge setImgC(double imgC) {
		this.imgC = imgC;
		return this;
	}

	public Folge setImgZ(double imgZ) {
		this.imgZ = imgZ;
		return this;
	}

	public double getRealZ() {
		return realZ;
	}

	public double getRealC() {
		return realC;
	}

	public double getImgZ() {
		return imgZ;
	}

	public double getImgC() {
		return imgC;
	}
}