INSERT INTO question (content, timer_question, timer_response, quiz_id)
   VALUES ("Qui vient d&#39;être élu à la présidence de Les Républicains ?", 3, 10,1),
   ("Au coeur de quelle réserve naturelle un cargo s&#39;est-il échoué ?", 3, 10, 1),
   ("Quel pourcentage de Français serait « illectroné » (incapable de maîtriser les outils numériques) ?", 3, 10, 1),
   ("Avec quel pays les Kurdes ont conclu un accord ce week-end ?", 3, 10, 1),
   ("Quel typhon vient de frapper le Japon causant au moins 26 morts ?", 3, 10, 1),
   ("Combien de canonisations ont été réalisés ce week-end par le pape François ?", 3, 10, 1),
   ("Sur quelle plage deux migrants ont-ils été retrouvés morts ?", 3, 10, 1),
   ("Kais Saied est devenu président de la Tunisie. Quelle profession exerce-t-il ?", 3, 10, 1),
   ("Quel est le nom du navire qui vient de secourir 176 personnes en Méditerranée ?", 3, 10, 1), 
   ("Quelle équipe affronte Les Bleus ce soir dans le cadre qualifications à l&#39;Euro 2020 ?", 3, 10, 1);


INSERT INTO response (content, is_true, question_id) VALUES ( "Julien Aubert", b'0', 1), ("Christian Jacob", b'1', 1), ("Guillaume Larrivé", b'0', 1), ("Laurent Wauquiez", b'0', 1);
INSERT INTO response (content, is_true, question_id) VALUES ( "Les Bouches de Bonifacio", b'1', 2), ("Les Bouches de Bonifacio", b'0', 2), ("Les îles Cerbicale", b'0', 2), ("Scandola", b'0', 2);
INSERT INTO response (content, is_true, question_id) VALUES ( "6 millions", b'0', 3), ("11 millions", b'1', 3), ("16 millions", b'0', 3), ("21 millions", b'0', 3);
INSERT INTO response (content, is_true, question_id) VALUES ( "L'Arménie", b'0', 4), ("L'Irak", b'0', 4), ("L'Iran", b'0', 4), ("La Syrie", b'1', 4);
INSERT INTO response (content, is_true, question_id) VALUES ( "Mangkhut", b'0', 5), ("Haiyan", b'0', 5), ("Hagibis", b'1', 5), ("Yolanda", b'0', 5);
INSERT INTO response (content, is_true, question_id) VALUES ( "1", b'0', 6), ("3", b'0', 6), ("5", b'1', 6), ("7", b'0', 6);
INSERT INTO response (content, is_true, question_id) VALUES ( "Berck", b'0', 7), ("Boulogne", b'0', 7), ("Le Touquet", b'1', 7), ("Calais", b'0', 7);
INSERT INTO response (content, is_true, question_id) VALUES ( "Avocat", b'0', 8), ("Juge", b'0', 8), ("Juriste", b'1', 8), ("Notaire", b'0', 8);
INSERT INTO response (content, is_true, question_id) VALUES ( "Rainbow Warrior", b'0', 9), ("Aquarius", b'0', 9), ("Sea Sheperd", b'0', 9), ("Ocean Viking", b'1', 9);
INSERT INTO response (content, is_true, question_id) VALUES ( "L'Islande", b'0', 10), ("La Turquie", b'1', 10), ("L'Albanie", b'0', 10), ("Andorre", b'0', 10);

INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/societe/live/2019/11/14/crise-des-hopitaux-suivez-en-direct-la-journee-de-mobilisation-nationale_6019142_3224.html", "Le monde","Des médecins aux aides-soignantes, des doyens aux étudiants, un hôpital public à bout de souffle.", "Crise des hôpitaux : suivez en direct la journée de mobilisation nationale", 1);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/international/article/2019/11/14/donald-trump-se-montre-accommodant-avec-recep-tayyip-erdogan_6019154_3210.html","Le monde","Après plusieurs semaines tendues autour de l’offensive d’Ankara contre les alliés kurdes dans le nord-est syrien, le président américain a accueilli très chaleureusement son homologue turc, mercredi 13 novembre.", "Donald Trump se montre accommodant avec Recep Tayyip Erdogan", 2);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/societe/live/2019/11/14/crise-des-hopitaux-suivez-en-direct-la-journee-de-mobilisation-nationale_6019142_3224.html", "Le monde","Des médecins aux aides-soignantes, des doyens aux étudiants, un hôpital public à bout de souffle.", "Crise des hôpitaux : suivez en direct la journée de mobilisation nationale", 3);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/international/article/2019/11/14/donald-trump-se-montre-accommodant-avec-recep-tayyip-erdogan_6019154_3210.html","Le monde","Après plusieurs semaines tendues autour de l’offensive d’Ankara contre les alliés kurdes dans le nord-est syrien, le président américain a accueilli très chaleureusement son homologue turc, mercredi 13 novembre.", "Donald Trump se montre accommodant avec Recep Tayyip Erdogan", 4);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/societe/live/2019/11/14/crise-des-hopitaux-suivez-en-direct-la-journee-de-mobilisation-nationale_6019142_3224.html", "Le monde","Des médecins aux aides-soignantes, des doyens aux étudiants, un hôpital public à bout de souffle.", "Crise des hôpitaux : suivez en direct la journée de mobilisation nationale", 5);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/international/article/2019/11/14/donald-trump-se-montre-accommodant-avec-recep-tayyip-erdogan_6019154_3210.html","Le monde","Après plusieurs semaines tendues autour de l’offensive d’Ankara contre les alliés kurdes dans le nord-est syrien, le président américain a accueilli très chaleureusement son homologue turc, mercredi 13 novembre.", "Donald Trump se montre accommodant avec Recep Tayyip Erdogan", 6);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/societe/live/2019/11/14/crise-des-hopitaux-suivez-en-direct-la-journee-de-mobilisation-nationale_6019142_3224.html", "Le monde","Des médecins aux aides-soignantes, des doyens aux étudiants, un hôpital public à bout de souffle.", "Crise des hôpitaux : suivez en direct la journée de mobilisation nationale", 7);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/international/article/2019/11/14/donald-trump-se-montre-accommodant-avec-recep-tayyip-erdogan_6019154_3210.html","Le monde","Après plusieurs semaines tendues autour de l’offensive d’Ankara contre les alliés kurdes dans le nord-est syrien, le président américain a accueilli très chaleureusement son homologue turc, mercredi 13 novembre.", "Donald Trump se montre accommodant avec Recep Tayyip Erdogan", 8);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/societe/live/2019/11/14/crise-des-hopitaux-suivez-en-direct-la-journee-de-mobilisation-nationale_6019142_3224.html", "Le monde","Des médecins aux aides-soignantes, des doyens aux étudiants, un hôpital public à bout de souffle.", "Crise des hôpitaux : suivez en direct la journée de mobilisation nationale", 9);
INSERT INTO article(link, media, summary, title, question_id) VALUES
("https://www.lemonde.fr/international/article/2019/11/14/donald-trump-se-montre-accommodant-avec-recep-tayyip-erdogan_6019154_3210.html","Le monde","Après plusieurs semaines tendues autour de l’offensive d’Ankara contre les alliés kurdes dans le nord-est syrien, le président américain a accueilli très chaleureusement son homologue turc, mercredi 13 novembre.", "Donald Trump se montre accommodant avec Recep Tayyip Erdogan", 10);


