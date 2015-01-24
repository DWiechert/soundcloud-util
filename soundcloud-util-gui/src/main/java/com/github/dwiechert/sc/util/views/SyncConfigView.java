package com.github.dwiechert.sc.util.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FileUtils;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.github.dwiechert.sc.util.views.models.FolderConfigRenderer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SyncConfigView {
	private final JPanel panel;
	private SyncConfig syncConfig = new SyncConfig();
	private FolderConfig currentFolderConfig = new FolderConfig();

	public SyncConfigView(final JPanel panel) {
		this.panel = panel;

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
					currentFolderConfig = selected;
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
							currentFolderConfig = new FolderConfig();
							currentFolderConfig.setArtistUrl("Artist URL");
							syncConfig.getConfigs().add(currentFolderConfig);
							listModel.addElement(currentFolderConfig);
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
								syncConfig.getConfigs().remove(index);
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
		if (currentFolderConfig.getArtistUrl() != null) {
			artistUrlText.setText(currentFolderConfig.getArtistUrl());
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
				currentFolderConfig.setArtistUrl(artistUrlText.getText());
				list.updateUI();
			}
		});
		right.add(artistUrlText);

		right.add(new JLabel("Local Folder"));
		final JTextField localFolderText = new JTextField();
		if (currentFolderConfig.getLocalFolder() != null) {
			localFolderText.setText(currentFolderConfig.getLocalFolder());
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
				currentFolderConfig.setLocalFolder(localFolderText.getText());
			}
		});
		right.add(localFolderText);

		right.add(new JLabel("Download Folder"));
		final JTextField downloadFolderText = new JTextField();
		if (currentFolderConfig.getDownloadFolder() != null) {
			downloadFolderText.setText(currentFolderConfig.getDownloadFolder());
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
				currentFolderConfig.setDownloadFolder(downloadFolderText.getText());
			}
		});
		right.add(downloadFolderText);
	}

	public void save() {
		final JFileChooser chooser = new JFileChooser(new File("."));
		chooser.setSelectedFile(new File("scsync.config"));
		if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(panel)) {
			final Gson gson = new GsonBuilder().setPrettyPrinting().create();
			final String json = gson.toJson(syncConfig);
			try {
				FileUtils.write(chooser.getSelectedFile(), json);
				JOptionPane.showMessageDialog(panel, "SyncConfig was successfully saved to file " + chooser.getSelectedFile(), "Save Success",
						JOptionPane.PLAIN_MESSAGE);
			} catch (final IOException e) {
				JOptionPane.showMessageDialog(panel, e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
