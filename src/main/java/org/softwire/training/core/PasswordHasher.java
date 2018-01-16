package org.softwire.training.core;

import com.muquit.libsodiumjna.SodiumLibrary;
import com.muquit.libsodiumjna.exceptions.SodiumLibraryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * There is no need to include a salt as this is done for you by the libsodium implementation
 * https://download.libsodium.org/doc/password_hashing/the_argon2i_function.html
 */
public class PasswordHasher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHasher.class);

    private static final String LIB_LIBSODIUM_DLL = "lib/libsodium.dll";

    public PasswordHasher() {
        SodiumLibrary.setLibraryPath(LIB_LIBSODIUM_DLL);
        SodiumLibrary.sodium().sodium_init();
        LOGGER.info(MessageFormat.format(
                "libsodium library version: {0}",
                SodiumLibrary.libsodiumVersionString()));
    }

    public String hash(
            String password) throws SodiumLibraryException {
        return SodiumLibrary.cryptoPwhashStr(password.getBytes(StandardCharsets.UTF_8));
    }

    public boolean verify(
            String password,
            String hashedPassword) {
        return SodiumLibrary.cryptoPwhashStrVerify(
                hashedPassword, password.getBytes(StandardCharsets.UTF_8));
    }
}
