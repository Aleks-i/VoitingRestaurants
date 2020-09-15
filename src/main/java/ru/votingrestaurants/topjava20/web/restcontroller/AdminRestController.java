package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.service.AdminService;

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

    @GetMapping("/{id}")
    public Admin getAmin(@PathVariable int id){
        LOG.info("get admin with id{}", id);
        return adminService.getAdmin(id);
    }

    @GetMapping
    public List<Admin> getAll() {
        LOG.info("getAllAdmins");
        return adminService.getAll();
    }
}
