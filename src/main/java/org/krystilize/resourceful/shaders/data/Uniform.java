package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Uniform {
    private final @NotNull String name;
    private final float[] values;
    private final @NotNull String type;
    private final int count;
    private final @NotNull UniformType<?> uniformType;

    <T> Uniform(@NotNull String name, @NotNull UniformType<T> type, @NotNull T value) {
        this.name = name;
        this.values = type.toValues(value);
        this.type = type.getDatatype();
        this.count = values.length;
        this.uniformType = type;
    }

    public @NotNull String getName() {
        return name;
    }

    /**
     * @deprecated Use getName() instead - maintained for compatibility
     */
    @Deprecated
    public @NotNull String name() {
        return name;
    }

    public float[] getValues() {
        return values;
    }

    /**
     * @deprecated Use getValues() instead - maintained for compatibility
     */
    @Deprecated
    public float[] value() {
        return values;
    }

    public @NotNull String getType() {
        return type;
    }

    /**
     * @deprecated Use getType() instead - maintained for compatibility
     */
    @Deprecated
    public @NotNull String type() {
        return type;
    }

    /**
     * Gets the UniformType for modern uniform block serialization
     */
    public @NotNull UniformType<?> getUniformType() {
        return uniformType;
    }

    public int getCount() {
        return count;
    }
}