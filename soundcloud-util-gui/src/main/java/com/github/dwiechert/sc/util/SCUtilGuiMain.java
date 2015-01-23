package com.github.dwiechert.sc.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SCUtilGuiMain {
	public SCUtilGuiMain() {
		// Main frame
		final JFrame frame = new JFrame();

		// Main panel
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());

		// Main menu
		final JMenuBar menuBar = new JMenuBar();

		// File menu
		final JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.addSeparator();
		final Action exit = new AbstractAction("Exit") {
			private static final long serialVersionUID = -2588612763307147743L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		};
		final JMenuItem exitItem = new JMenuItem(exit);
		fileMenu.add(exitItem);

		// View menu
		final JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		final JMenuItem syncConfig = new JMenuItem("SyncConfig");
		syncConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				panel.removeAll();
				panel.updateUI();
				new SyncConfigView(panel);
			}
		});
		viewMenu.add(syncConfig);
		final JMenuItem syncDownload = new JMenuItem("SyncDownload");
		syncDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				panel.removeAll();
				panel.updateUI();
				JOptionPane.showMessageDialog(frame, "SyncDownload is currently not implemented.", "Not Implemented", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewMenu.add(syncDownload);

		// Build the parent panel
		frame.add(panel);
		frame.setJMenuBar(menuBar);
		frame.setTitle("SoundCloud Utility");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SCUtilGuiMain();
			}
		});
	}
}
