package com.controller.exeption;

import lombok.*;

@Builder
public record ResponseTemplate(boolean valid, String message, String warning, String moreInfoUrl) {

}
