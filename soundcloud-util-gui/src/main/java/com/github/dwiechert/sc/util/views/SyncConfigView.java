package com.github.dwiechert.sc.util.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.github.dwiechert.sc.util.GuiUtils;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.views.models.FolderConfigRenderer;

public class SyncConfigView {
	public SyncConfigView(final JPanel panel) {
		// Left side will be a list
		final JPanel left = new JPanel();
		left.setBorder(BorderFactory.createEtchedBorder());
		left.setLayout(new BorderLayout());

		// Right side will be info
		final JPanel right = new JPanel();
		right.setBorder(BorderFactory.createEtchedBorder());
		right.setLayout(new GridLayout(10, 1));

		// Add split so user can move the parts
		final JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		pane.setOneTouchExpandable(true);
		pane.setDividerLocation(150);
		final Dimension minSize = new Dimension(100, 50);
		left.setMinimumSize(minSize);
		right.setMinimumSize(minSize);
		panel.add(pane, BorderLayout.CENTER);

		// Build the left side list
		final JList<FolderConfig> list = buildLeftSideList(left);

		// Build the right side info
		buildRightSideInfo(right, list);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(final ListSelectionEvent e) {
				final FolderConfig selected = list.getSelectedValue();
				if (selected != null) {
					GuiUtils.currentFolderConfig = selected;
				}
				right.removeAll();
				right.updateUI();
				buildRightSideInfo(right, list);
			}
		});
	}

	private JList<FolderConfig> buildLeftSideList(final JPanel left) {
		final DefaultListModel<FolderConfig> listModel = new DefaultListModel<FolderConfig>();
		final JList<FolderConfig> list = new JList<>(listModel);

		if (!GuiUtils.syncConfig.getConfigs().isEmpty()) {
			for (final FolderConfig folder : GuiUtils.syncConfig.getConfigs()) {
				listModel.addElement(folder);
			}
			list.setSelectedIndex(0);
		}

		list.setCellRenderer(new FolderConfigRenderer());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					final JPopupMenu menu = new JPopupMenu();
					final JMenuItem add = new JMenuItem("Add");
					add.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(final MouseEvent e) {
							GuiUtils.currentFolderConfig = new FolderConfig();
							GuiUtils.currentFolderConfig.setArtistUrl("Artist URL");
							GuiUtils.syncConfig.getConfigs().add(GuiUtils.currentFolderConfig);
							listModel.addElement(GuiUtils.currentFolderConfig);
						}
					});
					menu.add(add);
					menu.addSeparator();
					final int index = list.getSelectedIndex();
					if (index >= 0) {
						final JMenuItem remove = new JMenuItem("Remove");
						remove.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(final MouseEvent e) {
								GuiUtils.syncConfig.getConfigs().remove(index);
								listModel.remove(index);
							}
						});
						menu.add(remove);
					}
					menu.show(list, e.getX(), e.getY());
				}
			}
		});
		left.add(list, BorderLayout.CENTER);

		return list;
	}

	private void buildRightSideInfo(final JPanel right, final JList<FolderConfig> list) {
		right.add(new JLabel("Artist URL"));
		final JTextField artistUrlText = new JTextField();
		if (GuiUtils.currentFolderConfig.getArtistUrl() != null) {
			artistUrlText.setText(GuiUtils.currentFolderConfig.getArtistUrl());
		}
		artistUrlText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateArtistUrl();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateArtistUrl();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateArtistUrl();
			}

			private void updateArtistUrl() {
				GuiUtils.currentFolderConfig.setArtistUrl(artistUrlText.getText());
				list.updateUI();
			}
		});
		right.add(artistUrlText);

		right.add(new JLabel("Local Folder"));
		final JTextField localFolderText = new JTextField();
		if (GuiUtils.currentFolderConfig.getLocalFolder() != null) {
			localFolderText.setText(GuiUtils.currentFolderConfig.getLocalFolder());
		}
		localFolderText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLocalFolder();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLocalFolder();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateLocalFolder();
			}

			private void updateLocalFolder() {
				GuiUtils.currentFolderConfig.setLocalFolder(localFolderText.getText());
			}
		});
		right.add(localFolderText);

		right.add(new JLabel("Download Folder"));
		final JTextField downloadFolderText = new JTextField();
		if (GuiUtils.currentFolderConfig.getDownloadFolder() != null) {
			downloadFolderText.setText(GuiUtils.currentFolderConfig.getDownloadFolder());
		}
		downloadFolderText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateDownloadFolder();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateDownloadFolder();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateDownloadFolder();
			}

			private void updateDownloadFolder() {
				GuiUtils.currentFolderConfig.setDownloadFolder(downloadFolderText.getText());
			}
		});
		right.add(downloadFolderText);
	}
}
