package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Attributes {

    public static @NotNull Attribute of(@NotNull String name) {
        return new Attribute(name);
    }

    public static final Attribute VANILLA_POSITION = Attributes.of("Position");
    public static final Attribute VANILLA_UV0 = Attributes.of("UV0");
    public static final Attribute VANILLA_UV2 = Attributes.of("UV2");
    public static final Attribute VANILLA_COLOR = Attributes.of("Color");
    public static final Attribute VANILLA_NORMAL = Attributes.of("Normal");
}
