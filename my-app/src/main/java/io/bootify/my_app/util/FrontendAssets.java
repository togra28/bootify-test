package io.bootify.my_app.util;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


/**
 * Resolve production assets from the build manifest, loaded on first use.
 */
public class FrontendAssets {

    private static final JsonNode MANIFEST = loadManifest();

    public static String getJsPath() {
        return "/" + MANIFEST.required("src/main/resources/js/app.js").required("file").asString();
    }

    public static String getCssPath() {
        return "/" + MANIFEST.required("style.css").required("file").asString();
    }

    private static JsonNode loadManifest() {
        try {
            return new ObjectMapper().readTree(new ClassPathResource("/static/.vite/manifest.json", FrontendAssets.class).getContentAsByteArray());
        } catch (final IOException exception) {
            throw new IllegalStateException("Cannot read frontend asset manifest. Run the frontend build first.", exception);
        }
    }

}
