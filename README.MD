# Load Database From CSV

### In this project, we read data from csv file, construct model object in load functions and create record in database. The tables we populate include: 
#### 1.Private Database: Students, Courses, Electives, WorkExperiences, PriorEducations, Photos, Privacies
#### 2.Admin Database: Electives
#### 3.Public Database: Students, Undergraduates, WorkExperiences, as well as Aggregated Data

### To load database, we have to prepare CSV file first. All the required sample files are stored in samples folder. The header has already been defined for each CSV. Please keep header unchanged and populate the content.
<img width="367" alt="screen shot 2018-05-09 at 10 39 02 pm" src="https://user-images.githubusercontent.com/19380097/39854174-435706e6-53db-11e8-95bf-f5f2887d6385.png">

### All load functions are defined in loadRecords folder. We map each row in CSV to a data model object. For new record, we insert it into database, while for existed record, we update it.
<img width="1411" alt="screen shot 2018-05-09 at 11 38 15 pm" src="https://user-images.githubusercontent.com/19380097/39855783-2b715a2a-53e2-11e8-953e-52a877005b3f.png">

### There are two ways to start load process.
#### The 1st way: Run main function in MainScript. We need to comment out line 65-70 and specify the directory of CSV files explicitly. If we run in 2nd way, we need to comment out line 72-76 and uncomment line 65-70.
<img width="1383" alt="screen shot 2018-05-09 at 10 59 31 pm" src="https://user-images.githubusercontent.com/19380097/39854490-c72668d0-53dc-11e8-8434-3011d724a1bb.png">

#### The 2nd way: Package the project into jar file.
##### Define artifacts. Please note MANIFEST.MF file should be in resourses folder.
<img width="1026" alt="screen shot 2018-05-09 at 11 15 16 pm" src="https://user-images.githubusercontent.com/19380097/39855081-51e8f814-53df-11e8-840c-00a5f83f5176.png">

##### Build artifacts. Jar file is output to the directory as we specified above.
<img width="931" alt="screen shot 2018-05-09 at 11 23 39 pm" src="https://user-images.githubusercontent.com/19380097/39855251-0b867ae4-53e0-11e8-972e-68353a7d08f8.png">

##### And then run the jar using this command: java -jar CSV.jar [StudentFilePath] [CourseFilePath] [ElectiveFilePath] [WorkExperienceFilePath] [PriorEducationFilePath]
<img width="1416" alt="screen shot 2018-05-09 at 11 36 09 pm" src="https://user-images.githubusercontent.com/19380097/39855692-c8f8c838-53e1-11e8-9e89-c523f1c89316.png">

### Note:
#### 1.If there is any change in database schema, we have to change data models, hibernate mappings, csv file format, load functions correspondingly, and rebuild the jar.
#### 2.Hibernate mappings and configurations are defined in resources.
