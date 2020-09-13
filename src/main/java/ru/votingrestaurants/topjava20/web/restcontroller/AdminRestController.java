package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.service.AdminService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL_ADMINS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminRestController.class);
    public static final String REST_URL_ADMINS = "/rest/admins";

    @Autowired
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        LOG.info("amin id {}", admin);
//        checkNew(admin);
        Admin createdAdmin = adminService.create(admin);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_ADMINS + "/{id}")
                .buildAndExpand(createdAdmin.getId()).toUri();
        return ResponseEntity.created(ofNewResource).body(createdAdmin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleted(@PathVariable int id) {
        LOG.info("delete admin with id{}", id);
        adminService.delete(id);
    }

    @GetMapping("/{id}")
    public Admin get(@PathVariable int id){
        LOG.info("get admin with id{}", id);
        return adminService.get(id);
    }

    @GetMapping
    public List<Admin> getAll() {
        LOG.info("getAllAdmins");
        return adminService.getAll();
    }
}
