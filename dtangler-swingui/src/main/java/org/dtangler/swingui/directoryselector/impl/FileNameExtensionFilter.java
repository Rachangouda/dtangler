package org.dtangler.swingui.directoryselector.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileFilter;

public class FileNameExtensionFilter extends FileFilter
{
	private List<String> fileNameExtensions = new ArrayList<>();
	private String description;

	public FileNameExtensionFilter(String description, List<String> fileNameExtensions) {
		this.description = description;

		for (String fileNameExtension : fileNameExtensions) {
			this.fileNameExtensions.add(fileNameExtension.toLowerCase());
		}
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getName().toLowerCase();
		for (String fileNameExtension : fileNameExtensions) {
			if (name.endsWith(fileNameExtension)) {
				return true;
			}
		}
		return false;
	}

	public String getDescription() {
		return description == null ? "" : description;
	}

}