package katzundmaus;

public class Cat extends Thread {

	private Room room;
	private boolean catIsAlive = true;
	private long nextDinner;
	private int m;
	private int k;

	public Cat(Room room, int m, int k) {
		this.room = room;
		this.nextDinner = System.currentTimeMillis() + m;
		this.m = m;
		this.k = k;
	}

	public void run() {
		while (this.catIsAlive() && !room.hasWinner()) {
			room.catEnter(this);
			try {
				sleep(k);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			eatMouse();
			if (this.catIsAlive()) {
				room.catLeave(this);
			}
		}
	}

	private void eatMouse() {
		if (this.catIsAlive()) {
			if (room.getAndEatMouse()) {
				nextDinner = System.currentTimeMillis() + m;
			}
		}
	}

	public synchronized boolean catIsAlive() {
		if( !catIsAlive)
			return false;
		if (System.currentTimeMillis() > nextDinner) {
			room.buryCat(this);
			killCat();
			return false;
		}
		return true;
	}

	public synchronized void killCat() {
		this.catIsAlive = false;
	}

}
