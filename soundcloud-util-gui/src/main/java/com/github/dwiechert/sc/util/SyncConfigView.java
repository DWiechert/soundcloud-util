package com.github.dwiechert.sc.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SyncConfigView {
	private final JPanel panel;

	public SyncConfigView(final JPanel panel) {
		this.panel = panel;

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
	}
}
