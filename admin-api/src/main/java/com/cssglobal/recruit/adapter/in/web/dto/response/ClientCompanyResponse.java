package com.cssglobal.recruit.adapter.in.web.dto.response;

import java.time.Instant;
import java.util.UUID;

public record ClientCompanyResponse(UUID id, String name, String contactName, String contactEmail, String phone,
                                    String logoUrl, String city, String country, boolean active, Instant createdOn,
                                    Instant updatedOn) {
}
