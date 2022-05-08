create table if not exists "user"
(
    id         bigserial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    email      varchar(200),
    image_url  varchar,
    role       varchar(100),
    created_at timestamp
);

create table if not exists model
(
    id         bigserial primary key,
    name       varchar(100),
    author_id  bigint,
    image_url  varchar,
    created_at timestamp,
    constraint user_fk
        foreign key (author_id)
            references "user" (id)
);

create table if not exists post
(
    id         bigserial primary key,
    title      varchar(200),
    content    text,
    deleted    boolean,
    author_id  bigint,
    created_at timestamp,
    constraint user_fk
        foreign key (author_id)
            references "user" (id)
);

create table if not exists comment
(
    id        bigserial primary key,
    content   varchar,
    deleted   boolean,
    author_id bigint,
    post_id   bigint,
    constraint user_fk
        foreign key (author_id)
            references "user" (id),
    constraint post_fk
        foreign key (post_id)
            references post (id)
)



