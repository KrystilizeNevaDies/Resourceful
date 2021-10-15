package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.builders.Program;

public class Pass {
    private final @NotNull String name;
    private final @NotNull String intarget;
    private final @NotNull String outtarget;

    Pass(
            @NotNull Program program,
            @NotNull Target in,
            @NotNull Target out
    ) {
        this.name = program.getName();
        this.intarget = in.getName();
        this.outtarget = out.getName();
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getIntarget() {
        return intarget;
    }

    public @NotNull String getOuttarget() {
        return outtarget;
    }
}