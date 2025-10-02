package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a uniform block for modern Minecraft versions (1.21.6+)
 * Uniform blocks replace loose uniforms and group related uniforms together
 */
public class UniformBlock {
    private final @NotNull String name;
    private final @NotNull List<Uniform> uniforms;

    public UniformBlock(@NotNull String name, @NotNull List<Uniform> uniforms) {
        this.name = name;
        this.uniforms = new ArrayList<>(uniforms);
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull List<Uniform> getUniforms() {
        return new ArrayList<>(uniforms);
    }

    /**
     * Serializes this uniform block to the format expected by Minecraft
     * @return A list of uniform definition objects
     */
    public @NotNull List<Object> serialize() {
        return uniforms.stream()
                .map(this::serializeUniform)
                .toList();
    }

    private @NotNull Object serializeUniform(@NotNull Uniform uniform) {
        return java.util.Map.of(
                "name", uniform.getName(),
                "type", uniform.getUniformType().typeName(),
                "value", uniform.getValues()
        );
    }
}