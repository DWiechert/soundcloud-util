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

import com.github.dwiechert.sc.util.views.SyncConfigView;
import com.github.dwiechert.sc.util.views.SyncDownloadView;

public class SCUtilGuiMain {
	private static final String SYNC_MAIN_TITLE = "SoundCloud Utility";
	private static final String SYNC_CONFIG_TITLE = SYNC_MAIN_TITLE + " - SyncConfig";
	private static final String SYNC_DOWNLOAD_TITLE = SYNC_MAIN_TITLE + " - SyncDownload";
	private Object currentView;

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
		final JMenuItem save = new JMenuItem("Save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				if (currentView instanceof SyncConfigView) {
					((SyncConfigView) currentView).save();
				} else {
					JOptionPane.showMessageDialog(frame, "Save is only allowed from the SyncConfig view.", "Save Error", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		fileMenu.add(save);
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
				frame.setTitle(SYNC_CONFIG_TITLE);
				currentView = new SyncConfigView(panel);
				JOptionPane.showMessageDialog(frame, "SyncConfig is not currently fully implemented.", "Not Finished", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewMenu.add(syncConfig);
		final JMenuItem syncDownload = new JMenuItem("SyncDownload");
		syncDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				panel.removeAll();
				panel.updateUI();
				frame.setTitle(SYNC_DOWNLOAD_TITLE);
				currentView = new SyncDownloadView(panel);
				JOptionPane.showMessageDialog(frame, "SyncDownload is not currently fully implemented.", "Not Finished", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewMenu.add(syncDownload);

		// Build the parent panel
		frame.add(panel);
		frame.setJMenuBar(menuBar);
		frame.setTitle(SYNC_MAIN_TITLE);
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
