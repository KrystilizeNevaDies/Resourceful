package org.krystilize.resourceful;

import org.junit.jupiter.api.Test;
import org.krystilize.resourceful.resourcepack.ResourcePack;
import org.krystilize.resourceful.resourcepack.ResourcePackBuilder;
import org.krystilize.resourceful.shaders.Shaders;
import org.krystilize.resourceful.shaders.builders.CoreShader;
import org.krystilize.resourceful.shaders.data.*;
import org.krystilize.resourceful.shaders.type.CoreShaderType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ModernResourcePackTest {

    @Test
    public void testResourcePackWithUniformBlocks() throws IOException {
        // Create a resource pack using uniform blocks format (Minecraft 1.21.6+)
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("A modern resource pack with uniform blocks");

        // Create a shader using uniform blocks
        CoreShader modernShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                .uniformBlocks(
                        UniformBlocks.MATRICES,
                        UniformBlocks.FOG,
                        UniformBlocks.COLOR,
                        UniformBlocks.CHUNK
                )
                .attributes(
                        Attributes.VANILLA_POSITION,
                        Attributes.VANILLA_COLOR,
                        Attributes.VANILLA_UV0,
                        Attributes.VANILLA_UV2,
                        Attributes.VANILLA_NORMAL
                )
                .fragment(ExampleShader.class)
                .vertexIsPresent(true)
                .samplers(
                        Samplers.SAMPLER0,
                        Samplers.SAMPLER2
                )
                .build();

        builder.component(modernShader);
        ResourcePack pack = builder.build();

        // Compile the resource pack
        byte[] compiledPack = pack.compile();
        assertNotNull(compiledPack, "Compiled resource pack should not be null");
        assertTrue(compiledPack.length > 0, "Compiled resource pack should not be empty");

        // Save to file for manual inspection
        File outputFile = new File("modern-test-output.zip");
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(compiledPack);
        }
        
        assertTrue(outputFile.exists(), "Output file should be created");
        assertTrue(outputFile.length() > 0, "Output file should not be empty");
        
        System.out.println("Generated modern resource pack: " + outputFile.getAbsolutePath() + " (" + outputFile.length() + " bytes)");
        
        // Clean up
        outputFile.delete();
    }
}