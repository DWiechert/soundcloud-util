package com.github.dwiechert.sc.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class SCUtilGuiMain extends JFrame {
	public SCUtilGuiMain() {
		// Main panel
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());

		// Left side will be a list
		final JPanel left = new JPanel();
		left.setBorder(BorderFactory.createEtchedBorder());
		left.setLayout(new BorderLayout());

		// Right side will be info
		final JPanel right = new JPanel();
		right.setBorder(BorderFactory.createEtchedBorder());
		right.setLayout(new GridLayout(2, 2));

		// Add split so user can move the parts
		final JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		pane.setOneTouchExpandable(true);
		pane.setDividerLocation(150);
		final Dimension minSize = new Dimension(100, 50);
		left.setMinimumSize(minSize);
		right.setMinimumSize(minSize);
		panel.add(pane, BorderLayout.CENTER);

		// Build the left side list
		final String[] data = { "a", "b", "c", "d" };
		final JList<String> list = new JList<>(data);
		left.add(list, BorderLayout.CENTER);

		// Build the right side info
		final JButton button1 = new JButton("Button1");
		right.add(button1);
		final JLabel label1 = new JLabel("Label1");
		right.add(label1);
		final JLabel label2 = new JLabel("Label2");
		right.add(label2);

		// Build the parent panel
		add(panel);
		setTitle("SoundCloud Utility");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final SCUtilGuiMain main = new SCUtilGuiMain();
				main.setVisible(true);
			}
		});
	}
}
