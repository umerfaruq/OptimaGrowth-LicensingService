package com.optimagrowth.licensingservice.license.controller;

import com.optimagrowth.licensingservice.license.model.License;
import com.optimagrowth.licensingservice.license.service.LicenseService;
import java.util.Locale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService service;

    public LicenseController(LicenseService service) {
        this.service = service;
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
        @PathVariable("organizationId") String organizationId,
        @PathVariable("licenseId") String licenseId
    ) {
        var license = service.getLicense(organizationId, licenseId);
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> updateLicense(
        @PathVariable("organizationId") String organizationId,
        @RequestHeader(value = "Accept-Language", required = false) Locale locale,
        @RequestBody License request
    ) {
        return ResponseEntity.ok(service.createLicense(request, organizationId, locale));
    }

    @PutMapping
    public ResponseEntity<String> createLicense(
        @PathVariable("organizationId") String organizationId,
        @RequestHeader(value = "Accept-Language", required = false) Locale locale,
        @RequestBody License request
    ) {
        return ResponseEntity.ok(service.updateLicense(request, organizationId, locale));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
        @PathVariable("organizationId") String organizationId,
        @PathVariable("licenseId") String licenseId,
        @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(service.deleteLicense(licenseId, organizationId, locale));
    }
}
