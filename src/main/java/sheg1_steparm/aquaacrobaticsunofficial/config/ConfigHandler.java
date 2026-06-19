package sheg1_steparm.aquaacrobaticsunofficial.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sheg1_steparm.aquaacrobaticsunofficial.Tags;
import sheg1_steparm.aquaacrobaticsunofficial.biome.BiomeWaterFogColors;
import sheg1_steparm.aquaacrobaticsunofficial.client.handler.FogHandler;

@Config(modid = Tags.MOD_ID)
@Mod.EventBusSubscriber
public class ConfigHandler {
    @Config.Name("Push Player Out Of Blocks")
    @Config.Comment({"STANDARD - The player will occasionally be pushed out of certain spaces. Collisions are evaluated for full cubes only, non-full cubes are ignored. This is the default behavior up to Minecraft 1.12.", "APPROXIMATE - The player can move into more spaces, but will still be pushed out of some. Collisions are evaluated for full cubes only, non-full cubes are ignored.", "EXACT - The player can move into all spaces as expected. Collisions are evaluated for all types of cubes. This is the default behavior in Minecraft 1.13 and onwards."})
    public static PlayerBlockCollisions playerBlockCollisions = PlayerBlockCollisions.APPROXIMATE;

    public static class MovementConfig {
        @Config.Name("Easy Elytra Takeoff")
        @Config.Comment("Taking off with an elytra from the ground is now far easier like in Minecraft 1.15 and onwards.")
        public static boolean easyElytraTakeoff = true;

        @Config.Name("No Double Tap Sprinting")
        @Config.Comment("Prevent sprinting from being triggered by double tapping the walk forward key.")
        public static boolean noDoubleTapSprinting = false;

        @Config.Name("Sideways Sprinting")
        @Config.Comment("Enables sprinting to the left and right.")
        public static boolean sidewaysSprinting = false;

        @Config.Name("Sideways Swimming")
        @Config.Comment("Enables swimming to the left and right.")
        public static boolean sidewaysSwimming = false;

        @Config.Name("Enable Crawling")
        @Config.Comment("Enables crawling to prevent suffocation. Note that if you disable this there will probably be behavioral differences from 1.13.")
        public static boolean enableCrawling = true;

        @Config.Name("Enable Toggle Crawling")
        @Config.Comment("Enables a keybind to toggle crawling.")
        public static boolean enableToggleCrawling = false;

        @Config.Name("New Projectile Behavior")
        @Config.Comment("Modify projectile behavior to be closer to that of newer versions (fixes MC-73884 and allows bubble columns to work with ender pearls).")
        public static boolean newProjectileBehavior = false;

        @Config.Name("New Climbing Behavior")
        @Config.Comment("Allow climbing vines and climbing by pressing jump.")
        public static boolean newClimbingBehavior = false;
    }

    public static class BlocksConfig {
        @Config.Name("Brighter Water")
        @Config.Comment("Make water only reduce light level by 1 per Y-level, instead of 3.")
        public static boolean brighterWater = true;

        @Config.Name("New Water")
        @Config.Comment("Use the new water rendering in 1.13+.")
        public static boolean newWaterColors = true;

        @Config.Name("New Water Fog")
        @Config.Comment("Use the new fog rendering in 1.13+.")
        public static boolean newWaterFog = true;
    }

    public static class MiscellaneousConfig {
        @Config.Name("Replenish Air Slowly")
        @Config.Comment("Replenish air slowly when out of water instead of immediately.")
        public static boolean slowAirReplenish = false;

        @Config.Name("Sneaking Dismounts Parrots")
        @Config.Comment("Parrots no longer leave the players shoulders as easily, instead the player needs to press the sneak key.")
        public static boolean sneakingForParrots = true;

        @Config.Name("Eating Animation")
        @Config.Comment("Animate eating in third-person view.")
        public static boolean eatingAnimation = true;

        @Config.Name("Bubble Columns")
        @Config.Comment("Enable bubble columns.")
        public static boolean bubbleColumns = false;

        @Config.Name("Custom Biome Water Colors")
        @Config.Comment("Allows overriding the water and fog colors for a biome. Specify each entry like this (without quotes) - 'modname:biome,color,fogcolor'")
        public static String[] customBiomeWaterColors = new String[]{};

        @Config.Name("WorldProvider Fog Blacklist")
        @Config.Comment("List of WorldProviders in which fog should be disabled.")
        public static String[] providerFogBlacklist = new String[]{"thebetweenlands.common.world.WorldProviderBetweenlands"};

        @Config.Name("Floating Items")
        @Config.Comment("Whether or not items should float in water like in 1.13+.")
        public static boolean floatingItems = true;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent evt) {
        if (evt.getModID().equals(Tags.MOD_ID)) {
            ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        }
        BiomeWaterFogColors.recomputeColors();
        FogHandler.recomputeBlacklist();
    }

    public enum PlayerBlockCollisions {
        STANDARD, APPROXIMATE, EXACT
    }
}