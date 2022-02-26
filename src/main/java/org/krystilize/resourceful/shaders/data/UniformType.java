package org.krystilize.resourceful.shaders.data;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Function;

public class UniformType<T> {

    public static @NotNull UniformType<Boolean> BOOL = new UniformType<>("bool", value -> new float[] {value ? 1.0F : 0.0F});
    public static @NotNull UniformType<Integer> INT = new UniformType<>("int", value -> new float[] {value});
    public static @NotNull UniformType<Integer> UINT = new UniformType<>("uint", value -> new float[] {value});
    public static @NotNull UniformType<Float> FLOAT = new UniformType<>("float", value -> new float[] {value});
    public static @NotNull UniformType<Double> DOUBLE = new UniformType<>("double", value -> new float[] {value.floatValue()});

    public static @NotNull UniformType<Boolean[]> BOOLEAN_VEC2 = new UniformType<>("bool", value -> new float[] {
            value[0] ? 1.0F : 0.0F, value[1] ? 1.0F : 0.0F,
    });
    public static @NotNull UniformType<Boolean[]> BOOLEAN_VEC3 = new UniformType<>("bool", value -> new float[] {
            value[0] ? 1.0F : 0.0F, value[1] ? 1.0F : 0.0F, value[2] ? 1.0F : 0.0F,
    });
    public static @NotNull UniformType<Boolean[]> BOOLEAN_VEC4 = new UniformType<>("bool", value -> new float[] {
            value[0] ? 1.0F : 0.0F, value[1] ? 1.0F : 0.0F, value[2] ? 1.0F : 0.0F, value[3] ? 1.0F : 0.0F,
    });

    public static @NotNull UniformType<Integer[]> INT_VEC2 = new UniformType<>("int", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Integer[]> INT_VEC3 = new UniformType<>("int", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Integer[]> INT_VEC4 = new UniformType<>("int", value -> toFloatArray(value, Float.class::cast));

    public static @NotNull UniformType<Integer[]> UINT_VEC2 = new UniformType<>("uint", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Integer[]> UINT_VEC3 = new UniformType<>("uint", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Integer[]> UINT_VEC4 = new UniformType<>("uint", value -> toFloatArray(value, Float.class::cast));

    public static @NotNull UniformType<Float[]> FLOAT_VEC2 = new UniformType<>("float", value -> toFloatArray(value, Function.identity()));
    public static @NotNull UniformType<Float[]> FLOAT_VEC3 = new UniformType<>("float", value -> toFloatArray(value, Function.identity()));
    public static @NotNull UniformType<Float[]> FLOAT_VEC4 = new UniformType<>("float", value -> toFloatArray(value, Function.identity()));

    public static @NotNull UniformType<Double[]> DOUBLE_VEC2 = new UniformType<>("double", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Double[]> DOUBLE_VEC3 = new UniformType<>("double", value -> toFloatArray(value, Float.class::cast));
    public static @NotNull UniformType<Double[]> DOUBLE_VEC4 = new UniformType<>("double", value -> toFloatArray(value, Float.class::cast));

    public static @NotNull UniformType<Float[][]> MAT2X2 = new UniformType<>("matrix2x2", value -> new float[] {
            value[0][0], value[0][1], value[1][0], value[1][1],
    });
    public static @NotNull UniformType<Float[][]> MAT2X3 = new UniformType<>("matrix2x3", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[1][0], value[1][1], value[1][2],
    });
    public static @NotNull UniformType<Float[][]> MAT2X4 = new UniformType<>("matrix2x4", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[0][3], value[1][0], value[1][1], value[1][2],
            value[1][3],
    });
    public static @NotNull UniformType<Float[][]> MAT3X2 = new UniformType<>("matrix3x2", value -> new float[] {
            value[0][0], value[0][1], value[1][0], value[1][1], value[2][0], value[2][1],
    });
    public static @NotNull UniformType<Float[][]> MAT3X3 = new UniformType<>("matrix3x3", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[1][0], value[1][1], value[1][2], value[2][0],
            value[2][1], value[2][2],
    });
    public static @NotNull UniformType<Float[][]> MAT3X4 = new UniformType<>("matrix3x4", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[0][3], value[1][0], value[1][1], value[1][2],
            value[1][3], value[2][0], value[2][1], value[2][2], value[2][3],
    });
    public static @NotNull UniformType<Float[][]> MAT4X2 = new UniformType<>("matrix4x2", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[0][3], value[1][0], value[1][1], value[1][2],
            value[1][3],
    });
    public static @NotNull UniformType<Float[][]> MAT4X3 = new UniformType<>("matrix4x3", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[0][3], value[1][0], value[1][1], value[1][2],
            value[1][3], value[2][0], value[2][1], value[2][2], value[2][3],
    });
    public static @NotNull UniformType<Float[][]> MAT4X4 = new UniformType<>("matrix4x4", value -> new float[] {
            value[0][0], value[0][1], value[0][2], value[0][3], value[1][0], value[1][1], value[1][2],
            value[1][3], value[2][0], value[2][1], value[2][2], value[2][3], value[3][0], value[3][1],
            value[3][2], value[3][3],
    });

    public static @NotNull UniformType<Float[][]> MAT2 = MAT2X2;
    public static @NotNull UniformType<Float[][]> MAT3 = MAT3X3;
    public static @NotNull UniformType<Float[][]> MAT4 = MAT4X4;

    private static <T> float[] toFloatArray(T[] values, Function<T, Float> function) {
        float[] result = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = function.apply(values[i]);
        }
        return result;
    }

    private final @NotNull String datatype;
    private final @NotNull Function<T, float[]> toValuesFunction;

    UniformType(@NotNull String datatype, @NotNull Function<T, float[]> toValuesFunction) {
        this.datatype = datatype;
        this.toValuesFunction = toValuesFunction;
    }

    public @NotNull String getDatatype() {
        return datatype;
    }

    public float[] toValues(@NotNull T type) {
        return toValuesFunction.apply(type);
    }
}