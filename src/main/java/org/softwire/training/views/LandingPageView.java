package org.softwire.training.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public class LandingPageView extends View {
    public LandingPageView() {
        super("LandingPageView.mustache", StandardCharsets.UTF_8);
    }
}
