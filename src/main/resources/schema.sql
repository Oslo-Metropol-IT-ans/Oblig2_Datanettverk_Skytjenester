
create table User(
    id int auto_increment not null,
    username varchar(75) not null unique ,
    password text not null,
    primary key (id)
);

create table Room(
    id int auto_increment not null primary key,
    name varchar(75) not null unique
);

create table RoomUser(
    user_id int not null ,
    room_id int not null ,
    constraint pkRoomUser PRIMARY KEY (user_id, room_id),
    constraint userFK foreign key (user_id) references User(id) on update cascade ,
    constraint roomFK foreign key (room_id) references Room(id) on update cascade
);

create table Message(
    id int not null primary key,
    room_id int not null,
    user_id int not null,
    message text not null,
    constraint messageRoomFK foreign key (room_id) references Room(id) on update cascade,
    constraint messageUserFK foreign key (user_id) references User(id) on update cascade
);

create view MessageView AS
    select username, message.id, room_id, user_id, message from User, Message
    where User.id = message.user_id;

