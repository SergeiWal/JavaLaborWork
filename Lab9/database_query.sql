use lab9;
create table Users
(
	id int primary key auto_increment,
    user_name nvarchar(20),
    login nvarchar(20),
    user_password bigint,
    user_role nvarchar(10),
    check(user_role in('user','admin'))
);

ALTER TABLE Users
MODIFY COLUMN user_password nvarchar(50);


select * from Users;
insert Users(user_name, login, user_password, user_role)
	values('Sergei','hateGnom', '1234', 'admin');
    
delete from Users;

use lab9;
drop table Uniwers;
create table Uniwers
(
	id int primary key auto_increment,
	uni_name nvarchar(100),
    city nvarchar(50),
    country nvarchar(50)
);


insert into Uniwers ( uni_name, city, country) values ('Universitas 17 Agustus 1945 Cirebon', 'Waitenepang', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ('Centre d''Etudes Supérieures Industrielles Paris', 'Nîmes', 'France');
insert into Uniwers ( uni_name, city, country) values ('Molde University College', 'Mo I Rana', 'Norway');
insert into Uniwers ( uni_name, city, country) values ('Technological University (Hmawbi)', 'Monywa', 'Myanmar');
insert into Uniwers ( uni_name, city, country) values ('Ouachita Baptist University', 'Charleston', 'United States');
insert into Uniwers ( uni_name, city, country) values ('Tianjin University of Commerce', 'Sishan', 'China');
insert into Uniwers ( uni_name, city, country) values ('Pontifícia Universidade Católica do Rio de Janeiro', 'Viradouro', 'Brazil');
insert into Uniwers ( uni_name, city, country) values ('Staatliche Hochschule für Musik', 'Cottbus', 'Germany');
insert into Uniwers ( uni_name, city, country) values ('Tokuyama University', 'Kasama', 'Japan');
insert into Uniwers ( uni_name, city, country) values ( 'Dongbei University of Finance And Economics', 'Yangfang', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Trisakti Institute of Tourism ', 'Belajen', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'Birsk State Pedagogical Institute', 'Gorno-Altaysk', 'Russia');
insert into Uniwers ( uni_name, city, country) values ( 'Södertörn University College', 'Södertälje', 'Sweden');
insert into Uniwers ( uni_name, city, country) values ( 'Université de Bangui', 'Ndélé', 'Central African Republic');
insert into Uniwers ( uni_name, city, country) values ( 'Bratsk State Technical University', 'Vesëlyy', 'Russia');
insert into Uniwers ( uni_name, city, country) values ( 'Voronezh State Technical University', 'Ol’ginka', 'Russia');
insert into Uniwers ( uni_name, city, country) values ( 'Yaroslavl International University of Business and New Technologies', 'Vereshchagino', 'Russia');
insert into Uniwers ( uni_name, city, country) values ( 'Tianjin University of Technology', 'Yuanmou', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Institute of Teachers Education, Batu Lintang', 'Kuching', 'Malaysia');
insert into Uniwers ( uni_name, city, country) values ( 'Jiangxi Normal University', 'Hengjie', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Arab European University', '‘Uqayribāt', 'Syria');
insert into Uniwers ( uni_name, city, country) values ( 'Philosophisch-Theologische Hochschule SVD Sankt Augustin', 'Tobi Village', 'Palau');
insert into Uniwers ( uni_name, city, country) values ( 'Universitas Mahasaraswati Denpasar', 'Tabu', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'The University of Nottingham Ningbo China', 'Jianshan', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Instituto Superior Politécnico Gaya', 'Casal', 'Portugal');
insert into Uniwers ( uni_name, city, country) values ( 'Universitas Pembangunan Panca Budi', 'Jatirejo', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'Jiangxi University of Finance and Economics', 'Chifeng', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Dalian University of Foreign Language', 'Pinglin', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Jiangxi University of Finance and Economics', 'Gaopu', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Instituto Universitario Aeronáutico', 'Villa Ángela', 'Argentina');
insert into Uniwers ( uni_name, city, country) values ( 'Instituts Supérieurs des Etudes Technologiques', 'Sousse', 'Tunisia');
insert into Uniwers ( uni_name, city, country) values ( 'Ecole Nationale Vétérinaire de Lyon', 'Paris La Défense', 'France');
insert into Uniwers ( uni_name, city, country) values ( 'Leyte State University ', 'Jolo', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Beijing Information Science and Technology University', 'Pagnag', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Ningbo University', 'Lang', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Yan''an University', 'Qingshui', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'International University Institute of Luxembourg', 'Fentange', 'Luxembourg');
insert into Uniwers ( uni_name, city, country) values ( 'Bicol University', 'Guiuan', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Ecole Supérieure de Commerce de Paris', 'Montbéliard', 'France');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad del Norte', 'San Pedro del Paraná', 'Paraguay');
insert into Uniwers ( uni_name, city, country) values ( 'Theatre Academy Finland', 'Luopioinen', 'Finland');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad de Matanzas Camilo Cienfuegos', 'Banes', 'Cuba');
insert into Uniwers ( uni_name, city, country) values ( 'Hiroshima Jogakuin University', 'Amagasaki', 'Japan');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad Andina Simón Bolívar', 'Macas', 'Ecuador');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad Austral Buenos Aires', 'Fray Luis A. Beltrán', 'Argentina');
insert into Uniwers ( uni_name, city, country) values ( 'Western State College', 'Portland', 'United States');
insert into Uniwers ( uni_name, city, country) values ( 'Zhejiang University', 'Nehe', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Universidade do Estado do Rio Grande do Norte', 'Boa Vista', 'Brazil');
insert into Uniwers ( uni_name, city, country) values ( 'Technological Education Institute of Kavala', 'Profítis Ilías', 'Greece');
insert into Uniwers ( uni_name, city, country) values ( 'Mendel University of Agriculture and Forestry', 'Radvanice', 'Czech Republic');
insert into Uniwers ( uni_name, city, country) values ( 'Novosibirsk State University', 'Kipen’', 'Russia');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad de Sevilla', 'Cartagena', 'Spain');
insert into Uniwers ( uni_name, city, country) values ( 'Southern Medial University', 'Shatoujiao', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Lulea University of Technology', 'Åstorp', 'Sweden');
insert into Uniwers ( uni_name, city, country) values ( 'Hawassa University', 'Bahir Dar', 'Ethiopia');
insert into Uniwers ( uni_name, city, country) values ( 'Wuhan University School of Medicine', 'Jiangkou', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Mahamakut Buddhist University', 'Kathu', 'Thailand');
insert into Uniwers ( uni_name, city, country) values ( 'Ateneo de Naga University', 'Union', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad de Lima', 'Quiches', 'Peru');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad Especializada de Las Americas', 'Santa Marta', 'Panama');
insert into Uniwers ( uni_name, city, country) values ( 'Wadi International University', 'Al Khafsah', 'Syria');
insert into Uniwers ( uni_name, city, country) values ( 'Don Mariano Marcos Memorial State University', 'Bay-ang', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'National Pedagogical University M. Dragomanov ', 'Manyava', 'Ukraine');
insert into Uniwers ( uni_name, city, country) values ( 'Adam Mickiewicz University of Poznan', 'Purda', 'Poland');
insert into Uniwers ( uni_name, city, country) values ( 'Universitas Yarsi', 'Cilangkap', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'Technical University of Radom', 'Uścimów Stary', 'Poland');
insert into Uniwers ( uni_name, city, country) values ( 'City University Athens', 'Lárdos', 'Greece');
insert into Uniwers ( uni_name, city, country) values ( 'Instituto Tecnológico Metropolitano', 'La Montañita', 'Colombia');
insert into Uniwers ( uni_name, city, country) values ( 'Universidade de Tras-os-Montes e Alto Douro', 'Pataias', 'Portugal');
insert into Uniwers ( uni_name, city, country) values ( 'Ecole Universitaire d''Ingénieurs de Lille', 'Orange', 'France');
insert into Uniwers ( uni_name, city, country) values ( 'Howard University', 'George Hill', 'Anguilla');
insert into Uniwers ( uni_name, city, country) values ( 'Université de Bandundu Ville', 'Kampene', 'Democratic Republic of the Congo');
insert into Uniwers ( uni_name, city, country) values ( 'Dalian University', 'Xinzhou', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Armenian State University of Economics', 'Noyakert', 'Armenia');
insert into Uniwers ( uni_name, city, country) values ( 'Poznan University of Life Sciences', 'Mokotów', 'Poland');
insert into Uniwers ( uni_name, city, country) values ( 'Universitas Gadjah Mada', 'Jembayan Hitam', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'American University of Tirana', 'Luftinjë', 'Albania');
insert into Uniwers ( uni_name, city, country) values ( 'Hunan Normal University', 'Guizi', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'National University of Lesotho', 'Butha-Buthe', 'Lesotho');
insert into Uniwers ( uni_name, city, country) values ( 'Jiangxi University of Traditional Chinese Medicine', 'Aba', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Beijing Foreign Studies University', 'Yiqi', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Chongqing Wenli University', 'Cennan', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Jaramogi Oginga Odinga University of Science and Technology', 'Nanyuki', 'Kenya');
insert into Uniwers ( uni_name, city, country) values ( 'Dhurakijpundit University', 'Chat Trakan', 'Thailand');
insert into Uniwers ( uni_name, city, country) values ( 'Nanjing Normal University', 'Balitai', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Karolinska Institute Stockholm', 'Täby', 'Sweden');
insert into Uniwers ( uni_name, city, country) values ( 'Lankaran State University', 'Hacıqabul', 'Azerbaijan');
insert into Uniwers ( uni_name, city, country) values ( 'Universidad Comunera', 'Capitán Bado', 'Paraguay');
insert into Uniwers ( uni_name, city, country) values ( 'University of the East, Coloocan', 'Masaraway', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Anhui Medical University', 'Lucun', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'Araullo University', 'Dian-ay', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Tshwane University of Technology', 'Clanwilliam', 'South Africa');
insert into Uniwers ( uni_name, city, country) values ( 'North Kazakhstan State University', 'Zhezqazghan', 'Kazakhstan');
insert into Uniwers ( uni_name, city, country) values ( 'Rizal Technological University', 'Dapdap', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Universitas Simalungun', 'Sugiharjo', 'Indonesia');
insert into Uniwers ( uni_name, city, country) values ( 'Sibelius Academy', 'Himanka', 'Finland');
insert into Uniwers ( uni_name, city, country) values ( 'Universidade Tiradentes', 'Rancharia', 'Brazil');
insert into Uniwers ( uni_name, city, country) values ( 'University of the Philippines Mindanao', 'Balangiga', 'Philippines');
insert into Uniwers ( uni_name, city, country) values ( 'Shandong Normal University', 'Lecong', 'China');
insert into Uniwers ( uni_name, city, country) values ( 'University of Bihac', 'Bosanski Novi', 'Bosnia and Herzegovina');

select * from Uniwers;
delete from Uniwers where uni_name is null;
