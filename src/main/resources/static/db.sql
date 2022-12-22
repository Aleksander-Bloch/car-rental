CREATE TABLE Car
(
    CAR_ID     INTEGER     PRIMARY KEY,
    BRAND      VARCHAR(13) NOT NULL,
    MODEL      VARCHAR(10) NOT NULL,
    HORSEPOWER INTEGER     NOT NULL,
    YEAR       INTEGER     NOT NULL,
    MILEAGE    INTEGER     NOT NULL,
    GEARBOX    VARCHAR(10) NOT NULL,
    CATEGORY   VARCHAR(10) NOT NULL
);

CREATE TABLE Person
(
    PESEL   VARCHAR(12) PRIMARY KEY,
    NAME    VARCHAR(10) NOT NULL,
    SURNAME VARCHAR(12) NOT NULL
);

CREATE TABLE Users
(
    LOGIN        VARCHAR(15) PRIMARY KEY,
    PASSWORD     VARCHAR(15),
    PESEL        VARCHAR(12) NOT NULL REFERENCES Person,
    AMOUNT_SPENT INTEGER     NOT NULL,
    USER_ROLE    VARCHAR(10) NOT NULL CHECK (USER_ROLE IN ('Admin', 'Client')),
    STATUS       VARCHAR(10) NOT NULL CHECK (STATUS IN ('Bronze', 'Silver', 'Gold', 'Platinum'))
);

CREATE TABLE Price
(
    CAR_ID     INTEGER NOT NULL UNIQUE REFERENCES Car,
    DAY_RATE   INTEGER NOT NULL,
    WEEK_RATE  INTEGER NOT NULL,
    MONTH_RATE INTEGER NOT NULL
);

CREATE TABLE Rental
(
    LOGIN      VARCHAR(15) NOT NULL REFERENCES Users,
    CAR_ID     INTEGER     NOT NULL REFERENCES Car,
    START_DATE DATE        NOT NULL,
    END_DATE   DATE        NOT NULL
);

