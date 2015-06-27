package com.github.dwiechert.sc.util.views.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SongConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;

public class SyncDownloadTableModel extends AbstractTableModel {
	private final Object rowData2[][] = { { Boolean.TRUE, "1" }, { Boolean.TRUE, "2" }, { Boolean.FALSE, "3" }, { Boolean.TRUE, "4" }, { Boolean.FALSE, "5" }, };
	private final Object rowData[][];
	
	String columnNames[] = { "Sync", "Song" };
	
	public SyncDownloadTableModel(final SyncConfig config) {
		rowData = new Object[config.getConfigs().size()][];
		int index = 0;
		for (final FolderConfig folderConfig : config.getConfigs()) {
			rowData[index] = folderConfig.getSongs().toArray();
			index++;
		}
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return rowData[row][column];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return (getValueAt(1, column).getClass());
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		rowData[row][column] = value;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (column != 1);
	}
	
	public boolean shouldSync(int row) {
		return (boolean) rowData[row][0];
	}
}
