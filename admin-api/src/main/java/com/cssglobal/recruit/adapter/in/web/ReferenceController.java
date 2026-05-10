package com.cssglobal.recruit.adapter.in.web;

import com.cssglobal.recruit.adapter.in.web.dto.response.EnumsResponse;
import com.cssglobal.recruit.domain.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/reference")
public class ReferenceController {
    private static List<String> names(Enum<?>[] values) {
        return Arrays.stream(values).map(Enum::name).toList();
    }

    @GetMapping("/enums")
    public EnumsResponse enums() {
        return new EnumsResponse(names(CandidateStage.values()), names(SeniorityLevel.values()), names(JobStatus.values()), names(JobPriority.values()), names(LanguageCode.values()), names(ContractType.values()), names(WorkMode.values()), names(NoteVisibility.values()));
    }
}
