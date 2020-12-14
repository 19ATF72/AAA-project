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
