package snake;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SnakeFrame extends JFrame {

	private final int _WIDTH = 640;
	private final int _HEIGHT = 480;

	public SnakeFrame() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Centering Frame
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width / 2) - _WIDTH / 2, (screen.height / 2) - _HEIGHT / 2, _WIDTH, _HEIGHT);

		// Snake paint
		SnakePaint snake = new SnakePaint();
		add(snake);
		addKeyListener(snake);

		setUndecorated(true);
		setVisible(true);
	}

}
