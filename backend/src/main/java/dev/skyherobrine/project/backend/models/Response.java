package dev.skyherobrine.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Response {
    private int code;
    private String message;
    private Object data;
}
