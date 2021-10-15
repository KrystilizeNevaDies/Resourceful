package org.krystilize.resourceful.shaders;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.builders.CoreShaderBuilder;
import org.krystilize.resourceful.shaders.type.CoreShaderType;
import org.krystilize.resourceful.shaders.builders.PostShaderBuilder;
import org.krystilize.resourceful.shaders.type.PostShaderType;

public class Shaders {

    public static @NotNull PostShaderBuilder post(@NotNull PostShaderType type) {
        return new PostShaderBuilder(type);
    }

    public static @NotNull CoreShaderBuilder core(@NotNull CoreShaderType type) {
        return new CoreShaderBuilder(type);
    }
}
