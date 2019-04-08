package application;

public class Game {

	private int speed;
	private Snake snake;
	private Leaderboard lb;
	Game() {
		lb = new Leaderboard();
	}
	public Snake getSnake() {
		return snake;
	}
	public void setSnake(Snake snk) {
		snake = snk;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int s) {
		speed = s;
	}
}
