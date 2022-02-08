package com.gis.medfind.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.gis.medfind.Forms.RequestForm;
import com.gis.medfind.entity.Request;
import com.gis.medfind.serviceImplem.FileStorageServiceImpl;
import com.gis.medfind.serviceImplem.RequestHandlingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

    @Autowired
    public RequestHandlingServiceImpl requestService;

    @Autowired
    public FileStorageServiceImpl fileService;

    @Autowired
    public RequestForm requestForm;

    @ModelAttribute(name="RequestForm")
    public RequestForm requestForm(){
        return requestForm;
    }
 
    @GetMapping("/sendRequest")
    public String sendRequest(){
        return "sendRequest";
    }



    @PostMapping("/sendRequest")
    public String processRequest(@Valid @ModelAttribute RequestForm request, Errors errors, Model models) throws IOException {
                
        if (errors.hasErrors()){
            models.addAttribute("requestFailed", true);
            return "sendRequest";
        }   
        request.toRequest(requestService,fileService);
        models.addAttribute("requestSuccess", true);
        return "sendRequest";
    }



    @GetMapping("/handle_request")
    public String handleRequest(Model model){
        List<Request> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        
        return "validator";
    }
    @PostMapping("handle_request/approve")
    public String approveRequest(@RequestParam String requestId){
        requestService.acceptRequest(Long.parseLong(requestId));
        return "validator";
    }
    @PostMapping("handle_request/reject")
    public String rejectRequest(String requestId){
        requestService.rejectRequest(Long.parseLong(requestId));
        return "validator";
    }

    
}
