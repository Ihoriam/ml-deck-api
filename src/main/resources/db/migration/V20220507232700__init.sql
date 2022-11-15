create table if not exists "user"
(
    id         bigserial primary key,
    username   varchar(100),
    email      varchar(200),
    password   varchar(100),
    emoji      varchar(20),
    created_at timestamp,
    deleted    boolean
);

create table if not exists model
(
    id                   bigserial primary key,
    name                 varchar(128),
    category             varchar(64),
    description          text,
    docker_hub_image_url varchar,
    created_by           bigint,
    created_at           timestamp,
    updated_by           bigint,
    updated_at           timestamp,
    endorsement_count    integer check ( endorsement_count >= 0 ),
    deleted              boolean,
    constraint created_by_fk
        foreign key (created_by)
            references "user" (id),
    constraint updated_by_fk
        foreign key (updated_by)
            references "user" (id)
);

create table if not exists role
(
    id      serial primary key,
    name    varchar(100),
    deleted boolean
);

create table if not exists user_role
(
    user_id bigint,
    role_id int
);



