package org.acme.domain;

import java.util.UUID;

public record Client(UUID userId, Long secretCode, Long cartNumber, String address) {

}