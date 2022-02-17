package com.optimagrowth.licensingservice.license.service;

import com.optimagrowth.licensingservice.license.config.ServiceConfig;
import com.optimagrowth.licensingservice.license.model.License;
import com.optimagrowth.licensingservice.license.repository.LicenseRepository;
import java.util.Locale;
import java.util.UUID;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final MessageSource messages;
    private final LicenseRepository repository;
    private final ServiceConfig config;

    public LicenseServiceImpl(
        MessageSource messages,
        LicenseRepository repository,
        ServiceConfig config
    ) {
        this.messages = messages;
        this.repository = repository;
        this.config = config;
    }

    @Override
    public License getLicense(String organizationId, String licenseId) {
        var license = repository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException(
                String.format(
                    messages.getMessage("license.search.error.message", null, null),
                    licenseId,
                    organizationId
                )
            );
        }
        return license.withComment(config.getProperty());
    }

    @Override
    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        repository.save(license);
        return license.withComment(config.getProperty());
    }

    @Override
    public License updateLicense(License license) {
        repository.save(license);
        return license.withComment(config.getProperty());
    }

    @Override
    public String deleteLicense(String licenseId, Locale locale) {
        String responseMessage = null;
        var license = License.builder().licenseId(licenseId).build();

        repository.delete(license);

        responseMessage =
            String.format(messages.getMessage("license.delete.message", null, locale), licenseId);

        return responseMessage;
    }
}
