BEGIN;

INSERT INTO passengers(id, surname, firstname, email_address) VALUES(NEXTVAL('passengers_sequence_in_database'), 'BEAUVISAGE', 'Fabien', 'fab.beauvisage@gmail.com');
INSERT INTO passengers(id, surname, firstname, email_address) VALUES(NEXTVAL('passengers_sequence_in_database'), 'MUGISHA', 'Kamali-Derrick', 'kamaliderrick@gmail.com');

COMMIT;