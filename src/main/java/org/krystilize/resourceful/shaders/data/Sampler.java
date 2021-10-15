package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Sampler {
    private final @NotNull String name;

    Sampler(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String name() {
        return name;
    }
}
