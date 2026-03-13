CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username TEXT NOT NULL UNIQUE,
                       password TEXT NOT NULL,
                       email TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE genres (
                        id serial PRIMARY KEY,
                        name TEXT NOT NULL UNIQUE
);

CREATE TABLE movies (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                        name TEXT NOT NULL,
                        release_year INTEGER,
                        watch_status text;
user_rating INTEGER CHECK (user_rating >= 0 AND user_rating <= 10),
    user_review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE genre_movie (
                             movie_id UUID REFERENCES movies(id) ON DELETE CASCADE,
                             genre_id INTEGER REFERENCES genres(id) ON DELETE CASCADE,
                             PRIMARY KEY (movie_id, genre_id)
);