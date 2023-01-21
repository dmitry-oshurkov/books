ALTER TABLE public.author_sequence
    RENAME COLUMN sequences_id TO sequence_id;

ALTER TABLE public.book_author
    RENAME COLUMN authors_id TO author_id;

ALTER TABLE public.book_genre
    RENAME COLUMN genres_id TO genre_id;
