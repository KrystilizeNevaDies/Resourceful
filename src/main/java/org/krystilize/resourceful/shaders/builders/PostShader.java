package org.krystilize.resourceful.shaders.builders;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.compile.CompileComponent;
import org.krystilize.resourceful.resourcepack.compile.FileCompiler;
import org.krystilize.resourceful.shaders.data.AuxTarget;
import org.krystilize.resourceful.shaders.data.AuxTargets;
import org.krystilize.resourceful.shaders.data.Pass;
import org.krystilize.resourceful.shaders.data.Passes;
import org.krystilize.resourceful.shaders.type.PostShaderType;

import java.awt.image.BufferedImage;
import java.util.List;

public class PostShader implements CompileComponent {
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    private @NotNull final List<String> targets;
    private @NotNull final List<Pass> passes;

    @Expose(serialize = false, deserialize = false)
    private final @NotNull PostShaderType type;

    PostShader(@NotNull PostShaderBuilder builder) {
        this.targets = builder.targets;
        this.passes = builder.passes;
        this.type = builder.type;
    }

    @Override
    public void compile(@NotNull FileCompiler compiler) {
        prepare(compiler);
        compile(type.file(), compiler);
    }

    private void compile(@NotNull String fileName, @NotNull FileCompiler compiler) {
        synchronized (gson) {
            compiler.addFile("assets/minecraft/shaders/post/" + fileName, gson.toJson(this));
        }
    }

    private void prepare(@NotNull FileCompiler fileCompiler) {
        // Render all images if applicable
        for (Pass pass : passes) {
            if (pass instanceof Passes.ContainsAuxTargets containsAuxTargets) {

                // for each auxiliary target
                for (AuxTarget auxtarget : containsAuxTargets.getAuxTargets()) {

                    // If the target has an image
                    if (auxtarget instanceof AuxTargets.AuxImageTarget auxImageTarget) {

                        // Compile the image
                        BufferedImage image = auxImageTarget.getImage();
                        String imagePath = "minecraft/textures/effect/" + auxImageTarget.getId() + ".png";
                        fileCompiler.writeImage(imagePath, image);
                    }
                }
            }
        }
    }
}
