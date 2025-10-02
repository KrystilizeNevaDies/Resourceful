package org.krystilize.resourceful.shaders.type;

import org.jetbrains.annotations.NotNull;

public interface PostShaderType {

    @NotNull String file();

    PostShaderType BLUR = () -> "blur.json";
    PostShaderType CREEPER = () -> "creeper.json";
    PostShaderType SPIDER = () -> "spider.json";
    PostShaderType ENDERMAN = () -> "invert.json";
    PostShaderType GLOWING_ENTITY = () -> "entity_outline.json";
    PostShaderType FABULOUS_GRAPHICS = () -> "transparency.json";
}