package org.krystilize.resourceful;

import org.jglrxavpok.jlsl.glsl.*;

import static org.jglrxavpok.jlsl.glsl.GLSL.*;

@Notes("#moj_import <fog.glsl>")
public abstract class ExampleShader extends FragmentShaderEnvironment
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
