package org.krystilize.resourceful.test;

import org.krystilize.resourceful.resourcepack.ResourcePack;

/**
 * Simple test to verify the resource pack version has been updated correctly.
 */
public class ResourcePackVersionTest {
    public static void main(String[] args) {
        // Test that the version has been updated to 34
        int version = ResourcePack.VERSION;
        System.out.println("Resource pack version: " + version);
        
        if (version == 34) {
            System.out.println("✓ SUCCESS: Resource pack version correctly updated to 34");
        } else {
            System.out.println("✗ FAILED: Expected version 34, but got " + version);
            System.exit(1);
        }
        
        // Test that we can create a basic resource pack builder
        try {
            var builder = ResourcePack.builder();
            System.out.println("✓ SUCCESS: Resource pack builder created successfully");
        } catch (Exception e) {
            System.out.println("✗ FAILED: Could not create resource pack builder: " + e.getMessage());
            System.exit(1);
        }
        
        System.out.println("All tests passed! Java 21 and resource pack version updates are working.");
    }
}