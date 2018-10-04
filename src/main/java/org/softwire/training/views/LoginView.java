package org.softwire.training.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public class LoginView extends View {
    public LoginView() {
        super("LoginUser.mustache", StandardCharsets.UTF_8);
    }
}
