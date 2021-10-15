package org.krystilize.resourceful.shaders.type;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface CoreShaderType {

    /**
     * Blit copies one buffer to another, however this cannot be overridden in a resource pack.
     */
    CoreShaderType VANILLA_BLIT = () -> "blit_screen.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_BLOCK = () -> "block.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_NEW_ENTITY = () -> "new_entity.json";
    /**
     * All particles.
     */
    CoreShaderType VANILLA_ALL_PARTICLES = () -> "particle.json";
    /**
     * Top sky and stars in the overworld
     * Dark fog when the player is in the void
     * The highlight color when selecting a world.
     */
    CoreShaderType VANILLA_SKY_STARS_FOG = () -> "position.json";
    /**
     * Used for solid color-block, including
     * <p>
     * Sunrise and Sunset
     * Chunk border
     * Black background of spyglass overlay
     * Sleep overlay
     * Black background on UI elements
     * Text box in UI
     * Floating tooltip
     * Text background
     * Highlighting over item slots
     */
    CoreShaderType VANILLA_SOLID_COLOR_BLOCK = () -> "position_color.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_POSITION_COLOR_LIGHTMAP = () -> "position_color_lightmap.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_POSITION_COLOR_NORMAL = () -> "position_color_normal.json";
    /**
     * Suffocation overlay screen effect
     * On fire overlay
     * Distortion effect overlay for nausea effect when "Distortion Effects" less than 100%
     */
    CoreShaderType VANILLA_SUFFOCATION_FIRE_NAUSEA = () -> "position_color_tex.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_POSITION_COLOR_TEX_LIGHTMAP = () -> "position_color_tex_lightmap.json";
    /**
     * Used to render a texture
     * <p>
     * The sun and the moon
     * World border
     * Underwater screen effect
     * Spyglass texture overlay
     * Carved pumpkin overlay
     * Powder snow overlay
     * Portal overlay
     * Hotbar
     * CrossHair
     * Bossbar overlay
     * health bar, armor bar, food bar, air bar, jump bar, experience bar
     * Effect icons
     * Normal GUIs
     * Button in GUI
     * Game logo and mojang logo
     */
    CoreShaderType VANILLA_TEXTURE_RENDER = () -> "position_tex.json";
    /**
     * Skybox with the minecraft:the_end dimension effect.
     * Dirt background of GUIs
     */
    CoreShaderType VANILLA_END_SKY_GUI_BACKGROUND = () -> "position_tex_color.json";
    /**
     * Clouds.
     */
    CoreShaderType VANILLA_CLOUDS = () -> "position_tex_color_normal.json";
    /**
     * Unknown
     */
    CoreShaderType VANILLA_UNKNOWN_POSITION_TEX_LIGHTMAP_COLOR = () -> "position_tex_lightmap_color.json";
    /**
     * The beam of the beacon.
     */
    CoreShaderType VANILLA_BEACON_BEAM = () -> "rendertype_beacon_beam.json";
    /**
     * The block cracks when mining a block.
     */
    CoreShaderType VANILLA_BLOCK_CRACKS = () -> "rendertype_crumbling.json";
    /**
     * All non-cube-hitbox blocks.[verify]
     */
    CoreShaderType VANILLA_NON_CUBE_BLOCKS = () -> "rendertype_cutout.json";
    /**
     * Some blocks: grass blocks, iron bars, glass panes, tripwire hooks, hoppers, chains (leaves when using fancy or fabulous graphics)
     */
    CoreShaderType VANILLA_VARIOUS_BLOCKS = () -> "rendertype_cutout_mipped.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_END_GATEWAY = () -> "rendertype_end_gateway.json";
    /**
     * The inside of an end portal, as well as the insides of end gateways.
     */
    CoreShaderType VANILLA_END_PORTAL_INSIDE = () -> "rendertype_end_portal.json";
    /**
     * All solid blocks, lava, and when in fast mode, leaves.
     */
    CoreShaderType VANILLA_SOLID_BLOCKS = () -> "rendertype_solid.json";
    /**
     * Translucent blocks: water (still and flowing), ice, nether portal, stained and tinted glass, slime and honey, bubbles.
     */
    CoreShaderType VANILLA_TRANSLUCENT_BLOCKS = () -> "rendertype_translucent.json";
    /**
     * Blocks which are translucent and are being moved by a piston.
     */
    CoreShaderType VANILLA_TRANSLUCENT_MOVING_BLOCKS = () -> "rendertype_translucent_moving_block.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_TRANSLUCENT_NO_CRUMBLING = () -> "rendertype_translucent_no_crumbling.json";
    /**
     * Tripwire (sections connected to tripwire hooks are instead rendered completely solid
     */
    CoreShaderType VANILLA_TRIPWIRE_WIRE = () -> "rendertype_tripwire.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_WATER_MASK = () -> "rendertype_water_mask.json";
    /**
     * Charged Creeper swirling outline.
     */
    CoreShaderType VANILLA_CHARGED_CREEPER_OUTLINE = () -> "rendertype_energy_swirl.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ENTITY_ALPHA = () -> "rendertype_entity_alpha.json";
    /**
     * Items in the inventory, head (e.g. of armor stands) hand that are blocks (not flat textures)? Doesn’t seem to work with shulker boxes.
     */
    CoreShaderType VANILLA_ITEM_DISPLAY = () -> "rendertype_entity_cutout.json";
    /**
     * All entities and guardian beams.
     */
    CoreShaderType VANILLA_ENTITIES = () -> "rendertype_entity_cutout_no_cull.json";
    /**
     * Skulls on entities and as an item.
     */
    CoreShaderType VANILLA_SKULLS = () -> "rendertype_entity_cutout_no_cull_z_offset.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ENTITY_DECAL = () -> "rendertype_entity_decal.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ENTITY_GLINT = () -> "rendertype_entity_glint.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ENTITY_GLINT_DIRECT = () -> "rendertype_entity_glint_direct.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ENTITY_NO_OUTLINE = () -> "rendertype_entity_no_outline.json";
    /**
     * The blocks that are affected by an entity's shadow, but not the shadow itself.
     */
    CoreShaderType VANILLA_SHADOWED_BLOCKS = () -> "rendertype_entity_shadow.json";
    /**
     * End crystal beams.
     */
    CoreShaderType VANILLA_END_CRYSTAL_BEAMS = () -> "rendertype_entity_smooth_cutout.json";
    /**
     * Certain entities or entity-style objects: banners, shulker heads, books on lecterns/enchantment tables, shields, beds, bell part of bells, capes, shells of conduits, paintings, tridents, the ears on the Deadmau5 skin.
     * <p>
     * With item frames, the item frame entity itself is part of the shader, however items on it are not. Only non-filled in parts of maps placed on an item frame where the underlying frame is exposed are part of the shader.
     * <p>
     * Also includes the first person bottom skin layer.
     */
    CoreShaderType VANILLA_VARIOUS_ENTITY_OBJECTS = () -> "rendertype_entity_solid.json";
    /**
     * Translucent entities and parts of entities: slimes, players (all layers) along with player heads with custom skins on them, markings on horses, shulker bullets, elder guardian particle effect.
     */
    CoreShaderType VANILLA_TRANSLUCENT_ENTITIES = () -> "rendertype_entity_translucent.json";
    /**
     * Flat texture items held by entities (and in the inventory).
     */
    CoreShaderType VANILLA_FLAT_TEXTURE_ITEM_DISPLAY = () -> "rendertype_entity_translucent_cull.json";
    /**
     * A shader for the entire body of an entity that has glowing eyes (not the eyes themselves). This is not fully opaque.
     */
    CoreShaderType VANILLA_ENTITIES_WITH_GLOWING_EYES = () -> "rendertype_eyes.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_ITEM_ENTITY_TRANSLUCENT_CULL = () -> "rendertype_item_entity_translucent_cull.json";
    /**
     * A leash on an entity. Applies when on a fence too.
     */
    CoreShaderType VANILLA_LEASHES = () -> "rendertype_leash.json";
    /**
     * Glowing effect on entities. This buffer only draws the already-outlined and semi-transparent glowing, not
     * covering the entire entity like pre-snapshot.
     */
    CoreShaderType VANILLA_GLOWING_EFFECT = () -> "rendertype_outline.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_GLINT = () -> "rendertype_glint.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_GLINT_DIRECT = () -> "rendertype_glint_direct.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_GLINT_TRANSLUCENT = () -> "rendertype_glint_translucent.json";
    /**
     * Unknown.
     */
    CoreShaderType VANILLA_UNKNOWN_LIGHTNING = () -> "rendertype_lightning.json";
    /**
     * The outline when hovering over a block, as well as the debug crosshair and hitboxes.
     */
    CoreShaderType VANILLA_LINES = () -> "rendertype_lines.json";
    /**
     * All parts of text, including the shadow. This encompasses all text rendered including: F3 Menu, Menu button text,
     * Entity names, Item names, descriptions & amounts in the inventory and the Chat etc.
     * <p>
     * Also includes the filled in parts of maps placed in item frames and the entirety of non-empty map items held in
     * hand.
     */
    CoreShaderType VANILLA_TEXT_AND_SHADER = () -> "rendertype_text.json";
    /**
     * The background nameplate of an entity’s custom name.
     */
    CoreShaderType VANILLA_ENTITY_CUSTOM_NAME_NAMEPLATE = () -> "rendertype_text_see_through.json";

    @NotNull String file();

}