package com.github.dwiechert.sc.util.views.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.github.dwiechert.sc.util.GuiUtils;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SongConfig;

public class SyncDownloadTableModel extends AbstractTableModel {
	private static final String columnNames[] = { "Sync", "Song" };
	private final List<Pair<Boolean, String>> data = new ArrayList<>();

	public SyncDownloadTableModel() {
		for (final FolderConfig folderConfig : GuiUtils.syncConfig.getConfigs()) {
			for (final SongConfig songConfig : folderConfig.getSongs()) {
				data.add(Pair.createPair(songConfig.isSyncOn(), songConfig.getSongUrl()));
			}
		}
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		int index = 0;
		for (final Pair<Boolean, String> entry : data) {
			if (index == row) {
				if (column == 0) {
					return entry.getElement0();
				} else if (column == 1) {
					return entry.getElement1();
				}
			}

			index++;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		// Get the selected Pair
		final Pair<Boolean, String> selected = data.get(row);
		// Mark the sync status on the actual object
		folderLoop: for (final FolderConfig folderConfig : GuiUtils.syncConfig.getConfigs()) {
			for (final SongConfig songConfig : folderConfig.getSongs()) {
				if (songConfig.getSongUrl().equals(selected.getElement1())) {
					songConfig.setSyncOn((Boolean) value);
					break folderLoop;
				}
			}
		}
		// Reset the GUI
		data.set(row, Pair.createPair((Boolean) value, selected.getElement1()));
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (column != 1);
	}

	public boolean shouldSync(int row) {
		return data.get(row).getElement0();
	}
}
