package com.github.dwiechert.sc.util.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.github.dwiechert.sc.util.views.models.SyncDownloadTableModel;

public class SyncDownloadView {
	public SyncDownloadView(final JPanel panel) {
		// Build the left side table
		final SyncDownloadTableModel model = new SyncDownloadTableModel();
		final JTable table = new JTable(model);
		final JScrollPane left = new JScrollPane(table);

		// Build the right side info
		final JPanel right = new JPanel();
		right.setBorder(BorderFactory.createEtchedBorder());
		right.setLayout(new GridLayout(2, 2));
		final JLabel syncCountLabel = new JLabel("Sync Count:");
		right.add(syncCountLabel);
		final JLabel countLabel = new JLabel("");
		right.add(countLabel);
		final JButton totalToSyncButton = new JButton("Count Total To Sync");
		totalToSyncButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				int total = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					if (model.shouldSync(i)) {
						total++;
					}
				}
				countLabel.setText(String.valueOf(total));
			}
		});
		right.add(totalToSyncButton);

		// Add split so user can move the parts
		final JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		pane.setOneTouchExpandable(true);
		pane.setDividerLocation(150);
		final Dimension minSize = new Dimension(100, 50);
		left.setMinimumSize(minSize);
		right.setMinimumSize(minSize);
		panel.add(pane, BorderLayout.CENTER);
	}
}
