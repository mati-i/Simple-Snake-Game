package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePaint extends JPanel implements KeyListener, ActionListener {

	private int randomX = (int) Math.round(Math.random() * 600);
	private int randomY = (int) Math.round(Math.random() * 420);

	private int snakeWidth = 10;
	private int snakeHeight = 10;
	private Timer time;
	private int direction;
	private Point snake;
	private LinkedList<Point> snakeLength = new LinkedList();
	private boolean gameOver = false;

	public SnakePaint() {
		setBackground(Color.GRAY);

		snake = new Point(300, 240);
		snakeLength.add(snake);
		time = new Timer(60, this);
		time.start();
	}

	public void paintComponent(Graphics g) {
		Font gameFont;
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D quadrate = new Rectangle2D.Double(snake.x, snake.y, snakeWidth, snakeHeight);
		for (int n = 0; n < snakeLength.size(); n++) {
			g2.setColor(Color.BLACK);
			Point p = snakeLength.get(n);
			quadrate = new Rectangle2D.Double(p.x, p.y, snakeWidth, snakeHeight);

			g2.fill(quadrate);
		}

		Ellipse2D food = new Ellipse2D.Double(randomX, randomY, 10, 10);
		g2.setPaint(Color.RED);
		g2.fill(food);

		if (gameOver) {
			gameFont = new Font("Arial", Font.BOLD, 30);
			g.setFont(gameFont);
			g.drawString("GAME OVER", 250, 480 / 2);
		}

	}

	public void gameOver() {
		for (int i = 2; i < snakeLength.size(); i++) {
			Point p2 = snakeLength.get(i);
			if (snake.x == p2.x && snake.y == p2.y)
				gameOver = true;

		}

	}

	public void snakeGrowth() {

		snakeLength.add(0, new Point(snake.x, snake.y));
		snakeLength.remove(snakeLength.size() - 1);

		if (snake.x > (randomX - 7) && snake.x < (randomX + 7) && snake.y > (randomY - 7) && snake.y < (randomY + 7)) {
			randomX = (int) Math.round(Math.random() * 600);
			randomY = (int) Math.round(Math.random() * 420);
			snakeLength.add(0, new Point(snake.x, snake.y));

		}

	}

	public void snakeMovement() {
		if (!gameOver) { // el signo "!" invierte el resultado booleano.
			switch (direction) {
			case 38:
				snake.y -= 10;

				if (snake.y < 0)
					snake.y = 480;
				break;
			case 40:
				snake.y += 10;
				if (snake.y > 480)
					snake.y = 0;
				break;
			case 39:
				snake.x += 10;
				if (snake.x > 640)
					snake.x = 0;
				break;
			case 37:
				snake.x -= 10;
				if (snake.x < 0)
					snake.x = 640;
				break;
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_UP:
			if (direction != 40)
				direction = 38;
			break;

		case KeyEvent.VK_DOWN:
			if (direction != 38)
				direction = 40;

			break;
		case KeyEvent.VK_RIGHT:
			if (direction != 37)
				direction = 39;
			break;
		case KeyEvent.VK_LEFT:
			if (direction != 39)
				direction = 37;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		snakeMovement();
		snakeGrowth();
		gameOver();
		repaint();
	}

}
