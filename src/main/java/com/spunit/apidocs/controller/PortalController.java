package com.spunit.apidocs.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Portal Controller for API Documentation landing page
 */
@Slf4j
@Controller
@Hidden
public class PortalController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${api.info.version}")
    private String apiVersion;

    @GetMapping("/portal")
    public String portal(Model model) {
        model.addAttribute("applicationName", applicationName);
        model.addAttribute("apiVersion", apiVersion);
        model.addAttribute("swaggerUrl", "/swagger-ui.html");
        model.addAttribute("apiDocsUrl", "/v3/api-docs");

        log.info("Portal page accessed");

        // Return a simple redirect to Swagger UI for now
        return "redirect:/swagger-ui.html";
    }
}

