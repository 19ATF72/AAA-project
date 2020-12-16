// GETS PATIENTS WITH NAME AND TYPE
SELECT 
    u.uuid,
    u.username,
    p.address,  
    pt.type_name
FROM
    patient p
INNER JOIN patient_type pt
	ON p.patient_type_ptid = pt.ptid
INNER JOIN users u
        ON p.USERS_UUID = u.UUID
WHERE p.patient_type_ptid = 1;


// GETS EMPLOYEES WITH NAME AND TYPE
SELECT 
    e.eid, 
    et.type_name,
    u.username
FROM
    users u
INNER JOIN employee e
	ON e.users_uuid = u.uuid
INNER JOIN employee_type et
        ON e.EMPLOYEE_TYPE_TID = et.etid
        
// SELECT USERS / EMPLOYEE WHERE

SELECT *
FROM users
WHERE (created BETWEEN '2019-11-01' AND '2021-09-29');

SELECT u.uuid,u.username,p.address,pt.type_name, u.created
FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid 
INNER JOIN users u ON p.USERS_UUID = u.UUID 
WHERE (u.created BETWEEN '2019-11-01' AND '2021-09-29') FETCH FIRST 10 ROWS ONLY


// SELECT APPOINTMENT & USER DETAILS WHERE APPOINTMENT STATUS IS INVOICED

SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.date,a.charge,aps.appointment_status 
FROM patient p 
INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid 
INNER JOIN users u ON p.USERS_UUID = u.UUID 
INNER JOIN appointment a ON p.PID = a.PATIENT_PID
INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid
WHERE aps.asid = 4
FETCH FIRST 10 ROWS ONLY


// WHERE DATE VARIATION

SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.date,a.charge,aps.appointment_status 
FROM patient p 
INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid 
INNER JOIN users u ON p.USERS_UUID = u.UUID 
INNER JOIN appointment a ON p.PID = a.PATIENT_PID
INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid
WHERE aps.asid = 4 AND (a.date BETWEEN '2019-11-01' AND '2021-09-29')
FETCH FIRST 10 ROWS ONLY
