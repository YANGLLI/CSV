#Load Database From CSV

##In this project, we read data from csv file, construct model object in load functions and create record in database. The tables we populate include: 
####1.Private Database: Students, Courses, Electives, WorkExperiences, PriorEducations, Photos, Privacies
####2.Admin Database: Electives
####3.Public Database: Students, Undergraduates, WorkExperiences, as well as Aggregated Data

## User can load database by running main function in MainScript, or running the jar. 
####1. The jar file resides in folder out > artifacts > CSV_jar > CSV.jar
####2. To load database from csv files, run the jar with file path: java -jar CSV.jar [StudentFilePath] [CourseFilePath] [ElectiveFilePath] [WorkExperienceFilePath] [PriorEducationFilePath]

## If there is any change in database schema, dev has to change models, hibernate mappings, csv file, load functions correspondingly, and rebuild the jar.

## Detail load functions are implemented in loadRecords. Hibernate mappings and configurations are defined in resources.

## CSV samples are created in samples folder. A Jupyter Notebook can be found in the same folder which was used to generate sample files.
