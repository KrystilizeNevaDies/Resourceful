package org.krystilize.resourceful.shaders.data;

import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public class AuxTargets {

    public static AuxTarget of(
            @NotNull String id,
            @NotNull String name
    ) {
        return new AuxTarget(id, name);
    }

    public static AuxTarget providedImage(
            @NotNull String id,
            @NotNull String name,
            int width,
            int height,
            boolean bilinear
    ) {
        return new AuxProvidedImageTarget(id, name, width, height, bilinear);
    }

    public static AuxTarget image(
            @NotNull BufferedImage image,
            @NotNull String id,
            @NotNull String name,
            boolean bilinear
    ) {
        return new AuxImageTarget(image, id, name, bilinear);
    }

    public static class AuxImageTarget extends AuxTarget {
        @Expose(serialize = false, deserialize = false)
        private final @NotNull BufferedImage image;
        private final int width;
        private final int height;
        private final boolean bilinear;

        private AuxImageTarget(
                @NotNull BufferedImage image,
                @NotNull String id,
                @NotNull String name,
                boolean bilinear
        ) {
            super(id, name);
            this.image = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.bilinear = bilinear;
        }

        public @NotNull BufferedImage getImage() {
            return this.image;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public boolean isBilinear() {
            return bilinear;
        }
    }

    public static class AuxProvidedImageTarget extends AuxTarget {
        private final int width;
        private final int height;
        private final boolean bilinear;

        private AuxProvidedImageTarget(
                @NotNull String id,
                @NotNull String name,
                int width,
                int height,
                boolean bilinear
        ) {
            super(id, name);
            this.width = width;
            this.height = height;
            this.bilinear = bilinear;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public boolean isBilinear() {
            return bilinear;
        }
    }
}