create sequence if not exists model_example_seq;

create table model_example
(
    id       bigint primary key default nextval('model_example_seq'),
    model_id bigint not null,
    prompt   text   not null,
    response text   not null,
    constraint pk_model_example primary key (id)
);

alter table model_example
    add constraint fk_model_example_on_model foreign key (model_id) references model (id);