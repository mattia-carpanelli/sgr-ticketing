DELETE FROM
  Users
WHERE
  id = 999999;
INSERT INTO
  Users (
    id,
    name,
    lastname,
    email,
    username,
    password,
    token
  )
VALUES
  (
    999999,
    'Ticketing',
    'Administrator',
    'ticketing.administrator@sgrconsulting.com',
    't.administrator',
    '$2a$10$KqZqyOXrd8OB/Hu7D/87/.7..ubq8V5B7jk17MSZLWckpdX2HtUhi',
    'sgr_3d790518-44ab-4456-8468-26bf663b3721'
  );