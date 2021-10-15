package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Targets {

    public static final @NotNull Target MINECRAFT_MAIN = Targets.of("minecraft:main");
    public static final @NotNull Target MINECRAFT_FINAL = Targets.of("final");

    public static @NotNull Target of(@NotNull String name) {
        return new Target(name);
    }

    public static @NotNull Target sized(@NotNull String name, int width, int height) {
        return new DynamicTarget(name, width, height);
    }

    public static class DynamicTarget extends Target {
        private final int width;
        private final int height;

        private DynamicTarget(@NotNull String name, int width, int height) {
            super(name);
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
