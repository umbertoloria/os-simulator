package com.umbertoloria.graphics;

import com.umbertoloria.components.Computer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Watcher extends JComponent {

	private Computer pc;
	private JFrame f;
	private Timer t;

	public Watcher(Computer pc) {
		this.pc = pc;
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();

		f = new JFrame("OS Simulator - Umberto Loria");
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(1408, 638);
		f.setLocation((sc.width - f.getWidth()) / 2, (sc.height - f.getHeight()) / 2);

		f.add(this);

		f.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					close();
				}
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		t = new Timer(16, (e) -> repaint());
		t.start();
	}

	private Renderer renderer = new Renderer();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		renderer.attach(g);
		pc.draw(renderer);
	}

	private boolean closing = false;

	private void close() {
		t.stop();
		f.dispose();
		closing = true;
	}

	public boolean isClosing() {
		return closing;
	}

}
