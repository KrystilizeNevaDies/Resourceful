package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Target {
    private final @NotNull String name;

    Target(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getName() {
        return name;
    }
}