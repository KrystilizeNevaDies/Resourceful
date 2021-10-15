#version 150
// Original class name: org.krystilize.resourceful.ExampleShader compiled from ExampleShader.java and of version 61
uniform sampler2D Sampler0;
uniform vec4 ColorModulator;
in vec2 texCoord0;
out vec4 fragColor;

void main()
{
    vec4 color = texture(Sampler0, texCoord0); //Line #49
    if(!(color.a != 0.0)) //Line #50
    {
        return; //Line #51
    }
    fragColor = color*ColorModulator; //Line #53
}
