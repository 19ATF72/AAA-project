INSERT INTO user_status (user_status) VALUES ('pending');
INSERT INTO user_status (user_status) VALUES ('approved');
INSERT INTO user_status (user_status) VALUES ('blocked');

INSERT INTO users (username, pass, email, created, last_access, logged_in, picture, user_status_usid) 
VALUES ('root', 'root', 'root@admin.com', '2020-12-12 15:24:50.221', '2020-12-12 15:24:50.221', 0, 'default.png', 2);
INSERT INTO users (username, pass, email, created, last_access, logged_in, picture, user_status_usid) 
VALUES ('bobby', 'tables', 'bobby@tables.com', '2020-12-12 15:24:50.221', '2020-12-12 15:24:50.221', 0, 'default.png', 1);
INSERT INTO users (username, pass, email, created, last_access, logged_in, picture, user_status_usid) 
VALUES ('mehmet', 'aydin', 'mehmet@aydin.com', '2020-12-12 15:24:50.221', '2020-12-12 15:24:50.221', 0, 'default.png', 3);

INSERT INTO patient_type (type_name) VALUES ('private');
INSERT INTO patient_type (type_name) VALUES ('nhs');

INSERT INTO patient (address, patient_type_ptid, users_uuid) 
VALUES ('n/a', 1, 2);

INSERT INTO patient_prescriptions (patient_pid, medicine) 
VALUES (1, 'dropalin');

INSERT INTO organisation_type (type_name) VALUES ('dermatology');
INSERT INTO organisation_type (type_name) VALUES ('general');

INSERT INTO organisation (name, organisation_type_oid, address, postcode, phone_number) 
VALUES ('testpital', 1, 'testtown', 'bs9', '07423428377');

INSERT INTO employee_type (type_name) VALUES ('doctor');
INSERT INTO employee_type (type_name) VALUES ('nurse');

INSERT INTO employee (salary, address, employee_type_tid, organisation_oid, users_uuid) 
VALUES (8.25, 'testtown', 1, 1, 3);

INSERT INTO appointment_status (appointment_status) VALUES ('scheduled');
INSERT INTO appointment_status (appointment_status) VALUES ('completed');
INSERT INTO appointment_status (appointment_status) VALUES ('cancelled');
INSERT INTO appointment_status (appointment_status) VALUES ('invoiced');
INSERT INTO appointment_status (appointment_status) VALUES ('paid');

INSERT INTO appointment_type (type_name) VALUES ('private');
INSERT INTO appointment_type (type_name) VALUES ('nhs');

INSERT INTO watchdog (users_uid, type, message, timestamp) 
VALUES (1, 'test', 'test message', 1);
INSERT INTO watchdog (users_uid, type, message, timestamp) 
VALUES (2, 'test', 'test message', 1);

INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) 
VALUES (1, 'give him lots of dropalin', 42.0, '1970-01-01', 28800, 29400, 1, 1, 1, 1, 2);

INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) 
VALUES (2, 'give him even more dropalin', 50.0, '1970-01-02', 32400, 33600, 1, 1, 2, 1, 2);

