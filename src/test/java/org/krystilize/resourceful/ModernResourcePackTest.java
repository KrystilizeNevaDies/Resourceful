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
        // Create a resource pack using the new uniform blocks format
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("A modern resource pack with uniform blocks");

        // Create a shader using uniform blocks (1.21.6+ format)
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

    @Test
    public void testLegacyResourcePackWithLooseUniforms() throws IOException {
        // Create a resource pack using the old loose uniforms format (pre-1.21.6)
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("A legacy resource pack with loose uniforms");

        // Create a shader using loose uniforms (legacy format)
        CoreShader legacyShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                .uniforms(
                        Uniforms.VANILLA_MODEL_VIEW_MATRIX,
                        Uniforms.VANILLA_PROJECTION_MATRIX,
                        Uniforms.of("ChunkOffset", 0.0F, 0.0F, 0.0F),
                        Uniforms.of("ColorModulator", 1.0F, 0.8F, 1.0F, 1.0F),
                        Uniforms.of("FogStart", 0.0F),
                        Uniforms.of("FogEnd", 1.0F),
                        Uniforms.of("FogColor", 0.0F, 0.0F, 0.0F, 0.0F)
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

        builder.component(legacyShader);
        ResourcePack pack = builder.build();

        // Compile the resource pack
        byte[] compiledPack = pack.compile();
        assertNotNull(compiledPack, "Compiled resource pack should not be null");
        assertTrue(compiledPack.length > 0, "Compiled resource pack should not be empty");

        // Save to file for manual inspection
        File outputFile = new File("legacy-test-output.zip");
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(compiledPack);
        }
        
        assertTrue(outputFile.exists(), "Output file should be created");
        assertTrue(outputFile.length() > 0, "Output file should not be empty");
        
        System.out.println("Generated legacy resource pack: " + outputFile.getAbsolutePath() + " (" + outputFile.length() + " bytes)");
        
        // Clean up
        outputFile.delete();
    }
}