package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.shaders.builders.Program;

import java.util.List;

public class Passes {
    public static @NotNull Pass of(
            @NotNull Program program,
            @NotNull Target in,
            @NotNull Target out
    ) {
        return new Pass(program, in, out);
    }

    public static @NotNull Pass aux(
            @NotNull Program program,
            @NotNull Target in,
            @NotNull Target out,
            @NotNull AuxTarget... auxtargets
    ) {
        return new AuxPass(program, in, out, List.of(auxtargets));
    }

    public static @NotNull Pass uniform(
            @NotNull Program program,
            @NotNull Target in,
            @NotNull Target out,
            @NotNull Uniform... uniforms
    ) {
        return new UniformPass(program, in, out, List.of(uniforms));
    }

    public static @NotNull Pass auxUniform(
            @NotNull Program program,
            @NotNull Target in,
            @NotNull Target out,
            @NotNull List<AuxTarget> auxtargets,
            @NotNull List<Uniform> uniforms
    ) {
        return new AuxUniformPass(program, in, out, auxtargets, uniforms);
    }

    public interface ContainsAuxTargets {
        @NotNull List<AuxTarget> getAuxTargets();
    }

    public interface ContainsUniforms {
        @NotNull List<Uniform> getUniforms();
    }

    public static class AuxPass extends Pass implements ContainsAuxTargets {
        private final @NotNull List<AuxTarget> auxtargets;

        private AuxPass(
                @NotNull Program program,
                @NotNull Target in,
                @NotNull Target out,
                @NotNull List<AuxTarget> auxtargets
        ) {
            super(program, in, out);
            this.auxtargets = auxtargets;
        }

        public @NotNull List<AuxTarget> getAuxTargets() {
            return auxtargets;
        }
    }

    public static class UniformPass extends Pass implements ContainsUniforms {
        private final @NotNull List<Uniform> uniforms;

        private UniformPass(
                @NotNull Program program,
                @NotNull Target in,
                @NotNull Target out,
                @NotNull List<Uniform> uniforms
        ) {
            super(program, in, out);
            this.uniforms = uniforms;
        }

        public @NotNull List<Uniform> getUniforms() {
            return uniforms;
        }
    }

    public static class AuxUniformPass extends Pass implements ContainsUniforms, ContainsAuxTargets {
        private final @NotNull List<AuxTarget> auxtargets;
        private final @NotNull List<Uniform> uniforms;

        private AuxUniformPass(
                @NotNull Program program,
                @NotNull Target in,
                @NotNull Target out,
                @NotNull List<AuxTarget> auxtargets,
                @NotNull List<Uniform> uniforms
        ) {
            super(program, in, out);
            this.auxtargets = auxtargets;
            this.uniforms = uniforms;
        }

        public @NotNull List<AuxTarget> getAuxTargets() {
            return auxtargets;
        }

        public @NotNull List<Uniform> getUniforms() {
            return uniforms;
        }
    }
}