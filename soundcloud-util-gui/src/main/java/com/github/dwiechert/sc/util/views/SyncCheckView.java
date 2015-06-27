package com.github.dwiechert.sc.util.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.github.dwiechert.sc.util.SCUtilFactory;
import com.github.dwiechert.sc.util.GuiUtils;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.syncCheck.SyncChecker;

public class SyncCheckView {
	public SyncCheckView(final JPanel panel) {
		// Top will be button
		final JPanel top = new JPanel();
		top.setBorder(BorderFactory.createEtchedBorder());
		top.setLayout(new BorderLayout());

		// Bottom will be text area
		final JPanel bottom = new JPanel();
		bottom.setBorder(BorderFactory.createEtchedBorder());
		bottom.setLayout(new BorderLayout());

		// App split so user can mvoe the parts
		final JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, bottom);
		pane.setOneTouchExpandable(true);
		pane.setDividerLocation(150);
		final Dimension minSize = new Dimension(100, 50);
		top.setMinimumSize(minSize);
		bottom.setMinimumSize(minSize);
		panel.add(pane, BorderLayout.CENTER);

		// Build top button
		final JButton button = new JButton("Run SyncCheck");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						for (final FolderConfig folderConfig : GuiUtils.syncConfig.getConfigs()) {
							if (!folderConfig.isSyncOn()) {
								System.out.println("Sync is turned off for artist URL " + folderConfig.getArtistUrl()
										+ ". Will not run syncCheck for this artist.");
								continue;
							}
							final SyncChecker checker = SCUtilFactory.getSyncChecker(folderConfig.getArtistUrl());
							checker.check(folderConfig);
						}
						JOptionPane
								.showMessageDialog(panel, "Finished SyncCheck, make sure to save.", "SyncCheck Success", JOptionPane.PLAIN_MESSAGE);
					}
				});
				thread.start();
			}
		});
		top.add(button);

		// Build bottom text area
		final JTextArea text = new JTextArea();
		final JScrollPane scroll = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		System.setOut(new PrintStream(new JTextAreaOutputStream(text)));
		bottom.add(scroll);
	}

	private class JTextAreaOutputStream extends OutputStream {
		private final JTextArea destination;

		public JTextAreaOutputStream(final JTextArea destination) {
			if (destination == null) {
				throw new IllegalArgumentException("Destination is null");
			}

			this.destination = destination;
		}

		@Override
		public void write(final byte[] buffer, final int offset, final int length) throws IOException {
			final String text = new String(buffer, offset, length);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					destination.append(text);
					destination.setCaretPosition(destination.getDocument().getLength());
				}
			});
		}

		@Override
		public void write(final int b) throws IOException {
			write(new byte[] { (byte) b }, 0, 1);
		}
	}
}
