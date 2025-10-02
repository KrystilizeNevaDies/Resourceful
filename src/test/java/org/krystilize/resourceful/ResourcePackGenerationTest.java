package org.krystilize.resourceful;

import org.junit.jupiter.api.Test;
import org.krystilize.resourceful.resourcepack.ResourcePack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ResourcePackGenerationTest {

    @Test
    public void testResourcePackGeneration() throws IOException {
        // Generate the resource pack
        ResourcePack pack = ExampleResourcePackGenerator.generatePack();
        assertNotNull(pack, "Generated resource pack should not be null");

        // Compile the resource pack
        byte[] compiledPack = pack.compile();
        assertNotNull(compiledPack, "Compiled resource pack should not be null");
        assertTrue(compiledPack.length > 0, "Compiled resource pack should not be empty");

        // Save to file for manual inspection
        File outputFile = new File("test-output.zip");
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(compiledPack);
        }
        
        assertTrue(outputFile.exists(), "Output file should be created");
        assertTrue(outputFile.length() > 0, "Output file should not be empty");
        
        System.out.println("Generated resource pack: " + outputFile.getAbsolutePath() + " (" + outputFile.length() + " bytes)");
        
        // Clean up
        outputFile.delete();
    }

    @Test
    public void testResourcePackFormat() {
        assertEquals(69, ResourcePack.VERSION, "Resource pack format should be 69 for Minecraft 1.21.9");
    }
}