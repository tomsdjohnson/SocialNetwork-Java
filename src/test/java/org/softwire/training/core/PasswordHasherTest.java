package org.softwire.training.core;

import com.muquit.libsodiumjna.exceptions.SodiumLibraryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordHasherTest {

    private final PasswordHasher passwordHasher = new PasswordHasher();

    @Test
    public void roundTripPasswordHashing() throws SodiumLibraryException {
        String password = "password";

        String hashedPassword = passwordHasher.hash(password);

        assertTrue(passwordHasher.verify(password, hashedPassword));
    }

    @Test
    public void failOnIncorrectPassword() throws SodiumLibraryException {
        String hashedPassword = passwordHasher.hash("password");

        assertFalse(passwordHasher.verify("incorrect", hashedPassword));
    }
}
