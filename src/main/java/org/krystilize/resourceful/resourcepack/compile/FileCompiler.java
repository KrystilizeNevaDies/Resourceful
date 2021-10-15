package org.krystilize.resourceful.resourcepack.compile;

import com.google.gson.JsonObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

public interface FileCompiler {
    void addFile(String path, byte[] bytes);

    default void addFile(String path, String contents) {
        this.addFile(path, contents.getBytes());
    }

    default void addFile(String path, JsonObject jsonObject) {
        this.addFile(path, jsonObject.getAsString().getBytes());
    }

    default void writeBytes(String path, Consumer<OutputStream> outputStreamConsumer) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStreamConsumer.accept(outputStream);
        this.addFile(path, outputStream.toByteArray());
    }

    default void writeImage(String path, BufferedImage image) {
        this.writeBytes(path, outputStream -> {
            try {
                ImageIO.write(image, "png", outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
