package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.request.ClientCompanyRequest;
import com.cssglobal.recruit.adapter.in.web.dto.response.ClientCompanyResponse;
import com.cssglobal.recruit.application.service.ClientCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientCompanyController {
    private final ClientCompanyService service;

    @GetMapping
    public Page<ClientCompanyResponse> search(Pageable pageable) {
        return service.search(pageable);
    }

    @GetMapping("/{id}")
    public ClientCompanyResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping
    public ClientCompanyResponse create(@Valid @RequestBody ClientCompanyRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ClientCompanyResponse update(@PathVariable UUID id, @Valid @RequestBody ClientCompanyRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
