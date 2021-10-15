#version 150
// Original class name: org.krystilize.resourceful.ExampleShader compiled from ExampleShader.java and of version 61

#moj_import <fog.glsl>
uniform sampler2D Sampler0;
uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
in vec2 texCoord0;
in float vertexDistance;
in vec4 vertexColor;
in vec4 normal;
out vec4 fragColor;

void main()
{
    vec4 color = texture(Sampler0, texCoord0)*vertexColor*ColorModulator; //Line #58
    if(color.a < 0.1) //Line #59
    {
        return; //Line #60
    }
    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor); //Line #62
}
