package org.dtangler.swingui.directoryselector.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileNameExtensionFilterTest {

    private FileNameExtensionFilter fileNameExtensionFilter;
    private List<String> extensions;

    @Before
    public void setUp() {
        extensions = new ArrayList<>();
        extensions.add(".jar");
        fileNameExtensionFilter = new FileNameExtensionFilter("Jar files", extensions);
    }

    @Test
    public void testAccept() throws IOException {
        File tempFile = File.createTempFile("tempFile", ".jar");
        tempFile.deleteOnExit();
        assertTrue(fileNameExtensionFilter.accept(tempFile));
    }

    @Test
    public void getDescription() {
        assertTrue(fileNameExtensionFilter.getDescription().equals("Jar files"));
    }

    @Test
    public void getEmptyDescription() {
        fileNameExtensionFilter = new FileNameExtensionFilter(null, extensions);
        assertTrue(fileNameExtensionFilter.getDescription().equals(""));
    }
}