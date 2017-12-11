CREATE TABLE IF NOT EXISTS InputNumber (
	id int(12) primary key auto_increment,
	num1 int(12) NOT NULL,
	num2 int(12) NOT NULL
);

CREATE TABLE IF NOT EXISTS Gcd (
	id int(11) primary key auto_increment,
	calculatedGcd int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS USER(
	id int(11) primary key auto_increment,
	userName varchar(100) NOT NULL,
	password varchar(100) NOT NULL
);

INSERT INTO USER (userName, password) 
	SELECT 'gcdappuser', md5('password') FROM DUAL
WHERE NOT EXISTS (SELECT * FROM USER WHERE username = 'gcdappuser');