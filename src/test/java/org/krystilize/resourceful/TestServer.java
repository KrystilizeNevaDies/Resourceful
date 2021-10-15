package org.krystilize.resourceful;

import com.google.common.hash.Hashing;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.world.biomes.Biome;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.ResourcePack;
import org.krystilize.resourceful.resourcepack.ResourcePackBuilder;
import org.krystilize.resourceful.shaders.Shaders;
import org.krystilize.resourceful.shaders.builders.CoreShader;
import org.krystilize.resourceful.shaders.type.CoreShaderType;
import org.krystilize.resourceful.shaders.data.*;
import org.krystilize.resourceful.util.NetworkingUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TestServer {

    private static final Random random = new Random();

    public static void main(String[] args) {
        // Initialization
        MinecraftServer minecraftServer = MinecraftServer.init();

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        // Create the instance
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        // Set the ChunkGenerator
        instanceContainer.setChunkGenerator(new GeneratorDemo());

        // Add an event callback to specify the spawning instance (and the spawn position)
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 2, 0));
        });

        var resourcePackBytes = generatePack().compile();
        new ResourcePackServer(resourcePackBytes).start();

        new File("generatedResourcePack/").mkdirs();
        try {
            FileWriter writer = new FileWriter("generatedResourcePack/data.zip");
            writer.write(new String(resourcePackBytes, StandardCharsets.UTF_8));
            writer.close();

            try (FileOutputStream outputStream = new FileOutputStream("generatedResourcePack/data.zip")) {
                outputStream.write(resourcePackBytes);
            }

            UnzipFile.unzip("generatedResourcePack/data.zip", "generatedResourcePack/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        globalEventHandler.addListener(PlayerSpawnEvent.class, event -> {
            final Player player = event.getPlayer();
            PlayerConnection connection = player.getPlayerConnection();
            player.setGameMode(GameMode.CREATIVE);

            // Convert server address to address with new port
            String resultAddress = connection.getServerAddress();
            resultAddress = NetworkingUtils.changeAddressPort(resultAddress, 8001);
            resultAddress = NetworkingUtils.qualifyAddressWithHttp(resultAddress);
            player.setResourcePack(
                    net.minestom.server.resourcepack.ResourcePack.forced(
                            resultAddress,
                            Hashing.sha1().hashBytes(resourcePackBytes).toString()
                    )
            );
            player.sendMessage(resultAddress);
        });

        globalEventHandler.addListener(PlayerHandAnimationEvent.class, event -> {
            Player player = event.getPlayer();

            LivingEntity entity = new LivingEntity(EntityType.PIG);
            entity.setInstance(player.getInstance());
            entity.teleport(player.getPosition());
            entity.setGlowing(true);
        });

        // Remove my client's resource packs
        File file = new File("C:\\Users\\Krystilize\\AppData\\Roaming\\.minecraft\\server-resource-packs");
        if (file.exists())
        try {
            Files.walk(file.toPath())
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", 25565);
    }

    private static ResourcePack generatePack() {

        ResourcePackBuilder builder = ResourcePack.builder();
        builder.description("A generated resource pack");


        { // Apply shader
            CoreShader coreShader = Shaders.core(CoreShaderType.VANILLA_SOLID_BLOCKS)
                    .uniforms(
                            Uniforms.VANILLA_MODEL_VIEW_MATRIX,
                            Uniforms.VANILLA_PROJECTION_MATRIX,
                            Uniforms.of("ChunkOffset", 0.0F, 0.0F, 0.0F),
                            Uniforms.of("ColorModulator", 1.0F, 0.8F, 1.0F, 1.0F),
                            Uniforms.of("FogStart", 0.0F),
                            Uniforms.of("FogEnd", 1.0F),
                            Uniforms.of("FogColor", 0.0F, 0.0F, 0.0F, 0.0F)
                    )
                    .attributes(
                            Attributes.VANILLA_POSITION,
                            Attributes.VANILLA_COLOR,
                            Attributes.VANILLA_UV0,
                            Attributes.VANILLA_UV2,
                            Attributes.VANILLA_NORMAL
                    )
                    .fragment(ExampleShader.class)
                    .vertexIsPresent(true)
                    .samplers(
                            Samplers.SAMPLER0,
                            Samplers.SAMPLER2
                    )
                    .build();

            builder.component(coreShader);
        }
        return builder.build();
    }

    private static class GeneratorDemo implements ChunkGenerator {

        @Override
        public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {
            // Set chunk blocks
            for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++)
                for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                    List<Block> blocks = new ArrayList<>(Block.values());

                    Block randomBlock = blocks.get(random.nextInt(blocks.size()));

                    if (random.nextInt(50) == 1) {
                        batch.setBlock(x, 3, z, Block.REDSTONE_BLOCK);
                        batch.setBlock(x, 4, z, Block.TRIPWIRE);
                    }

                    if (chunkX == 0 && chunkZ == 0) {
                        batch.setBlock(x, 0, z, Block.STONE);
                    } else {
                        batch.setBlock(x, 0, z, randomBlock);
                    }
                }
        }
        @Override
        public void fillBiomes(Biome[] biomes, int chunkX, int chunkZ) {
            Arrays.fill(biomes, Biome.PLAINS);
        }
        @Override
        public List<ChunkPopulator> getPopulators() {
            return null;
        }
    }
}