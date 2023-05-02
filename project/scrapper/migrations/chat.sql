CREATE TABLE users (
id INTEGER PRIMARY KEY,
chat_id INTEGER NOT NULL,
username VARCHAR(255) -- added in migration 
);

CREATE TABLE links (
id INTEGER PRIMARY KEY,
user_id INTEGER NOT NULL,
link TEXT NOT NULL,
CONSTRAINT fk_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE
);




