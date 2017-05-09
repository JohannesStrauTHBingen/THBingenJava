package katzundmaus;

import java.util.ArrayList;

public class Room {

	private Cat cat = null;
	private ArrayList<Mouse> mice = new ArrayList<>();
	private int livingCats;
	private int livingMice;
	private boolean winner = false;

	public Room(int n, int k, int m, int mice, int cats) {
		livingCats = cats;
		livingMice = mice;

		for (int i = 0; i < mice; i++) {
			new Mouse(this, n).start();
		}
		for (int i = 0; i < cats; i++) {
			new Cat(this, m, k).start();
		}
		printScore();
	}

	public synchronized void printScore() {
		if (livingCats == 0 || livingMice == 0) {
			winner = true;
		}
		System.out.println("Cats\t" + livingCats + " : " + livingMice + "\tMice");
	}

	public synchronized void catEnter(Cat cat) {
		while ((this.cat != null || mice.size() == 0) && cat.catIsAlive() && !hasWinner()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (cat.catIsAlive()) {
			this.cat = cat;
		}
	}

	public synchronized void mouseEnter(Mouse mouse) {
		while (cat != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mice.add(mouse);
		notifyAll();
	}

	public synchronized boolean getAndEatMouse() {
		if (mice.size() > 0) {
			mice.remove(0).killMouse();
			livingMice--;
			printScore();
			return true;
		}
		return false;
	}

	public synchronized void buryCat(Cat cat) {
		livingCats--;
		printScore();
		if (this.cat == cat) {
			this.cat = null;
			notifyAll();
		}
	}

	public synchronized void catLeave(Cat cat) {
		this.cat = null;
		notifyAll();
	}

	public synchronized void mouseLeave(Mouse mouse) {
		mice.remove(mouse);
	}
	
	public synchronized boolean hasWinner() {
		return winner;
	}
	
}
