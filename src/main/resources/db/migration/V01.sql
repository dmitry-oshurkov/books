CREATE TABLE author
(
    id          integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    updated     timestamptz DEFAULT NOW() NOT NULL,
    last_name   varchar(255)              NOT NULL,
    first_name  varchar(255)              NOT NULL,
    middle_name varchar(255),
    CONSTRAINT author_name_uk UNIQUE (last_name, first_name)
);


CREATE TABLE sequence
(
    id      integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    updated timestamptz DEFAULT NOW() NOT NULL,
    name    varchar(255) UNIQUE       NOT NULL
);


CREATE TABLE genre
(
    id      integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    updated timestamptz DEFAULT NOW() NOT NULL,
    name    varchar(255) UNIQUE       NOT NULL
);


CREATE TABLE book
(
    id                   integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    updated              timestamptz DEFAULT NOW() NOT NULL,
    title                varchar(255)              NOT NULL,
    summary              varchar(4000),
    summary_content_type varchar(255),
    recommended          boolean                   NOT NULL,
    verified             boolean                   NOT NULL,
    unread               boolean                   NOT NULL,
    language             varchar(255),
    publisher            varchar(255),
    issued               varchar(255),
    cover                bytea,
    cover_content_type   varchar(255),
    sequence_id          integer REFERENCES sequence ON DELETE CASCADE,
    sequence_number      integer,
    hash                 uuid UNIQUE               NOT NULL
);

COMMENT ON TABLE book IS 'Книги';
COMMENT ON COLUMN book.id IS 'идентификатор';
COMMENT ON COLUMN book.updated IS 'время изменения данных';
COMMENT ON COLUMN book.title IS 'название';
COMMENT ON COLUMN book.summary IS 'описание';
COMMENT ON COLUMN book.recommended IS 'рекомендована ли к прочтению';
COMMENT ON COLUMN book.verified IS 'источник проверен';
COMMENT ON COLUMN book.unread IS 'не прочитана';
COMMENT ON COLUMN book.hash IS 'уникальный код книги';

CREATE INDEX book_recommended_index ON book (recommended);
CREATE INDEX book_unread_index ON book (unread);
CREATE INDEX book_verified_index ON book (verified);


CREATE TABLE author_sequence
(
    author_id    integer REFERENCES author ON DELETE CASCADE   NOT NULL,
    sequences_id integer REFERENCES sequence ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (author_id, sequences_id)
);


CREATE TABLE book_file
(
    id      integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    updated timestamptz DEFAULT NOW()                 NOT NULL,
    book_id integer REFERENCES book ON DELETE CASCADE NOT NULL,
    content bytea                                     NOT NULL,
    type    integer                                   NOT NULL,
    hash    uuid UNIQUE                               NOT NULL
);


CREATE TABLE book_author
(
    book_id    integer REFERENCES book ON DELETE CASCADE   NOT NULL,
    authors_id integer REFERENCES author ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (book_id, authors_id)
);


CREATE TABLE book_genre
(
    book_id   integer REFERENCES book ON DELETE CASCADE  NOT NULL,
    genres_id integer REFERENCES genre ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (book_id, genres_id)
);
