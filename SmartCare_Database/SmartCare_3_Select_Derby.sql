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
FROM employee_has_appointment_slots
WHERE (date BETWEEN '2020-11-01' AND '2010-09-29');



// GET SUM OF PAID APPOINTMENTS

SELECT SUM(charge) 
FROM appointment 
WHERE APPOINTMENT_STATUS_ASID = 5 
AND (DATE_PAID BETWEEN '2020-12-01' AND '2021-01-01')



// GET SUM OF EMPLOYEE COSTS

SELECT SUM(e.salary) FROM appointment a INNER JOIN employee e ON a.EMPLOYEE_EID = e.EID
WHERE APPOINTMENT_STATUS_ASID = 5 
AND (DATE_PAID BETWEEN '2020-12-01' AND '2021-01-01')


// GET DETAILS TO DISPLAY ON PAGE

SELECT a.aid, a.charge, e.salary, a.DATE_PAID FROM appointment a INNER JOIN employee e ON a.employee_eid = e.EID AND (DATE_PAID BETWEEN '2020-12-01' AND '2021-01-01')

