package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.jlsl.glsl.Vec2;
import org.jglrxavpok.jlsl.glsl.Vec3;
import org.jglrxavpok.jlsl.glsl.Vec4;

public class Uniforms {

    public static <T> @NotNull Uniform of(@NotNull String name, @NotNull UniformType<T> type, @NotNull T value) {
        return new Uniform(name, type, value);
    }

    public static @NotNull Uniform of(@NotNull String name, int value) {
        return new Uniform(name, UniformType.INT, value);
    }

    public static @NotNull Uniform of(@NotNull String name, float value) {
        return new Uniform(name, UniformType.FLOAT, value);
    }

    public static @NotNull Uniform of(@NotNull String name, float x, float y) {
        return new Uniform(name, UniformType.FLOAT_VEC2, new Float[]{x, y});
    }

    public static @NotNull Uniform of(@NotNull String name, float x, float y, float z) {
        return new Uniform(name, UniformType.FLOAT_VEC3, new Float[]{x, y, z});
    }

    public static @NotNull Uniform of(@NotNull String name, float x, float y, float z, float w) {
        return new Uniform(name, UniformType.FLOAT_VEC4, new Float[]{x, y, z, w});
    }

    public static @NotNull Uniform of(@NotNull String name, double value) {
        return new Uniform(name, UniformType.DOUBLE, value);
    }

    public static @NotNull Uniform of(@NotNull String name, boolean value) {
        return new Uniform(name, UniformType.BOOL, value);
    }

    public static @NotNull Uniform of(@NotNull String name, Vec2 value) {
        return new Uniform(name, UniformType.DOUBLE_VEC2, new Double[]{value.x, value.y});
    }

    public static @NotNull Uniform of(@NotNull String name, Vec3 value) {
        return new Uniform(name, UniformType.DOUBLE_VEC3, new Double[]{value.x, value.y, value.z});
    }

    public static @NotNull Uniform of(@NotNull String name, Vec4 value) {
        return new Uniform(name, UniformType.DOUBLE_VEC4, new Double[]{value.x, value.y, value.z, value.w});
    }

    public static final @NotNull Uniform VANILLA_MODEL_VIEW_MATRIX = Uniforms.of(
            "ModelViewMat", UniformType.MAT4X4, new Float[][] {
                    { 1.0F, 0.0F, 0.0F, 0.0F },
                    { 0.0F, 1.0F, 0.0F, 0.0F },
                    { 0.0F, 0.0F, 1.0F, 0.0F },
                    { 0.0F, 0.0F, 0.0F, 1.0F }
            }
    );

    public static final @NotNull Uniform VANILLA_PROJECTION_MATRIX = Uniforms.of(
            "ProjMat", UniformType.MAT4X4, new Float[][] {
                    { 1.0F, 0.0F, 0.0F, 0.0F },
                    { 0.0F, 1.0F, 0.0F, 0.0F },
                    { 0.0F, 0.0F, 1.0F, 0.0F },
                    { 0.0F, 0.0F, 0.0F, 1.0F }
            }
    );
}
