/*
 Navicat Premium Data Transfer

 Source Server         : ProjectWeb
 Source Server Type    : MySQL
 Source Server Version : 100432
 Source Host           : localhost:3306
 Source Schema         : ltw

 Target Server Type    : MySQL
 Target Server Version : 100432
 File Encoding         : 65001

 Date: 30/03/2024 11:47:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sizeTableImage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES (1, 'Áo dài tay', 'daiTaySizeGuide.png');
INSERT INTO `categories` VALUES (2, 'Áo oversize', 'oversizeSizeGuide.png');
INSERT INTO `categories` VALUES (3, 'Áo nỉ dài tay', 'niTayDaiSizeGuide.png');
INSERT INTO `categories` VALUES (4, 'Quần short', 'quanShortDaiSizeGuide.png');
INSERT INTO `categories` VALUES (5, 'Áo Polo', 'aoPoloSizeGuide.png');
INSERT INTO `categories` VALUES (6, 'Áo sơ mi tay dài', 'aoSoMiTayDaiSizeGuide.png');
INSERT INTO `categories` VALUES (7, 'Áo sơ mi ngắn tay', 'aoSoMiTayNganSizeGuide.png');
INSERT INTO `categories` VALUES (8, 'Quần jeans', 'quanJeanSizeGuide.png');
INSERT INTO `categories` VALUES (9, 'Quần Kaki', 'quanKakiSizeGuide.png');
INSERT INTO `categories` VALUES (10, 'Áo ba lỗ', 'aoBaLoSizeGuide.png');
INSERT INTO `categories` VALUES (11, 'Quần Joggers', 'quanJoggersSizeGuide.png');
INSERT INTO `categories` VALUES (21, 'Áo jacket', 'a5c1abd2-0975-4f50-9f88-49e92f62ce06.png');

-- ----------------------------
-- Table structure for colors
-- ----------------------------
DROP TABLE IF EXISTS `colors`;
CREATE TABLE `colors`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `codeColor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `productId` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `productId`(`productId`) USING BTREE,
  CONSTRAINT `FK__productId` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 895 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of colors
-- ----------------------------
INSERT INTO `colors` VALUES (1, '#ffffff', 1);
INSERT INTO `colors` VALUES (2, '#000000', 1);
INSERT INTO `colors` VALUES (3, '#ff0000', 1);
INSERT INTO `colors` VALUES (4, '#0000ff', 1);
INSERT INTO `colors` VALUES (5, '#ffffff', 2);
INSERT INTO `colors` VALUES (6, '#000000', 2);
INSERT INTO `colors` VALUES (7, '#ff0000', 2);
INSERT INTO `colors` VALUES (8, '#0000ff', 2);
INSERT INTO `colors` VALUES (9, '#ffffff', 3);
INSERT INTO `colors` VALUES (10, '#000000', 3);
INSERT INTO `colors` VALUES (11, '#ff0000', 3);
INSERT INTO `colors` VALUES (12, '#0000ff', 3);
INSERT INTO `colors` VALUES (13, '#ffffff', 4);
INSERT INTO `colors` VALUES (14, '#000000', 4);
INSERT INTO `colors` VALUES (15, '#ff0000', 4);
INSERT INTO `colors` VALUES (16, '#0000ff', 4);
INSERT INTO `colors` VALUES (17, '#ffffff', 5);
INSERT INTO `colors` VALUES (18, '#000000', 5);
INSERT INTO `colors` VALUES (19, '#ff0000', 5);
INSERT INTO `colors` VALUES (20, '#0000ff', 5);
INSERT INTO `colors` VALUES (21, '#ffffff', 6);
INSERT INTO `colors` VALUES (22, '#000000', 6);
INSERT INTO `colors` VALUES (23, '#FF0000', 6);
INSERT INTO `colors` VALUES (24, '#0000FF', 6);
INSERT INTO `colors` VALUES (25, '#ffffff', 7);
INSERT INTO `colors` VALUES (26, '#000000', 7);
INSERT INTO `colors` VALUES (27, '#FF0000', 7);
INSERT INTO `colors` VALUES (28, '#0000FF', 7);
INSERT INTO `colors` VALUES (29, '#ffffff', 8);
INSERT INTO `colors` VALUES (30, '#000000', 8);
INSERT INTO `colors` VALUES (31, '#FF0000', 8);
INSERT INTO `colors` VALUES (32, '#0000FF', 8);
INSERT INTO `colors` VALUES (33, '#ffffff', 9);
INSERT INTO `colors` VALUES (34, '#000000', 9);
INSERT INTO `colors` VALUES (35, '#ff0000', 9);
INSERT INTO `colors` VALUES (36, '#0000ff', 9);
INSERT INTO `colors` VALUES (37, '#ffffff', 10);
INSERT INTO `colors` VALUES (38, '#000000', 10);
INSERT INTO `colors` VALUES (39, '#ff0000', 10);
INSERT INTO `colors` VALUES (40, '#0000ff', 10);
INSERT INTO `colors` VALUES (41, '#ffffff', 11);
INSERT INTO `colors` VALUES (42, '#000000', 11);
INSERT INTO `colors` VALUES (43, '#FF0000', 11);
INSERT INTO `colors` VALUES (44, '#0000FF', 11);
INSERT INTO `colors` VALUES (45, '#ffffff', 12);
INSERT INTO `colors` VALUES (46, '#000000', 12);
INSERT INTO `colors` VALUES (47, '#FF0000', 12);
INSERT INTO `colors` VALUES (48, '#0000FF', 12);
INSERT INTO `colors` VALUES (49, '#ffffff', 13);
INSERT INTO `colors` VALUES (50, '#000000', 13);
INSERT INTO `colors` VALUES (51, '#ff0000', 13);
INSERT INTO `colors` VALUES (52, '#0000ff', 13);
INSERT INTO `colors` VALUES (53, '#ffffff', 14);
INSERT INTO `colors` VALUES (54, '#000000', 14);
INSERT INTO `colors` VALUES (55, '#ff0000', 14);
INSERT INTO `colors` VALUES (56, '#0000ff', 14);
INSERT INTO `colors` VALUES (57, '#ffffff', 15);
INSERT INTO `colors` VALUES (58, '#000000', 15);
INSERT INTO `colors` VALUES (59, '#ff0000', 15);
INSERT INTO `colors` VALUES (60, '#0000ff', 15);
INSERT INTO `colors` VALUES (61, '#ffffff', 16);
INSERT INTO `colors` VALUES (62, '#000000', 16);
INSERT INTO `colors` VALUES (63, '#FF0000', 16);
INSERT INTO `colors` VALUES (64, '#0000FF', 16);
INSERT INTO `colors` VALUES (65, '#ffffff', 17);
INSERT INTO `colors` VALUES (66, '#000000', 17);
INSERT INTO `colors` VALUES (67, '#FF0000', 17);
INSERT INTO `colors` VALUES (68, '#ffffff', 21);
INSERT INTO `colors` VALUES (69, '#ffffff', 22);
INSERT INTO `colors` VALUES (70, '#000000', 22);
INSERT INTO `colors` VALUES (71, '#FF0000', 22);
INSERT INTO `colors` VALUES (72, '#0000FF', 22);
INSERT INTO `colors` VALUES (73, '#ffffff', 23);
INSERT INTO `colors` VALUES (74, '#000000', 23);
INSERT INTO `colors` VALUES (75, '#ff0000', 23);
INSERT INTO `colors` VALUES (76, '#0000ff', 23);
INSERT INTO `colors` VALUES (77, '#ffffff', 24);
INSERT INTO `colors` VALUES (78, '#000000', 24);
INSERT INTO `colors` VALUES (79, '#ff0000', 24);
INSERT INTO `colors` VALUES (80, '#0000ff', 24);
INSERT INTO `colors` VALUES (81, '#ffffff', 25);
INSERT INTO `colors` VALUES (82, '#000000', 25);
INSERT INTO `colors` VALUES (83, '#ff0000', 25);
INSERT INTO `colors` VALUES (84, '#0000ff', 25);
INSERT INTO `colors` VALUES (85, '#ffffff', 26);
INSERT INTO `colors` VALUES (86, '#000000', 26);
INSERT INTO `colors` VALUES (87, '#FF0000', 26);
INSERT INTO `colors` VALUES (88, '#0000FF', 26);
INSERT INTO `colors` VALUES (89, '#ffffff', 27);
INSERT INTO `colors` VALUES (90, '#000000', 27);
INSERT INTO `colors` VALUES (91, '#FF0000', 27);
INSERT INTO `colors` VALUES (92, '#0000FF', 27);
INSERT INTO `colors` VALUES (93, '#ffffff', 28);
INSERT INTO `colors` VALUES (94, '#000000', 28);
INSERT INTO `colors` VALUES (95, '#FF0000', 28);
INSERT INTO `colors` VALUES (96, '#0000FF', 28);
INSERT INTO `colors` VALUES (97, '#ffffff', 29);
INSERT INTO `colors` VALUES (98, '#000000', 29);
INSERT INTO `colors` VALUES (99, '#ff0000', 29);
INSERT INTO `colors` VALUES (100, '#0000ff', 29);
INSERT INTO `colors` VALUES (101, '#ffffff', 30);
INSERT INTO `colors` VALUES (102, '#000000', 30);
INSERT INTO `colors` VALUES (103, '#FF0000', 30);
INSERT INTO `colors` VALUES (104, '#0000FF', 30);
INSERT INTO `colors` VALUES (105, '#ffffff', 31);
INSERT INTO `colors` VALUES (106, '#000000', 31);
INSERT INTO `colors` VALUES (107, '#FF0000', 31);
INSERT INTO `colors` VALUES (108, '#0000FF', 31);
INSERT INTO `colors` VALUES (109, '#ffffff', 32);
INSERT INTO `colors` VALUES (110, '#000000', 32);
INSERT INTO `colors` VALUES (111, '#ff0000', 32);
INSERT INTO `colors` VALUES (112, '#0000ff', 32);
INSERT INTO `colors` VALUES (113, '#ffffff', 33);
INSERT INTO `colors` VALUES (114, '#000000', 33);
INSERT INTO `colors` VALUES (115, '#ff0000', 33);
INSERT INTO `colors` VALUES (116, '#0000ff', 33);
INSERT INTO `colors` VALUES (117, '#ffffff', 34);
INSERT INTO `colors` VALUES (118, '#000000', 34);
INSERT INTO `colors` VALUES (119, '#FF0000', 34);
INSERT INTO `colors` VALUES (120, '#0000FF', 34);
INSERT INTO `colors` VALUES (121, '#ffffff', 35);
INSERT INTO `colors` VALUES (122, '#000000', 35);
INSERT INTO `colors` VALUES (123, '#FF0000', 35);
INSERT INTO `colors` VALUES (124, '#0000FF', 35);
INSERT INTO `colors` VALUES (125, '#ffffff', 36);
INSERT INTO `colors` VALUES (126, '#000000', 36);
INSERT INTO `colors` VALUES (127, '#FF0000', 36);
INSERT INTO `colors` VALUES (128, '#0000FF', 36);
INSERT INTO `colors` VALUES (129, '#ffffff', 37);
INSERT INTO `colors` VALUES (130, '#000000', 37);
INSERT INTO `colors` VALUES (131, '#FF0000', 37);
INSERT INTO `colors` VALUES (132, '#0000FF', 37);
INSERT INTO `colors` VALUES (133, '#ffffff', 38);
INSERT INTO `colors` VALUES (134, '#000000', 38);
INSERT INTO `colors` VALUES (135, '#FF0000', 38);
INSERT INTO `colors` VALUES (136, '#0000FF', 38);
INSERT INTO `colors` VALUES (137, '#ffffff', 39);
INSERT INTO `colors` VALUES (138, '#000000', 39);
INSERT INTO `colors` VALUES (139, '#FF0000', 39);
INSERT INTO `colors` VALUES (140, '#0000FF', 39);
INSERT INTO `colors` VALUES (141, '#ffffff', 40);
INSERT INTO `colors` VALUES (142, '#000000', 40);
INSERT INTO `colors` VALUES (143, '#FF0000', 40);
INSERT INTO `colors` VALUES (144, '#0000FF', 40);
INSERT INTO `colors` VALUES (145, '#ffffff', 41);
INSERT INTO `colors` VALUES (146, '#000000', 41);
INSERT INTO `colors` VALUES (147, '#FF0000', 41);
INSERT INTO `colors` VALUES (148, '#0000FF', 41);
INSERT INTO `colors` VALUES (149, '#ffffff', 42);
INSERT INTO `colors` VALUES (150, '#000000', 42);
INSERT INTO `colors` VALUES (151, '#FF0000', 42);
INSERT INTO `colors` VALUES (152, '#0000FF', 42);
INSERT INTO `colors` VALUES (153, '#ffffff', 43);
INSERT INTO `colors` VALUES (154, '#000000', 43);
INSERT INTO `colors` VALUES (155, '#FF0000', 43);
INSERT INTO `colors` VALUES (156, '#0000FF', 43);
INSERT INTO `colors` VALUES (157, '#ffffff', 44);
INSERT INTO `colors` VALUES (158, '#000000', 44);
INSERT INTO `colors` VALUES (159, '#FF0000', 44);
INSERT INTO `colors` VALUES (160, '#0000FF', 44);
INSERT INTO `colors` VALUES (161, '#ffffff', 45);
INSERT INTO `colors` VALUES (162, '#000000', 45);
INSERT INTO `colors` VALUES (163, '#FF0000', 45);
INSERT INTO `colors` VALUES (164, '#0000FF', 45);
INSERT INTO `colors` VALUES (165, '#ffffff', 46);
INSERT INTO `colors` VALUES (166, '#000000', 46);
INSERT INTO `colors` VALUES (167, '#FF0000', 46);
INSERT INTO `colors` VALUES (168, '#0000FF', 46);
INSERT INTO `colors` VALUES (169, '#ffffff', 47);
INSERT INTO `colors` VALUES (170, '#000000', 47);
INSERT INTO `colors` VALUES (171, '#FF0000', 47);
INSERT INTO `colors` VALUES (172, '#0000FF', 47);
INSERT INTO `colors` VALUES (173, '#ffffff', 48);
INSERT INTO `colors` VALUES (174, '#000000', 48);
INSERT INTO `colors` VALUES (175, '#FF0000', 48);
INSERT INTO `colors` VALUES (176, '#0000FF', 48);
INSERT INTO `colors` VALUES (177, '#ffffff', 49);
INSERT INTO `colors` VALUES (178, '#000000', 49);
INSERT INTO `colors` VALUES (179, '#FF0000', 49);
INSERT INTO `colors` VALUES (180, '#0000FF', 49);
INSERT INTO `colors` VALUES (181, '#ffffff', 50);
INSERT INTO `colors` VALUES (182, '#000000', 50);
INSERT INTO `colors` VALUES (183, '#FF0000', 50);
INSERT INTO `colors` VALUES (184, '#0000FF', 50);
INSERT INTO `colors` VALUES (185, '#ffffff', 51);
INSERT INTO `colors` VALUES (186, '#000000', 51);
INSERT INTO `colors` VALUES (187, '#FF0000', 51);
INSERT INTO `colors` VALUES (188, '#0000FF', 51);
INSERT INTO `colors` VALUES (189, '#ffffff', 52);
INSERT INTO `colors` VALUES (190, '#000000', 52);
INSERT INTO `colors` VALUES (191, '#FF0000', 52);
INSERT INTO `colors` VALUES (192, '#0000FF', 52);
INSERT INTO `colors` VALUES (193, '#ffffff', 53);
INSERT INTO `colors` VALUES (194, '#000000', 53);
INSERT INTO `colors` VALUES (195, '#FF0000', 53);
INSERT INTO `colors` VALUES (196, '#0000FF', 53);
INSERT INTO `colors` VALUES (197, '#ffffff', 54);
INSERT INTO `colors` VALUES (198, '#000000', 54);
INSERT INTO `colors` VALUES (199, '#FF0000', 54);
INSERT INTO `colors` VALUES (200, '#0000FF', 54);
INSERT INTO `colors` VALUES (201, '#ffffff', 55);
INSERT INTO `colors` VALUES (202, '#000000', 55);
INSERT INTO `colors` VALUES (203, '#FF0000', 55);
INSERT INTO `colors` VALUES (204, '#0000FF', 55);
INSERT INTO `colors` VALUES (205, '#ffffff', 56);
INSERT INTO `colors` VALUES (206, '#000000', 56);
INSERT INTO `colors` VALUES (207, '#FF0000', 56);
INSERT INTO `colors` VALUES (208, '#0000FF', 56);
INSERT INTO `colors` VALUES (209, '#ffffff', 57);
INSERT INTO `colors` VALUES (210, '#000000', 57);
INSERT INTO `colors` VALUES (211, '#FF0000', 57);
INSERT INTO `colors` VALUES (212, '#0000FF', 57);
INSERT INTO `colors` VALUES (213, '#ffffff', 58);
INSERT INTO `colors` VALUES (214, '#000000', 58);
INSERT INTO `colors` VALUES (215, '#FF0000', 58);
INSERT INTO `colors` VALUES (216, '#0000FF', 58);
INSERT INTO `colors` VALUES (217, '#ffffff', 59);
INSERT INTO `colors` VALUES (218, '#000000', 59);
INSERT INTO `colors` VALUES (219, '#FF0000', 59);
INSERT INTO `colors` VALUES (220, '#0000FF', 59);
INSERT INTO `colors` VALUES (221, '#ffffff', 60);
INSERT INTO `colors` VALUES (222, '#000000', 60);
INSERT INTO `colors` VALUES (223, '#FF0000', 60);
INSERT INTO `colors` VALUES (224, '#0000FF', 60);
INSERT INTO `colors` VALUES (461, '#000000', 70);
INSERT INTO `colors` VALUES (462, '#601f1f', 70);
INSERT INTO `colors` VALUES (463, '#ea0b0b', 70);
INSERT INTO `colors` VALUES (464, '#601f1f', 71);
INSERT INTO `colors` VALUES (465, '#000000', 71);
INSERT INTO `colors` VALUES (466, '#601f1f', 72);
INSERT INTO `colors` VALUES (467, '#FF0000', 72);
INSERT INTO `colors` VALUES (468, '#000000', 73);
INSERT INTO `colors` VALUES (469, '#FF0000', 73);
INSERT INTO `colors` VALUES (470, '#601f1f', 74);
INSERT INTO `colors` VALUES (471, '#000000', 74);
INSERT INTO `colors` VALUES (472, '#FF0000', 74);
INSERT INTO `colors` VALUES (473, '#601f1f', 75);
INSERT INTO `colors` VALUES (474, '#000000', 75);
INSERT INTO `colors` VALUES (475, '#ffffff', 76);
INSERT INTO `colors` VALUES (476, '#000000', 76);
INSERT INTO `colors` VALUES (477, '#601f1f', 77);
INSERT INTO `colors` VALUES (478, '#FF0000', 77);
INSERT INTO `colors` VALUES (479, '#ffffff', 77);
INSERT INTO `colors` VALUES (480, '#000000', 77);
INSERT INTO `colors` VALUES (481, '#601f1f', 78);
INSERT INTO `colors` VALUES (482, '#ffffff', 78);
INSERT INTO `colors` VALUES (483, '#FF0000', 78);
INSERT INTO `colors` VALUES (484, '#ffffff', 79);
INSERT INTO `colors` VALUES (485, '#000000', 79);
INSERT INTO `colors` VALUES (486, '#FF0000', 79);
INSERT INTO `colors` VALUES (487, '#601f1f', 80);
INSERT INTO `colors` VALUES (488, '#ffffff', 80);
INSERT INTO `colors` VALUES (489, '#FF0000', 80);
INSERT INTO `colors` VALUES (490, '#000000', 80);
INSERT INTO `colors` VALUES (491, '#601f1f', 81);
INSERT INTO `colors` VALUES (492, '#FF0000', 81);
INSERT INTO `colors` VALUES (493, '#000000', 81);
INSERT INTO `colors` VALUES (494, '#ffffff', 82);
INSERT INTO `colors` VALUES (495, '#FF0000', 82);
INSERT INTO `colors` VALUES (496, '#000000', 82);
INSERT INTO `colors` VALUES (497, '#000000', 83);
INSERT INTO `colors` VALUES (498, '#ffffff', 83);
INSERT INTO `colors` VALUES (499, '#601f1f', 83);
INSERT INTO `colors` VALUES (500, '#FF0000', 84);
INSERT INTO `colors` VALUES (501, '#601f1f', 84);
INSERT INTO `colors` VALUES (502, '#ffffff', 84);
INSERT INTO `colors` VALUES (503, '#601f1f', 85);
INSERT INTO `colors` VALUES (504, '#FF0000', 85);
INSERT INTO `colors` VALUES (505, '#000000', 85);
INSERT INTO `colors` VALUES (506, '#ffffff', 86);
INSERT INTO `colors` VALUES (507, '#601f1f', 86);
INSERT INTO `colors` VALUES (508, '#000000', 86);
INSERT INTO `colors` VALUES (509, '#FF0000', 87);
INSERT INTO `colors` VALUES (510, '#ffffff', 87);
INSERT INTO `colors` VALUES (511, '#000000', 88);
INSERT INTO `colors` VALUES (512, '#FF0000', 88);
INSERT INTO `colors` VALUES (513, '#ffffff', 88);
INSERT INTO `colors` VALUES (514, '#ffffff', 89);
INSERT INTO `colors` VALUES (515, '#601f1f', 89);
INSERT INTO `colors` VALUES (516, '#000000', 89);
INSERT INTO `colors` VALUES (517, '#000000', 90);
INSERT INTO `colors` VALUES (518, '#ffffff', 90);
INSERT INTO `colors` VALUES (519, '#FF0000', 91);
INSERT INTO `colors` VALUES (520, '#ffffff', 91);
INSERT INTO `colors` VALUES (521, '#000000', 91);
INSERT INTO `colors` VALUES (522, '#601f1f', 92);
INSERT INTO `colors` VALUES (523, '#000000', 92);
INSERT INTO `colors` VALUES (524, '#FF0000', 92);
INSERT INTO `colors` VALUES (525, '#FF0000', 93);
INSERT INTO `colors` VALUES (526, '#ffffff', 93);
INSERT INTO `colors` VALUES (527, '#601f1f', 94);
INSERT INTO `colors` VALUES (528, '#FF0000', 94);
INSERT INTO `colors` VALUES (529, '#000000', 94);
INSERT INTO `colors` VALUES (530, '#601f1f', 95);
INSERT INTO `colors` VALUES (531, '#FF0000', 95);
INSERT INTO `colors` VALUES (532, '#ffffff', 95);
INSERT INTO `colors` VALUES (533, '#000000', 96);
INSERT INTO `colors` VALUES (534, '#FF0000', 96);
INSERT INTO `colors` VALUES (535, '#601f1f', 96);
INSERT INTO `colors` VALUES (536, '#ffffff', 97);
INSERT INTO `colors` VALUES (537, '#FF0000', 97);
INSERT INTO `colors` VALUES (538, '#ffffff', 98);
INSERT INTO `colors` VALUES (539, '#000000', 98);
INSERT INTO `colors` VALUES (540, '#601f1f', 98);
INSERT INTO `colors` VALUES (541, '#FF0000', 99);
INSERT INTO `colors` VALUES (542, '#000000', 99);
INSERT INTO `colors` VALUES (543, '#601f1f', 100);
INSERT INTO `colors` VALUES (544, '#ffffff', 100);
INSERT INTO `colors` VALUES (545, '#FF0000', 101);
INSERT INTO `colors` VALUES (546, '#000000', 101);
INSERT INTO `colors` VALUES (547, '#ffffff', 101);
INSERT INTO `colors` VALUES (548, '#601f1f', 102);
INSERT INTO `colors` VALUES (549, '#ffffff', 102);
INSERT INTO `colors` VALUES (550, '#FF0000', 102);
INSERT INTO `colors` VALUES (551, '#000000', 103);
INSERT INTO `colors` VALUES (552, '#601f1f', 103);
INSERT INTO `colors` VALUES (553, '#FF0000', 103);
INSERT INTO `colors` VALUES (554, '#ffffff', 104);
INSERT INTO `colors` VALUES (555, '#601f1f', 104);
INSERT INTO `colors` VALUES (556, '#ffffff', 105);
INSERT INTO `colors` VALUES (557, '#601f1f', 105);
INSERT INTO `colors` VALUES (558, '#FF0000', 105);
INSERT INTO `colors` VALUES (559, '#ffffff', 106);
INSERT INTO `colors` VALUES (560, '#FF0000', 106);
INSERT INTO `colors` VALUES (561, '#601f1f', 106);
INSERT INTO `colors` VALUES (562, '#ffffff', 107);
INSERT INTO `colors` VALUES (563, '#000000', 107);
INSERT INTO `colors` VALUES (564, '#601f1f', 107);
INSERT INTO `colors` VALUES (565, '#FF0000', 108);
INSERT INTO `colors` VALUES (566, '#601f1f', 108);
INSERT INTO `colors` VALUES (567, '#ffffff', 108);
INSERT INTO `colors` VALUES (568, '#ffffff', 109);
INSERT INTO `colors` VALUES (569, '#000000', 109);
INSERT INTO `colors` VALUES (570, '#FF0000', 109);
INSERT INTO `colors` VALUES (571, '#000000', 110);
INSERT INTO `colors` VALUES (572, '#FF0000', 111);
INSERT INTO `colors` VALUES (573, '#ffffff', 111);
INSERT INTO `colors` VALUES (574, '#000000', 111);
INSERT INTO `colors` VALUES (575, '#601f1f', 112);
INSERT INTO `colors` VALUES (576, '#ffffff', 112);
INSERT INTO `colors` VALUES (577, '#FF0000', 112);
INSERT INTO `colors` VALUES (578, '#000000', 112);
INSERT INTO `colors` VALUES (579, '#ffffff', 113);
INSERT INTO `colors` VALUES (580, '#601f1f', 113);
INSERT INTO `colors` VALUES (581, '#000000', 113);
INSERT INTO `colors` VALUES (582, '#601f1f', 114);
INSERT INTO `colors` VALUES (583, '#FF0000', 114);
INSERT INTO `colors` VALUES (584, '#601f1f', 115);
INSERT INTO `colors` VALUES (585, '#FF0000', 115);
INSERT INTO `colors` VALUES (586, '#000000', 115);
INSERT INTO `colors` VALUES (587, '#ffffff', 115);
INSERT INTO `colors` VALUES (588, '#601f1f', 116);
INSERT INTO `colors` VALUES (589, '#000000', 116);
INSERT INTO `colors` VALUES (590, '#FF0000', 117);
INSERT INTO `colors` VALUES (591, '#ffffff', 117);
INSERT INTO `colors` VALUES (592, '#000000', 117);
INSERT INTO `colors` VALUES (593, '#601f1f', 118);
INSERT INTO `colors` VALUES (594, '#000000', 118);
INSERT INTO `colors` VALUES (595, '#ffffff', 118);
INSERT INTO `colors` VALUES (596, '#601f1f', 119);
INSERT INTO `colors` VALUES (597, '#ffffff', 119);
INSERT INTO `colors` VALUES (598, '#FF0000', 120);
INSERT INTO `colors` VALUES (599, '#000000', 120);
INSERT INTO `colors` VALUES (600, '#601f1f', 120);
INSERT INTO `colors` VALUES (601, '#ffffff', 120);
INSERT INTO `colors` VALUES (602, '#FF0000', 121);
INSERT INTO `colors` VALUES (603, '#000000', 121);
INSERT INTO `colors` VALUES (604, '#ffffff', 122);
INSERT INTO `colors` VALUES (605, '#FF0000', 122);
INSERT INTO `colors` VALUES (606, '#000000', 122);
INSERT INTO `colors` VALUES (607, '#601f1f', 123);
INSERT INTO `colors` VALUES (608, '#000000', 123);
INSERT INTO `colors` VALUES (609, '#FF0000', 123);
INSERT INTO `colors` VALUES (610, '#FF0000', 124);
INSERT INTO `colors` VALUES (611, '#000000', 124);
INSERT INTO `colors` VALUES (612, '#601f1f', 124);
INSERT INTO `colors` VALUES (613, '#ffffff', 125);
INSERT INTO `colors` VALUES (614, '#FF0000', 125);
INSERT INTO `colors` VALUES (615, '#601f1f', 126);
INSERT INTO `colors` VALUES (616, '#FF0000', 126);
INSERT INTO `colors` VALUES (617, '#601f1f', 127);
INSERT INTO `colors` VALUES (618, '#ffffff', 127);
INSERT INTO `colors` VALUES (619, '#000000', 127);
INSERT INTO `colors` VALUES (620, '#FF0000', 128);
INSERT INTO `colors` VALUES (621, '#601f1f', 128);
INSERT INTO `colors` VALUES (622, '#000000', 128);
INSERT INTO `colors` VALUES (623, '#000000', 129);
INSERT INTO `colors` VALUES (624, '#FF0000', 129);
INSERT INTO `colors` VALUES (625, '#601f1f', 129);
INSERT INTO `colors` VALUES (626, '#601f1f', 130);
INSERT INTO `colors` VALUES (627, '#000000', 130);
INSERT INTO `colors` VALUES (628, '#ffffff', 130);
INSERT INTO `colors` VALUES (629, '#000000', 131);
INSERT INTO `colors` VALUES (630, '#601f1f', 131);
INSERT INTO `colors` VALUES (631, '#FF0000', 131);
INSERT INTO `colors` VALUES (632, '#ffffff', 132);
INSERT INTO `colors` VALUES (633, '#601f1f', 133);
INSERT INTO `colors` VALUES (634, '#000000', 133);
INSERT INTO `colors` VALUES (635, '#ffffff', 133);
INSERT INTO `colors` VALUES (636, '#FF0000', 134);
INSERT INTO `colors` VALUES (637, '#ffffff', 134);
INSERT INTO `colors` VALUES (638, '#ffffff', 135);
INSERT INTO `colors` VALUES (639, '#FF0000', 135);
INSERT INTO `colors` VALUES (640, '#601f1f', 136);
INSERT INTO `colors` VALUES (641, '#FF0000', 136);
INSERT INTO `colors` VALUES (642, '#ffffff', 136);
INSERT INTO `colors` VALUES (643, '#000000', 137);
INSERT INTO `colors` VALUES (644, '#FF0000', 137);
INSERT INTO `colors` VALUES (645, '#ffffff', 138);
INSERT INTO `colors` VALUES (646, '#601f1f', 138);
INSERT INTO `colors` VALUES (647, '#FF0000', 139);
INSERT INTO `colors` VALUES (648, '#601f1f', 139);
INSERT INTO `colors` VALUES (649, '#000000', 139);
INSERT INTO `colors` VALUES (650, '#ffffff', 139);
INSERT INTO `colors` VALUES (651, '#601f1f', 140);
INSERT INTO `colors` VALUES (652, '#000000', 140);
INSERT INTO `colors` VALUES (653, '#ffffff', 140);
INSERT INTO `colors` VALUES (654, '#FF0000', 141);
INSERT INTO `colors` VALUES (655, '#601f1f', 141);
INSERT INTO `colors` VALUES (656, '#000000', 141);
INSERT INTO `colors` VALUES (657, '#ffffff', 142);
INSERT INTO `colors` VALUES (658, '#FF0000', 142);
INSERT INTO `colors` VALUES (659, '#601f1f', 143);
INSERT INTO `colors` VALUES (660, '#FF0000', 143);
INSERT INTO `colors` VALUES (661, '#601f1f', 144);
INSERT INTO `colors` VALUES (662, '#ffffff', 144);
INSERT INTO `colors` VALUES (663, '#000000', 144);
INSERT INTO `colors` VALUES (664, '#FF0000', 144);
INSERT INTO `colors` VALUES (665, '#000000', 145);
INSERT INTO `colors` VALUES (666, '#601f1f', 145);
INSERT INTO `colors` VALUES (667, '#ffffff', 145);
INSERT INTO `colors` VALUES (668, '#ffffff', 146);
INSERT INTO `colors` VALUES (669, '#601f1f', 146);
INSERT INTO `colors` VALUES (670, '#601f1f', 147);
INSERT INTO `colors` VALUES (671, '#ffffff', 147);
INSERT INTO `colors` VALUES (672, '#FF0000', 148);
INSERT INTO `colors` VALUES (673, '#000000', 148);
INSERT INTO `colors` VALUES (674, '#000000', 149);
INSERT INTO `colors` VALUES (675, '#ffffff', 150);
INSERT INTO `colors` VALUES (676, '#FF0000', 150);
INSERT INTO `colors` VALUES (677, '#601f1f', 151);
INSERT INTO `colors` VALUES (678, '#000000', 151);
INSERT INTO `colors` VALUES (679, '#FF0000', 152);
INSERT INTO `colors` VALUES (680, '#000000', 152);
INSERT INTO `colors` VALUES (681, '#000000', 153);
INSERT INTO `colors` VALUES (682, '#FF0000', 153);
INSERT INTO `colors` VALUES (683, '#601f1f', 154);
INSERT INTO `colors` VALUES (684, '#000000', 154);
INSERT INTO `colors` VALUES (685, '#000000', 155);
INSERT INTO `colors` VALUES (686, '#601f1f', 155);
INSERT INTO `colors` VALUES (687, '#FF0000', 155);
INSERT INTO `colors` VALUES (688, '#ffffff', 156);
INSERT INTO `colors` VALUES (689, '#601f1f', 156);
INSERT INTO `colors` VALUES (690, '#000000', 156);
INSERT INTO `colors` VALUES (691, '#601f1f', 157);
INSERT INTO `colors` VALUES (692, '#ffffff', 157);
INSERT INTO `colors` VALUES (693, '#ffffff', 158);
INSERT INTO `colors` VALUES (694, '#601f1f', 158);
INSERT INTO `colors` VALUES (695, '#ffffff', 159);
INSERT INTO `colors` VALUES (696, '#601f1f', 159);
INSERT INTO `colors` VALUES (697, '#ffffff', 160);
INSERT INTO `colors` VALUES (698, '#601f1f', 160);
INSERT INTO `colors` VALUES (699, '#000000', 160);
INSERT INTO `colors` VALUES (700, '#FF0000', 161);
INSERT INTO `colors` VALUES (701, '#601f1f', 162);
INSERT INTO `colors` VALUES (702, '#000000', 162);
INSERT INTO `colors` VALUES (703, '#000000', 163);
INSERT INTO `colors` VALUES (704, '#601f1f', 163);
INSERT INTO `colors` VALUES (705, '#601f1f', 164);
INSERT INTO `colors` VALUES (706, '#ffffff', 164);
INSERT INTO `colors` VALUES (707, '#FF0000', 164);
INSERT INTO `colors` VALUES (708, '#FF0000', 165);
INSERT INTO `colors` VALUES (709, '#601f1f', 165);
INSERT INTO `colors` VALUES (710, '#ffffff', 166);
INSERT INTO `colors` VALUES (711, '#FF0000', 166);
INSERT INTO `colors` VALUES (712, '#ffffff', 167);
INSERT INTO `colors` VALUES (713, '#FF0000', 167);
INSERT INTO `colors` VALUES (714, '#000000', 167);
INSERT INTO `colors` VALUES (715, '#ffffff', 168);
INSERT INTO `colors` VALUES (716, '#FF0000', 168);
INSERT INTO `colors` VALUES (717, '#000000', 168);
INSERT INTO `colors` VALUES (718, '#FF0000', 169);
INSERT INTO `colors` VALUES (719, '#0000FF', 170);
INSERT INTO `colors` VALUES (720, '#000000', 170);
INSERT INTO `colors` VALUES (721, '#ffffff', 170);
INSERT INTO `colors` VALUES (722, '#FF0000', 170);
INSERT INTO `colors` VALUES (723, '#0000FF', 171);
INSERT INTO `colors` VALUES (724, '#ffffff', 171);
INSERT INTO `colors` VALUES (725, '#000000', 171);
INSERT INTO `colors` VALUES (726, '#ffffff', 172);
INSERT INTO `colors` VALUES (727, '#FF0000', 172);
INSERT INTO `colors` VALUES (728, '#000000', 172);
INSERT INTO `colors` VALUES (729, '#0000FF', 172);
INSERT INTO `colors` VALUES (730, '#000000', 173);
INSERT INTO `colors` VALUES (731, '#FF0000', 173);
INSERT INTO `colors` VALUES (732, '#ffffff', 173);
INSERT INTO `colors` VALUES (733, '#0000FF', 173);
INSERT INTO `colors` VALUES (734, '#0000FF', 174);
INSERT INTO `colors` VALUES (735, '#ffffff', 174);
INSERT INTO `colors` VALUES (736, '#000000', 174);
INSERT INTO `colors` VALUES (737, '#FF0000', 174);
INSERT INTO `colors` VALUES (738, '#ffffff', 175);
INSERT INTO `colors` VALUES (739, '#000000', 175);
INSERT INTO `colors` VALUES (740, '#0000FF', 175);
INSERT INTO `colors` VALUES (741, '#FF0000', 175);
INSERT INTO `colors` VALUES (742, '#ffffff', 176);
INSERT INTO `colors` VALUES (743, '#FF0000', 176);
INSERT INTO `colors` VALUES (744, '#000000', 176);
INSERT INTO `colors` VALUES (745, '#0000FF', 176);
INSERT INTO `colors` VALUES (746, '#ffffff', 177);
INSERT INTO `colors` VALUES (747, '#0000FF', 177);
INSERT INTO `colors` VALUES (748, '#000000', 177);
INSERT INTO `colors` VALUES (749, '#0000FF', 178);
INSERT INTO `colors` VALUES (750, '#FF0000', 178);
INSERT INTO `colors` VALUES (751, '#ffffff', 178);
INSERT INTO `colors` VALUES (752, '#000000', 178);
INSERT INTO `colors` VALUES (753, '#0000FF', 179);
INSERT INTO `colors` VALUES (754, '#FF0000', 179);
INSERT INTO `colors` VALUES (755, '#000000', 179);
INSERT INTO `colors` VALUES (756, '#000000', 180);
INSERT INTO `colors` VALUES (757, '#FF0000', 180);
INSERT INTO `colors` VALUES (758, '#0000FF', 180);
INSERT INTO `colors` VALUES (759, '#000000', 181);
INSERT INTO `colors` VALUES (760, '#0000FF', 181);
INSERT INTO `colors` VALUES (761, '#FF0000', 181);
INSERT INTO `colors` VALUES (762, '#ffffff', 181);
INSERT INTO `colors` VALUES (763, '#0000FF', 182);
INSERT INTO `colors` VALUES (764, '#000000', 182);
INSERT INTO `colors` VALUES (765, '#FF0000', 182);
INSERT INTO `colors` VALUES (766, '#ffffff', 182);
INSERT INTO `colors` VALUES (767, '#FF0000', 183);
INSERT INTO `colors` VALUES (768, '#ffffff', 183);
INSERT INTO `colors` VALUES (769, '#0000FF', 183);
INSERT INTO `colors` VALUES (770, '#000000', 183);
INSERT INTO `colors` VALUES (771, '#000000', 184);
INSERT INTO `colors` VALUES (772, '#FF0000', 184);
INSERT INTO `colors` VALUES (773, '#ffffff', 184);
INSERT INTO `colors` VALUES (774, '#0000FF', 185);
INSERT INTO `colors` VALUES (775, '#000000', 185);
INSERT INTO `colors` VALUES (776, '#FF0000', 185);
INSERT INTO `colors` VALUES (777, '#ffffff', 185);
INSERT INTO `colors` VALUES (778, '#000000', 186);
INSERT INTO `colors` VALUES (779, '#FF0000', 186);
INSERT INTO `colors` VALUES (780, '#0000FF', 186);
INSERT INTO `colors` VALUES (781, '#ffffff', 186);
INSERT INTO `colors` VALUES (782, '#FF0000', 187);
INSERT INTO `colors` VALUES (783, '#ffffff', 187);
INSERT INTO `colors` VALUES (784, '#000000', 187);
INSERT INTO `colors` VALUES (785, '#0000FF', 188);
INSERT INTO `colors` VALUES (786, '#FF0000', 188);
INSERT INTO `colors` VALUES (787, '#000000', 188);
INSERT INTO `colors` VALUES (788, '#0000FF', 189);
INSERT INTO `colors` VALUES (789, '#ffffff', 189);
INSERT INTO `colors` VALUES (790, '#FF0000', 189);
INSERT INTO `colors` VALUES (791, '#ffffff', 190);
INSERT INTO `colors` VALUES (792, '#0000FF', 190);
INSERT INTO `colors` VALUES (793, '#FF0000', 190);
INSERT INTO `colors` VALUES (794, '#000000', 190);
INSERT INTO `colors` VALUES (795, '#0000FF', 191);
INSERT INTO `colors` VALUES (796, '#ffffff', 191);
INSERT INTO `colors` VALUES (797, '#000000', 191);
INSERT INTO `colors` VALUES (798, '#FF0000', 191);
INSERT INTO `colors` VALUES (799, '#000000', 192);
INSERT INTO `colors` VALUES (800, '#ffffff', 192);
INSERT INTO `colors` VALUES (801, '#FF0000', 192);
INSERT INTO `colors` VALUES (802, '#0000FF', 192);
INSERT INTO `colors` VALUES (803, '#0000FF', 193);
INSERT INTO `colors` VALUES (804, '#000000', 193);
INSERT INTO `colors` VALUES (805, '#FF0000', 193);
INSERT INTO `colors` VALUES (806, '#ffffff', 193);
INSERT INTO `colors` VALUES (807, '#000000', 194);
INSERT INTO `colors` VALUES (808, '#FF0000', 194);
INSERT INTO `colors` VALUES (809, '#ffffff', 194);
INSERT INTO `colors` VALUES (810, '#0000FF', 195);
INSERT INTO `colors` VALUES (811, '#ffffff', 195);
INSERT INTO `colors` VALUES (812, '#FF0000', 195);
INSERT INTO `colors` VALUES (813, '#000000', 196);
INSERT INTO `colors` VALUES (814, '#0000FF', 196);
INSERT INTO `colors` VALUES (815, '#FF0000', 196);
INSERT INTO `colors` VALUES (816, '#ffffff', 196);
INSERT INTO `colors` VALUES (817, '#FF0000', 197);
INSERT INTO `colors` VALUES (818, '#000000', 197);
INSERT INTO `colors` VALUES (819, '#ffffff', 197);
INSERT INTO `colors` VALUES (820, '#000000', 198);
INSERT INTO `colors` VALUES (821, '#0000FF', 198);
INSERT INTO `colors` VALUES (822, '#FF0000', 198);
INSERT INTO `colors` VALUES (823, '#ffffff', 198);
INSERT INTO `colors` VALUES (824, '#ffffff', 199);
INSERT INTO `colors` VALUES (825, '#000000', 199);
INSERT INTO `colors` VALUES (826, '#0000FF', 199);
INSERT INTO `colors` VALUES (827, '#FF0000', 199);
INSERT INTO `colors` VALUES (828, '#0000FF', 200);
INSERT INTO `colors` VALUES (829, '#FF0000', 200);
INSERT INTO `colors` VALUES (830, '#ffffff', 200);
INSERT INTO `colors` VALUES (831, '#000000', 201);
INSERT INTO `colors` VALUES (832, '#ffffff', 201);
INSERT INTO `colors` VALUES (833, '#FF0000', 201);
INSERT INTO `colors` VALUES (834, '#000000', 202);
INSERT INTO `colors` VALUES (835, '#ffffff', 202);
INSERT INTO `colors` VALUES (836, '#0000FF', 202);
INSERT INTO `colors` VALUES (837, '#FF0000', 202);
INSERT INTO `colors` VALUES (838, '#ffffff', 203);
INSERT INTO `colors` VALUES (839, '#0000FF', 203);
INSERT INTO `colors` VALUES (840, '#000000', 203);
INSERT INTO `colors` VALUES (841, '#FF0000', 203);
INSERT INTO `colors` VALUES (842, '#FF0000', 204);
INSERT INTO `colors` VALUES (843, '#000000', 204);
INSERT INTO `colors` VALUES (844, '#ffffff', 204);
INSERT INTO `colors` VALUES (845, '#0000FF', 204);
INSERT INTO `colors` VALUES (846, '#000000', 205);
INSERT INTO `colors` VALUES (847, '#FF0000', 205);
INSERT INTO `colors` VALUES (848, '#ffffff', 205);
INSERT INTO `colors` VALUES (849, '#0000FF', 205);
INSERT INTO `colors` VALUES (850, '#0000FF', 206);
INSERT INTO `colors` VALUES (851, '#FF0000', 206);
INSERT INTO `colors` VALUES (852, '#ffffff', 206);
INSERT INTO `colors` VALUES (853, '#000000', 207);
INSERT INTO `colors` VALUES (854, '#ffffff', 207);
INSERT INTO `colors` VALUES (855, '#FF0000', 207);
INSERT INTO `colors` VALUES (856, '#0000FF', 207);
INSERT INTO `colors` VALUES (857, '#FF0000', 208);
INSERT INTO `colors` VALUES (858, '#ffffff', 208);
INSERT INTO `colors` VALUES (859, '#0000FF', 208);
INSERT INTO `colors` VALUES (860, '#000000', 208);
INSERT INTO `colors` VALUES (861, '#0000FF', 209);
INSERT INTO `colors` VALUES (862, '#FF0000', 209);
INSERT INTO `colors` VALUES (863, '#ffffff', 209);
INSERT INTO `colors` VALUES (864, '#000000', 209);
INSERT INTO `colors` VALUES (865, '#FF0000', 210);
INSERT INTO `colors` VALUES (866, '#ffffff', 210);
INSERT INTO `colors` VALUES (867, '#000000', 210);
INSERT INTO `colors` VALUES (868, '#0000FF', 210);
INSERT INTO `colors` VALUES (869, '#ffffff', 211);
INSERT INTO `colors` VALUES (870, '#0000FF', 211);
INSERT INTO `colors` VALUES (871, '#000000', 212);
INSERT INTO `colors` VALUES (872, '#FF0000', 212);
INSERT INTO `colors` VALUES (873, '#ffffff', 212);
INSERT INTO `colors` VALUES (874, '#ffffff', 213);
INSERT INTO `colors` VALUES (875, '#000000', 213);
INSERT INTO `colors` VALUES (876, '#0000FF', 213);
INSERT INTO `colors` VALUES (877, '#FF0000', 213);
INSERT INTO `colors` VALUES (878, '#FF0000', 214);
INSERT INTO `colors` VALUES (879, '#000000', 214);
INSERT INTO `colors` VALUES (880, '#ffffff', 214);
INSERT INTO `colors` VALUES (881, '#0000FF', 214);
INSERT INTO `colors` VALUES (882, '#FF0000', 215);
INSERT INTO `colors` VALUES (883, '#0000FF', 215);
INSERT INTO `colors` VALUES (884, '#000000', 215);
INSERT INTO `colors` VALUES (885, '#0000FF', 216);
INSERT INTO `colors` VALUES (886, '#ffffff', 216);
INSERT INTO `colors` VALUES (887, '#000000', 216);
INSERT INTO `colors` VALUES (888, '#FF0000', 217);
INSERT INTO `colors` VALUES (889, '#ffffff', 217);
INSERT INTO `colors` VALUES (890, '#000000', 217);
INSERT INTO `colors` VALUES (891, '#FF0000', 218);
INSERT INTO `colors` VALUES (892, '#ffffff', 218);
INSERT INTO `colors` VALUES (893, '#000000', 218);
INSERT INTO `colors` VALUES (894, '#0000FF', 218);

-- ----------------------------
-- Table structure for contact_subjects
-- ----------------------------
DROP TABLE IF EXISTS `contact_subjects`;
CREATE TABLE `contact_subjects`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact_subjects
-- ----------------------------
INSERT INTO `contact_subjects` VALUES (1, 'Thắc mắc chung');
INSERT INTO `contact_subjects` VALUES (2, 'Thắc mắc về sản phẩm');
INSERT INTO `contact_subjects` VALUES (3, 'Mua sắm trực tuyến');
INSERT INTO `contact_subjects` VALUES (4, 'Bảo vệ dữ liệu');
INSERT INTO `contact_subjects` VALUES (5, 'Đăng ký tài khoản');
INSERT INTO `contact_subjects` VALUES (6, 'Kích hoạt tài khoản');
INSERT INTO `contact_subjects` VALUES (7, 'Dịch vụ bảo hành & hoàn trả');
INSERT INTO `contact_subjects` VALUES (8, 'Công việc');
INSERT INTO `contact_subjects` VALUES (9, 'Tiếp thị');
INSERT INTO `contact_subjects` VALUES (10, 'Các yêu cầu khác');

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NULL DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_contacts_users`(`userId`) USING BTREE,
  CONSTRAINT `FK_contacts_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES (1, 1, 'Nguyễn Văn Quốc', 'nguyenvanquoc@gmail.com', 'Tôi muốn biết cách thức để đặt may một mẫu đồ trên website');
INSERT INTO `contacts` VALUES (2, 10, 'Trần Văn Khoa', 'tranvankhoa@gmail.com', 'Tôi có thể theo dõi đơn hàng của mình ở đâu');
INSERT INTO `contacts` VALUES (3, 5, 'Trần Thị Mai', 'tranthimai@gmail.com', 'Tôi muốn biết cách để hủy bỏ một đơn hàng');
INSERT INTO `contacts` VALUES (4, 3, 'Lê Hoàng Long', 'lehoanglong@gmail.com', 'Tôi muốn yêu cầu đặt may lại những mẫu đồ lúc trước thì phải làm như thế nào?');
INSERT INTO `contacts` VALUES (5, 2, 'Phạm Huy Hoàng', 'phamhuyhoang@gmail.com', 'Tôi muốn biết cách để thay đổi thông tin tài khoản cá nhân của mình');
INSERT INTO `contacts` VALUES (6, 4, 'Lê Thị Ngọc Hà', 'lethingocha@gmail.com', 'Khoảng bao lâu thì tôi có thể nhận được đồ đặt may');
INSERT INTO `contacts` VALUES (7, 6, 'Đỗ Anh Duy', 'doanhduy@gmail.com', 'Tôi phải làm gì khi để sai thông tin giao hàng?');
INSERT INTO `contacts` VALUES (8, 8, 'Hoàng Thị Thu Hồng', 'hoangthithuhong@gmail.com', 'Tôi có thể đổi, trả hàng nếu chưa vừa ý không?');
INSERT INTO `contacts` VALUES (9, 9, 'Nguyễn Minh Hiếu', 'nguyenminhhieu@gmail.com', 'Có giới hạn số mẫu đồ đặt may trong một đơn hàng không?');
INSERT INTO `contacts` VALUES (10, 7, 'Bùi Thị Lan', 'buithilan@gmail.com', 'Tôi muốn biết cách sử dụng mã giảm giá');

-- ----------------------------
-- Table structure for delivery_methods
-- ----------------------------
DROP TABLE IF EXISTS `delivery_methods`;
CREATE TABLE `delivery_methods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeShipping` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `shippingFee` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_methods
-- ----------------------------
INSERT INTO `delivery_methods` VALUES (1, 'Tiết kiệm', 'Bạn sẽ nhận được hàng dự kiến sau 3 - 4 ngày kể từ ngày đặt hàng', 30000);
INSERT INTO `delivery_methods` VALUES (2, 'Nhanh', 'Bạn sẽ nhận được hàng dự kiến sau 1 - 2 ngày kể từ ngày đặt hàng', 45000);
INSERT INTO `delivery_methods` VALUES (3, 'Hỏa tốc', 'Bạn sẽ nhận được hàng sớm nhất trong ngày hoặc vào ngày hôm sau tùy thuộc vào thời điểm đã đặt hàng', 60000);

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameImage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0',
  `productId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `productId`(`productId`) USING BTREE,
  CONSTRAINT `FK_images_products` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 354 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (4, '5/product5.jpg', 5);
INSERT INTO `images` VALUES (5, '6/product6.jpg', 6);
INSERT INTO `images` VALUES (6, '7/product7.jpg', 7);
INSERT INTO `images` VALUES (7, '8/product8.jpg', 8);
INSERT INTO `images` VALUES (8, '9/product9.jpg', 9);
INSERT INTO `images` VALUES (9, '10/product10.jpg', 10);
INSERT INTO `images` VALUES (10, '11/product11.jpg', 11);
INSERT INTO `images` VALUES (11, '12/product12.jpg', 12);
INSERT INTO `images` VALUES (12, '13/product13.jpg', 13);
INSERT INTO `images` VALUES (13, '14/product14.jpg', 14);
INSERT INTO `images` VALUES (14, '15/product15.jpg', 15);
INSERT INTO `images` VALUES (15, '16/product16.jpg', 16);
INSERT INTO `images` VALUES (16, '17/product17.jpg', 17);
INSERT INTO `images` VALUES (17, '22/product22.jpg', 22);
INSERT INTO `images` VALUES (18, '23/product23.jpg', 23);
INSERT INTO `images` VALUES (19, '24/product24.jpg', 24);
INSERT INTO `images` VALUES (20, '25/product25.jpg', 25);
INSERT INTO `images` VALUES (21, '26/product26.jpg', 26);
INSERT INTO `images` VALUES (22, '27/product27.jpg', 27);
INSERT INTO `images` VALUES (23, '28/product28.jpg', 28);
INSERT INTO `images` VALUES (24, '30/product30.jpg', 30);
INSERT INTO `images` VALUES (25, '31/product31.jpg', 31);
INSERT INTO `images` VALUES (26, '32/product32.jpg', 32);
INSERT INTO `images` VALUES (27, '33/product33.jpg', 33);
INSERT INTO `images` VALUES (28, '34/product34.jpg', 34);
INSERT INTO `images` VALUES (29, '35/product35.jpg', 35);
INSERT INTO `images` VALUES (30, '36/product36.jpg', 36);
INSERT INTO `images` VALUES (31, '37/product37.jpg', 37);
INSERT INTO `images` VALUES (32, '38/product38.jpg', 38);
INSERT INTO `images` VALUES (33, '39/product39.jpg', 39);
INSERT INTO `images` VALUES (34, '40/product40.jpg', 40);
INSERT INTO `images` VALUES (35, '41/product41.jpg', 41);
INSERT INTO `images` VALUES (36, '42/product42.jpg', 42);
INSERT INTO `images` VALUES (37, '43/product43.jpg', 43);
INSERT INTO `images` VALUES (38, '44/product44.jpg', 44);
INSERT INTO `images` VALUES (39, '45/product45.jpg', 45);
INSERT INTO `images` VALUES (40, '46/product46.jpg', 46);
INSERT INTO `images` VALUES (41, '47/product47.jpg', 47);
INSERT INTO `images` VALUES (42, '48/product48.jpg', 48);
INSERT INTO `images` VALUES (43, '49/product49.jpg', 49);
INSERT INTO `images` VALUES (44, '50/product50.jpg', 50);
INSERT INTO `images` VALUES (45, '51/product51.jpg', 51);
INSERT INTO `images` VALUES (46, '52/product52.jpg', 52);
INSERT INTO `images` VALUES (47, '53/product53.jpg', 53);
INSERT INTO `images` VALUES (48, '54/product54.jpg', 54);
INSERT INTO `images` VALUES (49, '55/product55.jpg', 55);
INSERT INTO `images` VALUES (50, '56/product56.jpg', 56);
INSERT INTO `images` VALUES (51, '57/product57.jpg', 57);
INSERT INTO `images` VALUES (52, '58/product58.jpg', 58);
INSERT INTO `images` VALUES (53, '59/product59.jpg', 59);
INSERT INTO `images` VALUES (54, '60/product60.jpg', 60);
INSERT INTO `images` VALUES (55, '21/product21.jpg', 21);
INSERT INTO `images` VALUES (56, '25/product25.jpg', 25);
INSERT INTO `images` VALUES (57, '66/product66.jpg', 66);
INSERT INTO `images` VALUES (58, '29/product29.jpg', 29);
INSERT INTO `images` VALUES (59, '29/product29.jpg', 29);
INSERT INTO `images` VALUES (60, '29/product29.jpg', 29);
INSERT INTO `images` VALUES (61, '29/product29.jpg', 29);
INSERT INTO `images` VALUES (62, '70/product70.jpg', 70);
INSERT INTO `images` VALUES (64, '71/product71.jpg', 71);
INSERT INTO `images` VALUES (65, '72/product72.jpg', 72);
INSERT INTO `images` VALUES (66, '73/product73.jpg', 73);
INSERT INTO `images` VALUES (67, '74/product74.jpg', 74);
INSERT INTO `images` VALUES (68, '75/product75.jpg', 75);
INSERT INTO `images` VALUES (69, '76/product76.jpg', 76);
INSERT INTO `images` VALUES (70, '77/product77.jpg', 77);
INSERT INTO `images` VALUES (71, '78/product78.jpg', 78);
INSERT INTO `images` VALUES (72, '79/product79.jpg', 79);
INSERT INTO `images` VALUES (73, '80/product80.jpg', 80);
INSERT INTO `images` VALUES (74, '81/product81.jpg', 81);
INSERT INTO `images` VALUES (75, '82/product82.jpg', 82);
INSERT INTO `images` VALUES (76, '83/product83.jpg', 83);
INSERT INTO `images` VALUES (77, '84/product84.jpg', 84);
INSERT INTO `images` VALUES (78, '85/product85.jpg', 85);
INSERT INTO `images` VALUES (79, '86/product86.jpg', 86);
INSERT INTO `images` VALUES (80, '87/product87.jpg', 87);
INSERT INTO `images` VALUES (81, '88/product88.jpg', 88);
INSERT INTO `images` VALUES (82, '89/product89.jpg', 89);
INSERT INTO `images` VALUES (83, '90/product90.jpg', 90);
INSERT INTO `images` VALUES (84, '91/product91.jpg', 91);
INSERT INTO `images` VALUES (85, '92/product92.jpg', 92);
INSERT INTO `images` VALUES (86, '93/product93.jpg', 93);
INSERT INTO `images` VALUES (87, '94/product94.jpg', 94);
INSERT INTO `images` VALUES (88, '95/product95.jpg', 95);
INSERT INTO `images` VALUES (89, '96/product96.jpg', 96);
INSERT INTO `images` VALUES (90, '97/product97.jpg', 97);
INSERT INTO `images` VALUES (91, '98/product98.jpg', 98);
INSERT INTO `images` VALUES (92, '99/product99.jpg', 99);
INSERT INTO `images` VALUES (93, '100/product100.jpg', 100);
INSERT INTO `images` VALUES (94, '101/product101.jpg', 101);
INSERT INTO `images` VALUES (95, '102/product102.jpg', 102);
INSERT INTO `images` VALUES (96, '103/product103.jpg', 103);
INSERT INTO `images` VALUES (97, '104/product104.jpg', 104);
INSERT INTO `images` VALUES (98, '105/product105.jpg', 105);
INSERT INTO `images` VALUES (99, '106/product106.jpg', 106);
INSERT INTO `images` VALUES (100, '107/product107.jpg', 107);
INSERT INTO `images` VALUES (101, '108/product108.jpg', 108);
INSERT INTO `images` VALUES (102, '109/product109.jpg', 109);
INSERT INTO `images` VALUES (103, '110/product110.jpg', 110);
INSERT INTO `images` VALUES (104, '111/product111.jpg', 111);
INSERT INTO `images` VALUES (105, '112/product112.jpg', 112);
INSERT INTO `images` VALUES (106, '113/product113.jpg', 113);
INSERT INTO `images` VALUES (107, '114/product114.jpg', 114);
INSERT INTO `images` VALUES (108, '115/product115.jpg', 115);
INSERT INTO `images` VALUES (109, '116/product116.jpg', 116);
INSERT INTO `images` VALUES (110, '117/product117.jpg', 117);
INSERT INTO `images` VALUES (111, '118/product118.jpg', 118);
INSERT INTO `images` VALUES (112, '119/product119.jpg', 119);
INSERT INTO `images` VALUES (113, '120/product120.jpg', 120);
INSERT INTO `images` VALUES (114, '121/product121.jpg', 121);
INSERT INTO `images` VALUES (115, '122/product122.jpg', 122);
INSERT INTO `images` VALUES (116, '123/product123.jpg', 123);
INSERT INTO `images` VALUES (117, '124/product124.jpg', 124);
INSERT INTO `images` VALUES (118, '125/product125.jpg', 125);
INSERT INTO `images` VALUES (119, '126/product126.jpg', 126);
INSERT INTO `images` VALUES (120, '127/product127.jpg', 127);
INSERT INTO `images` VALUES (121, '128/product128.jpg', 128);
INSERT INTO `images` VALUES (122, '129/product129.jpg', 129);
INSERT INTO `images` VALUES (123, '130/product130.jpg', 130);
INSERT INTO `images` VALUES (124, '131/product131.jpg', 131);
INSERT INTO `images` VALUES (125, '132/product132.jpg', 132);
INSERT INTO `images` VALUES (126, '133/product133.jpg', 133);
INSERT INTO `images` VALUES (127, '134/product134.jpg', 134);
INSERT INTO `images` VALUES (128, '135/product135.jpg', 135);
INSERT INTO `images` VALUES (129, '136/product136.jpg', 136);
INSERT INTO `images` VALUES (130, '137/product137.jpg', 137);
INSERT INTO `images` VALUES (131, '138/product138.jpg', 138);
INSERT INTO `images` VALUES (132, '139/product139.jpg', 139);
INSERT INTO `images` VALUES (133, '140/product140.jpg', 140);
INSERT INTO `images` VALUES (134, '141/product141.jpg', 141);
INSERT INTO `images` VALUES (135, '142/product142.jpg', 142);
INSERT INTO `images` VALUES (136, '143/product143.jpg', 143);
INSERT INTO `images` VALUES (137, '144/product144.jpg', 144);
INSERT INTO `images` VALUES (138, '145/product145.jpg', 145);
INSERT INTO `images` VALUES (139, '146/product146.jpg', 146);
INSERT INTO `images` VALUES (140, '147/product147.jpg', 147);
INSERT INTO `images` VALUES (141, '148/product148.jpg', 148);
INSERT INTO `images` VALUES (142, '149/product149.jpg', 149);
INSERT INTO `images` VALUES (143, '150/product150.jpg', 150);
INSERT INTO `images` VALUES (144, '151/product151.jpg', 151);
INSERT INTO `images` VALUES (145, '152/product152.jpg', 152);
INSERT INTO `images` VALUES (146, '153/product153.jpg', 153);
INSERT INTO `images` VALUES (147, '154/product154.jpg', 154);
INSERT INTO `images` VALUES (148, '155/product155.jpg', 155);
INSERT INTO `images` VALUES (149, '156/product156.jpg', 156);
INSERT INTO `images` VALUES (150, '157/product157.jpg', 157);
INSERT INTO `images` VALUES (151, '158/product158.jpg', 158);
INSERT INTO `images` VALUES (152, '159/product159.jpg', 159);
INSERT INTO `images` VALUES (153, '160/product160.jpg', 160);
INSERT INTO `images` VALUES (154, '161/product161.jpg', 161);
INSERT INTO `images` VALUES (155, '162/product162.jpg', 162);
INSERT INTO `images` VALUES (156, '163/product163.jpg', 163);
INSERT INTO `images` VALUES (157, '164/product164.jpg', 164);
INSERT INTO `images` VALUES (158, '165/product165.jpg', 165);
INSERT INTO `images` VALUES (159, '166/product166.jpg', 166);
INSERT INTO `images` VALUES (160, '167/product167.jpg', 167);
INSERT INTO `images` VALUES (161, '168/product168.jpg', 168);
INSERT INTO `images` VALUES (162, '169/product169.jpg', 169);
INSERT INTO `images` VALUES (163, '170/product170.jpg', 170);
INSERT INTO `images` VALUES (164, '171/product171.jpg', 171);
INSERT INTO `images` VALUES (165, '172/product172.jpg', 172);
INSERT INTO `images` VALUES (166, '173/product173.jpg', 173);
INSERT INTO `images` VALUES (167, '174/product174.jpg', 174);
INSERT INTO `images` VALUES (168, '175/product175.jpg', 175);
INSERT INTO `images` VALUES (169, '176/product176.jpg', 176);
INSERT INTO `images` VALUES (170, '177/product177.jpg', 177);
INSERT INTO `images` VALUES (171, '178/product178.jpg', 178);
INSERT INTO `images` VALUES (172, '179/product179.jpg', 179);
INSERT INTO `images` VALUES (173, '180/product180.jpg', 180);
INSERT INTO `images` VALUES (174, '181/product181.jpg', 181);
INSERT INTO `images` VALUES (175, '182/product182.jpg', 182);
INSERT INTO `images` VALUES (176, '183/product183.jpg', 183);
INSERT INTO `images` VALUES (177, '184/product184.jpg', 184);
INSERT INTO `images` VALUES (178, '185/product185.jpg', 185);
INSERT INTO `images` VALUES (179, '186/product186.jpg', 186);
INSERT INTO `images` VALUES (180, '187/product187.jpg', 187);
INSERT INTO `images` VALUES (181, '188/product188.jpg', 188);
INSERT INTO `images` VALUES (182, '189/product189.jpg', 189);
INSERT INTO `images` VALUES (183, '190/product190.jpg', 190);
INSERT INTO `images` VALUES (184, '191/product191.jpg', 191);
INSERT INTO `images` VALUES (185, '192/product192.jpg', 192);
INSERT INTO `images` VALUES (186, '193/product193.jpg', 193);
INSERT INTO `images` VALUES (187, '194/product194.jpg', 194);
INSERT INTO `images` VALUES (188, '195/product195.jpg', 195);
INSERT INTO `images` VALUES (189, '196/product196.jpg', 196);
INSERT INTO `images` VALUES (190, '197/product197.jpg', 197);
INSERT INTO `images` VALUES (191, '198/product198.jpg', 198);
INSERT INTO `images` VALUES (192, '199/product199.jpg', 199);
INSERT INTO `images` VALUES (193, '200/product200.jpg', 200);
INSERT INTO `images` VALUES (194, '201/product201.jpg', 201);
INSERT INTO `images` VALUES (195, '202/product202.jpg', 202);
INSERT INTO `images` VALUES (196, '203/product203.jpg', 203);
INSERT INTO `images` VALUES (197, '204/product204.jpg', 204);
INSERT INTO `images` VALUES (198, '205/product205.jpg', 205);
INSERT INTO `images` VALUES (199, '206/product206.jpg', 206);
INSERT INTO `images` VALUES (200, '207/product207.jpg', 207);
INSERT INTO `images` VALUES (201, '208/product208.jpg', 208);
INSERT INTO `images` VALUES (202, '209/product209.jpg', 209);
INSERT INTO `images` VALUES (203, '210/product210.jpg', 210);
INSERT INTO `images` VALUES (204, '211/product211.jpg', 211);
INSERT INTO `images` VALUES (205, '212/product212.jpg', 212);
INSERT INTO `images` VALUES (206, '213/product213.jpg', 213);
INSERT INTO `images` VALUES (207, '214/product214.jpg', 214);
INSERT INTO `images` VALUES (208, '215/product215.jpg', 215);
INSERT INTO `images` VALUES (209, '216/product216.jpg', 216);
INSERT INTO `images` VALUES (210, '217/product217.jpg', 217);
INSERT INTO `images` VALUES (211, '218/product218.jpg', 218);
INSERT INTO `images` VALUES (220, '4/Screenshot 2024-03-24 170336.png', 4);
INSERT INTO `images` VALUES (221, '3/product55.jpg', 3);
INSERT INTO `images` VALUES (222, '3/product15.jpg', 3);
INSERT INTO `images` VALUES (223, '3/product42.jpg', 3);
INSERT INTO `images` VALUES (324, '2/product34.jpg', 2);
INSERT INTO `images` VALUES (328, '2/product5.jpg', 2);
INSERT INTO `images` VALUES (350, '1/product5.jpg', 1);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `productId` int NULL DEFAULT NULL,
  `productName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sizeRequired` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `colorRequired` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantityRequired` int NOT NULL DEFAULT 1,
  `price` double NOT NULL COMMENT 'Ghi lại giá trị của productId tại thười điểm mua',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_order_details_products`(`productId`) USING BTREE,
  INDEX `FK_order_details_orders`(`orderId`) USING BTREE,
  CONSTRAINT `FK_order_details_orders` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_details_products` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (1, '12e30d6', 43, 'Áo len dệt kim oversize', 'Dài áo: 70 cm, Dài tay: 22 cm, Rộng gấu: 54 cm, Rộng bắp tay: 24 cm, Rộng vai: 50 cm', '#ffffff', 3, 465000);
INSERT INTO `order_details` VALUES (2, '12e30d6', 50, 'Áo khoác sơ mi', 'xl', '#ffffff', 2, 468000);
INSERT INTO `order_details` VALUES (3, '14d8d71', 44, 'Áo khoác denim', 'x', '#ffffff', 14, 500000);
INSERT INTO `order_details` VALUES (4, '14d8d71', 48, 'Áo khoác len', 'xl', '#ffffff', 12, 416000);
INSERT INTO `order_details` VALUES (5, '650479a', 25, 'Áo nỉ chui đầu Lifewear', 'xl', '#ffffff', 11, 309000);
INSERT INTO `order_details` VALUES (6, '650479a', 32, 'Shorts thể thao 5\" Movement', 'x', '#ffffff', 3, 99000);
INSERT INTO `order_details` VALUES (7, '5638e62', 43, 'Áo len dệt kim oversize', 'x', '#ffffff', 3, 465000);
INSERT INTO `order_details` VALUES (8, '5638e62', 28, 'Polo Pique Cotton', 'xl', '#ffffff', 12, 259000);
INSERT INTO `order_details` VALUES (9, '84ec912', 17, 'Quần Kaki baggy trơn unisex nam nữ lưng thun dây r', 'xxl', '#ffffff', 11, 295000);
INSERT INTO `order_details` VALUES (10, '84ec912', 50, 'Áo khoác sơ mi', 'xxl', '#ffffff', 4, 468000);
INSERT INTO `order_details` VALUES (11, '381592e', 36, 'Sơ mi dài tay Easycare', 'xxl', '#ffffff', 11, 199000);
INSERT INTO `order_details` VALUES (12, '381592e', 21, 'Sơ mi dài tay Café-DriS', 'x', '#ffffff', 2, 429000);
INSERT INTO `order_details` VALUES (13, '60d0b4e', 12, 'Áo phông raglan unisex basic cotton dày dặn form o', 'x', '#ffffff', 5, 235000);
INSERT INTO `order_details` VALUES (14, '60d0b4e', 57, 'Quần jeans cotton', 'xxl', '#ffffff', 5, 511000);
INSERT INTO `order_details` VALUES (15, '444cfa1', 14, 'Áo sơ mi nam tay dài form rộng vải lụa dày dặn', 'xl', '#ffffff', 10, 246000);
INSERT INTO `order_details` VALUES (16, '7a7b008', 50, 'Áo khoác sơ mi', 'xxl', '#ffffff', 6, 468000);
INSERT INTO `order_details` VALUES (17, '9f290fc', 57, 'Quần jeans cotton', 'xl', '#ffffff', 3, 511000);
INSERT INTO `order_details` VALUES (18, '7a7b008', 12, 'Áo phông raglan unisex basic cotton dày dặn form o', 'x', '#ffffff', 5, 235000);
INSERT INTO `order_details` VALUES (19, '3be8fce', 57, 'Quần jeans cotton', 'xxl', '#ffffff', 3, 511000);
INSERT INTO `order_details` VALUES (20, '3be8fce', 60, 'Áo nỉ Square Cross', 'xxl', '#ffffff', 10, 549000);
INSERT INTO `order_details` VALUES (21, 'f422183', 52, 'Quần tây dáng đứng', 'x', '#ffffff', 16, 718000);
INSERT INTO `order_details` VALUES (22, 'f422183', 43, 'Áo len dệt kim oversize', 'xxl', '#ffffff', 4, 465000);
INSERT INTO `order_details` VALUES (23, '444cfa1', 54, 'Áo polo logo rubber', 'xxl', '#ffffff', 11, 324000);
INSERT INTO `order_details` VALUES (24, '444cfa1', 21, 'Sơ mi dài tay Café-DriS', 'xl', '#ffffff', 6, 429000);
INSERT INTO `order_details` VALUES (25, '01c010e', 45, 'Quần short đính đá', 'x', '#ffffff', 10, 549000);
INSERT INTO `order_details` VALUES (26, '01c010e', 9, 'Quần jean nam baggy cạp chun bản to ống rộng', 'x', '#ffffff', 7, 200000);
INSERT INTO `order_details` VALUES (27, '0194e6c', 32, 'Shorts thể thao 5\" Movement', 'xxl', '#ffffff', 7, 99000);
INSERT INTO `order_details` VALUES (28, '0194e6c', 12, 'Áo phông raglan unisex basic cotton dày dặn form o', 'xl', '#ffffff', 5, 235000);
INSERT INTO `order_details` VALUES (29, 'caa8198', 12, 'Áo phông raglan unisex basic cotton dày dặn form o', 'x', '#ffffff', 17, 235000);
INSERT INTO `order_details` VALUES (30, 'caa8198', 9, 'Quần jean nam baggy cạp chun bản to ống rộng', 'xxl', '#ffffff', 6, 200000);
INSERT INTO `order_details` VALUES (31, 'be560c3', 58, 'Áo suit len', 'x', '#ffffff', 1, 1471000);
INSERT INTO `order_details` VALUES (32, 'be560c3', 44, 'Áo khoác denim', 'x', '#ffffff', 1, 500000);
INSERT INTO `order_details` VALUES (33, 'f2ded5d', 55, 'Áo T-shirt oversize in logo Sandro', 'x', '#ffffff', 1, 268000);
INSERT INTO `order_details` VALUES (34, 'f2ded5d', 12, 'Áo phông raglan unisex basic cotton dày dặn form o', 'x', '#ffffff', 16, 235000);
INSERT INTO `order_details` VALUES (35, '9f290fc', 56, 'Áo sơ mi Square Cross', 'x', '#ffffff', 18, 634000);
INSERT INTO `order_details` VALUES (36, '662732646', 2, 'Áo polo thể thao unisex nam nữ', 'XL', '#0000ff', 3, 239000);
INSERT INTO `order_details` VALUES (37, '662732646', 2, 'Áo polo thể thao unisex nam nữ', 'Dài áo: 90 cm, Ngang ngực: 50 cm, Dài tay: 20 cm, Rộng vai: 45 cm', '#000000', 2, 239000);

-- ----------------------------
-- Table structure for order_statuses
-- ----------------------------
DROP TABLE IF EXISTS `order_statuses`;
CREATE TABLE `order_statuses`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_statuses
-- ----------------------------
INSERT INTO `order_statuses` VALUES (1, 'Chờ xác nhận');
INSERT INTO `order_statuses` VALUES (2, 'Đã xác nhận');
INSERT INTO `order_statuses` VALUES (3, 'Đang vận chuyển');
INSERT INTO `order_statuses` VALUES (4, 'Hoàn thành');
INSERT INTO `order_statuses` VALUES (5, 'Đã hủy');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'AUTO_INCREMENT',
  `userId` int NULL DEFAULT NULL,
  `dateOrder` date NULL DEFAULT NULL,
  `paymentMethodId` int NULL DEFAULT NULL,
  `fullName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transactionStatusId` int NULL DEFAULT NULL COMMENT 'Đơn hàng mới, \r\nđã xác nhận, \r\nđang vận chuyển, \r\nhoàn thành, \r\nđã hủy',
  `orderStatusId` int NULL DEFAULT NULL COMMENT 'Chưa thanh toán, đã thanh toán',
  `voucherId` int NULL DEFAULT NULL,
  `deliveryMethodId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_orders_users`(`userId`) USING BTREE,
  INDEX `FK_orders_vouchers`(`voucherId`) USING BTREE,
  INDEX `FK_orders_delivery_methods`(`deliveryMethodId`) USING BTREE,
  INDEX `FK_orders_payment_methods`(`paymentMethodId`) USING BTREE,
  INDEX `FK_orders_order_statuses`(`orderStatusId`) USING BTREE,
  INDEX `FK_orders_transaction_statuses`(`transactionStatusId`) USING BTREE,
  CONSTRAINT `FK_orders_delivery_methods` FOREIGN KEY (`deliveryMethodId`) REFERENCES `delivery_methods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_order_statuses` FOREIGN KEY (`orderStatusId`) REFERENCES `order_statuses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_payment_methods` FOREIGN KEY (`paymentMethodId`) REFERENCES `payment_methods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_transaction_statuses` FOREIGN KEY (`transactionStatusId`) REFERENCES `transaction_statuses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_vouchers` FOREIGN KEY (`voucherId`) REFERENCES `vouchers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0194e6c', 7, '2024-01-09', 1, 'Cáp Hữu Ðạt', 'HuuDat@gmail.com', '0328012964', 'Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang', 1, 1, NULL, 2);
INSERT INTO `orders` VALUES ('01c010e', 6, '2022-11-03', 3, 'Nhan Công Hiếu', 'CongHieu6@gmail.com', '0325976083', 'Xã Đắk Choong, Huyện Đắk Glei, Kon Tum', 2, 4, 1, 2);
INSERT INTO `orders` VALUES ('12e30d6', 15, '2022-10-31', 1, 'Lê Văn Nam', 'NamLe@gmail.com', '0909876543', 'Phường Bình Hòa, Thị xã Thuận An, Bình Dương', 1, 4, 2, 1);
INSERT INTO `orders` VALUES ('14d8d71', 4, '2022-10-30', 3, 'Trần Thị An', 'anTran@yahoo.com', '0901234567', 'Phường Hòa Khánh, Quận Liên Chiểu, Đà Nẵng', 2, 2, NULL, 3);
INSERT INTO `orders` VALUES ('381592e', 9, '2024-01-09', 1, 'Hầu Thanh Long', 'ThanhLong@egmail.com', '0707405391', 'Xã An Nông, Huyện Tịnh Biên, An Giang', 2, 5, NULL, 1);
INSERT INTO `orders` VALUES ('3be8fce', 14, '2024-01-09', 1, 'Nguyễn Chí Hiếu', 'HieuNguyen@gmail.com', '0703637448', '164 Ngô Tất Tố, Bình Định', 1, 3, NULL, 2);
INSERT INTO `orders` VALUES ('444cfa1', 4, '2022-11-01', 3, 'Nguyễn Thị Linh', 'linhNguyen@gmail.com', '0908765432', 'Phường Tây Sơn, Quận Đống Đa, Hà Nội', 2, 2, 5, 2);
INSERT INTO `orders` VALUES ('5638e62', 6, '2022-11-04', 3, 'Mai Thị Thu', 'ThuMai@gmail.com', '0909876123', 'Phường Hòa An, Quận Cẩm Lệ, Đà Nẵng', 1, 2, NULL, 2);
INSERT INTO `orders` VALUES ('60d0b4e', 10, '2022-11-06', 2, 'Phùng Thanh Nhàn', 'nhanPhung@gmail.com', '0907722801', '80/50, khu phố Bình Đường 2, An Bình, Dĩ An, Bình Dương', 2, 2, NULL, 3);
INSERT INTO `orders` VALUES ('650479a', 5, '2022-11-02', 2, 'Ngụy Ðông Phương', 'DongPhuong@gmail.com', '0783748159', 'Xã Nghĩa Hưng, Huyện Chư Păh, Gia Lai', 2, 2, NULL, 1);
INSERT INTO `orders` VALUES ('662732646', 15, '2024-01-27', 1, '', 'ducvui2003@gmail.com', '', '', 1, 1, NULL, 2);
INSERT INTO `orders` VALUES ('7a7b008', 12, '2024-01-08', 2, 'Quản Xuân Ninh', 'XuanNinh@gmail.com', '0392703698', 'Thị trấn Vĩnh Thạnh, Huyện Vĩnh Thạnh, Bình Định', 1, 3, NULL, 1);
INSERT INTO `orders` VALUES ('84ec912', 8, '2022-11-05', 3, 'Thi Hồ Nam', 'HoNam8@gmail.com', '0886819054', 'Xã Tham Đôn, Huyện Mỹ Xuyên, Sóc Trăng', 1, 2, 8, 1);
INSERT INTO `orders` VALUES ('9f290fc', 13, '2024-01-08', 1, 'Mai Tiến Dũng', 'TienDung@gmail.com', '0902346795', 'Xã Đức Long, Huyện Hoà An, Cao Bằng', 2, 3, NULL, 1);
INSERT INTO `orders` VALUES ('be560c3', 9, '2024-01-08', 1, 'Hầu Thanh Long', 'ThanhLong@egmail.com', '0707405391', 'Xã An Nông, Huyện Tịnh Biên, An Giang', 2, 5, NULL, 1);
INSERT INTO `orders` VALUES ('caa8198', 8, '2022-11-06', 1, 'Lê Thị Hà', 'HaLe@gmail.com', '0908887777', 'Phường Tây Sơn, Quận Thanh Khê, Đà Nẵng', 2, 4, NULL, 3);
INSERT INTO `orders` VALUES ('f2ded5d', 11, '2024-01-09', 1, 'Thục Kim Lan', 'KimLan@gmail.com', '0797204681', 'Phường Thốt Nốt, Quận Thốt Nốt, Cần Thơ', 2, 3, NULL, 2);
INSERT INTO `orders` VALUES ('f422183', 3, '2022-10-29', 3, 'Hoàng Văn Long', 'longhoang@gmail.com', '0905123456', 'Số 123 Đường Lê Lai, Phường Phạm Ngũ Lão, Quận 1, TP.HCM', 1, 2, NULL, 1);

-- ----------------------------
-- Table structure for parameters
-- ----------------------------
DROP TABLE IF EXISTS `parameters`;
CREATE TABLE `parameters`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `minValue` double NOT NULL DEFAULT 0,
  `maxValue` double NOT NULL DEFAULT 0,
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `categoryId` int NOT NULL,
  `guideImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK__categories`(`categoryId`) USING BTREE,
  CONSTRAINT `FK__categories` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parameters
-- ----------------------------
INSERT INTO `parameters` VALUES (1, 'Dài áo', 67.5, 80, 'cm', 1, 'aea7430c-9395-4f17-9cd8-70f6df5b3933.jpg');
INSERT INTO `parameters` VALUES (2, 'Ngang ngực', 48, 58, 'cm', 1, '703cc902-1901-4a6f-8e1f-39256b4d270b.jpg');
INSERT INTO `parameters` VALUES (3, 'Dài tay', 59, 70, 'cm', 1, '8a704f75-e7fb-47b7-b723-b2659cf29876.png');
INSERT INTO `parameters` VALUES (4, 'Rộng vai', 41, 49, 'cm', 1, 'cc885068-28c2-4edc-bb9a-0d04e680fffe.jpg');
INSERT INTO `parameters` VALUES (5, 'Dài áo', 69, 73, 'cm', 2, '0815f54f-3842-4e6f-b537-0c72867aa0f5.jpg');
INSERT INTO `parameters` VALUES (6, 'Dài tay', 19, 23, 'cm', 2, 'bd352337-f7d7-44fa-8350-84d856699ba6.png');
INSERT INTO `parameters` VALUES (7, 'Rộng gấu', 54, 60, 'cm', 2, '40eb8c2e-8717-48b1-b35c-208ddc64fe38.jpg');
INSERT INTO `parameters` VALUES (8, 'Rộng bắp tay', 21, 25, 'cm', 2, '81322aad-123f-4c8e-b68d-1f6e3f499ada.jpg');
INSERT INTO `parameters` VALUES (9, 'Rộng vai', 45, 50, 'cm', 2, 'ecc72a1d-95ba-4680-9c7d-53b53a358575.jpg');
INSERT INTO `parameters` VALUES (10, 'Dài áo', 63, 80, 'cm', 3, '67c9a61b-b4ea-4118-9d53-561f85fc18f2.jpg');
INSERT INTO `parameters` VALUES (11, 'Dài tay', 66, 85, 'cm', 3, '18f8995e-9523-40e4-bc10-fd5aa84a7d16.png');
INSERT INTO `parameters` VALUES (12, 'Rộng ngực', 47, 60, 'cm', 3, '6a4258c8-f14d-4942-a441-20f53d15c934.jpg');
INSERT INTO `parameters` VALUES (13, 'Rộng gấu áo', 43, 55, 'cm', 3, 'ee486720-2890-4f36-9458-6cfe81ab7a82.jpg');
INSERT INTO `parameters` VALUES (14, 'Dài quần', 36, 45, 'cm', 4, '6ce92687-4225-4e44-b0d8-241af6c396b1.jpg');
INSERT INTO `parameters` VALUES (15, 'Rộng mông', 50, 70, 'cm', 4, '083a490c-1a48-4f7e-b16b-8d4a3291ce60.jpg');
INSERT INTO `parameters` VALUES (16, 'Rộng gấu', 30, 37, 'cm', 4, '3c7923e5-3c0c-4643-8e64-a1e2c4fd2b84.jpg');
INSERT INTO `parameters` VALUES (17, 'Dài áo', 65, 90, 'cm', 5, '1243de4b-f15e-44f7-9f60-7ab7f80dd8f9.jpg');
INSERT INTO `parameters` VALUES (18, 'Ngang ngực', 45, 60, 'cm', 5, 'c75ee94f-18a0-4a87-b23e-2c202f4ba442.jpg');
INSERT INTO `parameters` VALUES (19, 'Dài tay', 19, 30, 'cm', 5, '2c42fd95-ee9e-4e0c-a8b9-fd6d3b94b226.png');
INSERT INTO `parameters` VALUES (20, 'Rộng vai', 40, 50, 'cm', 5, '14d080a8-013b-46c6-ab96-2cfd95df36d3.jpg');
INSERT INTO `parameters` VALUES (21, 'Dài áo', 65, 87, 'cm', 6, 'cee3ed29-08cf-4006-a05a-2b228a4290d4.jpg');
INSERT INTO `parameters` VALUES (22, 'Ngang ngực', 45, 60, 'cm', 6, '0b95c35f-fe45-4049-9254-f5772b8e4494.jpg');
INSERT INTO `parameters` VALUES (23, 'Dài tay', 55, 75, 'cm', 6, '79dcbc18-bb71-4366-95f2-c87feef51653.png');
INSERT INTO `parameters` VALUES (24, 'Rộng vai', 40, 60, 'cm', 6, '212ddcb1-bb6f-4709-a856-43bd7c333ffa.jpg');
INSERT INTO `parameters` VALUES (25, 'Dài áo', 65, 70, 'cm', 7, '91b6b04e-fb7e-4df3-860a-3ad54f239991.jpg');
INSERT INTO `parameters` VALUES (26, 'Ngang ngực', 45, 60, 'cm', 7, '76ef697c-baf0-4040-8456-95736d8580a5.jpg');
INSERT INTO `parameters` VALUES (27, 'Dài tay', 18, 25, 'cm', 7, 'e0112c5d-59d4-4a96-9b0c-212c7060f9a7.png');
INSERT INTO `parameters` VALUES (28, 'Rộng vai', 40, 60, 'cm', 7, '91efe139-5f96-4535-b864-a10e7bea9029.jpg');
INSERT INTO `parameters` VALUES (29, 'Rộng cạp', 35, 50, 'cm', 8, 'cd7036bb-062f-4468-801c-5f5162c8c474.png');
INSERT INTO `parameters` VALUES (30, 'Rộng mông', 45, 58, 'cm', 8, '65c8eb55-c9bd-493c-93f6-257e27ac5b78.jpg');
INSERT INTO `parameters` VALUES (31, 'Rộng đùi cách đũng quần 2.5cm', 25, 33, 'cm', 8, 'b3a5f505-d6d7-4dea-a927-5576e84fdfab.jpg');
INSERT INTO `parameters` VALUES (32, 'Rộng Gấu', 13, 17, 'cm', 8, '396a2913-f7fe-4c15-a70f-4df4a385ebab.jpg');
INSERT INTO `parameters` VALUES (33, 'Dài quần', 95, 120, 'cm', 8, 'e13b6fbb-a005-4441-b748-fb719edd99a7.jpg');
INSERT INTO `parameters` VALUES (34, 'Rộng cạp đo 1/2', 35, 50, 'cm', 9, '7f2e62d2-1ec4-4c1d-bd1d-10c11cefa407.jpg');
INSERT INTO `parameters` VALUES (35, 'Rộng mông', 45, 63, 'cm', 9, 'b76b7842-9615-436c-bb6f-e6e460db8aa7.jpg');
INSERT INTO `parameters` VALUES (36, 'Rộng ngang ống đo 1/2', 20, 30, 'cm', 9, 'de9015e5-dc73-428d-9683-6dee8defe94f.jpg');
INSERT INTO `parameters` VALUES (37, 'Dài quần', 47, 65, 'cm', 9, '73bf1c54-d229-4f60-9df9-b7c293bb1f58.jpg');
INSERT INTO `parameters` VALUES (38, 'Dài áo', 65, 80, 'cm', 10, 'cfb58f8d-95fc-44af-b735-3bb417d8428f.jpg');
INSERT INTO `parameters` VALUES (39, 'Rộng vai', 27, 35, 'cm', 10, '9c7f7009-e5c4-4e5a-9630-f2d8b23278d5.jpg');
INSERT INTO `parameters` VALUES (40, 'Rộng ngực', 39, 65, 'cm', 10, '6ebc0149-3401-41e3-80c8-077605ec71b5.jpg');
INSERT INTO `parameters` VALUES (41, 'Vòng nách trước', 20, 32, 'cm', 10, 'fcb6903a-f484-4f92-a923-9813056e4a54.png');
INSERT INTO `parameters` VALUES (42, 'Dài quần', 90, 110, 'cm', 11, '7cce925e-ee15-4913-a689-28c847a70dda.jpg');
INSERT INTO `parameters` VALUES (43, '1/2 vòng cạp', 30, 45, 'cm', 11, '0da8a263-cea3-48f3-bb43-76d773ba418f.jpg');
INSERT INTO `parameters` VALUES (44, '1/2 vòng mông', 45, 60, 'cm', 11, '58e5fe95-916a-4ad0-98d7-5974e8f3aed0.jpg');
INSERT INTO `parameters` VALUES (45, '1/2 vòng gấu', 10, 15, 'cm', 11, '7f9da392-8038-467d-94e1-3bf14b3aae13.jpg');
INSERT INTO `parameters` VALUES (59, 'Cao mũ', 23, 29, 'cm', 20, '33ff36b8-4c46-4920-b6f8-3e1e4072b016.png');
INSERT INTO `parameters` VALUES (60, 'Rộng ngực', 37, 45.5, 'cm', 20, '136fc424-bfba-45db-8686-66c7ff8a0521.jpg');
INSERT INTO `parameters` VALUES (61, 'Dài tay', 40, 53, 'cm', 20, '08a851aa-a829-469c-9c88-9cc68a7de72f.png');
INSERT INTO `parameters` VALUES (62, 'Cao mũ', 23, 29, 'cm', 21, '3eeba016-853e-474a-b4a5-595e51c888a5.png');
INSERT INTO `parameters` VALUES (63, 'Rộng ngực', 37, 45.5, 'cm', 21, '997401d4-a89d-41cf-b253-778aa1bfd9de.jpg');
INSERT INTO `parameters` VALUES (64, 'Dài áo', 40, 53, 'cm', 21, '54648d66-d38a-43b8-be7d-ae4acfc34e77.jpg');

-- ----------------------------
-- Table structure for payment_methods
-- ----------------------------
DROP TABLE IF EXISTS `payment_methods`;
CREATE TABLE `payment_methods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typePayment` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_methods
-- ----------------------------
INSERT INTO `payment_methods` VALUES (1, 'Thanh toán bằng tiền mặt khi nhận hàng (COD)');
INSERT INTO `payment_methods` VALUES (2, 'Thanh toán qua atm nội địa / internet banking');
INSERT INTO `payment_methods` VALUES (3, 'Thanh toán qua ví điện tử');

-- ----------------------------
-- Table structure for payment_owner
-- ----------------------------
DROP TABLE IF EXISTS `payment_owner`;
CREATE TABLE `payment_owner`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `paymentMethodId` int NULL DEFAULT 0,
  `ownerName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `paymentPlatform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `accountNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_paymentMethod_owner`(`paymentMethodId`) USING BTREE,
  CONSTRAINT `FK_paymentMethod_owner` FOREIGN KEY (`paymentMethodId`) REFERENCES `payment_methods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_owner
-- ----------------------------
INSERT INTO `payment_owner` VALUES (1, 2, 'TRỊNH TRẦN SỸ ĐÔNG', 'BIDV (Ngân hàng TMCP Đầu Tư & Phát Triển Việt Nam)', '721129272');
INSERT INTO `payment_owner` VALUES (2, 3, 'TRỊNH TRẦN SỸ ĐÔNG', 'Momo', '0334761730');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `categoryId` int NOT NULL DEFAULT 0,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `originalPrice` double NOT NULL,
  `salePrice` double NULL DEFAULT NULL,
  `visibility` bit(1) NULL DEFAULT b'1',
  `createAt` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_products_categories`(`categoryId`) USING BTREE,
  CONSTRAINT `FK_products_categories` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 219 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, 'ao the thao', 5, '<p>Chất liệu: AiryCotton (Cotton 85%, Poly 5%, Spandex 10%) mềm mại, co gi&atilde;n 4 chiều v&agrave; thấm h&uacute;t mồ h&ocirc;i tốt tạo cảm gi&aacute;c thoải m&aacute;i v&agrave; m&aacute;t mẻ</p>', 350000, 279000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (2, 'Áo polo thể thao unisex nam nữ', 5, '<p>Chất liệu: 100% Polyster kh&ocirc; nhanh, thấm h&uacute;t nhanh, kh&ocirc;ng nhăn v&agrave; khả năng khử m&ugrave;i tự nhi&ecirc;n tạo cảm gi&aacute;c tho&aacute;ng m&aacute;t khi vận động</p>', 299000, 239000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (3, 'Áo polo phối khóa kéo unisex nam nữ', 5, '<p>Chất liệu: 100% cotton d&agrave;y dặn, thấm h&uacute;t tốt, &iacute;t nhăn v&agrave; dễ phối đồ</p>', 350000, 289000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (4, 'Áo hoodie form rộng unisex nam nữ nỉ chân cua', 3, '<p>Chất liệu: mặt nền (100% coton), mặc trong (65/35 - 65% cotton v&agrave; 35 CVC) c&oacute; độ bền cao, kh&ocirc;ng phai m&agrave;u v&agrave; mang lại cảm gi&aacute;c dễ chịu, m&aacute;t mẻ cho người mặc</p>', 490000, 259000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (5, 'Áo hoodie zip form boxy unisex nỉ bông dày dặn', 3, '<p>Chất liệu: 100% cotton mềm mại, d&agrave;y dặn, ấm &aacute;p với thiết kế kho&aacute; k&eacute;o tiện lợi gi&uacute;p dễ d&agrave;ng khi mặc</p>', 300000, 250000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (6, 'Áo sweater unisex nam nữ basic nỉ chân cua', 3, 'Chất liệu: 100% cotton dày dặn, mêm mịn, đứng form, ít nhăn, siêu ấm áp cho mùa gió lạnh về', 350000, 288000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (7, 'Áo sweater nam trơn basic vải da cá', 3, ' Chất liệu: 100% cotton da cá dày dặn, mềm mịn co giãn 4 chiều thông thoáng, thấm hút tốt và không bị bạc màu sau thời gian dài sử dụng', 250000, 200000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (8, 'Quần jean unisex nam nữ cạp cao ống suông rộng', 8, 'Chất liệu: jeans (95% cotton, 5% spandex) không phai màu, thấm hút tốt và mềm mại, dễ phối đồ', 290000, 260000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (9, 'Quần jean nam baggy cạp chun bản to ống rộng', 8, '<p>Chất liệu: jeans (95% cotton, 5% spandex) kh&ocirc;ng phai m&agrave;u, thấm h&uacute;t tốt v&agrave; mềm mại, dễ phối đồ</p>', 290000, 200000, b'1', '2024-01-06');
INSERT INTO `products` VALUES (10, 'Quần jean unisex nam nữ cotton form slimfit', 8, '<p>Chất liệu: jeans (97% cotton, 3% spandex) co gi&atilde;n tốt, bền m&agrave;u v&agrave; độ rộng vừa phải tạo cảm gi&aacute;c thoải m&aacute;i cho người mặc</p>', 375000, 225000, b'1', '2024-01-25');
INSERT INTO `products` VALUES (11, 'Áo thun unisex nam nữ basic tee form oversize', 2, 'Chất liệu 100% cotton mềm mịn co giãn 2 chiều, thoáng mát, thấm hút mồ hôi, ít nhăn và mang lại cảm giác thoải mái cho người mặc', 390000, 260000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (12, 'Áo phông raglan unisex nam nữ basic cotton dày dặn form oversize', 2, 'Chất liệu: 100% cotton 2 chiều cao cấp dày dặn, thoáng mát không bí nóng tạo cảm giác thoải mái và tôn dáng cho người mặc', 275000, 235000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (13, 'Áo phông layer pattern unisex nam nữ form oversize', 2, '<p>Chất liệu: 100% cotton mềm mịn, thoải m&aacute;i v&agrave; đem lại sự thoải m&aacute;i tiện lợi nhất cho người mặc</p>', 358000, 279000, b'1', '2024-01-06');
INSERT INTO `products` VALUES (14, 'Áo sơ mi nam tay dài form rộng vải lụa dày dặn', 6, '<p>Chất liệu: 100% cotton vải lụa mềm mại, chống nhăn v&agrave; độ rộng vừa phải, t&ocirc;n d&aacute;ng, trẻ trung ph&ugrave; hợp mặc cho nhiều dịp</p>', 299000, 246000, b'1', '2024-01-06');
INSERT INTO `products` VALUES (15, 'abc', 6, '<p>Chất liệu: Oxford (65% cotton, 20% PE 15% viscose) tho&aacute;ng m&aacute;t, mềm mịn v&agrave; c&oacute; khả năng thấm h&uacute;t mồ h&ocirc;i tốt dễ d&agrave;ng giặt ủi v&agrave; bền m&agrave;u trong 1 thời gian d&agrave;i sử dụng</p>', 455000, 299000, b'1', '2024-03-27');
INSERT INTO `products` VALUES (16, 'Áo sơ mi công sở unisex nam nữ tay dài vải lụa form form slimfit', 6, 'Chất liệu: cotton lụa (80% cotton, 20% polys) miền mịn, vừa vặn không ôm sát và đủ để tạo cảm giác thoải mái cho người mặc', 389000, 289000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (17, 'Quần Kaki baggy trơn unisex nam nữ lưng thun dây rút', 9, 'Chất liệu: 100% cotton kaki dày dặn đàn hồi và co dãn nhẹ, mặt vải mềm mướt không xù lông mang lại cảm giác thoải mái, thoáng mát cho người mặc', 325000, 295000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (21, 'Sơ mi dài tay Café-DriS', 6, 'Chất liệu: 50% S.Café + 50% Recycled PET\r\nPhù hợp với: đi làm, đi chơi\r\nKiểu dáng: Regular fit dáng suông\r\nNgười mẫu: 177 cm - 74 kg, mặc size XL\r\nTự hào sản xuất tại Việt Nam', 499000, 429000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (22, 'Áo dài tay Cotton Compact', 3, '<p>Chất liệu: 95% Cotton Compact - 5% Spandex</p>\r\n\r\n<p>Kiểu d&aacute;ng: &aacute;o thun d&agrave;i tay Ph&ugrave; hợp với: giữ ấm cơ thể v&agrave;o m&ugrave;a đ&ocirc;ng</p>\r\n\r\n<p>Sản xuất tại xưởng 8 năm kinh nghiệm tại TP Hồ Ch&iacute; Minh</p>\r\n\r\n<p>Tự h&agrave;o sản xuất tại VIệt Nam</p>', 269000, 229000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (23, 'Áo dài tay nỉ', 3, '<p>Chất liệu 60% Cotton, 40% Polyester tạo cảm gi&aacute;c th&ocirc;ng tho&aacute;ng v&agrave; thoải m&aacute;i khi mặc Kiểu d&aacute;ng: &aacute;o nỉ d&agrave;i tay Ph&ugrave; hợp với: giữ ấm cơ thể v&agrave;o m&ugrave;a đ&ocirc;ng Người mẫu: 1m75, 69kg * Mặc &aacute;o XL, quần L Tự h&agrave;o sản xuất tại Việt Nam</p>', 339000, 289000, b'1', '2024-01-06');
INSERT INTO `products` VALUES (24, 'Áo dài tay Cotton Compact V2', 1, '<ul>	<li>Chất liệu: 95% Cotton Compact + 5% Spandex</li>	<li>Bề mặt vải Cotton mềm mịn, cảm gi&aacute;c m&aacute;t lần đầu chạm tay</li>	<li>Thấm h&uacute;t mồ h&ocirc;i tốt</li>	<li>Độ x&ugrave; l&ocirc;ng thấp</li>	<li>Vải c&oacute; độ bền cao, co gi&atilde;n 4 chiều v&agrave; hạn chế bai nh&atilde;o</li></ul>', 269000, 229000, b'1', '2024-01-05');
INSERT INTO `products` VALUES (25, 'Áo nỉ chui đầu Lifewear', 3, '<p>Chất liệu: Nỉ ch&acirc;n cua Th&agrave;nh phần: 60% Cotton + 40% Polyester Tho&aacute;ng kh&iacute; v&agrave; thoải m&aacute;i khi mặc, vẫn giữ ấm cho bạn trong trời thu đ&ocirc;ng Hạn chế x&ugrave; l&ocirc;ng, bền m&agrave;u Form d&aacute;ng: Regular, thoải m&aacute;i Tự h&agrave;o sản xuất tại Vi&ecirc;t Nam</p>', 339000, 309000, b'1', '2024-01-27');
INSERT INTO `products` VALUES (26, 'Shorts thể thao 9', 4, 'Chất liệu: 88% Polyester + 12% Spandex\r\nVải có khả năng thấm hút tốt và nhanh khô\r\nCo giãn 4 chiều, thoải mái vận động\r\nTúi to và sâu tiện lợi, thoải mái đựng đồ cá nhân\r\nCó 1 túi khoá kéo ẩn, đựng vừa chìa khoá hay Airpods\r\nĐộ dài đo từ đũng đến viền ống quần: 9\"\r\nTự hào sản xuất tại Việt Nam', 249000, 229000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (27, 'Shorts chạy bộ Ultra', 4, 'Chất liệu: 100% Polyester\r\nXử lý hoàn thiện vải: Quick-Dry + Wicking + Stretch\r\nCông nghệ Chafe-Free hạn chế tối đa ma sát trong quá trình vận động từ các đường may tối giản hoá\r\nPhù hợp với: chơi thể thao, chạy bộ. Được đánh giá bởi các Runner chuyên nghiệp\r\nĐộ dài quần: 5 inch\r\nTự hào sản xuất tại Việt Nam\r\nNgười mẫu: 183 cm - 76 kg, mặc quần 2XL', 279000, 229000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (28, 'Polo Pique Cotton', 5, 'Chất liệu: 100% Cotton\r\nXử lí hoàn thiện giúp bề mặt vải ít xù lông, mềm mịn và bền màu hơn\r\nKiểu dệt Pique giúp áo thoáng mát\r\nĐộ dày vải vừa phải giúp áo tôn dáng\r\nPhù hợp với đi làm, đi chơi\r\nSản xuất tại Nhà máy Tessellation (TGV), Việt Nam. \r\nNgười mẫu: 186cm - 77kg, mặc áo 2XL', 299000, 259000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (29, 'Polo thể thao Promax-S1', 5, '<p>Chất liệu: 100% Poly, định lượng vải 155gsm si&ecirc;u nhẹ</p><p>Xử l&yacute; ho&agrave;n thiện vải: Quick-Dry v&agrave; Wicking</p><p>Ph&ugrave; hợp với: đi l&agrave;m, đi chơi, mặc ở nh&agrave;</p><p>Kiểu d&aacute;ng: Regular fit d&aacute;ng su&ocirc;ng</p><p>Người mẫu: 184 cm - 73 kg, mặc &aacute;o size XL</p>', 239000, 239000, b'1', '2024-01-06');
INSERT INTO `products` VALUES (30, 'Jeans Copper Denim Slim Fit', 8, 'Chất liệu: 12 Oz / 99% Cotton - 1% Spandex\r\nDáng Slim Fit: Dáng ôm tôn dáng, giúp bạn \"hack\" đôi chân dài và gọn đẹp\r\nVải Denim được wash trước khi may nên không rút và hạn chế ra màu sau khi giặt\r\nCảm giác khi chạm mịn màng\r\nNgười mẫu: 175 cm - 69 kg, mặc áo XL, quần size 32\r\nTự hào sản xuất tại Việt Nam\r\nLưu ý:Sản phẩm vẫn sẽ bạc màu sau một thời gian dài sử dụng theo tính chất tự nhiên', 599000, 529000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (31, 'Áo giữ nhiệt Modal Ultra', 1, 'Chất liệu: 50% Modal (gỗ sồi), 42% Cotton, 8% Spandex\r\nƯu điểm của Cotton Modal: kháng khuẩn, giữ nhiệt, mềm mại và co giãn\r\nKiểu dáng: Slimfit ôm nhẹ vào cơ thể\r\nPhù hợp với: mặc hàng ngày hoặc cũng có thể vận động thể thao\r\nNgười mẫu: 1m77 - 69kg * Mặc áo size L\r\nTự hào sản xuất tại Việt Nam', 299000, 159000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (32, 'Shorts thể thao 5\" Movement', 4, '<p>Chất liệu: 100% Polyester kết hợp t&iacute;nh năng: Wicking (Thấm h&uacute;t nhanh), 4-way Mechanical stretch (Co gi&atilde;n 4 chiều) v&agrave; Quick-Dry (Nhanh kh&ocirc;)</p><p>Vải si&ecirc;u nhẹ chỉ 106 gsm, mang lại cảm gi&aacute;c mặc như kh&ocirc;ng mặc</p><p>Vải được dệt kiểu Double Weaving v&agrave; nhuộm bằng c&ocirc;ng nghệ Zero Water Discharge (Kh&ocirc;ng nước thải) theo đuổi xu hướng yếu tố thời trang bền vững</p><p>Ph&ugrave; hợp với: c&aacute;c hoạt động chạy nhẹ v&agrave; tập gym</p><p>Độ d&agrave;i quần: 5 inch</p>', 199000, 99000, b'1', '2024-01-10');
INSERT INTO `products` VALUES (33, 'Shorts thể thao 7\" Movement', 4, '<p>Chất liệu:</p><p>100% Polyester kết hợp t&iacute;nh năng: Wicking (Thấm h&uacute;t nhanh), 4-way Mechanical stretch (Co gi&atilde;n 4 chiều) v&agrave; Quick-Dry (Nhanh kh&ocirc;) Vải si&ecirc;u nhẹ chỉ 106 gsm, mang lại cảm gi&aacute;c mặc như kh&ocirc;ng mặc</p><p>Ph&ugrave; hợp với: mặc thể thao chạy nhẹ hoặc tập gym</p><p>Vải được dệt kiểu Double Weaving v&agrave; nhuộm bằng c&ocirc;ng nghệ Zero Water Discharge (Kh&ocirc;ng nước thải) theo đuổi xu hướng yếu tố thời trang bền vững</p><p>Độ d&agrave;i quần: 7 inch&nbsp;</p>', 239000, 119000, b'1', '2024-01-23');
INSERT INTO `products` VALUES (34, 'Quần Joggers Daily Wear', 11, 'Chất liệu: 100% Polyester\r\nCông nghệ ứng dụng: Quần jogger nam Daily Wear ứng dụng HeiQ ViroBlock giúp ức chế và tiêu diệt vi khuẩn trên bề mặt vải\r\nQuần jogger thể thao hoàn thiện tính năng trượt nước và chống UV 99%\r\nTính năng kháng nước của vải hiệu quả lên đến 30 lần giặt\r\nTự hào sản xuất tại Việt Nam\r\nNgười mẫu: 1m77 - 69kg, mặc quần size XL', 299000, 269000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (35, 'Polo thể thao V1', 5, 'Chất liệu: 100% Recycled Polyester\r\nĐịnh lượng vải 130gsm siêu nhẹ\r\nCông nghệ ứng dụng: Wicking & Quick-Dry\r\nKiểu dáng: áo Polo thể thao vừa vặn\r\nNhà cung cấp vải lĩnh vực đồ thể thao hàng đầu: Promax\r\nTự hào sản xuất tại Việt Nam \r\nNgười mẫu: 184 cm - 73 kg, mặc áo size 2XL', 249000, 199000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (36, 'Sơ mi dài tay Easycare', 6, 'Chất liệu: 100% sợi Polyester Nano-tech\r\nChất liệu thấm hút tốt đem lại sự thoáng khí\r\nKhông nhăn tự nhiên\r\nXử lý chống tia tử ngoại, bảo vệ sức khoẻ\r\nTự hào sản xuất tại Việt Nam\r\nNgười mẫu: 1m77 - 74kg * Mặc size XL', 450000, 199000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (37, 'Quần dài Kaki Excool', 9, 'Chất liệu: 43% Sợi Sorona + 57% Polyester co giãn\r\nCông nghệ Excool: Co giãn 4 chiều, Nhẹ, Thoáng khí, Chống tia UV SPF50+\r\nPhù hợp với: đi làm, đi chơi\r\nKiểu dáng hơi ôm một chút\r\nNgười mẫu: 175 cm - 69 kg, mặc quần size XL\r\nTự hào sản xuất tại Việt Nam', 499000, 429000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (38, 'Jeans Copper Denim Straight', 8, 'Chất liệu: 100% Cotton / 12 Oz\r\nDáng Straight: Dáng suông phóng thoáng, thoải mái, không thùng thình\r\nVải Denim được wash trước khi may nên không rút và hạn chế ra màu sau khi giặt\r\nCảm giác khi chạm mịn màng\r\nNgười mẫu: 175 cm - 69 kg, mặc áo XL, quần size 32\r\nTự hào sản xuất tại Việt Nam\r\nLưu ý: Sản phẩm vẫn sẽ bạc màu sau một thời gian dài sử dụng theo tính chất tự nhiên', 599000, 529000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (39, 'Quần Joggers Excool', 11, 'Thành phần: 100% Polyester\r\nQuần jogger co giãn 4 chiều giúp thoải mái vận động\r\nỨng dụng công nghệ Excool thấm hút tốt, nhanh khô, thoáng khí\r\nDáng quần Slim fit. Chọn cỡ thông thường hoặc lớn hơn một cỡ nếu thích mặc rộng\r\nBo gấu quần jogger cùng chất liệu quần, độ bo vừa phải\r\nQuần có 4 túi, 2 túi xéo và 1 túi ẩn có khoá; 1 túi sau\r\nTự hào sản xuất tại Việt Nam', 279000, 249000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (40, 'Shorts chạy bộ 5\" Power', 4, 'Chất liệu: 43% sợi Recycle, 57% Polyester, theo xu hướng thời trang bền vững\r\nCông nghệ ứng dụng: xử lý hoàn thiện vải Wicking (Thấm hút nhanh) và Mechanical stretch (Co giãn 2 chiều)\r\nPhù hợp với: chạy bộ, tập gym và các hoạt động thể thao khác nhau\r\nĐộ dài quần: 5 inch\r\nTự hào sản xuất tại Việt Nam', 239000, 149000, b'1', '2023-11-09');
INSERT INTO `products` VALUES (41, 'Áo sơ mi sọc', 6, 'Áo sơ mi cổ điển với tay áo dài và bản in sọc tương phản.Áo sơ mi nam Sandro, Dáng cổ điển, Sọc, Tay áo dài, Nút cài', 516000, 456000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (42, 'Áo T-shirt together', 10, 'Áo thun cotton oversized cổ tṛn, tay ngắn và được tô điểm bằng họa tiết Together trước ngực, Áo thun cotton nam Sandro, Dáng oversize, Cổ tṛn, Tay áo ngắn, Hoạ tiết together', 297000, 266000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (43, 'Áo len dệt kim oversize', 2, 'Áo len dệt kim oversize với cổ cao rộng và tay áo dài., Áo len đan nam Sandro, Dáng oversize, Cổ cao rộng, Tay áo dài', 516000, 465000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (44, 'Áo khoác denim', 1, 'Áo khoác denim bạc màu có lớp lót hiệu ứng da cừu, có khuy bấm, tay áo dài và túi vá., Áo khoác denim nam Sandro, Lớp lót hiệu ứng da cừu tương phản, Cổ áo sơ mi, Tay áo dài, 4 túi vá, Áo jacron da dập nổi SANDRO', 555000, 500000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (45, 'Quần short đính đá', 9, 'Quần short pha len với các nếp gấp được ủi, tô điểm bằng những viên đá cùng tông màu., Quần short len, Đính đá cùng tông, Ủi các nếp, Túi xẻ hai bên', 610000, 549000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (46, 'Quần jean ống rộng', 8, 'Quần jean denim oversize được tô điểm bằng các chi tiết đính đá giả cùng tông màu ở phía trước và phía sau., Quần jean ống rộng nữ Sandro, Denim thô, Đính đá, 5 túi', 767000, 691000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (47, 'Áo thun organic cotton phối cổ họa tiết', 5, 'Áo thun cotton hữu cơ, với tay áo ngắn và cổ áo tṛn phối họa tiết, Áo thun cổ phối đá, Cổ áo tṛn với viền rhinestone, Tay áo ngắn, Thiết kế vừa vặn', 454000, 409000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (48, 'Áo khoác len', 2, 'Áo khoác ngoài bằng len có cài cúc, tay áo dài có cổ tay cài cúc, túi nắp ở ngực và túi bên., Áo khoác len nam Sandro, Cài nút, Tay áo dài, 2 túi vá ngực', 454000, 416000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (49, 'Quần ống loe', 8, 'Quần ống loe pha len có thắt lưng ở eo., Quần ống loe phối len nữ Sandro, Đai grosgrain, Ủi nếp', 704000, 634000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (50, 'Áo khoác sơ mi', 6, 'Áo khoác len có khóa kéo, cổ áo sơ mi và tay dài., Áo khoác len nam Sandro, Cổ áo sơ mi, Tay áo dài, Vá túi ngực, Cổ tay cài cúc', 508000, 468000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (51, 'Quần short denim', 6, 'Quần short denim có eo co giăn và đường khâu nổi tương phản., Quần short denim nam của Sandro, Lưng thun, Đường khâu', 578000, 521000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (52, 'Quần tây dáng đứng', 9, 'Quần tây nam Sandro, Quần âu bằng vải nỉ, Kiểu cổ điển, Hai túi kiểu Ư ở phía trước và hai túi có ống đóng bằng nút ở phía sau', 797000, 718000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (53, 'Quần short cotton', 9, 'Quần short cotton với thắt lưng đàn hồi với túi dây rút và túi bên., Quần short cotton nam Sandro, Eo đàn hồi với dây, 2 túi, 2 túi nắp ở mặt sau', 516000, 465000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (54, 'Áo polo logo rubber', 5, 'Quần short cotton với thắt lưng đàn hồi với túi dây rút và túi bên., Quần short cotton nam Sandro, Eo đàn hồi với dây, 2 túi, 2 túi nắp ở mặt sau', 359000, 324000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (55, 'Áo T-shirt oversize in logo Sandro', 2, 'Áo T-shirt oversize in logo Sandro., Áo phông nam Sandro, Logo Sandro cao su ở mặt trước, Dáng oversize', 297000, 268000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (56, 'Áo sơ mi Square Cross', 6, 'Áo sơ mi cài khuy bồng bềnh với tay áo dài, in h́nh Square Cross và sọc tương phản ở phía dưới., Áo sơ mi dáng suông nam Sandro, Tay dài có cổ tay cài khuy, Cài khuy phía trước, H́nh Square Cross', 704000, 634000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (57, 'Quần jeans cotton', 8, 'Quần jeans nam Sandro, Cotton jeans, 5 túi, Tag da Sandro phía sau, Thiêt kế ôm vừa', 567000, 511000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (58, 'Áo suit len', 6, 'Áo khoác suit len cổ điển có tay dài, cổ tay cài khuy, 2 khuy cài, lỗ thông hơi phía sau và túi có nắp., Áo khoác nam Sandro, Tay dài có cổ tay cài khuy, Cài 2 khuy, 2 túi có nắp, Lỗ thông hơi phía sau, Túi bên trong', 1634000, 1471000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (59, 'Áo sơ mi denim', 7, 'Áo sơ mi denim với cổ áo cổ điển, tay ngắn, cài khuy và có túi trước ngực., Áo sơ mi denim nam, Cổ áo sơ mi, Tay áo ngắn, Cúc bấm, Xẻ hai bên', 767000, 691000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (60, 'Áo nỉ Square Cross', 3, 'Áo nỉ cotton cổ tṛn, tay dài, trang trí họa tiết Square Cross trước ngực., Áo nỉ nam Sandro, Cổ tṛn, Tay áo dài, Vá h́nh Square Cross', 610000, 549000, b'1', '2023-10-29');
INSERT INTO `products` VALUES (66, 'T-Shirt Cotton 200GSM', 1, '<p>Đặc điểm nổi bật:&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Chất liệu: 100% Cotton mềm mại</li>\r\n	<li>Định lượng vải: 200gsm, d&agrave;y dặn</li>\r\n	<li>D&aacute;ng &aacute;o vừa vặn, ph&ugrave; hợp với đi chơi, đi l&agrave;m v&agrave; ở nh&agrave;</li>\r\n	<li>Tự h&agrave;o sản xuất tại Việt Nam</li>\r\n</ul>\r\n', 149000, 75000, b'1', '2023-12-27');
INSERT INTO `products` VALUES (70, 'Áo Polo Nam Pique', 5, '<p>Chất liệu: 95% Cotton + 5% Spandex</p><p>Co gi&atilde;n 4 chiều tạo cảm gi&aacute;c thoải m&aacute;i khi mặc</p><p>Tay &aacute;o v&agrave; cổ &aacute;o được dệt ri&ecirc;ng với sợi Spandex đảm bảo độ đ&agrave;n hồi v&agrave; chống bai, nh&atilde;o qua c&aacute;c lần giặt</p><div class=\"ddict_btn\" style=\"top: 144px; left: 188.891px;\"><img src=\"chrome-extension://bpggmmljdiliancllaapiggllnkbjocb/logo/48.png\" /></div>', 299000, 159000, b'1', '2024-01-26');
INSERT INTO `products` VALUES (71, 'áo sơ mi cổ bẻ tay dài sợi modal ít nhăn trơn dáng vừa đơn giản', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 476200, 395889.13728, b'1', '2024-03-06');
INSERT INTO `products` VALUES (72, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 364188, 325503.975452, b'1', '2024-03-06');
INSERT INTO `products` VALUES (73, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 441309, 353154.377574, b'1', '2024-03-06');
INSERT INTO `products` VALUES (74, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 417929, 353965.694834, b'1', '2024-03-06');
INSERT INTO `products` VALUES (75, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 356684, 315491.005439, b'1', '2024-03-06');
INSERT INTO `products` VALUES (76, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 444859, 394617.404401, b'1', '2024-03-06');
INSERT INTO `products` VALUES (77, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 297994, 259511.828605, b'1', '2024-03-06');
INSERT INTO `products` VALUES (78, 'ÁO JERSEY LỬNG DÀI TAY', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 453792, 407333.106579, b'1', '2024-03-06');
INSERT INTO `products` VALUES (79, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 265390, 227974.358813, b'1', '2024-03-06');
INSERT INTO `products` VALUES (80, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 462882, 402356.308004, b'1', '2024-03-06');
INSERT INTO `products` VALUES (81, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 395089, 348403.598445, b'1', '2024-03-06');
INSERT INTO `products` VALUES (82, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Áo Thun Sweater Đơn Giản M34. Thành phần : 100% Poly. Cấu trúc dệt WAFFLE đặc biệt. Chống ngăn. Mềm mại. Tỏa nhiệt bề mặt. Co dãn tốt. May đắp logo', 307512, 274335.805687, b'1', '2024-03-06');
INSERT INTO `products` VALUES (83, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 397373, 326005.150842, b'1', '2024-03-06');
INSERT INTO `products` VALUES (84, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 259582, 224227.408078, b'1', '2024-03-06');
INSERT INTO `products` VALUES (85, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 484229, 393276.364215, b'1', '2024-03-06');
INSERT INTO `products` VALUES (86, 'Áo Polo Cổ Bẻ Tay Dài Vải Cotton 2 Chiều Thấm Hút Trơn Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 250145, 218595.57416, b'1', '2024-03-06');
INSERT INTO `products` VALUES (87, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 340778, 290621.385626, b'1', '2024-03-06');
INSERT INTO `products` VALUES (88, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 491449, 420006.046346, b'1', '2024-03-06');
INSERT INTO `products` VALUES (89, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 418380, 358143.694283, b'1', '2024-03-06');
INSERT INTO `products` VALUES (90, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 434672, 365503.611616, b'1', '2024-03-06');
INSERT INTO `products` VALUES (91, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 346071, 281867.531286, b'1', '2024-03-06');
INSERT INTO `products` VALUES (92, 'ÁO JERSEY LỬNG DÀI TAY', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 383054, 344123.926556, b'1', '2024-03-06');
INSERT INTO `products` VALUES (93, 'ÁO THUN CHẠY BỘ DÀI TAY ADIZERO', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 429273, 373898.163176, b'1', '2024-03-06');
INSERT INTO `products` VALUES (94, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Áo Thun Sweater Đơn Giản M34. Thành phần : 100% Poly. Cấu trúc dệt WAFFLE đặc biệt. Chống ngăn. Mềm mại. Tỏa nhiệt bề mặt. Co dãn tốt. May đắp logo', 483539, 407631.924747, b'1', '2024-03-06');
INSERT INTO `products` VALUES (95, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 386241, 331353.053609, b'1', '2024-03-06');
INSERT INTO `products` VALUES (96, 'ÁO THUN DÀI TAY STREET NEUCLASSIC', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 281443, 242246.958521, b'1', '2024-03-06');
INSERT INTO `products` VALUES (97, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 433321, 382981.160044, b'1', '2024-03-06');
INSERT INTO `products` VALUES (98, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 255413, 228077.880543, b'1', '2024-03-06');
INSERT INTO `products` VALUES (99, 'ÁO THUN CHẠY BỘ DÀI TAY ADIZERO', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 414980, 358131.188377, b'1', '2024-03-06');
INSERT INTO `products` VALUES (100, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 380436, 309821.518479, b'1', '2024-03-06');
INSERT INTO `products` VALUES (101, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 498485, 401022.082255, b'1', '2024-03-06');
INSERT INTO `products` VALUES (102, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 451275, 393182.466891, b'1', '2024-03-06');
INSERT INTO `products` VALUES (103, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 362746, 291558.728851, b'1', '2024-03-06');
INSERT INTO `products` VALUES (104, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 446294, 367662.429374, b'1', '2024-03-06');
INSERT INTO `products` VALUES (105, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 320301, 285485.569734, b'1', '2024-03-06');
INSERT INTO `products` VALUES (106, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 406458, 326095.742763, b'1', '2024-03-06');
INSERT INTO `products` VALUES (107, 'ÁO THUN DÀI TAY STREET NEUCLASSIC', 1, 'Áo Thun Sweater Đơn Giản M34. Thành phần : 100% Poly. Cấu trúc dệt WAFFLE đặc biệt. Chống ngăn. Mềm mại. Tỏa nhiệt bề mặt. Co dãn tốt. May đắp logo', 378987, 318068.600796, b'1', '2024-03-06');
INSERT INTO `products` VALUES (108, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 478525, 391108.621133, b'1', '2024-03-06');
INSERT INTO `products` VALUES (109, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 391079, 325428.130906, b'1', '2024-03-06');
INSERT INTO `products` VALUES (110, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 277329, 241124.672547, b'1', '2024-03-06');
INSERT INTO `products` VALUES (111, 'Áo phông dài tay nam cotton có hình in', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 399151, 347945.684144, b'1', '2024-03-06');
INSERT INTO `products` VALUES (112, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 341962, 283969.274147, b'1', '2024-03-06');
INSERT INTO `products` VALUES (113, 'ÁO THUN CHẠY BỘ DÀI TAY ADIZERO', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 458625, 406230.79146, b'1', '2024-03-06');
INSERT INTO `products` VALUES (114, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 273951, 219707.683149, b'1', '2024-03-06');
INSERT INTO `products` VALUES (115, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 291948, 242363.038757, b'1', '2024-03-06');
INSERT INTO `products` VALUES (116, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 347005, 298971.953905, b'1', '2024-03-06');
INSERT INTO `products` VALUES (117, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 305628, 267923.599085, b'1', '2024-03-06');
INSERT INTO `products` VALUES (118, 'Áo phông dài tay nam cotton USA có hình in', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 341792, 275864.873468, b'1', '2024-03-06');
INSERT INTO `products` VALUES (119, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 307585, 266367.548039, b'1', '2024-03-06');
INSERT INTO `products` VALUES (120, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 333425, 280836.470462, b'1', '2024-03-06');
INSERT INTO `products` VALUES (121, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 257362, 230874.253085, b'1', '2024-03-06');
INSERT INTO `products` VALUES (122, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Phong cách casual. classic của chiếc áo thun adidas này mang đến cảm giác thoải mái và phong cách thường ngày. Cổ tay bo gân tạo hiệu ứng sần tinh tế. cùng kiểu dáng suông rộng giúp bạn luôn thoải mái. Chất vải cotton mềm mại và kết cấu single jersey cho cảm giác dễ chịu và đáp ứng nhu cầu mặc mỗi ngày. Đón chào ngày mới với phong cách đậm chất di sản', 387108, 346057.74772, b'1', '2024-03-06');
INSERT INTO `products` VALUES (123, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 282381, 231498.155709, b'1', '2024-03-06');
INSERT INTO `products` VALUES (124, 'Áo Polo Cổ Bẻ Tay Dài Vải Cotton 2 Chiều Thấm Hút Trơn Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 339771, 274821.355162, b'1', '2024-03-06');
INSERT INTO `products` VALUES (125, 'Áo phông dài tay nam cotton USA có hình in', 1, 'Áo Thun Sweater Đơn Giản M34. Thành phần : 100% Poly. Cấu trúc dệt WAFFLE đặc biệt. Chống ngăn. Mềm mại. Tỏa nhiệt bề mặt. Co dãn tốt. May đắp logo', 326639, 286043.728964, b'1', '2024-03-06');
INSERT INTO `products` VALUES (126, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 254141, 216517.718865, b'1', '2024-03-06');
INSERT INTO `products` VALUES (127, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 420251, 341717.743445, b'1', '2024-03-06');
INSERT INTO `products` VALUES (128, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Phong cách casual. classic của chiếc áo thun adidas này mang đến cảm giác thoải mái và phong cách thường ngày. Cổ tay bo gân tạo hiệu ứng sần tinh tế. cùng kiểu dáng suông rộng giúp bạn luôn thoải mái. Chất vải cotton mềm mại và kết cấu single jersey cho cảm giác dễ chịu và đáp ứng nhu cầu mặc mỗi ngày. Đón chào ngày mới với phong cách đậm chất di sản', 404513, 327988.417187, b'1', '2024-03-06');
INSERT INTO `products` VALUES (129, 'Áo phông dài tay nam cotton có hình in', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 293111, 249169.362599, b'1', '2024-03-06');
INSERT INTO `products` VALUES (130, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 466186, 384820.741337, b'1', '2024-03-06');
INSERT INTO `products` VALUES (131, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 268402, 231890.115717, b'1', '2024-03-06');
INSERT INTO `products` VALUES (132, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 336744, 296948.224822, b'1', '2024-03-06');
INSERT INTO `products` VALUES (133, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Vừa', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 390612, 351050.340351, b'1', '2024-03-06');
INSERT INTO `products` VALUES (134, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chinh phục từng dặm đường với chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập cường độ cao ngắt quãng. Kiểu dáng regular fit chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật trong mọi điều kiện ánh sáng. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm thiểu lãng phí và hạn chế phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 496176, 423959.984204, b'1', '2024-03-06');
INSERT INTO `products` VALUES (135, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 271114, 240025.192345, b'1', '2024-03-06');
INSERT INTO `products` VALUES (136, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 389701, 343226.664375, b'1', '2024-03-06');
INSERT INTO `products` VALUES (137, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 335265, 273441.009034, b'1', '2024-03-06');
INSERT INTO `products` VALUES (138, 'Áo phông dài tay nam cotton USA có hình in', 1, 'Áo Thun Sweater Đơn Giản M34. Thành phần : 100% Poly. Cấu trúc dệt WAFFLE đặc biệt. Chống ngăn. Mềm mại. Tỏa nhiệt bề mặt. Co dãn tốt. May đắp logo', 451303, 402818.33489, b'1', '2024-03-06');
INSERT INTO `products` VALUES (139, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 486234, 426863.05402, b'1', '2024-03-06');
INSERT INTO `products` VALUES (140, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 479815, 393511.299988, b'1', '2024-03-06');
INSERT INTO `products` VALUES (141, 'Áo phông dài tay nam cotton USA có hình in', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 381017, 331204.856811, b'1', '2024-03-06');
INSERT INTO `products` VALUES (142, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 349991, 295920.696775, b'1', '2024-03-06');
INSERT INTO `products` VALUES (143, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 492145, 404260.402676, b'1', '2024-03-06');
INSERT INTO `products` VALUES (144, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Biểu Tượng Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 325397, 272932.500876, b'1', '2024-03-06');
INSERT INTO `products` VALUES (145, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 402098, 324365.875673, b'1', '2024-03-06');
INSERT INTO `products` VALUES (146, 'ÁO THUN DÀI TAY STREET NEUCLASSIC', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 428737, 384742.035294, b'1', '2024-03-06');
INSERT INTO `products` VALUES (147, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Đầu óc thư thái. Không chút phân tâm. Tập trung vào mục tiêu tập luyện với chiếc áo thun chạy bộ dài tay adidas này. Đây là lớp áo siêu nhẹ mỗi khi cần che chắn kín đáo. với công nghệ AEROREADY thấm hút ẩm giúp bạn luôn khô ráo. Đường may được tính toán kỹ lưỡng giúp giảm cọ xát. Làm từ một loạt chất liệu tái chế và có chứa tối thiểu 70% thành phần tái chế. sản phẩm này đại diện cho một trong số rất nhiều các giải pháp của chúng tôi hướng tới chấm dứt rác thải nhựa', 307110, 265035.931114, b'1', '2024-03-06');
INSERT INTO `products` VALUES (148, 'Áo phông dài tay nam cotton có hình in', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 479337, 386445.715378, b'1', '2024-03-06');
INSERT INTO `products` VALUES (149, 'ÁO THUN DÀI TAY STREET NEUCLASSIC', 1, 'Phong cách casual. classic của chiếc áo thun adidas này mang đến cảm giác thoải mái và phong cách thường ngày. Cổ tay bo gân tạo hiệu ứng sần tinh tế. cùng kiểu dáng suông rộng giúp bạn luôn thoải mái. Chất vải cotton mềm mại và kết cấu single jersey cho cảm giác dễ chịu và đáp ứng nhu cầu mặc mỗi ngày. Đón chào ngày mới với phong cách đậm chất di sản', 343569, 303559.276084, b'1', '2024-03-06');
INSERT INTO `products` VALUES (150, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 277833, 228328.183768, b'1', '2024-03-06');
INSERT INTO `products` VALUES (151, 'ÁO THUN CHẠY BỘ DÀI TAY ADIZERO', 1, 'Chinh phục từng cây số trong chiếc áo thun dài tay adidas này. Công nghệ AEROREADY giúp bạn luôn thoải mái khi chạy đường dài hay tập luyện cường độ cao. Dáng ôm vừa phải. thích ứng theo chuyển động cùng bạn. cùng các chi tiết phản quang đảm bảo bạn luôn nổi bật ở mọi điều kiện. Sản phẩm này làm từ tối thiểu 70% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 308350, 270925.237256, b'1', '2024-03-06');
INSERT INTO `products` VALUES (152, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 273313, 242919.031037, b'1', '2024-03-06');
INSERT INTO `products` VALUES (153, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 380575, 339032.361908, b'1', '2024-03-06');
INSERT INTO `products` VALUES (154, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Chất liệu: Modal Polyester. Thành phần: 12% Modal 88% Polyester. Co dãn tốt. Chống co rút tốt. Giữ form sau nhiều lần sử dụng. Thấm hút. Thoát mồ hôi tốt. Vạt áo dạng bầu vạt', 286796, 230868.621641, b'1', '2024-03-06');
INSERT INTO `products` VALUES (155, 'ÁO JERSEY LỬNG DÀI TAY', 1, 'Phong cách casual. classic của chiếc áo thun adidas này mang đến cảm giác thoải mái và phong cách thường ngày. Cổ tay bo gân tạo hiệu ứng sần tinh tế. cùng kiểu dáng suông rộng giúp bạn luôn thoải mái. Chất vải cotton mềm mại và kết cấu single jersey cho cảm giác dễ chịu và đáp ứng nhu cầu mặc mỗi ngày. Đón chào ngày mới với phong cách đậm chất di sản', 470886, 394993.10535, b'1', '2024-03-06');
INSERT INTO `products` VALUES (156, 'ÁO JERSEY LỬNG DÀI TAY', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 336379, 273440.622948, b'1', '2024-03-06');
INSERT INTO `products` VALUES (157, 'ÁO THUN CHẠY BỘ DÀI TAY ADIZERO', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 482405, 389146.860508, b'1', '2024-03-06');
INSERT INTO `products` VALUES (158, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 423933, 380484.508218, b'1', '2024-03-06');
INSERT INTO `products` VALUES (159, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 424942, 347116.369646, b'1', '2024-03-06');
INSERT INTO `products` VALUES (160, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 286776, 240128.582526, b'1', '2024-03-06');
INSERT INTO `products` VALUES (161, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 420037, 337858.033126, b'1', '2024-03-06');
INSERT INTO `products` VALUES (162, 'Áo Thun Cổ Tròn Tay Dài Vải Cotton 2 Chiều Thấm Hút Biểu Tượng Dáng Rộng', 1, 'Phong cách casual. classic của chiếc áo thun adidas này mang đến cảm giác thoải mái và phong cách thường ngày. Cổ tay bo gân tạo hiệu ứng sần tinh tế. cùng kiểu dáng suông rộng giúp bạn luôn thoải mái. Chất vải cotton mềm mại và kết cấu single jersey cho cảm giác dễ chịu và đáp ứng nhu cầu mặc mỗi ngày. Đón chào ngày mới với phong cách đậm chất di sản', 286954, 254847.677916, b'1', '2024-03-06');
INSERT INTO `products` VALUES (163, 'ÁO THUN DÀI TAY STREET NEUCLASSIC', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 416678, 353409.401281, b'1', '2024-03-06');
INSERT INTO `products` VALUES (164, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Với chất thể thao ngập tràn trong một item chủ đạo thường ngày. chiếc áo jersey adidas này mang phong cách bóng đá đến cho tủ đồ hàng ngày của bạn. Mượn cảm hứng từ kho di sản của chúng tôi. áo có họa tiết jacquard hình tia chớp dệt trực tiếp lên vải cho thiết kế tinh tế mà ấn tượng. Áo ôm lấy cơ thể. cùng dáng lửng mang đến nét biến tấu đầy phong cách. Đơn giản mà thời thượng. chiếc áo jersey này có thể kết hợp với mọi item từ quần jogger đến quần jean. Sản phẩm này làm từ 100% chất liệu tái chế. Bằng cách tái sử dụng các chất liệu đã được tạo ra. chúng tôi góp phần giảm lãng phí và giảm phụ thuộc vào các nguồn tài nguyên hữu hạn. cũng như giảm phát thải từ các sản phẩm mà chúng tôi sản xuất', 335368, 291164.10383, b'1', '2024-03-06');
INSERT INTO `products` VALUES (165, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 428681, 360223.877518, b'1', '2024-03-06');
INSERT INTO `products` VALUES (166, 'Áo Sơ Mi Cổ Bẻ Tay Dài Sợi Modal Ít Nhăn Trơn Dáng Vừa Đơn Giản', 1, 'Áo Thun Sweater Đơn Giản Y Nguyên Bản Ver119. Thành phần : 97% Polyesster 3% Spandex. Cấu trúc dệt đặc biệt: Jacquard Knitting. (*) Vải Jacquard có cấu trúc các sợi liên kết với nhau rất cao. kết hợp thêm kiểu dệt Jacquard tạo ra một loại vải có chất lượng cao và tạo hiệu ứng đẹp cho mặt vải. Bề mặt vải đẹp và lạ mắt.Đàn hồi tốt. Co dãn tốt. Họa tiết thêu 2D  3D  thêu đắp giống FEO', 299313, 244139.908669, b'1', '2024-03-06');
INSERT INTO `products` VALUES (167, 'ÁO THUN DÀI TAY OWN THE RUN', 1, 'Chất liệu: French Terry. Thành phần :100% Cotton. Thấm hút thoát ẩm. Mềm mại. Co giãn đàn hồi. Thân thiện môi trường. Thiết kế cổ Polo. Bo cổ Poly bền chắc khác màu. Bo tay và bo lai cotton đồng điệu cùng màu vải', 376335, 330106.514158, b'1', '2024-03-06');
INSERT INTO `products` VALUES (168, 'Áo phông dài tay nam cotton USA có hình in', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 371504, 303132.147234, b'1', '2024-03-06');
INSERT INTO `products` VALUES (169, 'Áo Thun Cổ Tròn Tay Dài Sợi Nhân Tạo Giữ Ấm Trơn Dáng Rộng', 1, 'Áo Thun Cổ Tròn Đơn Giản Y Nguyên Bản Ver122. Chất liệu: Cotton Plus. Thành phần : 80% Cotton 20% Polyester. Họa tiết thêu', 357124, 289511.426336, b'1', '2024-03-06');
INSERT INTO `products` VALUES (170, 'ÁO JACKET - JK231604', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1954436, 1734294.137974, b'1', '2024-03-10');
INSERT INTO `products` VALUES (171, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1612672, 1310263.562862, b'1', '2024-03-10');
INSERT INTO `products` VALUES (172, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1025260, 919683.37003, b'1', '2024-03-10');
INSERT INTO `products` VALUES (173, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1651517, 1337156.574898, b'1', '2024-03-10');
INSERT INTO `products` VALUES (174, 'ÁO JACKET - JK231604', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1863078, 1592706.160764, b'1', '2024-03-10');
INSERT INTO `products` VALUES (175, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1898887, 1534650.932148, b'1', '2024-03-10');
INSERT INTO `products` VALUES (176, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1876165, 1574676.180589, b'1', '2024-03-10');
INSERT INTO `products` VALUES (177, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1592280, 1286262.194505, b'1', '2024-03-10');
INSERT INTO `products` VALUES (178, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1702269, 1438574.039411, b'1', '2024-03-10');
INSERT INTO `products` VALUES (179, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1763738, 1468358.313158, b'1', '2024-03-10');
INSERT INTO `products` VALUES (180, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1661959, 1492493.874605, b'1', '2024-03-10');
INSERT INTO `products` VALUES (181, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1985166, 1762069.482529, b'1', '2024-03-10');
INSERT INTO `products` VALUES (182, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1965731, 1729934.988349, b'1', '2024-03-10');
INSERT INTO `products` VALUES (183, 'ÁO JACKET - JK231602', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1508025, 1319906.881327, b'1', '2024-03-10');
INSERT INTO `products` VALUES (184, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1874753, 1661432.870368, b'1', '2024-03-10');
INSERT INTO `products` VALUES (185, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1965340, 1740379.933995, b'1', '2024-03-10');
INSERT INTO `products` VALUES (186, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1619953, 1389290.506398, b'1', '2024-03-10');
INSERT INTO `products` VALUES (187, 'ÁO JACKET - JK231601', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1521175, 1293757.397044, b'1', '2024-03-10');
INSERT INTO `products` VALUES (188, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1979121, 1652321.944801, b'1', '2024-03-10');
INSERT INTO `products` VALUES (189, 'ÁO JACKET - JK231601', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1092566, 912542.03386, b'1', '2024-03-10');
INSERT INTO `products` VALUES (190, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1495349, 1212311.02593, b'1', '2024-03-10');
INSERT INTO `products` VALUES (191, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1406219, 1263337.856221, b'1', '2024-03-10');
INSERT INTO `products` VALUES (192, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1890898, 1627310.211794, b'1', '2024-03-10');
INSERT INTO `products` VALUES (193, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1302612, 1155744.880042, b'1', '2024-03-10');
INSERT INTO `products` VALUES (194, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1188647, 1031436.178509, b'1', '2024-03-10');
INSERT INTO `products` VALUES (195, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1126760, 963782.456533, b'1', '2024-03-10');
INSERT INTO `products` VALUES (196, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1032756, 840041.605157, b'1', '2024-03-10');
INSERT INTO `products` VALUES (197, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1252948, 1054615.266622, b'1', '2024-03-10');
INSERT INTO `products` VALUES (198, 'ÁO JACKET - JK231604', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1052755, 860731.855219, b'1', '2024-03-10');
INSERT INTO `products` VALUES (199, 'ÁO JACKET - JK231600', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1518520, 1338253.283996, b'1', '2024-03-10');
INSERT INTO `products` VALUES (200, 'ÁO JACKET - JK231602', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1938545, 1552748.74907, b'1', '2024-03-10');
INSERT INTO `products` VALUES (201, 'ÁO JACKET - JK231621', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1255970, 1071944.385017, b'1', '2024-03-10');
INSERT INTO `products` VALUES (202, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1780791, 1528567.539897, b'1', '2024-03-10');
INSERT INTO `products` VALUES (203, 'ÁO JACKET - JK231611', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1205151, 1027635.878084, b'1', '2024-03-10');
INSERT INTO `products` VALUES (204, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1403895, 1227689.521586, b'1', '2024-03-10');
INSERT INTO `products` VALUES (205, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1281256, 1090624.621443, b'1', '2024-03-10');
INSERT INTO `products` VALUES (206, 'ÁO JACKET - JK231601', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1114778, 913982.415498, b'1', '2024-03-10');
INSERT INTO `products` VALUES (207, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1372702, 1165571.205603, b'1', '2024-03-10');
INSERT INTO `products` VALUES (208, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1403099, 1134866.024378, b'1', '2024-03-10');
INSERT INTO `products` VALUES (209, 'ÁO JACKET - JK231611', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1792253, 1466793.957911, b'1', '2024-03-10');
INSERT INTO `products` VALUES (210, 'ÁO JACKET - JK231602', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1432121, 1236852.705859, b'1', '2024-03-10');
INSERT INTO `products` VALUES (211, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1620150, 1379405.573051, b'1', '2024-03-10');
INSERT INTO `products` VALUES (212, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1744033, 1464114.985671, b'1', '2024-03-10');
INSERT INTO `products` VALUES (213, 'ÁO JACKET - JK231604', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1374782, 1219807.393924, b'1', '2024-03-10');
INSERT INTO `products` VALUES (214, 'ÁO JACKET - JK231620', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1314835, 1144690.678744, b'1', '2024-03-10');
INSERT INTO `products` VALUES (215, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1398992, 1217467.12581, b'1', '2024-03-10');
INSERT INTO `products` VALUES (216, 'ÁO JACKET - JK231605', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1727578, 1433019.385656, b'1', '2024-03-10');
INSERT INTO `products` VALUES (217, 'ÁO JACKET - JK231608', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1045350, 846280.260977, b'1', '2024-03-10');
INSERT INTO `products` VALUES (218, 'ÁO JACKET - JK231602', 21, 'Chất liệu: 100% Polyester Độ bền màu cao hạn chế nhăn Không hấp hút chất bẩn dễ vệ sinh Thiết kế trẻ trung với kiểu dáng thể thao dễ mặc dễ phối đồ Tone màu phóng khoáng cá tính', 1412043, 1258770.194655, b'1', '2024-03-10');

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDetailId` int NULL DEFAULT NULL,
  `ratingStar` int NOT NULL,
  `feedback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reviewDate` date NOT NULL,
  `visibility` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_reviews_order_details`(`orderDetailId`) USING BTREE,
  CONSTRAINT `FK_reviews_order_details` FOREIGN KEY (`orderDetailId`) REFERENCES `order_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES (2, 2, 2, 'Risus eget sed hymenaeos id sapien suspendisse ultrices aliquam. Ullamcorper id consectetuer maecenas aenean praesent malesuada fusce dolor a integer. Proin praesent inceptos scelerisque sit proin donec senectus malesuada ac mus proin ad tincidunt ipsum. ', '2023-04-25', b'0');
INSERT INTO `reviews` VALUES (3, 3, 2, 'Quisque senectus sociosqu pulvinar cursus nullam orci fusce quisque. Pellentesque vitae tempor ligula odio sociosqu facilisi ut semper etiam varius tempor natoque donec. At fringilla lorem fusce fames massa netus lectus lacus sem rutrum. Aptent sodales ap', '2023-10-28', b'0');
INSERT INTO `reviews` VALUES (4, 4, 5, 'Natoque ac fusce phasellus laoreet arcu amet adipiscing viverra. Integer hymenaeos nunc augue malesuada eleifend pharetra pretium nam nullam lorem lobortis torquent condimentum sociis. Rhoncus et auctor morbi metus vel eget eros odio ipsum consequat integ', '2023-08-12', b'0');
INSERT INTO `reviews` VALUES (5, 5, 4, 'Turpis dictum dictum id fermentum malesuada mattis amet praesent. Blandit consectetuer faucibus donec class sollicitudin vivamus hymenaeos suspendisse duis massa taciti. Mollis dictum fermentum augue lacinia sodales viverra justo blandit duis senectus lig', '2023-06-09', b'0');
INSERT INTO `reviews` VALUES (6, 6, 3, 'Litora urna facilisi pharetra montes ornare nullam donec conubia. Rutrum sit rutrum ligula torquent consectetuer tortor cum ante. Tortor auctor parturient at id pede dictum varius cursus ac montes pellentesque primis ornare. Felis libero eros vestibulum b', '2023-04-15', b'0');
INSERT INTO `reviews` VALUES (7, 7, 3, 'Ut elementum id curabitur parturient senectus cras eleifend quis. Convallis lacus primis in praesent leo amet turpis lacinia. Natoque purus ad taciti et quisque a praesent neque pulvinar venenatis. Elementum felis tellus ipsum ipsum sit turpis auctor orci', '2023-01-27', b'0');
INSERT INTO `reviews` VALUES (8, 8, 1, 'Diam sed mauris convallis cubilia felis curabitur sociosqu proin. Molestie donec ligula orci tempor libero nonummy porttitor porta in. Urna egestas faucibus torquent morbi aliquam montes nisl integer cursus pellentesque senectus condimentum auctor. Aliqua', '2023-12-27', b'0');
INSERT INTO `reviews` VALUES (9, 9, 5, 'Interdum nostra tempor montes at porttitor habitant nunc congue. Urna posuere quam nunc turpis parturient nam sed id lectus at adipiscing ultrices. Vestibulum dui curabitur cum et turpis quam ad sociosqu curabitur erat sem. Auctor lobortis commodo congue ', '2023-10-22', b'0');
INSERT INTO `reviews` VALUES (10, 10, 3, 'Duis placerat sociosqu congue aliquet suscipit dapibus fames accumsan. Per velit rutrum aenean sapien torquent bibendum quam malesuada eleifend. Quam pharetra curae; rhoncus gravida leo cubilia lacus penatibus congue molestie fermentum ad. Nisi netus cubi', '2023-02-15', b'0');
INSERT INTO `reviews` VALUES (11, 11, 5, 'Praesent aliquet aptent cursus penatibus sollicitudin erat praesent senectus. Integer at cum mus fames tempor maecenas quam fermentum hymenaeos sollicitudin curabitur elit quis ante. Quisque tellus bibendum scelerisque quam hendrerit montes nullam mauris ', '2023-03-04', b'0');
INSERT INTO `reviews` VALUES (12, 12, 3, 'Mi neque fringilla ultricies ornare litora fusce fringilla ornare. Euismod praesent posuere lacinia vehicula malesuada odio praesent pellentesque dignissim. Curae; vestibulum id vel pellentesque maecenas imperdiet luctus dui pede pellentesque iaculis semp', '2023-03-19', b'1');
INSERT INTO `reviews` VALUES (13, 13, 1, 'Fringilla sagittis vel ipsum cursus nulla nonummy dolor dapibus. Risus congue egestas risus eleifend sociosqu magnis ultrices curae; aenean. Enim elit magna per ut pellentesque molestie orci nullam duis dui interdum hymenaeos. Velit vivamus ante euismod f', '2023-04-22', b'0');
INSERT INTO `reviews` VALUES (14, 14, 5, 'Scelerisque interdum nostra tempus neque egestas integer faucibus imperdiet. Parturient integer adipiscing ultrices morbi vel cum tempor vehicula consequat rhoncus lorem aliquam ridiculus quis. Commodo class curae; molestie cursus elit primis tortor ferme', '2023-09-12', b'0');
INSERT INTO `reviews` VALUES (15, 15, 5, 'Nec nec cubilia a neque torquent purus aliquam ut. Donec eget mauris tempor tellus posuere vehicula nostra elementum fringilla vestibulum suscipit id. In accumsan turpis molestie torquent turpis tellus nostra maecenas montes eros. Pellentesque et hymenaeo', '2023-07-10', b'0');
INSERT INTO `reviews` VALUES (16, 16, 4, 'Interdum quisque nisi placerat pharetra fusce parturient est hymenaeos. Volutpat ultrices nullam imperdiet ad ut ornare libero est. Eros est natoque aenean lectus bibendum consectetuer gravida hymenaeos. Tellus viverra fermentum ornare facilisi metus port', '2023-05-18', b'0');
INSERT INTO `reviews` VALUES (17, 17, 1, 'Id pellentesque dui at porta faucibus litora habitant integer. Ipsum etiam dapibus aliquet per est faucibus quam senectus taciti suscipit porta ultrices mauris sapien. Sem purus eu dolor curae; dignissim lorem rhoncus libero turpis elit luctus. Sed class ', '2023-10-11', b'1');
INSERT INTO `reviews` VALUES (18, 18, 4, 'Praesent nunc aliquam erat dapibus congue amet tincidunt felis. Ornare mattis natoque fringilla vel sapien commodo consectetuer etiam aptent libero ante ultricies malesuada pharetra. Nostra eu nec ad hymenaeos quis posuere et enim lacus imperdiet nostra v', '2023-07-17', b'1');
INSERT INTO `reviews` VALUES (20, 20, 3, 'Diam hendrerit erat donec est pretium sed suspendisse torquent. Ridiculus dolor penatibus rhoncus sem rhoncus justo purus mattis euismod ultrices quam volutpat posuere nisi. Fusce eget convallis vitae magnis mi cras cras euismod ad eget bibendum quam mass', '2023-11-15', b'0');
INSERT INTO `reviews` VALUES (21, 22, 1, 'Aliquam convallis natoque laoreet ligula venenatis malesuada natoque iaculis. Mi elit ornare molestie porta ullamcorper magnis placerat volutpat. Mus adipiscing lobortis elit sollicitudin molestie augue sem senectus per urna nunc imperdiet vitae quis. A l', '2023-12-12', b'1');
INSERT INTO `reviews` VALUES (22, 23, 1, 'Conubia suspendisse ultrices aliquet lectus aliquet tortor in eget. Elementum enim montes varius consectetuer pulvinar gravida mauris mi blandit tellus condimentum turpis elementum luctus. Rhoncus ut interdum diam placerat urna netus consequat suspendisse', '2023-03-07', b'0');
INSERT INTO `reviews` VALUES (27, 28, 3, 'Erat penatibus nisi varius vehicula ullamcorper proin ultrices lacinia. Euismod montes senectus magnis viverra magna libero ridiculus montes in euismod. Porttitor neque sagittis donec per quisque ridiculus netus non euismod nulla. Laoreet iaculis nam per ', '2023-06-10', b'1');
INSERT INTO `reviews` VALUES (28, 30, 5, 'Vivamus dapibus ultrices bibendum pellentesque augue interdum rhoncus nunc. Leo non praesent quisque pellentesque aliquet dolor tortor torquent risus donec porttitor turpis ligula mi. Penatibus nullam conubia quam id nonummy varius tempus nibh fames. Soda', '2023-10-10', b'0');
INSERT INTO `reviews` VALUES (29, 19, 5, 'Tuyệt', '2024-01-25', b'1');
INSERT INTO `reviews` VALUES (30, 24, 5, 'Sản phẩm tuyệt vời', '2024-01-26', b'1');
INSERT INTO `reviews` VALUES (31, 1, 5, 'Tuyệt', '2024-01-27', b'1');

-- ----------------------------
-- Table structure for sizes
-- ----------------------------
DROP TABLE IF EXISTS `sizes`;
CREATE TABLE `sizes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameSize` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `productId` int NULL DEFAULT NULL,
  `sizePrice` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK__products`(`productId`) USING BTREE,
  CONSTRAINT `FK__products` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 483 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sizes
-- ----------------------------
INSERT INTO `sizes` VALUES (1, 'X', 1, 0);
INSERT INTO `sizes` VALUES (2, 'XL', 1, 0);
INSERT INTO `sizes` VALUES (3, '2XL', 1, 0);
INSERT INTO `sizes` VALUES (4, 'X', 2, 0);
INSERT INTO `sizes` VALUES (5, 'XL', 2, 0);
INSERT INTO `sizes` VALUES (6, '2XL', 2, 0);
INSERT INTO `sizes` VALUES (7, 'X', 3, 0);
INSERT INTO `sizes` VALUES (8, 'XL', 3, 0);
INSERT INTO `sizes` VALUES (9, '2XL', 3, 0);
INSERT INTO `sizes` VALUES (10, 'X', 4, 0);
INSERT INTO `sizes` VALUES (11, 'XL', 4, 0);
INSERT INTO `sizes` VALUES (12, '2XL', 4, 0);
INSERT INTO `sizes` VALUES (13, 'X', 5, 0);
INSERT INTO `sizes` VALUES (14, 'XL', 5, 0);
INSERT INTO `sizes` VALUES (15, '2XL', 5, 0);
INSERT INTO `sizes` VALUES (16, 'X', 6, 0);
INSERT INTO `sizes` VALUES (17, 'XL', 6, 0);
INSERT INTO `sizes` VALUES (18, '2XL', 6, 0);
INSERT INTO `sizes` VALUES (19, 'X', 7, 0);
INSERT INTO `sizes` VALUES (20, 'XL', 7, 0);
INSERT INTO `sizes` VALUES (21, '2XL', 7, 0);
INSERT INTO `sizes` VALUES (22, 'X', 8, 0);
INSERT INTO `sizes` VALUES (23, 'XL', 8, 0);
INSERT INTO `sizes` VALUES (24, '2XL', 8, 0);
INSERT INTO `sizes` VALUES (25, 'X', 9, 0);
INSERT INTO `sizes` VALUES (26, 'XL', 9, 0);
INSERT INTO `sizes` VALUES (27, '2XL', 9, 0);
INSERT INTO `sizes` VALUES (28, 'X', 10, 0);
INSERT INTO `sizes` VALUES (29, 'XL', 10, 0);
INSERT INTO `sizes` VALUES (30, '2XL', 10, 0);
INSERT INTO `sizes` VALUES (31, 'X', 11, 0);
INSERT INTO `sizes` VALUES (32, 'XL', 11, 0);
INSERT INTO `sizes` VALUES (33, '2XL', 11, 0);
INSERT INTO `sizes` VALUES (34, 'X', 12, 0);
INSERT INTO `sizes` VALUES (35, 'XL', 12, 0);
INSERT INTO `sizes` VALUES (36, '2XL', 12, 0);
INSERT INTO `sizes` VALUES (37, 'X', 13, 0);
INSERT INTO `sizes` VALUES (38, 'XL', 13, 0);
INSERT INTO `sizes` VALUES (39, '2XL', 13, 0);
INSERT INTO `sizes` VALUES (40, 'X', 14, 0);
INSERT INTO `sizes` VALUES (41, 'XL', 14, 0);
INSERT INTO `sizes` VALUES (42, '2XL', 14, 0);
INSERT INTO `sizes` VALUES (43, 'X', 15, 0);
INSERT INTO `sizes` VALUES (44, 'XL', 15, 0);
INSERT INTO `sizes` VALUES (45, '2XL', 15, 0);
INSERT INTO `sizes` VALUES (46, 'X', 16, 0);
INSERT INTO `sizes` VALUES (47, 'XL', 16, 0);
INSERT INTO `sizes` VALUES (48, '2XL', 16, 0);
INSERT INTO `sizes` VALUES (49, 'X', 17, 0);
INSERT INTO `sizes` VALUES (50, 'XL', 17, 0);
INSERT INTO `sizes` VALUES (51, '2XL', 17, 0);
INSERT INTO `sizes` VALUES (52, 'X', 22, 0);
INSERT INTO `sizes` VALUES (53, 'XL', 22, 0);
INSERT INTO `sizes` VALUES (54, '2XL', 22, 0);
INSERT INTO `sizes` VALUES (55, 'X', 23, 0);
INSERT INTO `sizes` VALUES (56, 'XL', 23, 0);
INSERT INTO `sizes` VALUES (57, '2XL', 23, 0);
INSERT INTO `sizes` VALUES (58, 'X', 24, 0);
INSERT INTO `sizes` VALUES (59, 'XL', 24, 0);
INSERT INTO `sizes` VALUES (60, '2XL', 24, 0);
INSERT INTO `sizes` VALUES (61, 'X', 25, 0);
INSERT INTO `sizes` VALUES (62, 'XL', 25, 0);
INSERT INTO `sizes` VALUES (63, '2XL', 25, 0);
INSERT INTO `sizes` VALUES (64, 'X', 26, 0);
INSERT INTO `sizes` VALUES (65, 'XL', 26, 0);
INSERT INTO `sizes` VALUES (66, '2XL', 26, 0);
INSERT INTO `sizes` VALUES (67, 'X', 27, 0);
INSERT INTO `sizes` VALUES (68, 'XL', 27, 0);
INSERT INTO `sizes` VALUES (69, '2XL', 27, 0);
INSERT INTO `sizes` VALUES (70, 'X', 28, 0);
INSERT INTO `sizes` VALUES (71, 'XL', 28, 0);
INSERT INTO `sizes` VALUES (72, '2XL', 28, 0);
INSERT INTO `sizes` VALUES (73, 'X', 29, 0);
INSERT INTO `sizes` VALUES (74, 'XL', 29, 0);
INSERT INTO `sizes` VALUES (75, '2XL', 29, 0);
INSERT INTO `sizes` VALUES (76, 'X', 30, 0);
INSERT INTO `sizes` VALUES (77, 'XL', 30, 0);
INSERT INTO `sizes` VALUES (78, '2XL', 30, 0);
INSERT INTO `sizes` VALUES (79, 'X', 31, 0);
INSERT INTO `sizes` VALUES (80, 'XL', 31, 0);
INSERT INTO `sizes` VALUES (81, '2XL', 31, 0);
INSERT INTO `sizes` VALUES (82, 'X', 32, 0);
INSERT INTO `sizes` VALUES (83, 'XL', 32, 0);
INSERT INTO `sizes` VALUES (84, '2XL', 32, 0);
INSERT INTO `sizes` VALUES (85, 'X', 33, 0);
INSERT INTO `sizes` VALUES (86, 'XL', 33, 0);
INSERT INTO `sizes` VALUES (87, '2XL', 33, 0);
INSERT INTO `sizes` VALUES (88, 'X', 34, 0);
INSERT INTO `sizes` VALUES (89, 'XL', 34, 0);
INSERT INTO `sizes` VALUES (90, '2XL', 34, 0);
INSERT INTO `sizes` VALUES (91, 'X', 35, 0);
INSERT INTO `sizes` VALUES (92, 'XL', 35, 0);
INSERT INTO `sizes` VALUES (93, '2XL', 35, 0);
INSERT INTO `sizes` VALUES (94, 'X', 36, 0);
INSERT INTO `sizes` VALUES (95, 'XL', 36, 0);
INSERT INTO `sizes` VALUES (96, '2XL', 36, 0);
INSERT INTO `sizes` VALUES (97, 'X', 37, 0);
INSERT INTO `sizes` VALUES (98, 'XL', 37, 0);
INSERT INTO `sizes` VALUES (99, '2XL', 37, 0);
INSERT INTO `sizes` VALUES (100, 'X', 38, 0);
INSERT INTO `sizes` VALUES (101, 'XL', 38, 0);
INSERT INTO `sizes` VALUES (102, '2XL', 38, 0);
INSERT INTO `sizes` VALUES (103, 'X', 39, 0);
INSERT INTO `sizes` VALUES (104, 'XL', 39, 0);
INSERT INTO `sizes` VALUES (105, '2XL', 39, 0);
INSERT INTO `sizes` VALUES (106, 'X', 40, 0);
INSERT INTO `sizes` VALUES (107, 'XL', 40, 0);
INSERT INTO `sizes` VALUES (108, '2XL', 40, 0);
INSERT INTO `sizes` VALUES (109, 'X', 41, 0);
INSERT INTO `sizes` VALUES (110, 'XL', 41, 0);
INSERT INTO `sizes` VALUES (111, '2XL', 41, 0);
INSERT INTO `sizes` VALUES (112, 'X', 42, 0);
INSERT INTO `sizes` VALUES (113, 'XL', 42, 0);
INSERT INTO `sizes` VALUES (114, '2XL', 42, 0);
INSERT INTO `sizes` VALUES (115, 'X', 43, 0);
INSERT INTO `sizes` VALUES (116, 'XL', 43, 0);
INSERT INTO `sizes` VALUES (117, '2XL', 43, 0);
INSERT INTO `sizes` VALUES (118, 'X', 44, 0);
INSERT INTO `sizes` VALUES (119, 'XL', 44, 0);
INSERT INTO `sizes` VALUES (120, '2XL', 44, 0);
INSERT INTO `sizes` VALUES (121, 'X', 45, 0);
INSERT INTO `sizes` VALUES (122, 'XL', 45, 0);
INSERT INTO `sizes` VALUES (123, '2XL', 45, 0);
INSERT INTO `sizes` VALUES (124, 'X', 46, 0);
INSERT INTO `sizes` VALUES (125, 'XL', 46, 0);
INSERT INTO `sizes` VALUES (126, '2XL', 46, 0);
INSERT INTO `sizes` VALUES (127, 'X', 47, 0);
INSERT INTO `sizes` VALUES (128, 'XL', 47, 0);
INSERT INTO `sizes` VALUES (129, '2XL', 47, 0);
INSERT INTO `sizes` VALUES (130, 'X', 48, 0);
INSERT INTO `sizes` VALUES (131, 'XL', 48, 0);
INSERT INTO `sizes` VALUES (132, '2XL', 48, 0);
INSERT INTO `sizes` VALUES (133, 'X', 49, 0);
INSERT INTO `sizes` VALUES (134, 'XL', 49, 0);
INSERT INTO `sizes` VALUES (135, '2XL', 49, 0);
INSERT INTO `sizes` VALUES (136, 'X', 50, 0);
INSERT INTO `sizes` VALUES (137, 'XL', 50, 0);
INSERT INTO `sizes` VALUES (138, '2XL', 50, 0);
INSERT INTO `sizes` VALUES (139, 'X', 51, 0);
INSERT INTO `sizes` VALUES (140, 'XL', 51, 0);
INSERT INTO `sizes` VALUES (141, '2XL', 51, 0);
INSERT INTO `sizes` VALUES (142, 'X', 52, 0);
INSERT INTO `sizes` VALUES (143, 'XL', 52, 0);
INSERT INTO `sizes` VALUES (144, '2XL', 52, 0);
INSERT INTO `sizes` VALUES (145, 'X', 53, 0);
INSERT INTO `sizes` VALUES (146, 'XL', 53, 0);
INSERT INTO `sizes` VALUES (147, '2XL', 53, 0);
INSERT INTO `sizes` VALUES (148, 'X', 54, 0);
INSERT INTO `sizes` VALUES (149, 'XL', 54, 0);
INSERT INTO `sizes` VALUES (150, '2XL', 54, 0);
INSERT INTO `sizes` VALUES (151, 'X', 55, 0);
INSERT INTO `sizes` VALUES (152, 'XL', 55, 0);
INSERT INTO `sizes` VALUES (153, '2XL', 55, 0);
INSERT INTO `sizes` VALUES (154, 'X', 56, 0);
INSERT INTO `sizes` VALUES (155, 'XL', 56, 0);
INSERT INTO `sizes` VALUES (156, '2XL', 56, 0);
INSERT INTO `sizes` VALUES (157, 'X', 57, 0);
INSERT INTO `sizes` VALUES (158, 'XL', 57, 0);
INSERT INTO `sizes` VALUES (159, '2XL', 57, 0);
INSERT INTO `sizes` VALUES (160, 'X', 58, 0);
INSERT INTO `sizes` VALUES (161, 'XL', 58, 0);
INSERT INTO `sizes` VALUES (162, '2XL', 58, 0);
INSERT INTO `sizes` VALUES (163, 'X', 59, 0);
INSERT INTO `sizes` VALUES (164, 'XL', 59, 0);
INSERT INTO `sizes` VALUES (165, '2XL', 59, 0);
INSERT INTO `sizes` VALUES (166, 'X', 60, 0);
INSERT INTO `sizes` VALUES (167, 'XL', 60, 0);
INSERT INTO `sizes` VALUES (168, '2XL', 60, 0);
INSERT INTO `sizes` VALUES (180, '3XL', 24, 100000);
INSERT INTO `sizes` VALUES (183, 'XL', 29, 0);
INSERT INTO `sizes` VALUES (184, '2XL', 29, 100000);
INSERT INTO `sizes` VALUES (185, '3XL', 29, 100000);
INSERT INTO `sizes` VALUES (189, 'X', 21, 0);
INSERT INTO `sizes` VALUES (190, 'X', 70, 0);
INSERT INTO `sizes` VALUES (191, 'L', 70, 100000);
INSERT INTO `sizes` VALUES (192, 'M', 70, 0);
INSERT INTO `sizes` VALUES (193, 'XL', 70, 0);
INSERT INTO `sizes` VALUES (194, 'X', 71, 0);
INSERT INTO `sizes` VALUES (195, 'XL', 72, 0);
INSERT INTO `sizes` VALUES (196, 'S', 72, 0);
INSERT INTO `sizes` VALUES (197, 'XL', 73, 0);
INSERT INTO `sizes` VALUES (198, 'X', 73, 0);
INSERT INTO `sizes` VALUES (199, '2XL', 73, 0);
INSERT INTO `sizes` VALUES (200, '2XL', 74, 0);
INSERT INTO `sizes` VALUES (201, 'X', 74, 0);
INSERT INTO `sizes` VALUES (202, 'XL', 75, 0);
INSERT INTO `sizes` VALUES (203, 'M', 75, 0);
INSERT INTO `sizes` VALUES (204, 'X', 76, 0);
INSERT INTO `sizes` VALUES (205, '2XL', 76, 0);
INSERT INTO `sizes` VALUES (206, 'XL', 77, 0);
INSERT INTO `sizes` VALUES (207, '2XL', 77, 0);
INSERT INTO `sizes` VALUES (208, '2XL', 78, 0);
INSERT INTO `sizes` VALUES (209, 'S', 78, 0);
INSERT INTO `sizes` VALUES (210, '2XL', 79, 0);
INSERT INTO `sizes` VALUES (211, 'S', 81, 0);
INSERT INTO `sizes` VALUES (212, '2XL', 82, 0);
INSERT INTO `sizes` VALUES (213, 'X', 82, 0);
INSERT INTO `sizes` VALUES (214, 'M', 82, 0);
INSERT INTO `sizes` VALUES (215, 'M', 83, 0);
INSERT INTO `sizes` VALUES (216, '2XL', 83, 0);
INSERT INTO `sizes` VALUES (217, '2XL', 84, 0);
INSERT INTO `sizes` VALUES (218, 'M', 84, 0);
INSERT INTO `sizes` VALUES (219, 'S', 84, 0);
INSERT INTO `sizes` VALUES (220, 'M', 85, 0);
INSERT INTO `sizes` VALUES (221, 'XL', 86, 0);
INSERT INTO `sizes` VALUES (222, 'M', 87, 0);
INSERT INTO `sizes` VALUES (223, 'S', 87, 0);
INSERT INTO `sizes` VALUES (224, 'S', 88, 0);
INSERT INTO `sizes` VALUES (225, 'M', 88, 0);
INSERT INTO `sizes` VALUES (226, 'X', 89, 0);
INSERT INTO `sizes` VALUES (227, 'XL', 89, 0);
INSERT INTO `sizes` VALUES (228, 'S', 89, 0);
INSERT INTO `sizes` VALUES (229, '2XL', 91, 0);
INSERT INTO `sizes` VALUES (230, 'S', 91, 0);
INSERT INTO `sizes` VALUES (231, 'X', 91, 0);
INSERT INTO `sizes` VALUES (232, 'M', 92, 0);
INSERT INTO `sizes` VALUES (233, 'S', 92, 0);
INSERT INTO `sizes` VALUES (234, '2XL', 93, 0);
INSERT INTO `sizes` VALUES (235, 'S', 93, 0);
INSERT INTO `sizes` VALUES (236, 'XL', 93, 0);
INSERT INTO `sizes` VALUES (237, 'X', 94, 0);
INSERT INTO `sizes` VALUES (238, 'S', 95, 0);
INSERT INTO `sizes` VALUES (239, 'X', 95, 0);
INSERT INTO `sizes` VALUES (240, 'XL', 95, 0);
INSERT INTO `sizes` VALUES (241, '2XL', 96, 0);
INSERT INTO `sizes` VALUES (242, 'M', 97, 0);
INSERT INTO `sizes` VALUES (243, '2XL', 97, 0);
INSERT INTO `sizes` VALUES (244, 'X', 97, 0);
INSERT INTO `sizes` VALUES (245, 'S', 98, 0);
INSERT INTO `sizes` VALUES (246, 'X', 98, 0);
INSERT INTO `sizes` VALUES (247, 'S', 99, 0);
INSERT INTO `sizes` VALUES (248, 'X', 99, 0);
INSERT INTO `sizes` VALUES (249, '2XL', 99, 0);
INSERT INTO `sizes` VALUES (250, 'X', 100, 0);
INSERT INTO `sizes` VALUES (251, 'S', 100, 0);
INSERT INTO `sizes` VALUES (252, '2XL', 100, 0);
INSERT INTO `sizes` VALUES (253, 'X', 101, 0);
INSERT INTO `sizes` VALUES (254, 'M', 101, 0);
INSERT INTO `sizes` VALUES (255, 'S', 102, 0);
INSERT INTO `sizes` VALUES (256, 'XL', 102, 0);
INSERT INTO `sizes` VALUES (257, '2XL', 102, 0);
INSERT INTO `sizes` VALUES (258, 'S', 103, 0);
INSERT INTO `sizes` VALUES (259, 'X', 104, 0);
INSERT INTO `sizes` VALUES (260, 'XL', 104, 0);
INSERT INTO `sizes` VALUES (261, '2XL', 104, 0);
INSERT INTO `sizes` VALUES (262, 'S', 105, 0);
INSERT INTO `sizes` VALUES (263, 'M', 106, 0);
INSERT INTO `sizes` VALUES (264, 'X', 106, 0);
INSERT INTO `sizes` VALUES (265, '2XL', 107, 0);
INSERT INTO `sizes` VALUES (266, 'S', 107, 0);
INSERT INTO `sizes` VALUES (267, 'M', 108, 0);
INSERT INTO `sizes` VALUES (268, 'X', 108, 0);
INSERT INTO `sizes` VALUES (269, 'S', 109, 0);
INSERT INTO `sizes` VALUES (270, '2XL', 109, 0);
INSERT INTO `sizes` VALUES (271, 'S', 110, 0);
INSERT INTO `sizes` VALUES (272, 'M', 111, 0);
INSERT INTO `sizes` VALUES (273, 'S', 111, 0);
INSERT INTO `sizes` VALUES (274, 'S', 112, 0);
INSERT INTO `sizes` VALUES (275, 'XL', 112, 0);
INSERT INTO `sizes` VALUES (276, '2XL', 112, 0);
INSERT INTO `sizes` VALUES (277, 'X', 113, 0);
INSERT INTO `sizes` VALUES (278, 'M', 113, 0);
INSERT INTO `sizes` VALUES (279, 'S', 114, 0);
INSERT INTO `sizes` VALUES (280, 'M', 114, 0);
INSERT INTO `sizes` VALUES (281, 'XL', 114, 0);
INSERT INTO `sizes` VALUES (282, '2XL', 114, 0);
INSERT INTO `sizes` VALUES (283, 'S', 115, 0);
INSERT INTO `sizes` VALUES (284, 'X', 116, 0);
INSERT INTO `sizes` VALUES (285, '2XL', 116, 0);
INSERT INTO `sizes` VALUES (286, 'XL', 116, 0);
INSERT INTO `sizes` VALUES (287, 'S', 117, 0);
INSERT INTO `sizes` VALUES (288, '2XL', 117, 0);
INSERT INTO `sizes` VALUES (289, 'X', 117, 0);
INSERT INTO `sizes` VALUES (290, '2XL', 118, 0);
INSERT INTO `sizes` VALUES (291, 'M', 119, 0);
INSERT INTO `sizes` VALUES (292, 'S', 119, 0);
INSERT INTO `sizes` VALUES (293, 'X', 120, 0);
INSERT INTO `sizes` VALUES (294, 'S', 121, 0);
INSERT INTO `sizes` VALUES (295, 'XL', 121, 0);
INSERT INTO `sizes` VALUES (296, '2XL', 122, 0);
INSERT INTO `sizes` VALUES (297, 'M', 122, 0);
INSERT INTO `sizes` VALUES (298, 'XL', 122, 0);
INSERT INTO `sizes` VALUES (299, 'X', 122, 0);
INSERT INTO `sizes` VALUES (300, 'S', 123, 0);
INSERT INTO `sizes` VALUES (301, 'XL', 123, 0);
INSERT INTO `sizes` VALUES (302, 'M', 123, 0);
INSERT INTO `sizes` VALUES (303, 'M', 124, 0);
INSERT INTO `sizes` VALUES (304, 'XL', 124, 0);
INSERT INTO `sizes` VALUES (305, 'X', 125, 0);
INSERT INTO `sizes` VALUES (306, '2XL', 125, 0);
INSERT INTO `sizes` VALUES (307, '2XL', 126, 0);
INSERT INTO `sizes` VALUES (308, 'M', 126, 0);
INSERT INTO `sizes` VALUES (309, 'XL', 127, 0);
INSERT INTO `sizes` VALUES (310, 'M', 128, 0);
INSERT INTO `sizes` VALUES (311, 'S', 129, 0);
INSERT INTO `sizes` VALUES (312, 'M', 129, 0);
INSERT INTO `sizes` VALUES (313, 'M', 130, 0);
INSERT INTO `sizes` VALUES (314, 'XL', 130, 0);
INSERT INTO `sizes` VALUES (315, 'XL', 131, 0);
INSERT INTO `sizes` VALUES (316, 'M', 131, 0);
INSERT INTO `sizes` VALUES (317, 'S', 131, 0);
INSERT INTO `sizes` VALUES (318, '2XL', 132, 0);
INSERT INTO `sizes` VALUES (319, '2XL', 133, 0);
INSERT INTO `sizes` VALUES (320, 'M', 133, 0);
INSERT INTO `sizes` VALUES (321, 'XL', 133, 0);
INSERT INTO `sizes` VALUES (322, 'S', 133, 0);
INSERT INTO `sizes` VALUES (323, '2XL', 134, 0);
INSERT INTO `sizes` VALUES (324, 'XL', 134, 0);
INSERT INTO `sizes` VALUES (325, 'S', 134, 0);
INSERT INTO `sizes` VALUES (326, 'X', 135, 0);
INSERT INTO `sizes` VALUES (327, '2XL', 136, 0);
INSERT INTO `sizes` VALUES (328, 'M', 136, 0);
INSERT INTO `sizes` VALUES (329, 'X', 137, 0);
INSERT INTO `sizes` VALUES (330, 'XL', 137, 0);
INSERT INTO `sizes` VALUES (331, 'S', 138, 0);
INSERT INTO `sizes` VALUES (332, 'M', 138, 0);
INSERT INTO `sizes` VALUES (333, 'S', 139, 0);
INSERT INTO `sizes` VALUES (334, '2XL', 141, 0);
INSERT INTO `sizes` VALUES (335, 'X', 141, 0);
INSERT INTO `sizes` VALUES (336, 'M', 142, 0);
INSERT INTO `sizes` VALUES (337, 'XL', 142, 0);
INSERT INTO `sizes` VALUES (338, 'M', 143, 0);
INSERT INTO `sizes` VALUES (339, '2XL', 143, 0);
INSERT INTO `sizes` VALUES (340, 'X', 143, 0);
INSERT INTO `sizes` VALUES (341, 'M', 144, 0);
INSERT INTO `sizes` VALUES (342, 'S', 145, 0);
INSERT INTO `sizes` VALUES (343, 'XL', 145, 0);
INSERT INTO `sizes` VALUES (344, '2XL', 145, 0);
INSERT INTO `sizes` VALUES (345, '2XL', 146, 0);
INSERT INTO `sizes` VALUES (346, 'XL', 146, 0);
INSERT INTO `sizes` VALUES (347, 'X', 146, 0);
INSERT INTO `sizes` VALUES (348, 'XL', 147, 0);
INSERT INTO `sizes` VALUES (349, 'XL', 149, 0);
INSERT INTO `sizes` VALUES (350, 'XL', 150, 0);
INSERT INTO `sizes` VALUES (351, 'M', 150, 0);
INSERT INTO `sizes` VALUES (352, 'X', 151, 0);
INSERT INTO `sizes` VALUES (353, '2XL', 151, 0);
INSERT INTO `sizes` VALUES (354, 'XL', 151, 0);
INSERT INTO `sizes` VALUES (355, 'S', 152, 0);
INSERT INTO `sizes` VALUES (356, 'X', 153, 0);
INSERT INTO `sizes` VALUES (357, 'M', 153, 0);
INSERT INTO `sizes` VALUES (358, 'S', 153, 0);
INSERT INTO `sizes` VALUES (359, 'M', 154, 0);
INSERT INTO `sizes` VALUES (360, 'X', 154, 0);
INSERT INTO `sizes` VALUES (361, 'X', 155, 0);
INSERT INTO `sizes` VALUES (362, 'S', 155, 0);
INSERT INTO `sizes` VALUES (363, 'XL', 155, 0);
INSERT INTO `sizes` VALUES (364, 'M', 155, 0);
INSERT INTO `sizes` VALUES (365, 'M', 156, 0);
INSERT INTO `sizes` VALUES (366, 'X', 157, 0);
INSERT INTO `sizes` VALUES (367, 'X', 158, 0);
INSERT INTO `sizes` VALUES (368, '2XL', 158, 0);
INSERT INTO `sizes` VALUES (369, 'X', 159, 0);
INSERT INTO `sizes` VALUES (370, 'XL', 159, 0);
INSERT INTO `sizes` VALUES (371, 'XL', 160, 0);
INSERT INTO `sizes` VALUES (372, 'M', 160, 0);
INSERT INTO `sizes` VALUES (373, 'X', 160, 0);
INSERT INTO `sizes` VALUES (374, 'XL', 162, 0);
INSERT INTO `sizes` VALUES (375, 'X', 162, 0);
INSERT INTO `sizes` VALUES (376, '2XL', 162, 0);
INSERT INTO `sizes` VALUES (377, 'M', 163, 0);
INSERT INTO `sizes` VALUES (378, '2XL', 164, 0);
INSERT INTO `sizes` VALUES (379, 'M', 165, 0);
INSERT INTO `sizes` VALUES (380, 'XL', 165, 0);
INSERT INTO `sizes` VALUES (381, 'XL', 166, 0);
INSERT INTO `sizes` VALUES (382, 'X', 166, 0);
INSERT INTO `sizes` VALUES (383, 'M', 167, 0);
INSERT INTO `sizes` VALUES (384, '2XL', 168, 0);
INSERT INTO `sizes` VALUES (385, '2XL', 169, 0);
INSERT INTO `sizes` VALUES (386, 'XL', 169, 0);
INSERT INTO `sizes` VALUES (387, 'M', 169, 0);
INSERT INTO `sizes` VALUES (388, 'M', 170, 0);
INSERT INTO `sizes` VALUES (389, 'X', 170, 0);
INSERT INTO `sizes` VALUES (390, 'XL', 172, 0);
INSERT INTO `sizes` VALUES (391, 'M', 173, 0);
INSERT INTO `sizes` VALUES (392, 'X', 173, 0);
INSERT INTO `sizes` VALUES (393, 'S', 173, 0);
INSERT INTO `sizes` VALUES (394, 'XL', 174, 0);
INSERT INTO `sizes` VALUES (395, '2XL', 174, 0);
INSERT INTO `sizes` VALUES (396, 'XL', 175, 0);
INSERT INTO `sizes` VALUES (397, '2XL', 175, 0);
INSERT INTO `sizes` VALUES (398, 'S', 178, 0);
INSERT INTO `sizes` VALUES (399, 'X', 178, 0);
INSERT INTO `sizes` VALUES (400, 'M', 178, 0);
INSERT INTO `sizes` VALUES (401, 'S', 179, 0);
INSERT INTO `sizes` VALUES (402, 'M', 179, 0);
INSERT INTO `sizes` VALUES (403, 'X', 179, 0);
INSERT INTO `sizes` VALUES (404, 'X', 180, 0);
INSERT INTO `sizes` VALUES (405, 'S', 180, 0);
INSERT INTO `sizes` VALUES (406, 'M', 181, 0);
INSERT INTO `sizes` VALUES (407, 'M', 182, 0);
INSERT INTO `sizes` VALUES (408, 'M', 183, 0);
INSERT INTO `sizes` VALUES (409, '2XL', 183, 0);
INSERT INTO `sizes` VALUES (410, 'X', 183, 0);
INSERT INTO `sizes` VALUES (411, 'X', 184, 0);
INSERT INTO `sizes` VALUES (412, '2XL', 185, 0);
INSERT INTO `sizes` VALUES (413, 'XL', 185, 0);
INSERT INTO `sizes` VALUES (414, 'M', 186, 0);
INSERT INTO `sizes` VALUES (415, 'X', 186, 0);
INSERT INTO `sizes` VALUES (416, 'S', 187, 0);
INSERT INTO `sizes` VALUES (417, '2XL', 187, 0);
INSERT INTO `sizes` VALUES (418, 'XL', 187, 0);
INSERT INTO `sizes` VALUES (419, 'X', 188, 0);
INSERT INTO `sizes` VALUES (420, 'M', 188, 0);
INSERT INTO `sizes` VALUES (421, 'S', 189, 0);
INSERT INTO `sizes` VALUES (422, '2XL', 190, 0);
INSERT INTO `sizes` VALUES (423, 'S', 190, 0);
INSERT INTO `sizes` VALUES (424, 'M', 191, 0);
INSERT INTO `sizes` VALUES (425, 'XL', 191, 0);
INSERT INTO `sizes` VALUES (426, '2XL', 192, 0);
INSERT INTO `sizes` VALUES (427, '2XL', 193, 0);
INSERT INTO `sizes` VALUES (428, 'XL', 193, 0);
INSERT INTO `sizes` VALUES (429, 'M', 193, 0);
INSERT INTO `sizes` VALUES (430, 'M', 194, 0);
INSERT INTO `sizes` VALUES (431, 'XL', 194, 0);
INSERT INTO `sizes` VALUES (432, 'X', 195, 0);
INSERT INTO `sizes` VALUES (433, 'XL', 195, 0);
INSERT INTO `sizes` VALUES (434, 'S', 195, 0);
INSERT INTO `sizes` VALUES (435, 'XL', 196, 0);
INSERT INTO `sizes` VALUES (436, '2XL', 196, 0);
INSERT INTO `sizes` VALUES (437, '2XL', 197, 0);
INSERT INTO `sizes` VALUES (438, 'S', 197, 0);
INSERT INTO `sizes` VALUES (439, 'XL', 198, 0);
INSERT INTO `sizes` VALUES (440, 'X', 198, 0);
INSERT INTO `sizes` VALUES (441, '2XL', 198, 0);
INSERT INTO `sizes` VALUES (442, '2XL', 199, 0);
INSERT INTO `sizes` VALUES (443, 'S', 199, 0);
INSERT INTO `sizes` VALUES (444, 'M', 199, 0);
INSERT INTO `sizes` VALUES (445, 'X', 199, 0);
INSERT INTO `sizes` VALUES (446, '2XL', 200, 0);
INSERT INTO `sizes` VALUES (447, 'X', 200, 0);
INSERT INTO `sizes` VALUES (448, 'M', 201, 0);
INSERT INTO `sizes` VALUES (449, 'M', 202, 0);
INSERT INTO `sizes` VALUES (450, '2XL', 202, 0);
INSERT INTO `sizes` VALUES (451, 'XL', 202, 0);
INSERT INTO `sizes` VALUES (452, '2XL', 203, 0);
INSERT INTO `sizes` VALUES (453, 'M', 203, 0);
INSERT INTO `sizes` VALUES (454, 'X', 203, 0);
INSERT INTO `sizes` VALUES (455, '2XL', 204, 0);
INSERT INTO `sizes` VALUES (456, 'M', 205, 0);
INSERT INTO `sizes` VALUES (457, 'XL', 205, 0);
INSERT INTO `sizes` VALUES (458, 'XL', 206, 0);
INSERT INTO `sizes` VALUES (459, 'X', 206, 0);
INSERT INTO `sizes` VALUES (460, 'M', 207, 0);
INSERT INTO `sizes` VALUES (461, '2XL', 208, 0);
INSERT INTO `sizes` VALUES (462, 'X', 208, 0);
INSERT INTO `sizes` VALUES (463, 'XL', 209, 0);
INSERT INTO `sizes` VALUES (464, 'M', 210, 0);
INSERT INTO `sizes` VALUES (465, 'XL', 210, 0);
INSERT INTO `sizes` VALUES (466, 'X', 211, 0);
INSERT INTO `sizes` VALUES (467, 'XL', 211, 0);
INSERT INTO `sizes` VALUES (468, '2XL', 211, 0);
INSERT INTO `sizes` VALUES (469, 'S', 212, 0);
INSERT INTO `sizes` VALUES (470, 'X', 212, 0);
INSERT INTO `sizes` VALUES (471, 'XL', 213, 0);
INSERT INTO `sizes` VALUES (472, '2XL', 213, 0);
INSERT INTO `sizes` VALUES (473, 'M', 213, 0);
INSERT INTO `sizes` VALUES (474, '2XL', 214, 0);
INSERT INTO `sizes` VALUES (475, 'S', 215, 0);
INSERT INTO `sizes` VALUES (476, 'S', 216, 0);
INSERT INTO `sizes` VALUES (477, 'M', 216, 0);
INSERT INTO `sizes` VALUES (478, '2XL', 217, 0);
INSERT INTO `sizes` VALUES (479, 'XL', 217, 0);
INSERT INTO `sizes` VALUES (480, 'M', 217, 0);
INSERT INTO `sizes` VALUES (481, 'XL', 218, 0);
INSERT INTO `sizes` VALUES (482, 'M', 218, 0);

-- ----------------------------
-- Table structure for sliders
-- ----------------------------
DROP TABLE IF EXISTS `sliders`;
CREATE TABLE `sliders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameSlide` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `nameImage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `visibility` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sliders
-- ----------------------------
INSERT INTO `sliders` VALUES (1, 'Hoodie', 'Hoodie.png', b'1');
INSERT INTO `sliders` VALUES (2, 'T-Shirt', 'T-Shirt.png', b'1');
INSERT INTO `sliders` VALUES (3, 'Vest', 'Vest.png', b'1');
INSERT INTO `sliders` VALUES (4, 'Pants', 'Pants.png', b'1');

-- ----------------------------
-- Table structure for transaction_statuses
-- ----------------------------
DROP TABLE IF EXISTS `transaction_statuses`;
CREATE TABLE `transaction_statuses`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction_statuses
-- ----------------------------
INSERT INTO `transaction_statuses` VALUES (1, 'Chưa thanh toán');
INSERT INTO `transaction_statuses` VALUES (2, 'Đang xử lý');
INSERT INTO `transaction_statuses` VALUES (3, 'Đã thanh toán');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `passwordEncoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fullName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthDay` date NULL DEFAULT NULL,
  `isVerify` bit(1) NOT NULL DEFAULT b'0',
  `role` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `avatar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tokenVerify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Kiểm tra link xác thực qua email',
  `tokenVerifyTime` datetime NULL DEFAULT NULL,
  `tokenResetPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Kiểm tra link đặt lại mật khẩu',
  `tokenResetPasswordTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'HieuNguyen', 'iVjZFJCz5KHq5250p1HVrHrzGgI=', 'Nguyễn Chí Hiếu', 'Nam', 'HieuNguyen@gmail.com', '0703637448', '164 Ngô Tất Tố, Bình Định', '1990-01-01', b'1', '0', 'user_avatar-1.jpg', '', NULL, '', NULL);
INSERT INTO `users` VALUES (2, 'TienDung', 'yo7AvyS/L9jl0jJ9Ajc9zjvbXWA=', 'Mai Tiến Dũng', 'Nam', 'TienDung@gmail.com', '0902346795', 'Xã Đức Long, Huyện Hoà An, Cao Bằng', '1995-02-15', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (3, 'HoangVanLong', 'XISIbnTS1LVOKA0je+N7APNo2Zc=', 'Hoàng Văn Long', NULL, 'longhoang@gmail.com', '0905123456', 'Số 123 Đường Lê Lai, Phường Phạm Ngũ Lão, Quận 1, TP.HCM', '1992-07-18', b'0', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (4, 'PhamThiHuyen', 'BxIAkAGPWPmMu+WPsu3XFCMLs7s=', 'Phạm Thị Huyền', NULL, 'huyenpham@gmail.com', '0978223344', 'Số 456 Đường Lê Thánh Tôn, Phường Bến Nghé, Quận 1, TP.HCM', '1987-09-30', b'0', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (5, 'NguyPhuong', '/5fgMKPUGurXMVdonxXEur/hCNk=', 'Ngụy Ðông Phương', NULL, 'DongPhuong@gmail.com', '0783748159', 'Xã Nghĩa Hưng, Huyện Chư Păh, Gia Lai', '1988-07-20', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (6, 'ThuyNuonglunglinh', 'naC/w9G2msDVMoAgjNi7tXFt2sI=', 'Liễu Thụy Nương', NULL, 'LieuNuong4@gmail.com', '0769506724', 'Xã Vĩnh Phúc, Huyện Vĩnh Lộc, Thanh Hóa', '1992-12-10', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (7, 'HuuDat', 'I+bGbYRNxtj0F5KyRwuIg5EW5rQ=', 'Cáp Hữu Ðạt', NULL, 'HuuDat@gmail.com', '0328012964', 'Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang', '1999-05-30', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (8, 'CongHieu', '1I7jaymPCW0PEu8ZVtUmw6c680c=', 'Nhan Công Hiếu', NULL, 'CongHieu6@gmail.com', '0325976083', 'Xã Đắk Choong, Huyện Đắk Glei, Kon Tum', '1985-03-25', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (9, 'DragonFruit', '4cOtNVvvNkkmLI7eVc4Lv/WBLx8=', 'Hầu Thanh Long', NULL, 'ThanhLong@egmail.com', '0707405391', 'Xã An Nông, Huyện Tịnh Biên, An Giang', '1998-09-05', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (10, 'HamNoThi', 'AvHevQp9XVc7vWFn76bK5IzEGC4=', 'Thi Hồ Nam', NULL, 'HoNam8@gmail.com', '0886819054', 'Xã Tham Đôn, Huyện Mỹ Xuyên, Sóc Trăng', '1994-04-17', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (11, 'KimLan', 'Z5VSSjFyDrDUzrGY67tfh7Zyzko=', 'Thục Kim Lan', NULL, 'KimLan@gmail.com', '0797204681', 'Phường Thốt Nốt, Quận Thốt Nốt, Cần Thơ', '1991-11-12', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (12, 'XuanNinh', 'Jxjh5G/3ZXMH2YB9CyAGAa+kmsA=', 'Quản Xuân Ninh', NULL, 'XuanNinh@gmail.com', '039 270 3698', 'Thị trấn Vĩnh Thạnh, Huyện Vĩnh Thạnh, Bình Định', '1997-08-08', b'1', '0', NULL, '', NULL, '', NULL);
INSERT INTO `users` VALUES (15, 'ducvui2003', '8hwSKY+KnIf/dFNQg5AQp1bE6lE=', '', 'Nam', 'ducvui2003@gmail.com', '', '', '1906-03-26', b'1', '2', '6b2e96fd-bbae-4019-a995-f72953c663b5.jpg', NULL, '0000-00-00 00:00:00', NULL, NULL);
INSERT INTO `users` VALUES (22, '21130320', 'GxJwx1vpLrKRdrvTaGhQV+uTcvI=', NULL, NULL, '21130320@st.hcmuaf.edu.vn', NULL, NULL, NULL, b'1', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (23, 'thanhnam', '9ZfUqodcyinEWdBOzNQBJcRcKY8=', NULL, NULL, 'namcao123a@gmail.com', NULL, NULL, NULL, b'1', '2', NULL, 'ce9dc818-df97-41b3-9120-c11e1d323562', '2024-03-24 11:46:39', NULL, NULL);

-- ----------------------------
-- Table structure for vouchers
-- ----------------------------
DROP TABLE IF EXISTS `vouchers`;
CREATE TABLE `vouchers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `minimumPrice` double NULL DEFAULT NULL,
  `discountPercent` double NULL DEFAULT NULL,
  `expiryDate` date NULL DEFAULT NULL,
  `availableTurns` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vouchers
-- ----------------------------
INSERT INTO `vouchers` VALUES (1, 'MGG5%', 'Bạn được giảm giá 5% cho đơn hàng từ', 400000, 0.05, '2024-01-25', 100);
INSERT INTO `vouchers` VALUES (2, 'MGG10%', 'Bạn được giảm giá 10% cho đơn hàng từ', 500000, 0.1, '2024-01-24', 80);
INSERT INTO `vouchers` VALUES (3, 'MGG15%', 'Bạn được giảm giá 15% cho đơn hàng từ', 640000, 0.15, '2024-01-24', 80);
INSERT INTO `vouchers` VALUES (4, 'MGG20%', 'Bạn được giảm giá 20% cho đơn hàng từ', 900000, 0.2, '2024-01-24', 70);
INSERT INTO `vouchers` VALUES (5, 'MGG25%', 'Bạn được giảm giá 25% cho đơn hàng từ', 2200000, 0.25, '2024-01-24', 50);
INSERT INTO `vouchers` VALUES (6, 'MGG30%', 'Bạn được giảm giá 30% cho đơn hàng từ', 4000000, 0.3, '2024-01-24', 30);
INSERT INTO `vouchers` VALUES (7, 'MGG35%', 'Bạn được giảm giá 35% cho đơn hàng từ', 6500000, 0.35, '2024-01-24', 10);
INSERT INTO `vouchers` VALUES (8, 'MGG40%', 'Bạn được giảm giá 40% cho đơn hàng từ', 8000000, 0.4, '2024-01-24', 5);
INSERT INTO `vouchers` VALUES (9, 'MGG45%', 'Bạn được giảm giá 45% cho đơn hàng từ', 10000000, 0.45, '2024-01-24', 5);
INSERT INTO `vouchers` VALUES (10, 'MGG50%', 'Bạn được giảm giá 50% cho đơn hàng từ', 18000000, 0.5, '2024-01-24', 5);

SET FOREIGN_KEY_CHECKS = 1;
