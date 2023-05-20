CREATE TABLE link (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  url TEXT NOT NULL
);

CREATE TABLE chat (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL
);

CREATE TABLE link_tracking (
  id SERIAL PRIMARY KEY,
  link_id INTEGER NOT NULL,
  chat_id INTEGER NOT NULL,
  FOREIGN KEY (link_id) REFERENCES link(id),
  FOREIGN KEY (chat_id) REFERENCES chat(id)
);
