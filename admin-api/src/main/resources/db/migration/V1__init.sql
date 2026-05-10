create table app_users
(
    id               uuid primary key,
    keycloak_user_id varchar(80)              not null unique,
    email            varchar(160)             not null unique,
    first_name       varchar(80),
    last_name        varchar(80),
    phone            varchar(30),
    role             varchar(30)              not null,
    language         varchar(8)               not null,
    active           boolean                  not null,
    created_by       varchar(120),
    created_on       timestamp with time zone not null,
    updated_by       varchar(120),
    updated_on       timestamp with time zone,
    version          bigint                   not null
);
create table client_companies
(
    id            uuid primary key,
    name          varchar(160)             not null,
    contact_name  varchar(160),
    contact_email varchar(160),
    phone         varchar(40),
    logo_url      varchar(500),
    city          varchar(120),
    country       varchar(120),
    active        boolean                  not null,
    created_by    varchar(120),
    created_on    timestamp with time zone not null,
    updated_by    varchar(120),
    updated_on    timestamp with time zone,
    version       bigint                   not null
);
create table job_roles
(
    id                  uuid primary key,
    title               varchar(180)             not null,
    department          varchar(80)              not null,
    client_company_id   uuid references client_companies (id),
    location            varchar(180),
    seniority           varchar(30)              not null,
    status              varchar(30)              not null,
    priority            varchar(30)              not null,
    contract_type       varchar(30)              not null,
    work_mode           varchar(30)              not null,
    openings            int,
    deadline            date,
    salary_min          numeric(19, 2),
    salary_max          numeric(19, 2),
    description         varchar(4000),
    required_skills     varchar(1500),
    nice_to_have_skills varchar(1500),
    owner_id            uuid references app_users (id),
    created_by          varchar(120),
    created_on          timestamp with time zone not null,
    updated_by          varchar(120),
    updated_on          timestamp with time zone,
    version             bigint                   not null
);
create table job_role_languages
(
    job_role_id   uuid not null references job_roles (id) on delete cascade,
    language_code varchar(8)
);
create table candidates
(
    id                  uuid primary key,
    first_name          varchar(80)              not null,
    last_name           varchar(80)              not null,
    email               varchar(160),
    phone               varchar(40),
    linkedin_url        varchar(500),
    location            varchar(180),
    source              varchar(100),
    stage               varchar(40)              not null,
    status              varchar(40)              not null,
    role                varchar(80)              not null,
    seniority           varchar(30)              not null,
    rating              int,
    years_of_experience int,
    salary_expectation  numeric(19, 2),
    availability_date   date,
    current_company     varchar(180),
    skills              varchar(2000),
    cv_url              varchar(500),
    notes               varchar(4000),
    tags                varchar(120),
    avatar_url          varchar(500),
    job_role_id         uuid references job_roles (id),
    owner_id            uuid references app_users (id),
    created_by          varchar(120),
    created_on          timestamp with time zone not null,
    updated_by          varchar(120),
    updated_on          timestamp with time zone,
    version             bigint                   not null
);
create table candidate_languages
(
    candidate_id  uuid not null references candidates (id) on delete cascade,
    language_code varchar(8)
);
create table candidate_stage_history
(
    id           uuid primary key,
    candidate_id uuid                     not null references candidates (id) on delete cascade,
    from_stage   varchar(40),
    to_stage     varchar(40)              not null,
    comment      varchar(1000),
    changed_at   timestamp with time zone not null,
    created_by   varchar(120),
    created_on   timestamp with time zone not null,
    updated_by   varchar(120),
    updated_on   timestamp with time zone,
    version      bigint                   not null
);
create table candidate_notes
(
    id           uuid primary key,
    candidate_id uuid                     not null references candidates (id) on delete cascade,
    visibility   varchar(30)              not null,
    content      varchar(4000)            not null,
    created_by   varchar(120),
    created_on   timestamp with time zone not null,
    updated_by   varchar(120),
    updated_on   timestamp with time zone,
    version      bigint                   not null
);
create table interviews
(
    id           uuid primary key,
    candidate_id uuid                     not null references candidates (id) on delete cascade,
    starts_at    timestamp with time zone not null,
    ends_at      timestamp with time zone not null,
    title        varchar(180),
    meeting_url  varchar(500),
    feedback     varchar(1200),
    created_by   varchar(120),
    created_on   timestamp with time zone not null,
    updated_by   varchar(120),
    updated_on   timestamp with time zone,
    version      bigint                   not null
);
create table activity_logs
(
    id          uuid primary key,
    type        varchar(60)              not null,
    target_type varchar(80)              not null,
    target_id   varchar(80)              not null,
    message     varchar(1500),
    created_by  varchar(120),
    created_on  timestamp with time zone not null,
    updated_by  varchar(120),
    updated_on  timestamp with time zone,
    version     bigint                   not null
);
create index idx_candidate_email on candidates (email);
create index idx_candidate_stage on candidates (stage);
