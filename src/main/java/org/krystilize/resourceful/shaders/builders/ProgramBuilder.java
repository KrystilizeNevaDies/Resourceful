package org.krystilize.resourceful.shaders.builders;

import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.jlsl.glsl.FragmentShaderEnvironment;
import org.jglrxavpok.jlsl.glsl.VertexShaderEnvironment;
import org.krystilize.resourceful.shaders.data.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class ProgramBuilder<T, R> {

    final @NotNull String name;
    final @NotNull Map<String, Object> blend = Map.of(
            "func", "add",
            "srcrgb", "srcalpha",
            "dstrgb", "1-srcalpha"
    );
    final @NotNull List<String> attributes = new ArrayList<>();
    final @NotNull List<Sampler> samplers = new ArrayList<>();
    final @NotNull List<UniformBlock> uniformBlocks = new ArrayList<>();

    @Expose(serialize = false, deserialize = false)
    boolean vertexShaderPresent = false;
    @Expose(serialize = false, deserialize = false)
    boolean fragmentShaderPresent = false;

    @Expose(serialize = false, deserialize = false)
    @Nullable Class<? extends VertexShaderEnvironment> vertexShader;
    @Expose(serialize = false, deserialize = false)
    @Nullable Class<? extends FragmentShaderEnvironment> fragmentShader;


    public ProgramBuilder(@NotNull String name) {
        this.name = name;
    }

    public @NotNull T vertex(@Nullable Class<VertexShaderEnvironment> vertexShader) {
        this.vertexShader = vertexShader;
        return vertexIsPresent(true);
    }

    public @NotNull T vertexIsPresent(boolean isPresent) {
        this.vertexShaderPresent = isPresent;
        return (T) this;
    }

    public @NotNull T fragment(@Nullable Class<? extends FragmentShaderEnvironment> fragmentShader) {
        this.fragmentShader = fragmentShader;
        return fragmentIsPresent(true);
    }

    public @NotNull T fragmentIsPresent(boolean isPresent) {
        this.fragmentShaderPresent = isPresent;
        return (T) this;
    }

    public @NotNull T attributes(@NotNull Attribute... attributes) {
        for (Attribute attribute : attributes) {
            this.attributes.add(attribute.name());
        }
        return (T) this;
    }

    public @NotNull T samplers(@NotNull Sampler... samplers) {
        Collections.addAll(this.samplers, samplers);
        return (T) this;
    }

    /**
     * Adds uniform blocks for Minecraft 1.21.6+
     * This is the only supported uniform format in this library version
     */
    public @NotNull T uniformBlocks(@NotNull UniformBlock... uniformBlocks) {
        Collections.addAll(this.uniformBlocks, uniformBlocks);
        return (T) this;
    }

    public @NotNull R build() {
        return (R) new GeneratingProgram(this);
    }
}
