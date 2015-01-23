package com.github.dwiechert.sc.util.views.models;

import javax.swing.table.AbstractTableModel;

public class SyncDownloadTableModel extends AbstractTableModel {
	Object rowData[][] = { { Boolean.TRUE, "1" }, { Boolean.TRUE, "2" }, { Boolean.FALSE, "3" }, { Boolean.TRUE, "4" }, { Boolean.FALSE, "5" }, };

	String columnNames[] = { "Sync", "Song" };

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
