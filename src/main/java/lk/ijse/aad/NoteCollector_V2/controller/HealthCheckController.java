package lk.ijse.aad.NoteCollector_V2.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String HealthTest(){
        return "Note Controller is Working";
    }
}
