package org.krystilize.resourceful.shaders.builders;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.data.*;

import java.util.List;

public interface Program {

    @NotNull String getName();

    default @NotNull Pass pass(@NotNull Target in, @NotNull Target out) {
        return Passes.of(this, in, out);
    }

    default @NotNull Pass pass(@NotNull Target in, @NotNull Target out, @NotNull AuxTarget... auxtargets) {
        return Passes.aux(this, in, out, auxtargets);
    }

    default @NotNull Pass pass(@NotNull Target in, @NotNull Target out, @NotNull Uniform... uniforms) {
        return Passes.uniform(this, in, out, uniforms);
    }

    default @NotNull Pass pass(@NotNull Target in, @NotNull Target out, @NotNull List<AuxTarget> auxtargets,
                              @NotNull List<Uniform> uniforms) {
        return Passes.auxUniform(this, in, out, auxtargets, uniforms);
    }
}