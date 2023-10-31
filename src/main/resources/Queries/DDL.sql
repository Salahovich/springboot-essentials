CREATE TYPE course_level AS ENUM ('Beginner', 'Intermediate', 'Advanced');
CREATE TYPE gender AS ENUM ('Male', 'Female');

CREATE CAST (CHARACTER VARYING As gender) WITH INOUT AS IMPLICIT;
CREATE CAST (CHARACTER VARYING As course_level) WITH INOUT AS IMPLICIT;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE instructor_details
(
    id      UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    youtube VARCHAR(50) NOT NULL,
    hobby   VARCHAR(50) NOT NULL
);

CREATE TABLE instructor
(
    id           UUID        NOT NULL PRIMARY KEY,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    title        VARCHAR(50) NOT NULL,
    details_id   UUID        NOT NULL,
    FOREIGN KEY (details_id) REFERENCES instructor_details (id) ON DELETE CASCADE
);

CREATE TABLE course
(
    id            UUID         NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    start_date    TIMESTAMP    NOT NULL,
    end_date      TIMESTAMP    NOT NULL,
    course_level  COURSE_LEVEL NOT NULL,
    is_started    BOOLEAN      NOT NULL,
    instructor_id UUID         NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES instructor (id) ON DELETE CASCADE
);

CREATE TABLE student
(
    id           UUID         NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    age          INT          NOT NULL,
    gender       GENDER       NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(11)  NOT NULL,
    national_id  VARCHAR(14)  NOT NULL
);

CREATE TABLE student_course
(
    student_id UUID NOT NULL,
    course_id  UUID NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
);