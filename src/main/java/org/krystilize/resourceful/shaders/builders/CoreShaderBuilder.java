package org.krystilize.resourceful.shaders.builders;

import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.type.CoreShaderType;

public class CoreShaderBuilder extends ProgramBuilder<CoreShaderBuilder, CoreShader> {

    @Expose(serialize = false, deserialize = false)
    final @NotNull CoreShaderType type;

    public CoreShaderBuilder(@NotNull CoreShaderType type) {
        super(type.file());
        this.type = type;
    }

    public @NotNull CoreShader build() {
        return new CoreShader(this);
    }
}
