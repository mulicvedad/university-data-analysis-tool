package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.models.BaseModel;
import ba.unsa.etf.bp.udat.services.BaseService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;

public abstract class BaseController<M extends BaseModel, S extends BaseService<M, ? >> {
    protected S service;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    public Iterable<M> all() {
        return service.all();
    }

    @Transactional
    public ResponseEntity create(@RequestBody @Valid M newModel, @RequestHeader("Authorization") String token) throws ServiceException {
        M model = service.save(newModel);


        return ResponseEntity.ok(model);
    }

    public ResponseEntity get(@PathVariable("id") Long id) throws ServiceException {
        return ResponseEntity.ok(service.get(id));
    }

    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) throws ServiceException {

        service.delete(id);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity getPage(@PathVariable("pageNumber") int pageNumber) {
        Pageable page = new PageRequest(pageNumber - 1, 5);

        return ResponseEntity.ok(service.listAllByPage(page));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity serviceExHandler(Exception e) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("error", "Bad request")
                .add("message", e.getMessage());

        JsonObject responseObj = objectBuilder.build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObj.toString());
    }
}
