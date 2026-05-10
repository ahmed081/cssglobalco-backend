package com.cssglobal.recruit.adapter.in.web.dto.response;

import java.util.List;

public record EnumsResponse(List<String> candidateStages, List<String> seniorityLevels, List<String> jobStatuses,
                            List<String> jobPriorities, List<String> languages, List<String> contractTypes,
                            List<String> workModes, List<String> noteVisibilities) {
}
