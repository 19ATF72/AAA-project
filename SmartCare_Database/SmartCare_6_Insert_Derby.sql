INSERT INTO user_status (user_status) VALUES ('pending');
INSERT INTO user_status (user_status) VALUES ('approved');
INSERT INTO user_status (user_status) VALUES ('blocked');

INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Dr', 'Root', 'Admin', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'root@admin.com', '1999-10-10', '2020-12-12', '2020-12-12', 0, 'admin', '07530219756', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Dr', 'Tom', 'First', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'TomFirst@smartcare.com', '1980-10-10', '2020-12-12', '2020-12-12', 0, 'doctor', '07530219756', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Ms', 'Judy', 'Best', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'JudyBest@smartcare.com', '1970-06-10', '2020-12-12', '2020-12-12', 0, 'nurse', '07530219756', 2);

INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Mr', 'Rob', 'Smith', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'RobSmith@patient.com', '2000-10-10', '2020-12-12', '2020-12-12', 0, 'patient', '07406402094', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Ms', 'Liz', 'Brown', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'RobSmith@patient.com', '2000-10-10', '2020-12-12', '2020-12-12', 0, 'patient', '07530219756', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Mr', 'Max', 'Hesitant', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'MaxHesitant@patient.com', '2000-10-10', '2020-12-12', '2020-12-12', 0, 'patient', '07530219756', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Mr', 'Mike', 'Park', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'MikePark@patient.com', '2000-10-10', '2020-12-12', '2020-12-12', 0, 'patient', '07928582641', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, phone_number, user_status_usid)
VALUES ('Mrs', 'Julia', 'Carines', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'JuliaCarines@patient.com', '2000-10-10', '2020-12-12', '2020-12-12', 0, 'patient', '07928582641', 2);

INSERT INTO organisation_type (type_name) VALUES ('General');
INSERT INTO organisation_type (type_name) VALUES ('Dermatology');
INSERT INTO organisation_type (type_name) VALUES ('Ophthalmologist');

INSERT INTO organisation (name, organisation_type_oid, address, postcode, phone_number) 
VALUES ('Spire Bristol Dermatology & Skin Care Clinic', 2, 'Redland Hill, Redland, Durdham Down, Bristol', 'BS6 6UT', '01179804000');
INSERT INTO organisation (name, organisation_type_oid, address, postcode, phone_number) 
VALUES ('Bristol Eye Doctor', 3, '3 Clifton Hill, Clifton, Bristol', 'BS8 1BN', '01173691179');

INSERT INTO employee (salary, address, postcode, organisation_oid, users_uuid) 
VALUES (10, 'Clydesdale, Thorpe Lane, Tingley', 'WF3 1SL', 1, 2);
INSERT INTO employee (salary, address, postcode, organisation_oid, users_uuid) 
VALUES (5, '17 Elmham Crescent, Liverpool', 'L10 7LH', 1, 3);

INSERT INTO patient_type (type_name) VALUES ('private');
INSERT INTO patient_type (type_name) VALUES ('nhs');

INSERT INTO patient (address, postcode, patient_type_ptid, users_uuid) 
VALUES ('11 The Thistles, Newcastle', 'ST5 2HL', 2, 4);
INSERT INTO patient (address, postcode, patient_type_ptid, users_uuid) 
VALUES ('Penleat, Leat Road, Lifton', 'PL16 0AE', 2, 5);
INSERT INTO patient (address, postcode, patient_type_ptid, users_uuid) 
VALUES ('5 Dickens Court, Machen', 'CF83 8TE', 2, 6);
INSERT INTO patient (address, postcode, patient_type_ptid, users_uuid) 
VALUES ('5 Rowan Terrace, Cheriton Bishop', 'EX6 6BN', 2, 7);
INSERT INTO patient (address, postcode, patient_type_ptid, users_uuid) 
VALUES ('Dove Cottage, 1 Church Street, Middleham', 'DL8 4PQ', 2, 8);


/*INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, user_status_usid);
VALUES ('bobby', 'tables', 'bobby@tables.com', '2020-12-12', '2020-12-12', 0, 'default.png', 2);
INSERT INTO users (USERPREFIX ,USERFIRSTNAME, USERSURNAME, pass, email, DOB, created, last_access, logged_in, USERTYPE, user_status_usid);
VALUES ('mehmet', 'aydin', 'mehmet@aydin.com', '2020-12-12', '2020-12-12', 0, 'default.png', 3);
*/

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

INSERT INTO patient_prescriptions (patient_pid, medicine) 
VALUES (1, 'dropalin');
INSERT INTO patient_prescriptions (patient_pid, medicine) 
VALUES (2, 'tabalin');
INSERT INTO patient_prescriptions (patient_pid, medicine) 
VALUES (3, 'insulin');
INSERT INTO patient_prescriptions (patient_pid, medicine) 
VALUES (4, 'marvelin');

/*
INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) 
VALUES (1, 'This seesion produced A', 40.0, '2020-12-16', 32400, 33600, 1, 1, 2, 2, 4);

INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) 
VALUES (1, 'This seesion produced B', 40.0, '2020-12-17', 32400, 33600, 1, 1, 2, 3, 4);

INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) 
VALUES (1, 'Wow it is Stan Lee', 100.0, '2020-12-17', 32400, 33600, 2, 1, 1, 4, 5);
*/
