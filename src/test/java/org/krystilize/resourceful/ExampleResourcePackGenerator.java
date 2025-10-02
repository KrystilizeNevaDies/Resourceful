package org.krystilize.resourceful;

import org.jetbrains.annotations.NotNull;
import static org.jglrxavpok.jlsl.glsl.GLSL.*;

import org.jglrxavpok.jlsl.glsl.FragmentShaderEnvironment;
import org.jglrxavpok.jlsl.glsl.Sampler2D;
import org.jglrxavpok.jlsl.glsl.Vec2;
import org.jglrxavpok.jlsl.glsl.Vec4;
import org.krystilize.resourceful.resourcepack.ResourcePack;
import org.krystilize.resourceful.resourcepack.ResourcePackBuilder;
import org.krystilize.resourceful.shaders.Shaders;
import org.krystilize.resourceful.shaders.builders.CoreShader;
import org.krystilize.resourceful.shaders.data.Attributes;
import org.krystilize.resourceful.shaders.data.Samplers;
import org.krystilize.resourceful.shaders.data.UniformBlocks;
import org.krystilize.resourceful.shaders.type.CoreShaderType;

public class ExampleResourcePackGenerator {
    public static @NotNull ResourcePack generatePack() {
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("A generated resource pack");

        { // Apply shader
            CoreShader coreShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                    .uniformBlocks(
                            UniformBlocks.MATRICES,
                            UniformBlocks.CHUNK,
                            UniformBlocks.COLOR,
                            UniformBlocks.FOG
                    )
                    .attributes(
                            Attributes.VANILLA_POSITION,
                            Attributes.VANILLA_COLOR,
                            Attributes.VANILLA_UV0,
                            Attributes.VANILLA_UV2,
                            Attributes.VANILLA_NORMAL
                    )
                    .fragment(org.krystilize.resourceful.ExampleShader.class)
                    .vertexIsPresent(true)
                    .samplers(
                            Samplers.SAMPLER0,
                            Samplers.SAMPLER2
                    )
                    .build();

            builder.component(coreShader);
        }

        return builder.build();
    }

    @Notes("#moj_import <fog.glsl>")
    public abstract static class ExampleShader extends FragmentShaderEnvironment
    {
        @Uniform Sampler2D Sampler0;

        @Uniform Vec4 ColorModulator;
        @Uniform float FogStart;
        @Uniform float FogEnd;
        @Uniform Vec4 FogColor;

        @In Vec2 texCoord0;
        @In float vertexDistance;
        @In Vec4 vertexColor;
        @In Vec4 normal;

        @Out Vec4 fragColor;

        @Override
        public void main()
        {
            Vec4 color = texture(Sampler0, texCoord0).mul(vertexColor).mul(ColorModulator);
            fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
        }

        abstract Vec4 linear_fog(Vec4 color, float vertexDistance, float fogStart, float fogEnd, Vec4 fogColor);
    }

}
