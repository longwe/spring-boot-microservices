CREATE TABLE country (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
	INTEGER organizationId,
    INTEGER departmentId,
	name VARCHAR(128) NOT NULL,
    INTEGER age,
	position VARCHAR(128) NOT NULL,
	PRIMARY KEY (id)
);

