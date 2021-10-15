package org.krystilize.resourceful.resourcepack;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.compile.CompileComponent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ResourcePack {
    public static final int VERSION = 7;
    final Set<CompileComponent> compileComponents;
    final String description;
    /**
     * Creates a mutable resource pack builder.
     * @return the builder
     */
    public static ResourcePackBuilder builder() {
        return new ResourcePackBuilder();
    };

    ResourcePack(
            ResourcePackBuilder builder
    ) {
        this.compileComponents = Set.copyOf(builder.compileComponents);
        this.description = builder.description;
    }

    public byte @NotNull [] compile() {
        return new ResourcePackCompiler(this).getBytes();
    }
}
