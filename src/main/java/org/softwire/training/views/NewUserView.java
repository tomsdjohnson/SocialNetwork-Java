package org.softwire.training.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public class NewUserView extends View {
    public NewUserView() {
        super("NewUserView.mustache", StandardCharsets.UTF_8);
    }
}
