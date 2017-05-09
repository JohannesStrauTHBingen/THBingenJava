package katzundmaus;

public class Mouse extends Thread {

	private Room room;
	private boolean mouseIsAlive = true;
	private int n;

	public Mouse(Room room, int n) {
		this.room = room;
		this.n= n;
	}
	
	public boolean mouseIsAlive() {
		return mouseIsAlive ;
	}

	public void killMouse() {
		this.mouseIsAlive = false;
	}
	
	public void run() {
		while (mouseIsAlive && !room.hasWinner()) {
			room.mouseEnter(this);
			try {
				sleep(n);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(mouseIsAlive()){
				room.mouseLeave(this);		
			}
		}
	}
	
}
