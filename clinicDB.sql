DROP DATABASE if exists clinic;
CREATE DATABASE clinic;
USE clinic;

CREATE TABLE patient (
 patient_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 patient_login varchar(50) NOT NULL UNIQUE,
 patient_password varchar (512) NOT NULL,
 patient_name varchar(100) NOT NULL,
 patient_dateOfBirth date NOT NULL,
 patient_phone varchar(50) NOT NULL,
 patient_email varchar(100) NOT NULL
);

CREATE TABLE reservation (
 reservation_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 reservation_date date NOT NULL,
 reservation_time varchar(5) NOT NULL, 									#to be safer with java convertion of time - instead of DateTime or sth - 'hh:mm'; 
 reservation_doc varchar(50) NOT NULL,
 reservation_address varchar(512) NOT NULL,
 reservation_patient_id int(11) NOT NULL,
 FOREIGN KEY (reservation_patient_id) REFERENCES patient(patient_id)	
);

INSERT INTO patient(patient_id, patient_login, patient_password, patient_name, patient_dateOfBirth, patient_phone, patient_email) VALUES 
(1, 'tumorInMyBrain', 'patient1', 'Bob Marley', '1945-02-06', '111 111 111', 'jah@we.jm'),
(2, 'pinkGuy', 'patient2', 'George Joji Kusunoki Miller', '1992-09-18', '222 222 222', 'filthy@frank.ch')
;

INSERT INTO reservation(reservation_id, reservation_date, reservation_time, reservation_doc, reservation_address, reservation_patient_id) VALUES 
(1, '2023-05-23', '15:00', 'Neurology', 'british jamaica 4/20', '1'),
(2, '2020-07-10', '12:00', 'Psychiatry', 'realm1','2'),
(3, '2014-05-15', '12:00', 'Psychiatry', 'realm2','2')
;

#------------------------------------------------------------------------------------------------------------------------------------------------TEST
SELECT * FROM reservation;

SELECT * FROM patient where (patient_login='pinkGuy');
SELECT patient_id FROM patient where (patient_login='pinkGuy');

SELECT patient_id, patient_password FROM patient where (patient_login='pinkGuy');
SELECT patient_password FROM patient where (patient_login='pinkGuy');

SELECT * FROM reservation where (reservation_patient_id=3);
SELECT * FROM reservation WHERE reservation_patient_id =3;

#button disable if past test:
INSERT INTO reservation(reservation_id, reservation_date, reservation_date, reservation_doc, reservation_address, reservation_patient_id) VALUES 
(50, '2019-05-23', '13:00', 'Neurology', 'british jamaica 4/20', '3');

#isAvailable?
SELECT reservation_id FROM reservation where (reservation_date='2020-05-28' AND reservation_time='13:00');

#Update reservation
UPDATE reservation SET reservation_doc="Addiction Treatment" WHERE reservation_id =1;
#------------------------------------------------------------------------------------------------------------------------------------------------TEST


-- user
CREATE USER 'clinicClient'@'localhost' IDENTIFIED BY 'clinicClient'; 
GRANT INSERT ON `reservation` TO 'clinicClient'@'localhost';
GRANT SELECT ON `reservation` TO 'clinicClient'@'localhost';
GRANT DELETE ON `reservation` TO 'clinicClient'@'localhost';
GRANT INSERT ON `patient` TO 'clinicClient'@'localhost';
GRANT SELECT ON `patient` TO 'clinicClient'@'localhost';