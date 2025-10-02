# Resourceful - Minecraft 1.21.9 Update

## Major Changes and Migration Guide

### Resource Pack Format Update

The resource pack format has been updated from **34** to **69** to support Minecraft 1.21.9. This ensures compatibility with the latest Minecraft version.

```java
// Updated automatically
ResourcePack.VERSION // Now returns 69
```

### Critical: Uniform Blocks (1.21.6+)

**Breaking Change**: Minecraft 1.21.6+ replaced loose uniforms with uniform blocks. This library now supports both formats with automatic backward compatibility.

#### New Modern API (Recommended for 1.21.6+)

```java
CoreShader modernShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniformBlocks(
        UniformBlocks.MATRICES,    // Contains ModelViewMat, ProjMat
        UniformBlocks.FOG,         // Contains FogStart, FogEnd, FogColor
        UniformBlocks.COLOR,       // Contains ColorModulator
        UniformBlocks.CHUNK        // Contains ChunkOffset
    )
    .attributes(Attributes.VANILLA_POSITION, Attributes.VANILLA_COLOR)
    .fragment(MyShaderClass.class)
    .build();
```

#### Legacy API (Still Supported)

```java
CoreShader legacyShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniforms(
        Uniforms.VANILLA_MODEL_VIEW_MATRIX,
        Uniforms.VANILLA_PROJECTION_MATRIX,
        Uniforms.of("ColorModulator", 1.0f, 1.0f, 1.0f, 1.0f),
        Uniforms.of("FogStart", 0.0f),
        Uniforms.of("FogEnd", 1.0f),
        Uniforms.of("FogColor", 0.0f, 0.0f, 0.0f, 0.0f)
    )
    .attributes(Attributes.VANILLA_POSITION, Attributes.VANILLA_COLOR)
    .fragment(MyShaderClass.class)
    .build();
```

### Available Uniform Blocks

#### `UniformBlocks.MATRICES`
- `ModelViewMat` (matrix4x4)
- `ProjMat` (matrix4x4)

#### `UniformBlocks.FOG`
- `FogStart` (float)
- `FogEnd` (float)  
- `FogColor` (vec4)

#### `UniformBlocks.COLOR`
- `ColorModulator` (vec4)

#### `UniformBlocks.CHUNK`
- `ChunkOffset` (vec3)

### Custom Uniform Blocks

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

### New Post-Processing Shader

Added the missing `BLUR` post-processing shader:

```java
PostShader blurShader = Shaders.post(PostShaderType.BLUR)
    .fragment(BlurShaderClass.class)
    .build();
```

### Compatibility Matrix

| Minecraft Version | Resource Pack Format | Uniform Format | Support Status |
|-------------------|----------------------|----------------|----------------|
| 1.21.0 - 1.21.1   | 34                   | Loose Uniforms | ✅ Legacy mode |
| 1.21.2 - 1.21.5   | 42-55                | Loose Uniforms | ✅ Legacy mode |
| 1.21.6+           | 63+                  | Uniform Blocks | ✅ Modern mode |
| 1.21.9            | 69                   | Uniform Blocks | ✅ Current target |

### Migration Guide

#### For New Projects
Use the new uniform blocks API exclusively:

```java
CoreShader shader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
    .uniformBlocks(
        UniformBlocks.MATRICES,
        UniformBlocks.FOG,
        UniformBlocks.COLOR
    )
    .build();
```

#### For Existing Projects
Your existing code will continue to work unchanged. However, for best compatibility with modern Minecraft versions, consider migrating to uniform blocks:

**Before (Legacy):**
```java
.uniforms(
    Uniforms.VANILLA_MODEL_VIEW_MATRIX,
    Uniforms.VANILLA_PROJECTION_MATRIX,
    Uniforms.of("ColorModulator", 1.0f, 1.0f, 1.0f, 1.0f)
)
```

**After (Modern):**
```java
.uniformBlocks(
    UniformBlocks.MATRICES,  // Contains ModelViewMat + ProjMat
    UniformBlocks.COLOR      // Contains ColorModulator
)
```

### Testing

The library includes comprehensive tests for both formats:

```bash
./gradlew test
```

Test classes:
- `ResourcePackGenerationTest` - Basic functionality
- `ModernResourcePackTest` - Uniform blocks functionality
- `ResourcePackValidationTest` - Format validation and structure

### Dependencies

Added ASM dependencies for JLSL shader compilation:
- `org.ow2.asm:asm:9.6`
- `org.ow2.asm:asm-commons:9.6`
- `org.ow2.asm:asm-util:9.6`

No action required - these are automatically included.