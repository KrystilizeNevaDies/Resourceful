package org.krystilize.resourceful.shaders.builders;

import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.data.*;
import org.krystilize.resourceful.shaders.type.PostShaderType;

import java.util.*;

public class PostShaderBuilder {

    @NotNull final List<String> targets = new ArrayList<>();
    @NotNull final List<Pass> passes = new ArrayList<>();

    @Expose(serialize = false, deserialize = false)
    final @NotNull PostShaderType type;

    public PostShaderBuilder(@NotNull PostShaderType type) {
        this.type = type;
    }

    public @NotNull PostShaderBuilder targets(Target... targets) {
        for (Target target : targets) {
            this.targets.add(target.getName());
        }
        return this;
    }

    public @NotNull PostShaderBuilder passes(@NotNull Pass... passes) {
        Collections.addAll(this.passes, passes);
        return this;
    }

    public @NotNull PostShader build() {
        return new PostShader(this);
    }
}
