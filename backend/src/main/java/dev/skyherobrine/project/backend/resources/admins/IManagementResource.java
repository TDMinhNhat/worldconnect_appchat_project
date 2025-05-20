package dev.skyherobrine.project.backend.resources.admins;

import dev.skyherobrine.project.backend.models.Response;
import org.springframework.http.ResponseEntity;

public interface IManagementResource<S, P> {
    ResponseEntity<Response> getAll(Integer page, Integer size);

    ResponseEntity<Response> getById(Long id);

    ResponseEntity<Response> add(S s);

    ResponseEntity<Response> update(P p, S s);

    ResponseEntity<Response> delete(P p);
}
