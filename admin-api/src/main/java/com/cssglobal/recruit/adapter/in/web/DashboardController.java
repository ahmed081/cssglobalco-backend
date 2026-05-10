package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.response.DashboardResponse;
import com.cssglobal.recruit.application.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService service;

    @GetMapping
    public DashboardResponse get() {
        return service.get();
    }
}
