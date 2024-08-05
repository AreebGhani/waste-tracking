package org.aacounty.wastetrackingng.entity.serviceareas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ServiceAreasController {

    private final ServiceAreasRepository serviceAreaRepository;

    @Autowired
    public ServiceAreasController(ServiceAreasRepository serviceAreaRepository) {
        this.serviceAreaRepository = serviceAreaRepository;
    }

    @GetMapping("/serviceareas")
    public ResponseEntity<List<ServiceAreas>> getAll() {
        List<ServiceAreas> found = serviceAreaRepository.findAll();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping(value = "/serviceareas/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ServiceAreas> newServiceArea(@RequestBody ObjectNode bodyObject) throws Exception {
        try {
            String num = bodyObject.get("num").asText();
            ServiceAreas s = new ServiceAreas(num);
            serviceAreaRepository.save(s);
            return new ResponseEntity<>(s, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
