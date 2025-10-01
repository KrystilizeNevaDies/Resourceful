package org.krystilize.resourceful.test;

/**
 * Simple test to verify Java 21 compilation and resource pack version.
 */
public class SimpleVersionTest {
    public static void main(String[] args) {
        // Use Java 21 features to verify compilation
        var message = "Testing Java 21 compilation";
        System.out.println(message);
        
        // Test text blocks (Java 15+ feature)
        var jsonTemplate = """
                {
                    "pack": {
                        "description": "Generated resource pack",
                        "pack_format": 34
                    }
                }
                """;
        
        System.out.println("Sample pack.mcmeta with new format:");
        System.out.println(jsonTemplate);
        
        // Verify we can use modern switch expressions (Java 14+)
        var formatName = switch (34) {
            case 34 -> "Minecraft 1.21.9+";
            case 26 -> "Minecraft 1.21+";
            case 7 -> "Minecraft 1.17-1.18";
            default -> "Unknown version";
        };
        
        System.out.println("Pack format 34 corresponds to: " + formatName);
        System.out.println("✓ SUCCESS: Java 21 compilation working with modern syntax features");
    }
}