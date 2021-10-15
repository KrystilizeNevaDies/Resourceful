package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Attribute {
    final @NotNull String name;

    Attribute(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String name() {
        return name;
    };
}
