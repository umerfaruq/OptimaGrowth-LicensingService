package com.optimagrowth.licensingservice.license.service;

import com.optimagrowth.licensingservice.license.model.License;
import java.util.Locale;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId);

    String createLicense(License license, String organizationId, Locale locale);

    String updateLicense(License license, String organizationId, Locale locale);

    String deleteLicense(String licenseId, String organizationId, Locale locale);
}
