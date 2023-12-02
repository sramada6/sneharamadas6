INSERT INTO Sprint (TEAM_SIZE, SPRINT_LENGTH, STORY_POINTS_COMPLETED, SCRUM_CALL_LENGTH, START_DATE, END_DATE)
VALUES (5, 2, 20, 15, '2023-01-01', '2023-01-14');

INSERT INTO PLAYER (PLAYER_NAME, PLAYER_SCORE, SPRINT_ID)
VALUES ('Admin',  100, 1);


INSERT INTO PROBLEM_STATEMENT (NUM_OF_USER_STORIES, STATEMENT, COMMENTS)
VALUES (5, 'Inefficient Data Processing', 'The current data processing system is slow and needs optimization.');
INSERT INTO PROBLEM_STATEMENT (NUM_OF_USER_STORIES, STATEMENT, COMMENTS)
VALUES (3, 'Lack of Mobile Responsiveness', 'The application lacks responsive design, causing usability issues on mobile devices.');
INSERT INTO PROBLEM_STATEMENT (NUM_OF_USER_STORIES, STATEMENT, COMMENTS)
VALUES (8, 'Security Vulnerabilities', 'There are identified security vulnerabilities that need immediate attention.');


INSERT INTO USER_STORY (COMPLETION_DATE, CREATION_DATE, START_DATE, STATUS, STORY_DESCRIPTION, STORY_POINTS, STORY_TITLE, WORK_REMAINING, ASSIGNED_PLAYER_ID, PROBLEM_ID)
VALUES ('2023-02-28 16:45:00', '2023-02-15 10:00:00', '2023-02-20 09:30:00', 'Ready', 'Optimize database queries for better performance', 5, 'Database Optimization', 2,1, 1);

INSERT INTO USER_STORY (COMPLETION_DATE, CREATION_DATE, START_DATE, STATUS, STORY_DESCRIPTION, STORY_POINTS, STORY_TITLE, WORK_REMAINING, ASSIGNED_PLAYER_ID, PROBLEM_ID)
VALUES
('2023-01-15', '2023-01-01', '2023-01-02', 'Ready', 'Develop a login feature for the application', 1, 'Implement User Login', 0, 1, 1),
(NULL, '2023-01-03', '2023-01-05', 'Ready', 'Design the homepage layout and navigation', 2, 'Homepage Design', 1, 1, 1),
('2023-01-15', '2023-01-06', '2023-01-08', 'Ready', 'Write unit tests for the data processing module', 3, 'Unit Testing for Data Module', 2, 1, 1),
('2023-02-01', '2023-01-15', '2023-01-20', 'Ready', 'Implement user registration functionality', 5, 'User Registration Feature', 3, 1, 1),
('2023-02-10', '2023-02-05', '2023-02-08', 'Ready', 'Create a dashboard for user analytics', 1, 'User Analytics Dashboard', 0, 1, 1),
('2023-02-25', '2023-02-15', '2023-02-18', 'Ready', 'Optimize database queries for improved performance', 2, 'Database Performance Optimization', 1, 1, 1),
('2023-03-10', '2023-03-01', '2023-03-05', 'Ready', 'Implement a notification system for user alerts', 3, 'User Notification System', 2, 1, 1),
('2023-03-25', '2023-03-15', '2023-03-20', 'Ready', 'Design and implement a user feedback feature', 5, 'User Feedback System', 4, 1, 1),
('2023-04-05', '2023-04-01', '2023-04-03', 'Ready', 'Integrate third-party API for data synchronization', 1, 'API Integration for Data Sync', 0, 1, 1),
('2023-04-20', '2023-04-10', '2023-04-15', 'Ready', 'Implement a user profile management system', 2, 'User Profile Management', 1, 1, 1);