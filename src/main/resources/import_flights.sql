BEGIN;

INSERT INTO flights(id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id) VALUES(NEXTVAL('flights_sequence_in_database'), 'F1', 'France', 'Espagne', 2023-12-23, 23:30:00, 2023-12-24, 01:30:00, 1);
INSERT INTO flights(id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id) VALUES(NEXTVAL('flights_sequence_in_database'), 'F2', 'Espagne', 'France', 2023-12-26, 14:30:00, 2026-12-26, 16:30:00, 2);

COMMIT;