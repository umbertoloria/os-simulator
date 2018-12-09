package com.umbertoloria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Watcher extends JComponent {

	private Computer pc;

	Watcher(Computer pc) {
		this.pc = pc;
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame f = new JFrame("Watcher");
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(1514, 1200);
		f.setLocation((sc.width - 1514) / 2, (sc.height - 1200) / 2);

		f.add(this);

		f.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					f.dispose();
				}
			}
		});

		new Timer(16, (e) -> repaint()).start();
	}

	private int cols = 66;
	private int size = 10;//(int) (1500f / cols);
	private Renderer renderer = new Renderer();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		renderer.attach(g);
		pc.draw(renderer);
		/*g.translate(size, size);
		for (int i = 0; i < Bit.groups.size(); i++) {
			g.translate(0, 120);
			Bit.groups.get(i).draw(g, 64, size);
		}*/
	}

}
