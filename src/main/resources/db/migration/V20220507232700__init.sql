create table if not exists "user"
(
    id         bigserial primary key,
    username   varchar(100),
    email      varchar(200),
    password   varchar(100),
    image_url  varchar,
    created_at timestamp,
    deleted    boolean
);

create table if not exists model
(
    id         bigserial primary key,
    name       varchar(100),
    author_id  bigint,
    category   varchar(30),
    image_url  varchar,
    created_at timestamp,
    deleted    boolean,
    constraint user_fk
        foreign key (author_id)
            references "user" (id)
);

create table if not exists post
(
    id         bigserial primary key,
    title      varchar(200),
    content    text,
    author_id  bigint,
    created_at timestamp,
    deleted    boolean,
    constraint user_fk
        foreign key (author_id)
            references "user" (id)
);

create table if not exists comment
(
    id        bigserial primary key,
    content   varchar,
    author_id bigint,
    post_id   bigint,
    deleted   boolean,
    constraint user_fk
        foreign key (author_id)
            references "user" (id),
    constraint post_fk
        foreign key (post_id)
            references post (id)
);

create table if not exists role
(
    id serial primary key,
    name varchar (100),
    deleted    boolean
);

create table if not exists user_role
(
    user_id bigint,
    role_id int
);



