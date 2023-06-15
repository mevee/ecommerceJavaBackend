package com.example.todoapp.controller;

import com.example.todoapp.model.GenericResponse;
import com.example.todoapp.model.response.user.JavaAuthTokenResponse;
import com.example.todoapp.repository.repo.UserRepository;
import com.example.todoapp.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.todoapp.util.AppConstants.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    //    private static final Logger LOG = Logger.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    ResponseEntity<GenericResponse> register(@RequestBody JavaAuthTokenResponse request) {

        GenericResponse response = CommonUtils.getSuccessResponse(null);

        System.out.println("---------------Check register request---------------");
        log.info("-----REQUEST "+request);

        if (request == null || request.getEmail() == null || request.getPassword() == null || request.getfName() == null) {
            response.getMeta().setMessageCode(ERR400);
            response.getMeta().setStatus(ERR400);
            response.getMeta().setMessageDescription(BAD_CREDENTIALS);
        } else {
            boolean isSavedSuccess = false;
            try {
                isSavedSuccess = userRepository.register(request);
            } catch (Exception e) {
                isSavedSuccess = false;
                response.getMeta().setStatus(ERR400);
                response.getMeta().setMessageCode(ERR400);
                response.getMeta().setMessageDescription(e.getMessage());
            }
            if (isSavedSuccess) {
                response.getMeta().setStatus(SUCCESS);
                response.getMeta().setMessageCode(SUCCESS_CODE);
                response.getMeta().setMessageDescription(SUCCESS_MSG);
            }
        }

        System.out.println("------------return bad request or bad parameter response--------------");
        System.out.println("------------" + response.toString() + "------------");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
