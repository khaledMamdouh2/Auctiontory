create table user
(
  id        int auto_increment
    primary key,
  user_name varchar(30) not null,
  email     varchar(40) not null,
  type      varchar(30) not null,
  password  varchar(40) not null,
  constraint user_user_name_uindex
  unique (user_name),
  constraint user_email_uindex
  unique (email)
)
  engine = InnoDB;

create table batch_auction
(
  id       int auto_increment
    primary key,
  owner_id int         not null,
  deadline datetime    not null,
  title    varchar(30) not null,
  min_bid  int         not null,
  constraint batch_auction_title_uindex
  unique (title),
  constraint batch_auction_user_id_fk
  foreign key (owner_id) references user (id)
)
  engine = InnoDB;

create index batch_auction_user_id_fk
  on batch_auction (owner_id);

create table batch_product
(
  id          int auto_increment
    primary key,
  name        varchar(30) not null,
  description varchar(80) null,
  auction_id  int         not null,
  constraint batch_product_batch_auction_id_fk
  foreign key (auction_id) references batch_auction (id)
)
  engine = InnoDB;

create index batch_product_batch_auction_id_fk
  on batch_product (auction_id);

create table parts_auction
(
  id       int auto_increment
    primary key,
  deadline datetime    not null,
  owner_id int         null,
  title    varchar(30) not null,
  constraint parts_auction_user_id_fk
  foreign key (owner_id) references user (id)
)
  engine = InnoDB;

create index parts_auction_user_id_fk
  on parts_auction (owner_id);

create table parts_product
(
  id          int auto_increment
    primary key,
  name        varchar(30) not null,
  description varchar(80) null,
  auction_id  int         null,
  min_bid     int         not null,
  constraint parts_product_parts_auction_id_fk
  foreign key (auction_id) references parts_auction (id)
)
  engine = InnoDB;

create index parts_product_parts_auction_id_fk
  on parts_product (auction_id);

create table user_batch_bid
(
  user_id  int not null,
  batch_id int not null,
  price    int not null,
  primary key (user_id, batch_id),
  constraint user_batch_bid_user_id_fk
  foreign key (user_id) references user (id),
  constraint user_batch_bid_batch_auction_id_fk
  foreign key (batch_id) references batch_auction (id)
)
  engine = InnoDB;

create index user_batch_bid_batch_auction_id_fk
  on user_batch_bid (batch_id);

create table user_part_bid
(
  user_id         int not null,
  part_product_id int not null,
  price           int not null,
  primary key (user_id, part_product_id),
  constraint user_part_bid_user_id_fk
  foreign key (user_id) references user (id),
  constraint user_part_bid_parts_product_id_fk
  foreign key (part_product_id) references parts_product (id)
)
  engine = InnoDB;

create index user_part_bid_parts_product_id_fk
  on user_part_bid (part_product_id);

