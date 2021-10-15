package org.krystilize.resourceful.resourcepack.compile;

import org.jetbrains.annotations.NotNull;

public interface CompileComponent {
    void compile(@NotNull FileCompiler compiler);
}
