package com.github.dwiechert.sc.util.views.models;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.github.dwiechert.sc.util.models.FolderConfig;

public class FolderConfigRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setText(((FolderConfig) value).getArtistUrl());
		return this;
	}
}