INSERT INTO Car VALUES(1, 'Audi', 'Q3', 419, 2019, 1335, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(2, 'Audi', 'Q5', 454, 2019, 4042, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(3, 'Audi', 'Q3', 564, 2020, 1401, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(4, 'Audi', 'Q7', 262, 2017, 27195, 'Automatic', 'Premium');
INSERT INTO Car VALUES(5, 'Audi', 'A5', 437, 2019, 4500, 'Manual', 'Standard');
INSERT INTO Car VALUES(6, 'Audi', 'Q5', 489, 2019, 6000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(7, 'Audi', 'Q7', 402, 2019, 6000, 'Automatic', 'Premium');
INSERT INTO Car VALUES(8, 'Audi', 'A5', 580, 2020, 3390, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(9, 'Audi', 'TT', 195, 2016, 25529, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(10, 'Audi', 'TT', 405, 2019, 9121, 'Manual', 'Standard');
INSERT INTO Car VALUES(11, 'Audi', 'Q7', 462, 2019, 14495, 'Automatic', 'Standard');
INSERT INTO Car VALUES(12, 'Audi', 'Q3', 451, 2019, 4432, 'Automatic', 'Standard');
INSERT INTO Car VALUES(13, 'Audi', 'Q7', 567, 2020, 6000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(14, 'Audi', 'Q7', 270, 2017, 22489, 'Automatic', 'Standard');
INSERT INTO Car VALUES(15, 'Audi', 'Q3', 472, 2019, 67, 'Automatic', 'Standard');
INSERT INTO Car VALUES(16, 'Audi', 'Q7', 267, 2017, 27000, 'Automatic', 'Standard');
INSERT INTO Car VALUES(17, 'Audi', 'Q7', 373, 2018, 16753, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(18, 'Audi', 'A5', 596, 2020, 1000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(19, 'Audi', 'A6', 475, 2019, 2250, 'Automatic', 'Standard');
INSERT INTO Car VALUES(20, 'Audi', 'Q7', 210, 2015, 33090, 'Automatic', 'Standard');
INSERT INTO Car VALUES(21, 'Audi', 'S4', 442, 2019, 4900, 'Automatic', 'Standard');
INSERT INTO Car VALUES(22, 'Audi', 'Q7', 157, 2016, 50514, 'Automatic', 'Standard');
INSERT INTO Car VALUES(23, 'Audi', 'A6', 437, 2019, 4500, 'Automatic', 'Standard');
INSERT INTO Car VALUES(24, 'Audi', 'Q7', 315, 2018, 26885, 'Automatic', 'Premium');
INSERT INTO Car VALUES(25, 'Audi', 'Q7', 519, 2020, 4500, 'Semi-Auto', 'Premium');

INSERT INTO Car VALUES(26, 'BMW', 'X5', 529, 2019, 6215, 'Automatic', 'Premium');
INSERT INTO Car VALUES(27, 'BMW', 'Z4', 616, 2020, 101, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(28, 'BMW', 'M4', 547, 2019, 19, 'Automatic', 'Standard');
INSERT INTO Car VALUES(29, 'BMW', '5 Series', 344, 2017, 23000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(30, 'BMW', '5 Series', 521, 2019, 6775, 'Automatic', 'Standard');
INSERT INTO Car VALUES(31, 'BMW', 'M6', 242, 2016, 26642, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(32, 'BMW', '7 Series', 571, 2019, 17501, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(33, 'BMW', 'X5', 485, 2019, 8700, 'Automatic', 'Premium');
INSERT INTO Car VALUES(34, 'BMW', 'M4', 255, 2016, 21107, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(35, 'BMW', 'X3', 588, 2019, 8813, 'Automatic', 'Standard');
INSERT INTO Car VALUES(36, 'BMW', 'X5', 237, 2016, 49000, 'Automatic', 'Standard');
INSERT INTO Car VALUES(37, 'BMW', '3 Series', 561, 2019, 8200, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(38, 'BMW', 'X5', 524, 2019, 6742, 'Automatic', 'Standard');
INSERT INTO Car VALUES(39, 'BMW', '2 Series', 428, 2018, 103, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(40, 'BMW', '7 Series', 371, 2017, 28998, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(41, 'BMW', 'M4', 423, 2018, 8725, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(42, 'BMW', '4 Series', 653, 2020, 2500, 'Automatic', 'Standard');
INSERT INTO Car VALUES(43, 'BMW', 'Z4', 658, 2020, 105, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(44, 'BMW', 'M4', 303, 2019, 101, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(45, 'BMW', '3 Series', 484, 2019, 9752, 'Automatic', 'Standard');
INSERT INTO Car VALUES(46, 'BMW', '5 Series', 386, 2019, 3500, 'Automatic', 'Standard');
INSERT INTO Car VALUES(47, 'BMW', '3 Series', 621, 2020, 2894, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(48, 'BMW', 'X4', 567, 2019, 4832, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(49, 'BMW', '8 Series', 496, 2019, 12, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(50, 'BMW', '4 Series', 514, 2019, 5757, 'Automatic', 'Standard');

INSERT INTO Car VALUES(51, 'Mercedes-Benz', 'C300', 451, 2019, 10104, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(52, 'Mercedes-Benz', 'E53', 538, 2020, 2000, 'Automatic', 'Premium');
INSERT INTO Car VALUES(53, 'Mercedes-Benz', 'GLE', 489, 2019, 2870, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(54, 'Mercedes-Benz', 'C200', 349, 2019, 4749, 'Automatic', 'Standard');
INSERT INTO Car VALUES(55, 'Mercedes-Benz', 'SL Class', 180, 2016, 21681, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(56, 'Mercedes-Benz', 'E43', 406, 2019, 2400, 'Automatic', 'Standard');
INSERT INTO Car VALUES(57, 'Mercedes-Benz', 'C43', 403, 2019, 1600, 'Automatic', 'Premium');
INSERT INTO Car VALUES(58, 'Mercedes-Benz', 'C63S', 508, 2020, 3774, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(59, 'Mercedes-Benz', 'A45', 481, 2019, 5990, 'Automatic', 'Standard');
INSERT INTO Car VALUES(60, 'Mercedes-Benz', 'S63', 530, 2015, 23156, 'Automatic', 'Premium');
INSERT INTO Car VALUES(61, 'Mercedes-Benz', 'GL Class', 121, 2016, 17000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(62, 'Mercedes-Benz', 'V Class', 421, 2019, 1350, 'Automatic', 'Standard');
INSERT INTO Car VALUES(63, 'Mercedes-Benz', 'GLE Class', 555, 2020, 20, 'Automatic', 'Premium');
INSERT INTO Car VALUES(64, 'Mercedes-Benz', 'A45', 354, 2018, 6800, 'Automatic', 'Premium');
INSERT INTO Car VALUES(65, 'Mercedes-Benz', 'CLA45', 474, 2019, 9487, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(66, 'Mercedes-Benz', 'S500', 489, 2019, 3671, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(67, 'Mercedes-Benz', 'E53', 431, 2019, 1000, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(68, 'Mercedes-Benz', 'SL CLASS', 152, 2016, 18400, 'Automatic', 'Standard');
INSERT INTO Car VALUES(69, 'Mercedes-Benz', 'S43', 472, 2019, 7283, 'Semi-Auto', 'Premium');
INSERT INTO Car VALUES(70, 'Mercedes-Benz', 'A35', 492, 2019, 163, 'Automatic', 'Premium');
INSERT INTO Car VALUES(71, 'Mercedes-Benz', 'E43', 424, 2019, 9612, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(72, 'Mercedes-Benz', 'GTR', 731, 2019, 12422, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(73, 'Mercedes-Benz', 'V Class', 481, 2019, 3337, 'Semi-Auto', 'Standard');
INSERT INTO Car VALUES(74, 'Mercedes-Benz', 'GLE', 473, 2019, 10499, 'Automatic', 'Standard');
INSERT INTO Car VALUES(75, 'Mercedes-Benz', 'CLA35', 336, 2018, 2122, 'Semi-Auto', 'Supercar');

INSERT INTO Car VALUES(76, 'Ferrari', 'F8', 710, 2021, 3452, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(77, 'Porsche', 'Panamera', 440, 2022, 5436, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(78, 'Bugatti', 'Chiron', 1510, 2022, 320, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(79, 'Lamborghini', 'Aventador', 769, 2020, 3125, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(80, 'Maserati', 'GTR', 454, 2020, 4355,'Automatic', 'Supercar' );
INSERT INTO Car VALUES(81, 'Aston Martin', 'DBX', 542, 2018, 4562,'Automatic', 'Supercar');
INSERT INTO Car VALUES(82, 'McLaren', '720S', 710, 2019, 8457,'Automatic', 'Supercar');
INSERT INTO Car VALUES(83, 'Lamborghini', 'Urus', 657, 2021, 423,'Automatic', 'Supercar');
INSERT INTO Car VALUES(84, 'Rolls-Royce', 'Phantom', 563, 2019, 312,'Automatic', 'Supercar');
INSERT INTO Car VALUES(85, 'Ferrari', 'Roma', 612, 2021, 123, 'Automatic', 'Supercar');
INSERT INTO Car VALUES(86, 'Tesla', 'Model S', 670, 2018, 1234, 'Automatic', 'Electric');
INSERT INTO Car VALUES(87, 'Tesla', 'Model 3', 283, 2019, 4343, 'Automatic', 'Electric');
INSERT INTO Car VALUES(88, 'Tesla', 'Model X', 670, 2020, 3232, 'Automatic', 'Electric');
INSERT INTO Car VALUES(89, 'Tesla', 'Model Y', 384, 2021, 1000, 'Automatic', 'Electric');

INSERT INTO Person VALUES('67062379831,', 'Kyron', 'Mccann');
INSERT INTO Person VALUES('57080344443', 'Aaryan', 'Cook');
INSERT INTO Person VALUES('71091096255', 'Shivam', 'Wyatt');
INSERT INTO Person VALUES('87082183631', 'Zayd', 'Mcleod');
INSERT INTO Person VALUES('70061152153', 'Cody', 'Anderson');
INSERT INTO Person VALUES('74040669869', 'Eugene', 'Mitchell');
INSERT INTO Person VALUES('65061893437', 'Micheal', 'Campbell');
INSERT INTO Person VALUES('55052598829', 'Hussein', 'Murray');
INSERT INTO Person VALUES('85110395349', 'Faris', 'Boyle');
INSERT INTO Person VALUES('71051747733', 'Freddy', 'Russell');
INSERT INTO Person VALUES('64051038181', 'Rico', 'Bond');
INSERT INTO Person VALUES('77092282467', 'Adam', 'Brock');
INSERT INTO Person VALUES('55060212845', 'Stanley', 'Nguyen');
INSERT INTO Person VALUES('87090744517', 'Lorenzo', 'Lane');
INSERT INTO Person VALUES('82110554133', 'Emre', 'Fox');
INSERT INTO Person VALUES('86011184960', 'Theresa', 'Harrison');
INSERT INTO Person VALUES('55050274122', 'Kelsie', 'Golden');
INSERT INTO Person VALUES('96021092386', 'Max', 'Gardner');
INSERT INTO Person VALUES('79032062472', 'Lorraine', 'Case');
INSERT INTO Person VALUES('99101986772', 'Whitney', 'Castro');
INSERT INTO Person VALUES('98082328650', 'Harriett', 'Drake');
INSERT INTO Person VALUES('03241574720', 'Ruqayyah', 'Buckley');
INSERT INTO Person VALUES('63031772194', 'Jaydon', 'Pope');
INSERT INTO Person VALUES('48100826118', 'Kaiden', 'Stark');
INSERT INTO Person VALUES('53012984816', 'Lily-May', 'Hebert');
INSERT INTO Person VALUES('84010911446', 'Verity', 'Coleman');
INSERT INTO Person VALUES('98120745152', 'Leighton', 'Waller');
INSERT INTO Person VALUES('97061538446', 'Eliana', 'Sosa');
INSERT INTO Person VALUES('04260242886', 'Yasmin', 'Sloan');
INSERT INTO Person VALUES('66040576610', 'Philippa', 'Pierce');
INSERT INTO Person VALUES('12345678911', 'Andrew', 'Tate');

INSERT INTO Users VALUES('kyronmc562', '4BEK5DyWex', '67062379831', 913, 'Client', 'Bronze');
INSERT INTO Users VALUES('aaryanco92', 'MbQezqQKvs', '57080344443', 61, 'Client', 'Bronze');
INSERT INTO Users VALUES('shivamwy9', 'k623K8svec', '71091096255', 5989, 'Client', 'Silver');
INSERT INTO Users VALUES('zaydmc', 'Gvu2D7ghd3', '87082183631', 202, 'Client', 'Bronze');
INSERT INTO Users VALUES('codyan6', 'uCPNv8zNCC', '70061152153', 96, 'Client', 'Bronze');
INSERT INTO Users VALUES('eugenemi30', 'vZrsUHUffB', '74040669869', 91, 'Client', 'Bronze');
INSERT INTO Users VALUES('michealca', 'eEwbdmBbVS', '65061893437', 3715, 'Client', 'Silver');
INSERT INTO Users VALUES('husseinmu58', 'v5bdXXNSJr', '55052598829', 36, 'Client', 'Bronze');
INSERT INTO Users VALUES('farisbo874', 'R8PSMaGemY', '85110395349', 323, 'Client', 'Bronze');
INSERT INTO Users VALUES('freddyru46', 'tLs9KupCan', '71051747733', 970, 'Client', 'Bronze');
INSERT INTO Users VALUES('ricobo', 'b25yZmWCwf', '64051038181', 4973, 'Client', 'Silver');
INSERT INTO Users VALUES('adambr5', 'Vrumb6XWT6', '77092282467', 19, 'Client', 'Bronze');
INSERT INTO Users VALUES('stanleyng048', 'DqX7sdsUVw', '55060212845', 34061, 'Client', 'Gold');
INSERT INTO Users VALUES('lorenzola4', 'QYUBDutNSy', '87090744517', 77, 'Client', 'Bronze');
INSERT INTO Users VALUES('emrefo07', 'vwC9g2bzac', '82110554133', 9337, 'Client', 'Silver');
INSERT INTO Users VALUES('theresaha9', 'ZYJkdjDHn9', '86011184960', 209, 'Client', 'Bronze');
INSERT INTO Users VALUES('kelsiego', 'tFXywfxGZ7', '55050274122', 124008, 'Client', 'Platinum');
INSERT INTO Users VALUES('maxga7', 'ynjL9f77gF', '96021092386', 567, 'Client', 'Bronze');
INSERT INTO Users VALUES('lorraineca8', 'pSCFPHKSBJ', '79032062472', 123, 'Client', 'Bronze');
INSERT INTO Users VALUES('whitneyca8', 'wcJ93ncfCq', '99101986772', 58503, 'Client', 'Gold');
INSERT INTO Users VALUES('harriettdr', 'P6EEr5u2YP', '98082328650', 419, 'Client', 'Bronze');
INSERT INTO Users VALUES('ruqayyahbu800', 'qkC7mmFA6j', '03241574720', 3956, 'Client', 'Silver');
INSERT INTO Users VALUES('jaydonpo808', 'f3TGEUxr9V', '63031772194', 625, 'Client', 'Bronze');
INSERT INTO Users VALUES('kaidenst33', 'KqeTCXjWXd', '48100826118', 1542, 'Client', 'Silver');
INSERT INTO Users VALUES('lily-mayhe638', 'cMaPG9xnja', '53012984816', 75, 'Client', 'Bronze');
INSERT INTO Users VALUES('verityco91', 'nQP5uRP8rs', '84010911446', 975, 'Client', 'Bronze');
INSERT INTO Users VALUES('leightonwa', 'rMzV84yh8C', '98120745152', 188804, 'Client', 'Platinum');
INSERT INTO Users VALUES('elianaso5', 'SB3qHSsBja', '97061538446', 659, 'Client', 'Bronze');
INSERT INTO Users VALUES('yasminsl02', 'FMjKnktJee', '04260242886', 923, 'Client', 'Bronze');
INSERT INTO Users VALUES('philippapi86', 'x7rVNF7bFb', '66040576610', 5099, 'Client', 'Silver');
INSERT INTO Users VALUES('andrzej', 'tata', '12345678911', 0, 'Admin', 'Bronze');

INSERT INTO Price VALUES(1, 200, 1260, 4200);
INSERT INTO Price VALUES(2, 220, 1380, 4620);
INSERT INTO Price VALUES(3, 300, 1890, 6300);
INSERT INTO Price VALUES(4, 150, 940, 3150);
INSERT INTO Price VALUES(5, 210, 1320, 4410);
INSERT INTO Price VALUES(6, 230, 1440, 4830);
INSERT INTO Price VALUES(7, 270, 1700, 5670);
INSERT INTO Price VALUES(8, 310, 1950, 6510);
INSERT INTO Price VALUES(9, 100, 630, 2100);
INSERT INTO Price VALUES(10, 190, 1190, 3990);
INSERT INTO Price VALUES(11, 220, 1380, 4620);
INSERT INTO Price VALUES(12, 210, 1320, 4410);
INSERT INTO Price VALUES(13, 300, 1890, 6300);
INSERT INTO Price VALUES(14, 110, 690, 2310);
INSERT INTO Price VALUES(15, 220, 1380, 4620);
INSERT INTO Price VALUES(16, 110, 690, 2310);
INSERT INTO Price VALUES(17, 220, 1380, 4620);
INSERT INTO Price VALUES(18, 320, 2010, 6720);
INSERT INTO Price VALUES(19, 230, 1440, 4830);
INSERT INTO Price VALUES(20, 100, 630, 2100);
INSERT INTO Price VALUES(21, 210, 1320, 4410);
INSERT INTO Price VALUES(22, 100, 630, 2100);
INSERT INTO Price VALUES(23, 210, 1320, 4410);
INSERT INTO Price VALUES(24, 180, 1130, 3780);
INSERT INTO Price VALUES(25, 380, 2390, 7980);
INSERT INTO Price VALUES(26, 350, 2200, 7350);
INSERT INTO Price VALUES(27, 330, 2070, 6930);
INSERT INTO Price VALUES(28, 260, 1630, 5460);
INSERT INTO Price VALUES(29, 140, 880, 2940);
INSERT INTO Price VALUES(30, 250, 1570, 5250);
INSERT INTO Price VALUES(31, 100, 630, 2100);
INSERT INTO Price VALUES(32, 270, 1700, 5670);
INSERT INTO Price VALUES(33, 320, 2010, 6720);
INSERT INTO Price VALUES(34, 110, 690, 2310);
INSERT INTO Price VALUES(35, 280, 1760, 5880);
INSERT INTO Price VALUES(36, 100, 630, 2100);
INSERT INTO Price VALUES(37, 270, 1700, 5670);
INSERT INTO Price VALUES(38, 250, 1570, 5250);
INSERT INTO Price VALUES(39, 180, 1130, 3780);
INSERT INTO Price VALUES(40, 160, 1000, 3360);
INSERT INTO Price VALUES(41, 250, 1570, 5250);
INSERT INTO Price VALUES(42, 350, 2200, 7350);
INSERT INTO Price VALUES(43, 350, 2200, 7350);
INSERT INTO Price VALUES(44, 140, 880, 2940);
INSERT INTO Price VALUES(45, 230, 1440, 4830);
INSERT INTO Price VALUES(46, 180, 1130, 3780);
INSERT INTO Price VALUES(47, 330, 2070, 6930);
INSERT INTO Price VALUES(48, 270, 1700, 5670);
INSERT INTO Price VALUES(49, 330, 2070, 6930);
INSERT INTO Price VALUES(50, 250, 1570, 5250);
INSERT INTO Price VALUES(51, 210, 1320, 4410);
INSERT INTO Price VALUES(52, 400, 2520, 8400);
INSERT INTO Price VALUES(53, 330, 2070, 6930);
INSERT INTO Price VALUES(54, 170, 1070, 3570);
INSERT INTO Price VALUES(55, 100, 630, 2100);
INSERT INTO Price VALUES(56, 190, 1190, 3990);
INSERT INTO Price VALUES(57, 270, 1700, 5670);
INSERT INTO Price VALUES(58, 270, 1700, 5670);
INSERT INTO Price VALUES(59, 230, 1440, 4830);
INSERT INTO Price VALUES(60, 310, 1950, 6510);
INSERT INTO Price VALUES(61, 100, 630, 2100);
INSERT INTO Price VALUES(62, 200, 1260, 4200);
INSERT INTO Price VALUES(63, 410, 2580, 8610);
INSERT INTO Price VALUES(64, 210, 1320, 4410);
INSERT INTO Price VALUES(65, 230, 1440, 4830);
INSERT INTO Price VALUES(66, 390, 2450, 8190);
INSERT INTO Price VALUES(67, 200, 1260, 4200);
INSERT INTO Price VALUES(68, 100, 630, 2100);
INSERT INTO Price VALUES(69, 310, 1950, 6510);
INSERT INTO Price VALUES(70, 330, 2070, 6930);
INSERT INTO Price VALUES(71, 200, 1260, 4200);
INSERT INTO Price VALUES(72, 350, 2200, 7350);
INSERT INTO Price VALUES(73, 230, 1440, 4830);
INSERT INTO Price VALUES(74, 230, 1440, 4830);
INSERT INTO Price VALUES(75, 240, 1510, 5040);
INSERT INTO Price VALUES(76, 700, 4410, 14700);
INSERT INTO Price VALUES(77, 470, 2960, 9870);
INSERT INTO Price VALUES(78, 1630, 10260, 34230);
INSERT INTO Price VALUES(79, 690, 4340, 14490);
INSERT INTO Price VALUES(80, 400, 2520, 8400);
INSERT INTO Price VALUES(81, 390, 2450, 8190);
INSERT INTO Price VALUES(82, 570, 3590, 11970);
INSERT INTO Price VALUES(83, 650, 4090, 13650);
INSERT INTO Price VALUES(84, 450, 2830, 9450);
INSERT INTO Price VALUES(85, 600, 3780, 12600);
INSERT INTO Price VALUES(86, 320, 2010, 6720);
INSERT INTO Price VALUES(87, 150, 940, 3150);
INSERT INTO Price VALUES(88, 400, 2520, 8400);
INSERT INTO Price VALUES(89, 250, 1570, 5250);

INSERT INTO Rental VALUES('philippapi86', 1, '16-DEC-22', '17-DEC-22');
INSERT INTO Rental VALUES('emrefo07', 3, '20-OCT-22', '27-OCT-22');
INSERT INTO Rental VALUES('eugenemi30', 4, '18-NOV-22', '25-NOV-22');
INSERT INTO Rental VALUES('ricobo', 5, '4-NOV-22', '11-NOV-22');
INSERT INTO Rental VALUES('emrefo07', 6, '29-OCT-22', '5-NOV-22');
INSERT INTO Rental VALUES('farisbo874', 8, '7-DEC-22', '6-JAN-23');
INSERT INTO Rental VALUES('philippapi86', 10, '10-DEC-22', '17-DEC-22');
INSERT INTO Rental VALUES('stanleyng048', 11, '21-NOV-22', '21-DEC-22');
INSERT INTO Rental VALUES('codyan6', 13, '18-NOV-22', '18-DEC-22');
INSERT INTO Rental VALUES('kelsiego', 14, '5-NOV-22', '5-DEC-22');
INSERT INTO Rental VALUES('adambr5', 16, '17-NOV-22', '18-NOV-22');
INSERT INTO Rental VALUES('lorraineca8', 17, '1-DEC-22', '31-DEC-22');
INSERT INTO Rental VALUES('aaryanco92', 18, '11-NOV-22', '18-NOV-22');
INSERT INTO Rental VALUES('zaydmc', 19, '3-NOV-22', '10-NOV-22');
INSERT INTO Rental VALUES('kelsiego', 21, '4-NOV-22', '4-DEC-22');
INSERT INTO Rental VALUES('whitneyca8', 22, '12-DEC-22', '11-JAN-23');
INSERT INTO Rental VALUES('verityco91', 23, '18-OCT-22', '19-OCT-22');
INSERT INTO Rental VALUES('maxga7', 24, '28-NOV-22', '5-DEC-22');
INSERT INTO Rental VALUES('kyronmc562', 25, '31-OCT-22', '30-NOV-22');
INSERT INTO Rental VALUES('yasminsl02', 26, '15-DEC-22', '16-DEC-22');
INSERT INTO Rental VALUES('leightonwa', 27, '2-DEC-22', '9-DEC-22');
INSERT INTO Rental VALUES('leightonwa', 28, '24-OCT-22', '23-NOV-22');
INSERT INTO Rental VALUES('shivamwy9', 29, '24-OCT-22', '25-OCT-22');
INSERT INTO Rental VALUES('freddyru46', 30, '28-OCT-22', '27-NOV-22');
INSERT INTO Rental VALUES('ricobo', 33, '29-OCT-22', '5-NOV-22');
INSERT INTO Rental VALUES('jaydonpo808', 34, '27-OCT-22', '3-NOV-22');
INSERT INTO Rental VALUES('ruqayyahbu800', 36, '17-NOV-22', '17-DEC-22');
INSERT INTO Rental VALUES('kaidenst33', 38, '25-NOV-22', '26-NOV-22');
INSERT INTO Rental VALUES('michealca', 39, '4-DEC-22', '5-DEC-22');
INSERT INTO Rental VALUES('husseinmu58', 40, '1-DEC-22', '8-DEC-22');
INSERT INTO Rental VALUES('kaidenst33', 41, '22-NOV-22', '22-DEC-22');
INSERT INTO Rental VALUES('kelsiego', 42, '7-NOV-22', '7-DEC-22');
INSERT INTO Rental VALUES('harriettdr', 43, '9-DEC-22', '10-DEC-22');
INSERT INTO Rental VALUES('lily-mayhe638', 45, '8-DEC-22', '15-DEC-22');
INSERT INTO Rental VALUES('stanleyng048', 47, '6-NOV-22', '7-NOV-22');
INSERT INTO Rental VALUES('theresaha9', 50, '19-OCT-22', '20-OCT-22');
INSERT INTO Rental VALUES('shivamwy9', 58, '29-NOV-22', '6-DEC-22');
INSERT INTO Rental VALUES('lorenzola4', 59, '5-NOV-22', '6-NOV-22');
INSERT INTO Rental VALUES('kelsiego', 63, '12-NOV-22', '19-NOV-22');
INSERT INTO Rental VALUES('leightonwa', 64, '25-NOV-22', '25-DEC-22');
INSERT INTO Rental VALUES('kelsiego', 65, '30-OCT-22', '29-NOV-22');
INSERT INTO Rental VALUES('kelsiego', 66, '16-DEC-22', '17-DEC-22');
INSERT INTO Rental VALUES('leightonwa', 67, '12-NOV-22', '19-NOV-22');
INSERT INTO Rental VALUES('whitneyca8', 70, '30-NOV-22', '7-DEC-22');
INSERT INTO Rental VALUES('ruqayyahbu800', 71, '13-DEC-22', '14-DEC-22');
INSERT INTO Rental VALUES('leightonwa', 72, '15-NOV-22', '16-NOV-22');
INSERT INTO Rental VALUES('michealca', 74, '2-NOV-22', '3-NOV-22');
INSERT INTO Rental VALUES('kelsiego', 81, '17-NOV-22', '24-NOV-22');
