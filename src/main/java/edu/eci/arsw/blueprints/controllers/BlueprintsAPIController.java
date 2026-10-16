package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.ApiResponse;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/blueprints")
public class BlueprintsAPIController {

    private final BlueprintsServices services;

    public BlueprintsAPIController(BlueprintsServices services) { this.services = services; }

    // GET /blueprints
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ApiResponse.ok(ApiResponse("execute ok", Set()));
    }

    // GET /blueprints/{author}
    @GetMapping("/{author}")
    public ResponseEntity<ApiResponse> byAuthor(@PathVariable String author) {
        try {
            return ApiResponse.ok(ApiResponse("execute ok",Set(services));
        } catch (BlueprintNotFoundException e) {
            return ApiResponse.notFound(ApiResponse(e.getMessage(),null));
        }
    }

    // GET /blueprints/{author}/{bpname}
    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<ApiResponse> byAuthorAndName(@PathVariable String author, @PathVariable String bpname) {
        try {
            return ApiResponse.ok("execute ok",Set());
        } catch (BlueprintNotFoundException e) {
            return ApiResponse.notFound(e.getMessage(),null));
        }
    }

    // POST /blueprints
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody NewBlueprintRequest req) {
        try {
            Blueprint bp = new Blueprint(req.author(),   req.name(), req.points());
            services.addNewBlueprint(bp);
            return ApiResponse.created("Creado correctamente",);
        } catch (BlueprintPersistenceException e) {
            return ApiResponse.notFound(e.getMessage(),null));
        }
    }

    // PUT /blueprints/{author}/{bpname}/points
    @PutMapping("/{author}/{bpname}/points")
    public ResponseEntity<ApiResponse> addPoint(@PathVariable String author, @PathVariable String bpname,
                                      @RequestBody Point p) {
        try {
            services.addPoint(author, bpname, p.x(), p.y());
            return ApiResponse.accepted("Actulizacion exitosa",{"author": "john", "name": "house", "points": [...]}));
        } catch (BlueprintNotFoundException e) {
            return ApiResponse.notFound(e.getMessage(),null));
        }
    }

    public record NewBlueprintRequest(
            @NotBlank String author,
            @NotBlank String name,
            @Valid java.util.List<Point> points
    ) { }
}
