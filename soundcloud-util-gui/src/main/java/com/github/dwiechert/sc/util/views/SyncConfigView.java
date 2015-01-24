package com.github.dwiechert.sc.util.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.github.dwiechert.sc.util.views.models.FolderConfigRenderer;

public class SyncConfigView {
	private final JPanel panel;
	private SyncConfig syncConfig = new SyncConfig();
	private FolderConfig currentFolderConfig;

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
		DefaultListModel<FolderConfig> listModel = new DefaultListModel<FolderConfig>();
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
							System.out.println("Add was clicked");
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
								System.out.println(index);
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

		// Build the right side info
		final JButton button1 = new JButton("Button1");
		right.add(button1);
		final JLabel label1 = new JLabel("Label1");
		right.add(label1);
		final JLabel label2 = new JLabel("Label2");
		right.add(label2);
	}
}
