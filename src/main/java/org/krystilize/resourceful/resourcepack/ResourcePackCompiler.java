package org.krystilize.resourceful.resourcepack;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.compile.FileCompiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

final class ResourcePackCompiler implements FileCompiler {

    private final @NotNull ResourcePack resourcePack;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ZipOutputStream zipOutput = new ZipOutputStream(outputStream);
    private final byte[] bytes;
    private final Gson gson = new Gson();

    ResourcePackCompiler(@NotNull ResourcePack resourcePack) {
        this.resourcePack = resourcePack;
        this.bytes = compile();
    }

    private byte[] compile() {
        synchronized (gson) {

            // pack.mcmeta
            {
                String mcmeta = gson.toJson(new Mcmeta(ResourcePack.VERSION, resourcePack.description));
                addFile("pack.mcmeta", mcmeta.getBytes());
            }


            // Compile all components
            resourcePack.compileComponents.forEach(compileComponent -> compileComponent.compile(this));

            try {
                zipOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return outputStream.toByteArray();
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void addFile(String entry, byte[] bytes) {
        try {
            zipOutput.putNextEntry(new ZipEntry(entry));
            zipOutput.write(bytes);
            zipOutput.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Mcmeta {
        private final Map<String, Object> pack;

        private Mcmeta(int version, @NotNull String description) {
            this.pack = Map.of(
                    "description", description,
                    "pack_format", version
            );
        }
    }
}
