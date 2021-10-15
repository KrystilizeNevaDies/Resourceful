package org.krystilize.resourceful.util;

import org.jetbrains.annotations.NotNull;

public class NetworkingUtils {

    /**
     * Changes the port of the specified address, adds one if not found.
     *
     * @param address the address as a string
     * @param port the port as an int
     * @return the new address
     */
    public static @NotNull String changeAddressPort(@NotNull String address, int port) {
        if (address.contains(":")) {
            address = address.split("[:]")[0];
        }
        return address + ":" + port;
    }

    public static @NotNull String qualifyAddressWithHttp(@NotNull String address) {
        if (address.startsWith("http://")) {
            return address;
        }
        address = address.replaceFirst("https:[/][/]", "");
        return "http://" + address;
    }

    public static @NotNull String qualifyAddressWithHttps(@NotNull String address) {
        if (address.startsWith("https://")) {
            return address;
        }
        address = address.replaceFirst("http:[/][/]", "");
        return "https://" + address;
    }
}
