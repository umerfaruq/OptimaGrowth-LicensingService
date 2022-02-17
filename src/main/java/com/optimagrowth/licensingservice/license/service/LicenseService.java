package com.optimagrowth.licensingservice.license.service;

import com.optimagrowth.licensingservice.license.model.License;
import java.util.Locale;

public interface LicenseService {
    License getLicense(String organizationId, String licenseI);

    License createLicense(License license);

    License updateLicense(License license);

    String deleteLicense(String licenseId, Locale locale);
}
