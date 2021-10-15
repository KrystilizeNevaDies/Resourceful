package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.builders.Program;
import org.krystilize.resourceful.shaders.builders.ProgramBuilder;

public class Programs {

    @SuppressWarnings({"unchecked"})
    public static <T extends ProgramBuilder<T, Program>> @NotNull T builder(@NotNull String name) {
        return (T) new ProgramBuilder<T, Program>(name);
    }

    public static @NotNull Program provided(@NotNull String name) {
        return () -> name;
    }

    public static final Program MINECRAFT_BLUR = Programs.provided("blur");
    public static final Program MINECRAFT_FLIP = Programs.provided("flip");
    public static final Program MINECRAFT_NTSC_ENCODE = Programs.provided("ntsc_encode");
    public static final Program MINECRAFT_ANTIALIAS = Programs.provided("antialias");
    public static final Program MINECRAFT_BUMPY = Programs.provided("bumpy");
    public static final Program MINECRAFT_OUTLINE = Programs.provided("outline");
    public static final Program MINECRAFT_SCAN_PINCUSHION = Programs.provided("scan_pincushion");
    public static final Program MINECRAFT_BITS = Programs.provided("bits");
    public static final Program MINECRAFT_FXAA = Programs.provided("fxaa");
    public static final Program MINECRAFT_OUTLINE_COMBINE = Programs.provided("outline_combine");
    public static final Program MINECRAFT_BLIT = Programs.provided("blit");
    public static final Program MINECRAFT_COLOR_CONVOLVE = Programs.provided("color_convolve");
    public static final Program MINECRAFT_SOBEL = Programs.provided("sobel");
    public static final Program MINECRAFT_INVERT = Programs.provided("invert");
    public static final Program MINECRAFT_OUTLINE_SOFT = Programs.provided("outline_soft");
    public static final Program MINECRAFT_DECONVERGE = Programs.provided("deconverge");
    public static final Program MINECRAFT_SPIDER = Programs.provided("spider");
    public static final Program MINECRAFT_BLOBS = Programs.provided("blobs");
    public static final Program MINECRAFT_OUTLINE_WATERCOLOR = Programs.provided("outline_watercolor");
    public static final Program MINECRAFT_DOWNSCALE = Programs.provided("downscale");
    public static final Program MINECRAFT_NOTCH = Programs.provided("notch");
    public static final Program MINECRAFT_OVERLAY = Programs.provided("overlay");
    public static final Program MINECRAFT_TRANSPARENCY = Programs.provided("transparency");
    public static final Program MINECRAFT_BLOBS2 = Programs.provided("blobs2");
    public static final Program MINECRAFT_ENTITY_OUTLINE = Programs.provided("entity_outline");
    public static final Program MINECRAFT_NTSC_DECODE = Programs.provided("ntsc_decode");
    public static final Program MINECRAFT_PHOSPHOR = Programs.provided("phosphor");
    public static final Program MINECRAFT_WOBBLE = Programs.provided("wobble");
}
