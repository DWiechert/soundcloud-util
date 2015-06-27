package com.github.dwiechert.sc.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

import com.github.dwiechert.sc.util.models.SyncConfig;
import com.github.dwiechert.sc.util.views.SyncCheckView;
import com.github.dwiechert.sc.util.views.SyncConfigView;
import com.github.dwiechert.sc.util.views.SyncDownloadView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SCUtilGuiMain {
	private static final String SYNC_MAIN_TITLE = "SoundCloud Utility";
	private static final String SYNC_CONFIG_TITLE = SYNC_MAIN_TITLE + " - SyncConfig";
	private static final String SYNC_CHECK_TITLE = SYNC_MAIN_TITLE + " - SyncCheck";
	private static final String SYNC_DOWNLOAD_TITLE = SYNC_MAIN_TITLE + " - SyncDownload";

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
		final JMenuItem open = new JMenuItem("Open");
		open.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				open(frame);
			}
		});
		fileMenu.add(open);
		final JMenuItem save = new JMenuItem("Save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				save(frame);
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
				new SyncConfigView(panel);
				JOptionPane.showMessageDialog(frame, "SyncConfig is not currently fully implemented.", "Not Finished", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewMenu.add(syncConfig);
		
		final JMenuItem syncCheck = new JMenuItem("SyncCheck");
		syncCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				panel.removeAll();
				panel.updateUI();
				frame.setTitle(SYNC_CHECK_TITLE);
				new SyncCheckView(panel);
				JOptionPane.showMessageDialog(frame, "SyncCheck is not currently fully implemented.", "Not Finished", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewMenu.add(syncCheck);
		
		final JMenuItem syncDownload = new JMenuItem("SyncDownload");
		syncDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				panel.removeAll();
				panel.updateUI();
				frame.setTitle(SYNC_DOWNLOAD_TITLE);
				new SyncDownloadView(panel);
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

	private void save(final JFrame frame) {
		final JFileChooser chooser = new JFileChooser(new File(GuiUtils.CURRENT_DIRECTORY));
		chooser.setSelectedFile(new File("scsync.config"));
		if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(frame)) {
			final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			final String json = gson.toJson(GuiUtils.syncConfig);
			try {
				FileUtils.write(chooser.getSelectedFile(), json);
				JOptionPane.showMessageDialog(frame, "SyncConfig was successfully saved to file " + chooser.getSelectedFile(), "Save Success",
						JOptionPane.PLAIN_MESSAGE);
				GuiUtils.CURRENT_DIRECTORY = chooser.getSelectedFile().getParent();
			} catch (final IOException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void open(final JFrame frame) {
		final JFileChooser chooser = new JFileChooser(new File(GuiUtils.CURRENT_DIRECTORY));
		chooser.setSelectedFile(new File("scsync.config"));
		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(frame)) {
			final Gson gson = new GsonBuilder().create();
			try {
				GuiUtils.syncConfig = gson.fromJson(new FileReader(chooser.getSelectedFile()), SyncConfig.class);
				if (!GuiUtils.syncConfig.getConfigs().isEmpty()) {
					GuiUtils.currentFolderConfig = GuiUtils.syncConfig.getConfigs().get(0);
				}
				GuiUtils.CURRENT_DIRECTORY = chooser.getSelectedFile().getParent();
			} catch (final Exception e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(), "Open Error", JOptionPane.ERROR_MESSAGE);
			}
		}
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
