package org.krystilize.resourceful;

import org.junit.jupiter.api.Test;
import org.krystilize.resourceful.resourcepack.ResourcePack;
import org.krystilize.resourceful.resourcepack.ResourcePackBuilder;
import org.krystilize.resourceful.shaders.Shaders;
import org.krystilize.resourceful.shaders.builders.PostShader;
import org.krystilize.resourceful.shaders.type.PostShaderType;

import static org.junit.jupiter.api.Assertions.*;

public class PostShaderTest {

    @Test
    public void testAllPostShaderTypes() {
        // Test that all post-processing shader types are available and can be used
        
        PostShader blur = Shaders.post(PostShaderType.BLUR).build();
        PostShader creeper = Shaders.post(PostShaderType.CREEPER).build();
        PostShader spider = Shaders.post(PostShaderType.SPIDER).build();
        PostShader enderman = Shaders.post(PostShaderType.ENDERMAN).build();
        PostShader glowing = Shaders.post(PostShaderType.GLOWING_ENTITY).build();
        PostShader fabulous = Shaders.post(PostShaderType.FABULOUS_GRAPHICS).build();
        
        assertNotNull(blur);
        assertNotNull(creeper);
        assertNotNull(spider);
        assertNotNull(enderman);
        assertNotNull(glowing);
        assertNotNull(fabulous);
    }

    @Test
    public void testPostShaderFileNames() {
        assertEquals("blur.json", PostShaderType.BLUR.file());
        assertEquals("creeper.json", PostShaderType.CREEPER.file());
        assertEquals("spider.json", PostShaderType.SPIDER.file());
        assertEquals("invert.json", PostShaderType.ENDERMAN.file());
        assertEquals("entity_outline.json", PostShaderType.GLOWING_ENTITY.file());
        assertEquals("transparency.json", PostShaderType.FABULOUS_GRAPHICS.file());
    }

    @Test
    public void testPostShaderResourcePack() {
        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("Post-processing shader test pack");

        // Add a blur post-processing shader
        PostShader blurShader = Shaders.post(PostShaderType.BLUR).build();
        builder.component(blurShader);

        ResourcePack pack = builder.build();
        byte[] compiled = pack.compile();
        
        assertNotNull(compiled);
        assertTrue(compiled.length > 0);
    }
}