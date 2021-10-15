package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class AuxTarget {
    private final @NotNull String id;
    private final @NotNull String name;

    AuxTarget(@NotNull String id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }
}