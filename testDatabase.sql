CREATE TABLE electionDistrict (
 zip CHAR(10) NOT NULL,
 districtName CHAR(20)
);

ALTER TABLE electionDistrict ADD CONSTRAINT PK_electionDistrict PRIMARY KEY (zip);


CREATE TABLE politicalParty (
 partyID CHAR(10) NOT NULL,
 partyName CHAR(10),
 color CHAR(10)
);

ALTER TABLE politicalParty ADD CONSTRAINT PK_politicalParty PRIMARY KEY (partyID);


CREATE TABLE user (
 userID CHAR(20) NOT NULL,
 password CHAR(20) NOT NULL,
 firstName CHAR(20),
 lastName CHAR(20),
 address CHAR(10),
 state CHAR(15),
 county CHAR(20),
 email CHAR(25)
);

ALTER TABLE user ADD CONSTRAINT PK_user PRIMARY KEY (userID);


CREATE TABLE voter (
 voterName CHAR(20) NOT NULL,
 age INT,
 zip CHAR(10),
 userID CHAR(20) NOT NULL
);

ALTER TABLE voter ADD CONSTRAINT PK_voter PRIMARY KEY (voterName);


CREATE TABLE electionOfficer (
 EOName CHAR(10) NOT NULL,
 userID CHAR(20) NOT NULL
);

ALTER TABLE electionOfficer ADD CONSTRAINT PK_electionOfficer PRIMARY KEY (EOName);


CREATE TABLE ballot (
 ballotID CHAR(10) NOT NULL,
 zip CHAR(10),
 EOName CHAR(10),
 bName CHAR(10),
 approved VARCHAR(3)
);

ALTER TABLE ballot ADD CONSTRAINT PK_ballot PRIMARY KEY (ballotID);


CREATE TABLE ballotItem (
 itemID CHAR(10) NOT NULL,
 ballotID CHAR(10),
 voteCount CHAR(10)
);

ALTER TABLE ballotItem ADD CONSTRAINT PK_ballotItem PRIMARY KEY (itemID);


CREATE TABLE election (
 electionName CHAR(30) NOT NULL,
 itemID CHAR(10),
 startDate DATE,
 endDate DATE,
 isPartisan CHAR(3)
);

ALTER TABLE election ADD CONSTRAINT PK_election PRIMARY KEY (electionName);


CREATE TABLE issue (
 issueID CHAR(10) NOT NULL,
 itemID CHAR(20),
 questionTitle CHAR(30),
 description CHAR(60),
 yesCount INT,
 noCount INT
);

ALTER TABLE issue ADD CONSTRAINT PK_issue PRIMARY KEY (issueID);


CREATE TABLE voteRecord (
 recordID CHAR(20) NOT NULL,
 voterName CHAR(20),
 ballotID CHAR(10),
 date DATE
);

ALTER TABLE voteRecord ADD CONSTRAINT PK_voteRecord PRIMARY KEY (recordID);


CREATE TABLE candidate (
 choiceID CHAR(10) NOT NULL,
 electionName CHAR(30),
 partyID CHAR(10),
 title CHAR(10),
 voteCount INT,
 description CHAR(10)
);

ALTER TABLE candidate ADD CONSTRAINT PK_candidate PRIMARY KEY (choiceID);


ALTER TABLE voter ADD CONSTRAINT FK_voter_0 FOREIGN KEY (zip) REFERENCES electionDistrict (zip);
ALTER TABLE voter ADD CONSTRAINT FK_voter_1 FOREIGN KEY (userID) REFERENCES user (userID);


ALTER TABLE electionOfficer ADD CONSTRAINT FK_electionOfficer_0 FOREIGN KEY (userID) REFERENCES user (userID);


ALTER TABLE ballot ADD CONSTRAINT FK_ballot_0 FOREIGN KEY (zip) REFERENCES electionDistrict (zip);
ALTER TABLE ballot ADD CONSTRAINT FK_ballot_1 FOREIGN KEY (EOName) REFERENCES electionOfficer (EOName);


ALTER TABLE ballotItem ADD CONSTRAINT FK_ballotItem_0 FOREIGN KEY (ballotID) REFERENCES ballot (ballotID);


ALTER TABLE election ADD CONSTRAINT FK_election_0 FOREIGN KEY (itemID) REFERENCES ballotItem (itemID);


ALTER TABLE issue ADD CONSTRAINT FK_issue_0 FOREIGN KEY (itemID) REFERENCES ballotItem (itemID);


ALTER TABLE voteRecord ADD CONSTRAINT FK_voteRecord_0 FOREIGN KEY (voterName) REFERENCES voter (voterName);
ALTER TABLE voteRecord ADD CONSTRAINT FK_voteRecord_1 FOREIGN KEY (ballotID) REFERENCES ballot (ballotID);


ALTER TABLE candidate ADD CONSTRAINT FK_candidate_0 FOREIGN KEY (electionName) REFERENCES election (electionName);
ALTER TABLE candidate ADD CONSTRAINT FK_candidate_1 FOREIGN KEY (partyID) REFERENCES politicalParty (partyID);

INSERT INTO user (userID, password, firstName, lastName, address, state, county, email)
VALUES ("abc@xyz.com", "password", "john", "smith", "somewhere", "GA", "county1", "heyo@gmail.com");
 
INSERT INTO user (userID, password, firstName, lastName, address, state, county, email)
VALUES ("efg@xyz.com", "acesscode", "jane", "smith", "nowhere", "GA", "county2", "yo@gmail.com");

INSERT INTO user (userID, password, firstName, lastName, address, state, county, email)
VALUES ("u001", "password", "john", "smith", "somewhere", "GA", "county1", "heyo@gmail.com");
 
INSERT INTO user (userID, password, firstName, lastName, address, state, county, email)
VALUES ("u002", "acesscode", "jane", "smith", "nowhere", "GA", "county2", "yo@gmail.com");

INSERT INTO politicalParty (partyID, partyName, color)
VALUES("R1", "Republican", "red");

INSERT INTO politicalParty (partyID, partyName, color)
VALUES("NA", "NA", "white");

INSERT INTO politicalParty (partyID, partyName, color)
VALUES("D1", "Democrats", "blue");
