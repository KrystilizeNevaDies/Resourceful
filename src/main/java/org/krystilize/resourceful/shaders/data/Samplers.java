package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

public class Samplers {

    public static @NotNull Sampler of(@NotNull String name) {
        return new Sampler(name);
    }

    public static @NotNull Sampler VANILLA_DIFFUSE = Samplers.of("DiffuseSampler");
    public static @NotNull Sampler SAMPLER0 = Samplers.of("Sampler0");
    public static @NotNull Sampler SAMPLER2 = Samplers.of("Sampler2");
}
