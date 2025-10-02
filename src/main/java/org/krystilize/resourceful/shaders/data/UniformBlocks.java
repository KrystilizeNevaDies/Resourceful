package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating uniform blocks for modern Minecraft versions (1.21.6+)
 */
public class UniformBlocks {

    /**
     * Creates a uniform block with the given name and uniforms
     */
    public static @NotNull UniformBlock block(@NotNull String name, @NotNull Uniform... uniforms) {
        return new UniformBlock(name, Arrays.asList(uniforms));
    }

    /**
     * Creates a uniform block with the given name and uniforms
     */
    public static @NotNull UniformBlock block(@NotNull String name, @NotNull List<Uniform> uniforms) {
        return new UniformBlock(name, uniforms);
    }

    /**
     * Common uniform block for matrices used in core shaders
     */
    public static @NotNull UniformBlock MATRICES = block("Matrices",
            Uniforms.VANILLA_MODEL_VIEW_MATRIX,
            Uniforms.VANILLA_PROJECTION_MATRIX
    );

    /**
     * Common uniform block for fog-related uniforms
     */
    public static @NotNull UniformBlock FOG = block("Fog",
            Uniforms.of("FogStart", 0.0f),
            Uniforms.of("FogEnd", 1.0f),
            Uniforms.of("FogColor", 0.0f, 0.0f, 0.0f, 0.0f)
    );

    /**
     * Common uniform block for color modulation
     */
    public static @NotNull UniformBlock COLOR = block("Color",
            Uniforms.of("ColorModulator", 1.0f, 1.0f, 1.0f, 1.0f)
    );

    /**
     * Common uniform block for chunk-related uniforms
     */
    public static @NotNull UniformBlock CHUNK = block("Chunk",
            Uniforms.of("ChunkOffset", 0.0f, 0.0f, 0.0f)
    );
}