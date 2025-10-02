# Resourceful - Minecraft 1.21.9 Breaking Changes

## **⚠️ BREAKING CHANGES - Migration Required**

This version removes **ALL** backward compatibility and focuses exclusively on **Minecraft 1.21.9**. All legacy APIs have been removed.

## Critical Breaking Changes

### 1. **Legacy Uniforms API Removed**

**❌ REMOVED:**
```java
// This API no longer exists
CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniforms(
        Uniforms.VANILLA_MODEL_VIEW_MATRIX,
        Uniforms.VANILLA_PROJECTION_MATRIX,
        Uniforms.of("ColorModulator", 1.0f, 1.0f, 1.0f, 1.0f)
    )
    .build();
```

**✅ REQUIRED:**
```java
// Use uniform blocks exclusively
CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniformBlocks(
        UniformBlocks.MATRICES,    // Contains ModelViewMat + ProjMat
        UniformBlocks.COLOR        // Contains ColorModulator
    )
    .build();
```

### 2. **Resource Pack Format**

- **Updated from 34 → 69** for Minecraft 1.21.9 compatibility
- **No backward compatibility** with older Minecraft versions

### 3. **Uniform Class API Changes**

**❌ REMOVED deprecated methods:**
```java
uniform.name()      // Use getName() instead
uniform.value()     // Use getValues() instead  
uniform.type()      // Use getType() instead
```

### 4. **ProgramBuilder API Changes**

**❌ REMOVED:**
- `uniforms()` method completely removed
- Legacy uniform support removed from serialization

**✅ ONLY SUPPORTED:**
- `uniformBlocks()` method for all uniform configuration

## Complete Migration Guide

### **Before (Legacy - No Longer Works):**

```java
import org.krystilize.resourceful.shaders.data.Uniforms;

CoreShader legacyShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniforms(
        Uniforms.VANILLA_MODEL_VIEW_MATRIX,
        Uniforms.VANILLA_PROJECTION_MATRIX,
        Uniforms.of("ChunkOffset", 0.0F, 0.0F, 0.0F),
        Uniforms.of("ColorModulator", 1.0F, 0.8F, 1.0F, 1.0F),
        Uniforms.of("FogStart", 0.0F),
        Uniforms.of("FogEnd", 1.0F),
        Uniforms.of("FogColor", 0.0F, 0.0F, 0.0F, 0.0F)
    )
    .attributes(Attributes.VANILLA_POSITION, Attributes.VANILLA_COLOR)
    .fragment(MyShaderClass.class)
    .build();
```

### **After (Required - Uniform Blocks):**

```java
import org.krystilize.resourceful.shaders.data.UniformBlocks;

CoreShader modernShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniformBlocks(
        UniformBlocks.MATRICES,    // ModelViewMat + ProjMat
        UniformBlocks.CHUNK,       // ChunkOffset
        UniformBlocks.COLOR,       // ColorModulator  
        UniformBlocks.FOG          // FogStart + FogEnd + FogColor
    )
    .attributes(Attributes.VANILLA_POSITION, Attributes.VANILLA_COLOR)
    .fragment(MyShaderClass.class)
    .build();
```

## Available Uniform Blocks

### `UniformBlocks.MATRICES`
- `ModelViewMat` (matrix4x4) - Replaces `Uniforms.VANILLA_MODEL_VIEW_MATRIX`
- `ProjMat` (matrix4x4) - Replaces `Uniforms.VANILLA_PROJECTION_MATRIX`

### `UniformBlocks.FOG`
- `FogStart` (float) - Replaces `Uniforms.of("FogStart", ...)`
- `FogEnd` (float) - Replaces `Uniforms.of("FogEnd", ...)`  
- `FogColor` (vec4) - Replaces `Uniforms.of("FogColor", ...)`

### `UniformBlocks.COLOR`
- `ColorModulator` (vec4) - Replaces `Uniforms.of("ColorModulator", ...)`

### `UniformBlocks.CHUNK`
- `ChunkOffset` (vec3) - Replaces `Uniforms.of("ChunkOffset", ...)`

## Custom Uniform Blocks

For custom uniforms, create your own blocks:

```java
UniformBlock customBlock = UniformBlocks.block("MyCustomBlock",
    Uniforms.of("MyFloat", 1.0f),
    Uniforms.of("MyVec3", 1.0f, 0.5f, 0.0f),
    Uniforms.of("MyMatrix", new Float[][]{ ... })
);

CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniformBlocks(customBlock)
    .build();
```

## Migration Steps

1. **Update imports:** Remove `Uniforms` import, add `UniformBlocks`
2. **Replace method calls:** Change `.uniforms()` to `.uniformBlocks()`
3. **Map loose uniforms to blocks:** Use the mapping table above
4. **Update Uniform method calls:** Use non-deprecated methods
5. **Test with Minecraft 1.21.9:** Verify compatibility

## Version Support

| Version | Support Status |
|---------|----------------|
| < 1.21.9 | ❌ **No longer supported** |
| 1.21.9 | ✅ **Only supported version** |

## Post-Processing Shaders

All 6 post-processing effects supported:
- `PostShaderType.BLUR` (newly added)
- `PostShaderType.CREEPER`
- `PostShaderType.SPIDER`
- `PostShaderType.ENDERMAN` 
- `PostShaderType.GLOWING_ENTITY`
- `PostShaderType.FABULOUS_GRAPHICS`

## Dependencies

Required dependencies (automatically included):
- `org.ow2.asm:asm:9.6`
- `org.ow2.asm:asm-commons:9.6`
- `org.ow2.asm:asm-util:9.6`

## Breaking Change Summary

- ❌ **Removed:** `uniforms()` method from ProgramBuilder
- ❌ **Removed:** Legacy uniform fallback logic
- ❌ **Removed:** Deprecated methods from Uniform class
- ❌ **Removed:** Support for Minecraft versions < 1.21.9
- ❌ **Removed:** Loose uniform serialization format
- ✅ **Required:** Uniform blocks for all shader configuration
- ✅ **Required:** Minecraft 1.21.9 for generated resource packs