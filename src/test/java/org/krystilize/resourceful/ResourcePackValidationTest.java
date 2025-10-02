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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ResourcePackValidationTest {

    @Test
    public void testResourcePackHasCorrectFormat() {
        ResourcePack pack = generateModernPack();
        assertEquals(69, ResourcePack.VERSION, "Resource pack format should be 69 for Minecraft 1.21.9");
    }

    @Test
    public void testResourcePackContainsExpectedFiles() throws IOException {
        ResourcePack pack = generateModernPack();
        byte[] compiledPack = pack.compile();
        
        // Verify the zip contains expected files
        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(compiledPack))) {
            boolean foundPackMcmeta = false;
            boolean foundShaderJson = false;
            boolean foundFragmentShader = false;
            
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                if (name.equals("pack.mcmeta")) {
                    foundPackMcmeta = true;
                } else if (name.contains("rendertype_solid.json")) {
                    foundShaderJson = true;
                } else if (name.contains("rendertype_solid.fsh")) {
                    foundFragmentShader = true;
                }
            }
            
            assertTrue(foundPackMcmeta, "Resource pack should contain pack.mcmeta");
            assertTrue(foundShaderJson, "Resource pack should contain shader JSON file");
            assertTrue(foundFragmentShader, "Resource pack should contain fragment shader file");
        }
    }

    @Test
    public void testUniformBlocksAreRequired() {
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("Test uniform blocks are required");

        CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                .uniformBlocks(UniformBlocks.MATRICES, UniformBlocks.FOG)
                .build();

        builder.component(shader);
        ResourcePack pack = builder.build();

        // This should work with uniform blocks
        byte[] compiled = pack.compile();
        assertNotNull(compiled);
        assertTrue(compiled.length > 0);
    }

    @Test
    public void testMultipleUniformBlocks() {
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("Test multiple uniform blocks");

        CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                .uniformBlocks(
                        UniformBlocks.MATRICES,
                        UniformBlocks.FOG,
                        UniformBlocks.COLOR,
                        UniformBlocks.CHUNK
                )
                .build();

        builder.component(shader);
        ResourcePack pack = builder.build();

        byte[] compiled = pack.compile();
        assertNotNull(compiled);
        assertTrue(compiled.length > 0);
    }

    private ResourcePack generateModernPack() {
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("Modern test resource pack");

        CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                .uniformBlocks(
                        UniformBlocks.MATRICES,
                        UniformBlocks.FOG,
                        UniformBlocks.COLOR
                )
                .attributes(
                        Attributes.VANILLA_POSITION,
                        Attributes.VANILLA_COLOR
                )
                .fragment(ExampleShader.class)
                .vertexIsPresent(true)
                .samplers(Samplers.SAMPLER0)
                .build();

        builder.component(shader);
        return builder.build();
    }
}