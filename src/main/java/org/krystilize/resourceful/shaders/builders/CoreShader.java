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
import org.krystilize.resourceful.shaders.type.CoreShaderType;

public class CoreShader implements CompileComponent {
    private static final Gson gson = new Gson().newBuilder()
            .setPrettyPrinting()
            .create();
    private static final JLGL jlgl = new JLGL(150);

    final @NotNull GeneratingProgram program;
    final @NotNull CoreShaderType type;

    final @Nullable Class<? extends VertexShaderEnvironment> vertexShader;
    final @Nullable Class<? extends FragmentShaderEnvironment> fragmentShader;

    final boolean vertexShaderPresent;
    final boolean fragmentShaderPresent;

    CoreShader(@NotNull CoreShaderBuilder builder) {
        this.type = builder.type;
        this.program = new GeneratingProgram(builder);
        this.vertexShader = builder.vertexShader;
        this.fragmentShader = builder.fragmentShader;
        vertexShaderPresent = builder.vertexShaderPresent;
        fragmentShaderPresent = builder.fragmentShaderPresent;
    }

    @Override
    public void compile(@NotNull FileCompiler compiler) {
        compile(type.file(), compiler);

        String fileName = "assets/minecraft/shaders/core/" + type.file().replace(".json", "");
        if (vertexShader != null) {
            compiler.addFile(fileName + ".vsh", jlgl.generateGLSLShader(vertexShader));
        }
        if (fragmentShader != null) {
            compiler.addFile(fileName + ".fsh", jlgl.generateGLSLShader(fragmentShader));
        }
    }

    private void compile(@NotNull String fileName, @NotNull FileCompiler compiler) {
        synchronized (gson) {
            compiler.addFile(
                    "assets/minecraft/shaders/core/" + fileName,
                    gson.toJson(program.serializeObject())
            );
        }
    }
}
