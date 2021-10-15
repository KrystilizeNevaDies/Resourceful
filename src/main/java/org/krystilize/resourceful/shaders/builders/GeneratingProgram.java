package org.krystilize.resourceful.shaders.builders;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.jlsl.JLGL;
import org.jglrxavpok.jlsl.glsl.FragmentShaderEnvironment;
import org.jglrxavpok.jlsl.glsl.VertexShaderEnvironment;
import org.krystilize.resourceful.resourcepack.compile.CompileComponent;
import org.krystilize.resourceful.resourcepack.compile.FileCompiler;
import org.krystilize.resourceful.shaders.data.Sampler;
import org.krystilize.resourceful.shaders.data.Uniform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GeneratingProgram implements Program, CompileComponent {

    private static final Gson gson = new Gson().newBuilder()
            .setPrettyPrinting()
            .create();
    private static final JLGL jlgl = new JLGL(150);

    final @NotNull String name;
    final @NotNull Map<String, Object> blend;
    final @NotNull List<String> attributes;
    final @NotNull List<Sampler> samplers;
    final @NotNull List<Uniform> uniforms;

    @Expose(serialize = false, deserialize = false)
    final boolean vertexShaderPresent;
    @Expose(serialize = false, deserialize = false)
    final boolean fragmentShaderPresent;

    @Expose(serialize = false, deserialize = false)
    final @Nullable Class<? extends VertexShaderEnvironment> vertexShader;
    @Expose(serialize = false, deserialize = false)
    final @Nullable Class<? extends FragmentShaderEnvironment> fragmentShader;

    GeneratingProgram(@NotNull ProgramBuilder<?, ?> builder) {
        name = builder.name;
        blend = new HashMap<>(builder.blend);
        attributes = new ArrayList<>(builder.attributes);
        samplers = new ArrayList<>(builder.samplers);
        uniforms = new ArrayList<>(builder.uniforms);
        vertexShader = builder.vertexShader;
        fragmentShader = builder.fragmentShader;
        vertexShaderPresent = builder.vertexShaderPresent;
        fragmentShaderPresent = builder.fragmentShaderPresent;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @NotNull Map<String, Object> serializeObject() {
        Map<String, Object> serializingObject = new HashMap<>(Map.of(
                "blend", blend,
                "attributes", attributes,
                "samplers", samplers,
                "uniforms", uniforms
        ));

        String nameWithoutJson = name.replace(".json", "");

        if (vertexShaderPresent) {
            serializingObject.put("vertex", nameWithoutJson);
        }

        if (fragmentShaderPresent) {
            serializingObject.put("fragment", nameWithoutJson);
        }
        return serializingObject;
    }

    @Override
    public void compile(@NotNull FileCompiler compiler) {
        synchronized (gson) {
            compiler.addFile("assets/minecraft/shaders/program/" + name, gson.toJson(this.serializeObject()));
        }

        String fileName = "assets/minecraft/shaders/program/" + name.replace(".json", "");
        if (vertexShader != null) {
            compiler.addFile(fileName + ".vsh", jlgl.generateGLSLShader(vertexShader));
        }
        if (fragmentShader != null) {
            compiler.addFile(fileName + ".fsh", jlgl.generateGLSLShader(fragmentShader));
        }
    }
}
