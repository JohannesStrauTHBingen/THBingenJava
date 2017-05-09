package katzundmaus;

public class Main {

	public static void main(String[] args) {
		int cats;
		int k;
		int m;
		int mice;
		int n;
		
		try {
			cats = Integer.parseInt(args[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			cats = 5;
		}
		try {
			k = Integer.parseInt(args[1]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			k = (int) (Math.random() * 10000);
		}
		try {
			m = Integer.parseInt(args[2]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			m = (int) (Math.random() * 10000) + k;
		}

		try {
			mice = Integer.parseInt(args[3]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			mice = 5;
		}
		try {
			n = Integer.parseInt(args[4]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			n = (int) (Math.random() * 10000);
		}

		System.out.println("Spielkonfiguration\n------------------");
		System.out.println("Katzen:\t" + cats + "\t\t\tMäuse:" + mice);
		System.out.println("k:\t" + k + " ms" + "\t\t\tn:\t" + n + " ms");
		System.out.println("m:\t" + m + " ms\n\n");
		System.out.println("Spielstand\n----------");
		
		new Room(n, k, m, mice, cats);
	}

}
