package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Uniform {
    private final @NotNull String name;
    private final float[] values;
    private final @NotNull String type;
    private final int count;

    <T> Uniform(@NotNull String name, @NotNull UniformType<T> type, @NotNull T value) {
        this.name = name;
        this.values = type.toValues(value);
        this.type = type.getDatatype();
        this.count = values.length;
    }

    public @NotNull String getName() {
        return name;
    }

    public float[] getValues() {
        return values;
    }
}