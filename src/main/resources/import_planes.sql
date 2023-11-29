BEGIN;

INSERT INTO planes(id, operator, model, registration, capacity) VALUES(NEXTVAL('planes_sequence_in_database'), 'AirbusIndustrie', 'AIRBUS A380', 'P1', 545);
INSERT INTO planes(id, operator, model, registration, capacity) VALUES(NEXTVAL('planes_sequence_in_database'), 'Boeing', 'BOEING 788', 'P2', 279);

COMMIT;