package org.softwire.training.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public class NewUserView extends View {
    private String error;

    public NewUserView(String error) {
        super("NewUserView.mustache", StandardCharsets.UTF_8);
        this.error = error;
    }

    public NewUserView() {
        this(null);
    }

    public String getError() {
        return error;
    }
}
