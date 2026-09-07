package org.example;

import java.util.List;

public record StorageState(List<Cookie> cookies, List<Origin> origins) {

    public record Cookie() {
    }

    public record Origin(
            String origin,
            List<LocalStorageEntry> localStorage
    ) {}

    public record LocalStorageEntry(
            String name,
            String value
    ) {}
}
