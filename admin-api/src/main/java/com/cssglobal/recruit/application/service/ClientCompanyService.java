package com.cssglobal.recruit.application.service;

import com.cssglobal.recruit.adapter.in.web.dto.request.ClientCompanyRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.ClientCompanyResponse;
import com.cssglobal.recruit.adapter.out.persistence.ClientCompanyRepository;
import com.cssglobal.recruit.common.ApiException;
import com.cssglobal.recruit.domain.model.ClientCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientCompanyService {
    private final ClientCompanyRepository repo;

    @Transactional(readOnly = true)
    public Page<ClientCompanyResponse> search(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public ClientCompanyResponse get(UUID id) {
        return toResponse(find(id));
    }

    @Transactional
    public ClientCompanyResponse create(ClientCompanyRequest r) {
        ClientCompany c = new ClientCompany();
        apply(c, r);
        return toResponse(repo.save(c));
    }

    @Transactional
    public ClientCompanyResponse update(UUID id, ClientCompanyRequest r) {
        ClientCompany c = find(id);
        apply(c, r);
        return toResponse(repo.save(c));
    }

    @Transactional
    public void delete(UUID id) {
        repo.delete(find(id));
    }

    private ClientCompany find(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Client company not found"));
    }

    private void apply(ClientCompany c, ClientCompanyRequest r) {
        c.setName(r.name());
        c.setContactName(r.contactName());
        c.setContactEmail(r.contactEmail());
        c.setPhone(r.phone());
        c.setLogoUrl(r.logoUrl());
        c.setCity(r.city());
        c.setCountry(r.country());
        if (r.active() != null) c.setActive(r.active());
    }

    private ClientCompanyResponse toResponse(ClientCompany c) {
        return new ClientCompanyResponse(c.getId(), c.getName(), c.getContactName(), c.getContactEmail(), c.getPhone(), c.getLogoUrl(), c.getCity(), c.getCountry(), c.isActive(), c.getCreatedOn(), c.getUpdatedOn());
    }
}
