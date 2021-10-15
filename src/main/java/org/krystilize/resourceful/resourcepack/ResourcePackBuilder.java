package org.krystilize.resourceful.resourcepack;

import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.compile.CompileComponent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ResourcePackBuilder {
    final Set<CompileComponent> compileComponents = new HashSet<>();
    @NotNull String description = "";

    ResourcePackBuilder() {
    }

    /**
     * Sets the description of the resource pack.
     * @param description the description
     * @return the builder
     */
    public @NotNull ResourcePackBuilder description(@NotNull String description) {
        this.description = description;
        return this;
    }

    /**
     * Adds compile components to the resource pack.
     * @param compileComponents the compile components
     * @return the builder
     */
    public ResourcePackBuilder component(@NotNull CompileComponent... compileComponents) {
        Collections.addAll(this.compileComponents, compileComponents);
        return this;
    }

    /**
     * Builds the resource pack.
     * @return the resource pack
     */
    public @NotNull ResourcePack build() {
        return new ResourcePack(this);
    }
}