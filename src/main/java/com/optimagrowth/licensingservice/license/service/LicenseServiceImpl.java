package com.optimagrowth.licensingservice.license.service;

import com.optimagrowth.licensingservice.license.model.License;
import java.util.Locale;
import java.util.Random;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final MessageSource messages;

    public LicenseServiceImpl(MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public License getLicense(String organizationId, String licenseId) {
        return License
            .builder()
            .id(new Random().nextInt(1000))
            .licenseId(licenseId)
            .description("Software Product")
            .organizationId(organizationId)
            .productName("Ostock")
            .licenseType("full")
            .build();
    }

    @Override
    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage =
                String.format(
                    messages.getMessage("license.create.message", null, locale),
                    license.toString()
                );
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage =
                String.format(
                    messages.getMessage("license.update.message", null, locale),
                    license.toString()
                );
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        String responseMessage = null;
        if (licenseId != null) {
            responseMessage =
                String.format(
                    messages.getMessage("license.delete.message", null, locale),
                    licenseId,
                    organizationId
                );
        }
        return responseMessage;
    }
}
