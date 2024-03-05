// const listCategories = [
//     {
//         id: "category01",
//         nameType: "Áo dài tay",
//         styleGuideImage: "daiTaySizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 67, "max": 80,"unit": "cm"},{"name": "ngang ngực", "min": 48, "max": 60,"unit": "cm"},{"name": "dài tay", "min": 59, "max": 70,"unit": "cm"},{"name": "rộng vai", "min": 41.5, "max": 52,"unit": "cm"}]',
//     },
//     {
//         id: "category02",
//         nameType: "Áo oversize",
//         styleGuideImage: "oversizeSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 68.5, "max": 73,"unit": "cm"},{"name": "dài tay", "min": 19, "max": 23,"unit": "cm"},{"name": "rộng gấu", "min": 54, "max": 60,"unit": "cm"},{"name": "rộng bắp tay", "min": 21, "max": 25,"unit": "cm"},{"name": "rộng vai", "min": 45, "max": 50,"unit": "cm"}]',
//     },
//     {
//         id: "category03",
//         nameType: "Áo nỉ dài tay",
//         styleGuideImage: "niTayDaiSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 63, "max": 80,"unit": "cm"},{"name": "dài tay", "min": 66, "max": 85,"unit": "cm"},{"name": "rộng ngực", "min": 47, "max": 60,"unit": "cm"},{"name": "rộng gấu áo", "min": 43, "max": 55,"unit": "cm"}]',
//     },
//     {
//         id: "category04",
//         nameType: "Quần short",
//         styleGuideImage: "quanShortDaiSizeGuide.png",
//         ruleSize: '[{"name": "dài quần", "min": 36, "max": 45, "unit": "cm","unit": "cm"},{"name": "rộng mông", "min": 50, "max": 70,"unit": "cm"},{"name": "rộng gấu", "min": 30, "max": 37,"unit": "cm"}]',
//     },
//     {
//         id: "category05",
//         nameType: "Áo Polo",
//         styleGuideImage: "aoPoloSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 65, "max": 90,"unit": "cm"},{"name": "ngang ngực", "min": 45, "max": 60,"unit": "cm"},{"name": "dài tay", "min": 19, "max": 30,"unit": "cm"},{"name": "rộng vai", "min": 40, "max": 50,"unit": "cm"}]',
//     },
//     {
//         id: "category06",
//         nameType: "Áo sơ mi tay dài",
//         styleGuideImage: "aoSoMiTayDaiSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 65, "max": 87,"unit": "cm"},{"name": "ngang ngực", "min": 45, "max": 60,"unit": "cm"},{"name": "dài tay", "min": 55, "max": 75,"unit": "cm"},{"name": "rộng vai", "min": 40, "max": 60,"unit": "cm"}]',
//     },
//     {
//         id: "category07",
//         nameType: "Áo sơ mi ngắn tay",
//         styleGuideImage: "aoSoMiTayNganSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 65, "max": 70,"unit": "cm"},{"name": "ngang ngực", "min": 45, "max": 60,"unit": "cm"},{"name": "dài tay", "min": 18, "max": 25,"unit": "cm"},{"name": "rộng vai", "min": 40, "max": 60,"unit": "cm"}]',
//     },
//     {
//         id: "category08",
//         nameType: "Quần jeans",
//         styleGuideImage: "quanJeanSizeGuide.png",
//         ruleSize: '[{"name": "rộng cạp", "min": 35, "max": 50,"unit": "cm"},{"name": "rộng mông", "min": 45, "max": 58,"unit": "cm"},{"name": "rộng đùi cách đũng quần 2.5cm", "min": 25, "max": 33,"unit": "cm"},{"name": "rộng Gấu", "min": 13, "max": 17,"unit": "cm"},{"name": "dài quần", "min": 95, "max": 120,"unit": "cm"}]',
//     },
//     {
//         id: "category09",
//         nameType: "Quần Kaki",
//         styleGuideImage: "quanKakiSizeGuide.png",
//         ruleSize: '[{"name": "rộng cạp đo 1/2", "min": 35, "max": 50,"unit": "cm"},{"name": "rộng mông", "min": 45, "max": 63,"unit": "cm"},{"name": "rộng ngang ống đo 1/2", "min": 20, "max": 30,"unit": "cm"},{"name": "dài quần", "min": 47, "max": 65,"unit": "cm"}]',
//     },
//     {
//         id: "category10",
//         nameType: "Áo ba lỗ",
//         styleGuideImage: "aoBaLoSizeGuide.png",
//         ruleSize: '[{"name": "dài áo", "min": 65, "max": 80,"unit": "cm"},{"name": "rộng vai", "min": 27, "max": 35,"unit": "cm"},{"name": "rộng ngực", "min": 39, "max": 65,"unit": "cm"},{"name": "vòng nách trước", "min": 20, "max": 32,"unit": "cm"}]',
//     },
//     {
//         id: "category11",
//         nameType: "Quần Joggers",
//         styleGuideImage: "quanJoggersSizeGuide.png",
//         ruleSize: '{"name": "dài quần", "min": 90, "max": 110,"unit": "cm"},{"name": "1/2 vòng cạp", "min": 30, "max": 45, "unit": "cm"}, {"name": "1/2 vòng mông", "min": 45, "max": 60, "unit": "cm"}, {"name": "1/2 vòng gấu", "min": 10, "max": 15, "unit": "cm"}]',
//     },
// ]
//
// const listProduct = [
//     {
//         id: "1",
//         name: `Áo polo nam trơn basic form regular vải cá sấu`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product1.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: AiryCotton (Cotton 85%, Poly 5%, Spandex 10%) mềm mại, co giãn 4 chiều và thấm hút mồ hôi tốt tạo cảm giác thoải mái và mát mẻ`,
//         basePrice: 350000.0,
//         salePrice: 279000.0
//     },
//
//     {
//         id: "10",
//         name: `Quần jean unisex nam nữ cotton form slimfit`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product10.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: jeans (97% cotton, 3% spandex) co giãn tốt, bền màu và độ rộng vừa phải tạo cảm giác thoải mái cho người mặc`,
//         basePrice: 375000.0,
//         salePrice: 225000.0
//     },
//
//     {
//         id: "11",
//         name: `Áo thun unisex nam nữ basic tee form oversize`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product11.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu 100% cotton mềm mịn co giãn 2 chiều, thoáng mát, thấm hút mồ hôi, ít nhăn và mang lại cảm giác thoải mái cho người mặc`,
//         basePrice: 390000.0,
//         salePrice: 260000.0
//     },
//
//     {
//         id: "12",
//         name: `Áo phông raglan unisex nam nữ basic cotton dày dặn form oversize`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product12.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton 2 chiều cao cấp dày dặn, thoáng mát không bí nóng tạo cảm giác thoải mái và tôn dáng cho người mặc`,
//         basePrice: 275000.0,
//         salePrice: 235000.0
//     },
//
//     {
//         id: "13",
//         name: `Áo phông layer pattern unisex nam nữ form oversize`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]', imgSrc: "product13.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton mềm mịn, thoải mái và đem lại sự thoải mái tiện lợi nhất cho người mặc`,
//         basePrice: 358000.0,
//         salePrice: 279000.0
//     },
//
//     {
//         id: "14",
//         name: `Áo sơ mi nam tay dài form rộng vải lụa dày dặn`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product14.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton vải lụa mềm mại, chống nhăn và độ rộng vừa phải, tôn dáng, trẻ trung phù hợp mặc cho nhiều dịp`,
//         basePrice: 299000.0,
//         salePrice: 246000.0
//     },
//
//     {
//         id: "15",
//         name: `Áo sơ mi sọc nam tay dài vải Oxford`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product15.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: Oxford (65% cotton, 20% PE 15% viscose) thoáng mát, mềm mịn và có khả năng thấm hút mồ hôi tốt dễ dàng giặt ủi và bền màu trong 1 thời gian dài sử dụng`,
//         basePrice: 455000.0,
//         salePrice: 299000.0
//     },
//
//     {
//         id: "16",
//         name: `Áo sơ mi công sở unisex nam nữ tay dài vải lụa form form slimfit`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product16.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: cotton lụa (80% cotton, 20% polys) miền mịn, vừa vặn không ôm sát và đủ để tạo cảm giác thoải mái cho người mặc`,
//         basePrice: 389000.0,
//         salePrice: 289000.0
//     },
//
//     {
//         id: "17",
//         name: `Quần Kaki baggy trơn unisex nam nữ lưng thun dây rút`,
//         categoryId: "category09",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product17.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton kaki dày dặn đàn hồi và co dãn nhẹ, mặt vải mềm mướt không xù lông mang lại cảm giác thoải mái, thoáng mát cho người mặc`,
//         basePrice: 325000.0,
//         salePrice: 295000.0
//     },
//
//     {
//         id: "2",
//         name: `Áo polo thể thao unisex nam nữ`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product2.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Polyster khô nhanh, thấm hút nhanh, không nhăn và khả năng khử mùi tự nhiên tạo cảm giác thoáng mát khi vận động`,
//         basePrice: 299000.0,
//         salePrice: 239000.0
//     },
//
//     {
//         id: "21",
//         name: `Sơ mi dài tay Café-DriS`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]', imgSrc: "product21.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 50% S.Café + 50% Recycled PET
// Phù hợp với: đi làm, đi chơi
// Kiểu dáng: Regular fit dáng suông
// Người mẫu: 177 cm - 74 kg, mặc size XL
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 499000.0,
//         salePrice: 429000.0
//     },
//
//     {
//         id: "22",
//         name: `Áo dài tay Cotton Compact`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]', imgSrc: "product22.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 95% Cotton Compact - 5% Spandex
// Kiểu dáng: áo thun dài tay
// Phù hợp với: giữ ấm cơ thể vào mùa đông
// Sản xuất tại xưởng 8 năm kinh nghiệm tại TP Hồ Chí Minh * Xem nhà máy >
// Người mẫu: 1m75, 69kg * Mặc áo XL, quần L
// Tự hào sản xuất tại VIệt Nam`,
//         basePrice: 269000.0,
//         salePrice: 229000.0
//     },
//
//     {
//         id: "23",
//         name: `Áo dài tay nỉ`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]', imgSrc: "product23.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu 60% Cotton, 40% Polyester tạo cảm giác thông thoáng và thoải mái khi mặc
// Kiểu dáng: áo nỉ dài tay
// Phù hợp với: giữ ấm cơ thể vào mùa đông
// Người mẫu: 1m75, 69kg * Mặc áo XL, quần L
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 339000.0,
//         salePrice: 289000.0
//     },
//
//     {
//         id: "24",
//         name: `Áo dài tay Cotton Compact V2`,
//         categoryId: "category01",
//         color: '["#ffffff", "#000000"]', imgSrc: "product24.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 95% Cotton Compact + 5% Spandex
// Bề mặt vải Cotton mềm mịn, cảm giác mát lần đầu chạm tay
// Thấm hút mồ hôi tốt
// Độ xù lông thấp
// Vải có độ bền cao, co giãn 4 chiều và hạn chế bai nhão
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 269000.0,
//         salePrice: 229000.0
//     },
//
//     {
//         id: "25",
//         name: `Áo nỉ chui đầu Lifewear`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]', imgSrc: "product25.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: Nỉ chân cua
// Thành phần: 60% Cotton + 40% Polyester
// Thoáng khí và thoải mái khi mặc, vẫn giữ ấm cho bạn trong trời thu đông
// Hạn chế xù lông, bền màu
// Form dáng: Regular, thoải mái
// Tự hào sản xuất tại Viêt Nam`,
//         basePrice: 339000.0,
//         salePrice: 309000.0
//     },
//
//     {
//         id: "26",
//         name: `Shorts thể thao 9`,
//         categoryId: "category04",
//         color: '["#ffffff", "#000000"]', imgSrc: "product26.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 88% Polyester + 12% Spandex
// Vải có khả năng thấm hút tốt và nhanh khô
// Co giãn 4 chiều, thoải mái vận động
// Túi to và sâu tiện lợi, thoải mái đựng đồ cá nhân
// Có 1 túi khoá kéo ẩn, đựng vừa chìa khoá hay Airpods
// Độ dài đo từ đũng đến viền ống quần: 9"
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 249000.0,
//         salePrice: 229000.0
//     },
//
//     {
//         id: "27",
//         name: `Shorts chạy bộ Ultra`,
//         categoryId: "category04",
//         color: '["#ffffff", "#000000"]', imgSrc: "product27.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Polyester
// Xử lý hoàn thiện vải: Quick-Dry + Wicking + Stretch
// Công nghệ Chafe-Free hạn chế tối đa ma sát trong quá trình vận động từ các đường may tối giản hoá
// Phù hợp với: chơi thể thao, chạy bộ. Được đánh giá bởi các Runner chuyên nghiệp
// Độ dài quần: 5 inch
// Tự hào sản xuất tại Việt Nam
// Người mẫu: 183 cm - 76 kg, mặc quần 2XL`,
//         basePrice: 279000.0,
//         salePrice: 229000.0
//     },
//
//     {
//         id: "28",
//         name: `Polo Pique Cotton`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]', imgSrc: "product28.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Cotton
// Xử lí hoàn thiện giúp bề mặt vải ít xù lông, mềm mịn và bền màu hơn
// Kiểu dệt Pique giúp áo thoáng mát
// Độ dày vải vừa phải giúp áo tôn dáng
// Phù hợp với đi làm, đi chơi
// Sản xuất tại Nhà máy Tessellation (TGV), Việt Nam.
// Người mẫu: 186cm - 77kg, mặc áo 2XL`,
//         basePrice: 299000.0,
//         salePrice: 259000.0
//     },
//
//     {
//         id: "29",
//         name: `Polo thể thao Promax`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]', imgSrc: "product29.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Poly, định lượng vải 155gsm siêu nhẹ
// Xử lý hoàn thiện vải: Quick-Dry và Wicking
// Phù hợp với: đi làm, đi chơi, mặc ở nhà
// Kiểu dáng: Regular fit dáng suông
// Người mẫu: 184 cm - 73 kg, mặc áo size XL
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 239000.0,
//         salePrice: 189000.0
//     },
//
//     {
//         id: "3",
//         name: `Áo polo phối khóa kéo unisex nam nữ`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]', imgSrc: "product3.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton dày dặn, thấm hút tốt, ít nhăn và dễ phối đồ`,
//         basePrice: 350000.0,
//         salePrice: 289000.0
//     },
//
//     {
//         id: "30",
//         name: `Jeans Copper Denim Slim Fit`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]', imgSrc: "product30.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 12 Oz / 99% Cotton - 1% Spandex
// Dáng Slim Fit: Dáng ôm tôn dáng, giúp bạn "hack"đôi chân dài và gọn đẹp
// Vải Denim được wash trước khi may nên không rút và hạn chế ra màu sau khi giặt
// Cảm giác khi chạm mịn màng
// Người mẫu: 175 cm - 69 kg, mặc áo XL, quần size 32
// Tự hào sản xuất tại Việt Nam
// Lưu ý:Sản phẩm vẫn sẽ bạc màu sau một thời gian dài sử dụng theo tính chất tự nhiên`,
//         basePrice: 599000.0,
//         salePrice: 529000.0
//     },
//
//     {
//         id: "31",
//         name: `Áo giữ nhiệt Modal Ultra`,
//         categoryId: "category01",
//         color: '["#ffffff", "#000000"]', imgSrc: "product31.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 50% Modal (gỗ sồi), 42% Cotton, 8% Spandex
// Ưu điểm của Cotton Modal: kháng khuẩn, giữ nhiệt, mềm mại và co giãn
// Kiểu dáng: Slimfit ôm nhẹ vào cơ thể
// Phù hợp với: mặc hàng ngày hoặc cũng có thể vận động thể thao
// Người mẫu: 1m77 - 69kg * Mặc áo size L
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 299000.0,
//         salePrice: 159000.0
//     },
//
//     {
//         id: "32",
//         name: `Shorts thể thao 5"Movement`,
//         categoryId: "category04",
//         color: '["#ffffff", "#000000"]', imgSrc: "product32.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Polyester kết hợp tính năng: Wicking (Thấm hút nhanh), 4-way Mechanical stretch (Co giãn 4 chiều) và Quick-Dry (Nhanh khô)
// Vải siêu nhẹ chỉ 106 gsm, mang lại cảm giác mặc như không mặc
// Vải được dệt kiểu Double Weaving và nhuộm bằng công nghệ Zero Water Discharge (Không nước thải) theo đuổi xu hướng yếu tố thời trang bền vững
// Phù hợp với: các hoạt động chạy nhẹ và tập gym
// Độ dài quần: 5 inch
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 199000.0,
//         salePrice: 99000.0
//     },
//     {
//         id: "33",
//         name: `Shorts thể thao 7"Movement`,
//         categoryId: "category04",
//         color: '["#ffffff", "#000000"]', imgSrc: "product33.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Polyester kết hợp tính năng: Wicking (Thấm hút nhanh), 4-way Mechanical stretch (Co giãn 4 chiều) và Quick-Dry (Nhanh khô)
// Vải siêu nhẹ chỉ 106 gsm, mang lại cảm giác mặc như không mặc
// Phù hợp với: mặc thể thao chạy nhẹ hoặc tập gym
// Vải được dệt kiểu Double Weaving và nhuộm bằng công nghệ Zero Water Discharge (Không nước thải) theo đuổi xu hướng yếu tố thời trang bền vững
// Độ dài quần: 7 inch
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 239000.0,
//         salePrice: 119000.0
//     },
//
//     {
//         id: "34",
//         name: `Quần Joggers Daily Wear`,
//         categoryId: "category11",
//         color: '["#ffffff", "#000000"]', imgSrc: "product34.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Polyester
// Công nghệ ứng dụng: Quần jogger nam Daily Wear ứng dụng HeiQ ViroBlock giúp ức chế và tiêu diệt vi khuẩn trên bề mặt vải
// Quần jogger thể thao hoàn thiện tính năng trượt nước và chống UV 99%
// Tính năng kháng nước của vải hiệu quả lên đến 30 lần giặt
// Tự hào sản xuất tại Việt Nam
// Người mẫu: 1m77 - 69kg, mặc quần size XL`,
//         basePrice: 299000.0,
//         salePrice: 269000.0
//     },
//
//     {
//         id: "35",
//         name: `Polo thể thao V1`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]', imgSrc: "product35.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Recycled Polyester
// Định lượng vải 130gsm siêu nhẹ
// Công nghệ ứng dụng: Wicking & Quick-Dry
// Kiểu dáng: áo Polo thể thao vừa vặn
// Nhà cung cấp vải lĩnh vực đồ thể thao hàng đầu: Promax
// Tự hào sản xuất tại Việt Nam
// Người mẫu: 184 cm - 73 kg, mặc áo size 2XL`,
//         basePrice: 249000.0,
//         salePrice: 199000.0
//     },
//
//     {
//         id: "36",
//         name: `Sơ mi dài tay Easycare`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]', imgSrc: "product36.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% sợi Polyester Nano-tech
// Chất liệu thấm hút tốt đem lại sự thoáng khí
// Không nhăn tự nhiên
// Xử lý chống tia tử ngoại, bảo vệ sức khoẻ
// Tự hào sản xuất tại Việt Nam
// Người mẫu: 1m77 - 74kg * Mặc size XL`,
//         basePrice: 450000.0,
//         salePrice: 199000.0
//     },
//
//     {
//         id: "37",
//         name: `Quần dài Kaki Excool`,
//         categoryId: "category09",
//         color: '["#ffffff", "#000000"]', imgSrc: "product37.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 43% Sợi Sorona + 57% Polyester co giãn
// Công nghệ Excool: Co giãn 4 chiều, Nhẹ, Thoáng khí, Chống tia UV SPF50+
// Phù hợp với: đi làm, đi chơi
// Kiểu dáng hơi ôm một chút
// Người mẫu: 175 cm - 69 kg, mặc quần size XL
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 499000.0,
//         salePrice: 429000.0
//     },
//
//     {
//         id: "38",
//         name: `Jeans Copper Denim Straight`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]', imgSrc: "product38.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% Cotton / 12 Oz
// Dáng Straight: Dáng suông phóng thoáng, thoải mái, không thùng thình
// Vải Denim được wash trước khi may nên không rút và hạn chế ra màu sau khi giặt
// Cảm giác khi chạm mịn màng
// Người mẫu: 175 cm - 69 kg, mặc áo XL, quần size 32
// Tự hào sản xuất tại Việt Nam
// Lưu ý: Sản phẩm vẫn sẽ bạc màu sau một thời gian dài sử dụng theo tính chất tự nhiên`,
//         basePrice: 599000.0,
//         salePrice: 529000.0
//     },
//
//     {
//         id: "39",
//         name: `Quần Joggers Excool`,
//         categoryId: "category11",
//         color: '["#ffffff", "#000000"]', imgSrc: "product39.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Thành phần: 100% Polyester
// Quần jogger co giãn 4 chiều giúp thoải mái vận động
// Ứng dụng công nghệ Excool thấm hút tốt, nhanh khô, thoáng khí
// Dáng quần Slim fit. Chọn cỡ thông thường hoặc lớn hơn một cỡ nếu thích mặc rộng
// Bo gấu quần jogger cùng chất liệu quần, độ bo vừa phải
// Quần có 4 túi, 2 túi xéo và 1 túi ẩn có khoá; 1 túi sau
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 279000.0,
//         salePrice: 249000.0
//     },
//
//     {
//         id: "4",
//         name: `Áo hoodie form rộng unisex nam nữ nỉ chân cua`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product4.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: mặt nền (100% coton), mặc trong (65/35 - 65% cotton và 35 CVC) có độ bền cao, không phai màu và mang lại cảm giác dễ chịu, mát mẻ cho người mặc`,
//         basePrice: 490000.0,
//         salePrice: 2590000.0
//     },
//     {
//         id: "40",
//         name: `Shorts chạy bộ 5"Power`,
//         categoryId: "category04",
//         color: '["#ffffff", "#000000"]', imgSrc: "product40.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 43% sợi Recycle, 57% Polyester, theo xu hướng thời trang bền vững
// Công nghệ ứng dụng: xử lý hoàn thiện vải Wicking (Thấm hút nhanh) và Mechanical stretch (Co giãn 2 chiều)
// Phù hợp với: chạy bộ, tập gym và các hoạt động thể thao khác nhau
// Độ dài quần: 5 inch
// Tự hào sản xuất tại Việt Nam`,
//         basePrice: 239000.0,
//         salePrice: 149000.0
//     },
//     {
//         id: "41",
//         name: `Áo sơ mi sọc`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product41.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo sơ mi cổ điển với tay áo dài và bản in sọc tương phản.Áo sơ mi nam Sandro, Dáng cổ điển, Sọc, Tay áo dài, Nút cài`,
//         basePrice: 516000.0,
//         salePrice: 456000.0
//     },
//
//     {
//         id: "42",
//         name: `Áo T-shirt together`,
//         categoryId: "category10",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product42.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo thun cotton oversized cổ tṛn, tay ngắn và được tô điểm bằng họa tiết Together trước ngực, Áo thun cotton nam Sandro, Dáng oversize, Cổ tṛn, Tay áo ngắn, Hoạ tiết together`,
//         basePrice: 297000.0,
//         salePrice: 266000.0
//     },
//
//     {
//         id: "43",
//         name: `Áo len dệt kim oversize`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product43.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo len dệt kim oversize với cổ cao rộng và tay áo dài., Áo len đan nam Sandro, Dáng oversize, Cổ cao rộng, Tay áo dài`,
//         basePrice: 516000.0,
//         salePrice: 465000.0
//     },
//
//     {
//         id: "44",
//         name: `Áo khoác denim`,
//         categoryId: "category01",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product44.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo khoác denim bạc màu có lớp lót hiệu ứng da cừu, có khuy bấm, tay áo dài và túi vá., Áo khoác denim nam Sandro, Lớp lót hiệu ứng da cừu tương phản, Cổ áo sơ mi, Tay áo dài, 4 túi vá, Áo jacron da dập nổi SANDRO`,
//         basePrice: 555000.0,
//         salePrice: 500000.0
//     },
//
//     {
//         id: "45",
//         name: `Quần short đính đá`,
//         categoryId: "category09",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product45.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần short pha len với các nếp gấp được ủi, tô điểm bằng những viên đá cùng tông màu., Quần short len, Đính đá cùng tông, Ủi các nếp, Túi xẻ hai bên`,
//         basePrice: 610000.0,
//         salePrice: 549000.0
//     },
//
//     {
//         id: "46",
//         name: `Quần jean ống rộng`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product46.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần jean denim oversize được tô điểm bằng các chi tiết đính đá giả cùng tông màu ở phía trước và phía sau., Quần jean ống rộng nữ Sandro, Denim thô, Đính đá, 5 túi`,
//         basePrice: 767000.0,
//         salePrice: 691000.0
//     },
//
//     {
//         id: "47",
//         name: `Áo thun organic cotton phối cổ họa tiết`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product47.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo thun cotton hữu cơ, với tay áo ngắn và cổ áo tṛn phối họa tiết, Áo thun cổ phối đá, Cổ áo tṛn với viền rhinestone, Tay áo ngắn, Thiết kế vừa vặn`,
//         basePrice: 454000.0,
//         salePrice: 409000.0
//     },
//
//     {
//         id: "48",
//         name: `Áo khoác len`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product48.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo khoác ngoài bằng len có cài cúc, tay áo dài có cổ tay cài cúc, túi nắp ở ngực và túi bên., Áo khoác len nam Sandro, Cài nút, Tay áo dài, 2 túi vá ngực`,
//         basePrice: 454000.0,
//         salePrice: 416000.0
//     },
//
//     {
//         id: "49",
//         name: `Quần ống loe`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product49.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần ống loe pha len có thắt lưng ở eo., Quần ống loe phối len nữ Sandro, Đai grosgrain, Ủi nếp`,
//         basePrice: 704000.0,
//         salePrice: 634000.0
//     },
//
//     {
//         id: "5",
//         name: `Áo hoodie zip form boxy unisex nỉ bông dày dặn`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]', imgSrc: "product5.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton mềm mại, dày dặn, ấm áp với thiết kế khoá kéo tiện lợi giúp dễ dàng khi mặc`,
//         basePrice: 300000.0,
//         salePrice: 250000.0
//     },
//
//     {
//         id: "50",
//         name: `Áo khoác sơ mi`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product50.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo khoác len có khóa kéo, cổ áo sơ mi và tay dài., Áo khoác len nam Sandro, Cổ áo sơ mi, Tay áo dài, Vá túi ngực, Cổ tay cài cúc`,
//         basePrice: 508000.0,
//         salePrice: 468000.0
//     },
//
//     {
//         id: "51",
//         name: `Quần short denim`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product51.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần short denim có eo co giăn và đường khâu nổi tương phản., Quần short denim nam của Sandro, Lưng thun, Đường khâu`,
//         basePrice: 578000.0,
//         salePrice: 521000.0
//     },
//
//     {
//         id: "52",
//         name: `Quần tây dáng đứng`,
//         categoryId: "category09",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product52.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần tây nam Sandro, Quần âu bằng vải nỉ, Kiểu cổ điển, Hai túi kiểu Ư ở phía trước và hai túi có ống đóng bằng nút ở phía sau`,
//         basePrice: 797000.0,
//         salePrice: 718000.0
//     },
//
//     {
//         id: "53",
//         name: `Quần short cotton`,
//         categoryId: "category09",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product53.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần short cotton với thắt lưng đàn hồi với túi dây rút và túi bên., Quần short cotton nam Sandro, Eo đàn hồi với dây, 2 túi, 2 túi nắp ở mặt sau`,
//         basePrice: 516000.0,
//         salePrice: 465000.0
//     },
//
//     {
//         id: "54",
//         name: `Áo polo logo rubber`,
//         categoryId: "category05",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product54.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần short cotton với thắt lưng đàn hồi với túi dây rút và túi bên., Quần short cotton nam Sandro, Eo đàn hồi với dây, 2 túi, 2 túi nắp ở mặt sau`,
//         basePrice: 359000.0,
//         salePrice: 324000.0
//     },
//
//     {
//         id: "55",
//         name: `Áo T-shirt oversize in logo Sandro`,
//         categoryId: "category02",
//         color: '["#ffffff", "#000000"]', imgSrc: "product55.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo T-shirt oversize in logo Sandro., Áo phông nam Sandro, Logo Sandro cao su ở mặt trước, Dáng oversize`,
//         basePrice: 297000.0,
//         salePrice: 268000.0
//     },
//
//     {
//         id: "56",
//         name: `Áo sơ mi Square Cross`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product56.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo sơ mi cài khuy bồng bềnh với tay áo dài, in h́nh Square Cross và sọc tương phản ở phía dưới., Áo sơ mi dáng suông nam Sandro, Tay dài có cổ tay cài khuy, Cài khuy phía trước, H́nh Square Cross`,
//         basePrice: 704000.0,
//         salePrice: 634000.0
//     },
//
//     {
//         id: "57",
//         name: `Quần jeans cotton`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]', imgSrc: "product57.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Quần jeans nam Sandro, Cotton jeans, 5 túi, Tag da Sandro phía sau, Thiêt kế ôm vừa`,
//         basePrice: 567000.0,
//         salePrice: 511000.0
//     },
//
//     {
//         id: "58",
//         name: `Áo suit len`,
//         categoryId: "category06",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product58.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo khoác suit len cổ điển có tay dài, cổ tay cài khuy, 2 khuy cài, lỗ thông hơi phía sau và túi có nắp., Áo khoác nam Sandro, Tay dài có cổ tay cài khuy, Cài 2 khuy, 2 túi có nắp, Lỗ thông hơi phía sau, Túi bên trong`,
//         basePrice: 1634000.0,
//         salePrice: 1471000.0
//     },
//
//     {
//         id: "59",
//         name: `Áo sơ mi denim`,
//         categoryId: "category07",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product59.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Áo sơ mi denim với cổ áo cổ điển, tay ngắn, cài khuy và có túi trước ngực., Áo sơ mi denim nam, Cổ áo sơ mi, Tay áo ngắn, Cúc bấm, Xẻ hai bên`,
//         basePrice: 767000.0,
//         salePrice: 691000.0
//     },
//
//     {
//         id: "6",
//         name: `Áo sweater unisex nam nữ basic nỉ chân cua`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]', imgSrc: "product6.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: 100% cotton dày dặn, mêm mịn, đứng form, ít nhăn, siêu ấm áp cho mùa gió lạnh về`,
//         basePrice: 350000.0,
//         salePrice: 288000.0
//     },
//
//     {
//         id: "60",
//         name: `Áo nỉ Square Cross`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product60.jpg",
//
//         size: '["x","xl","xxl"]',
//         desc: `Áo nỉ cotton cổ tṛn, tay dài, trang trí họa tiết Square Cross trước ngực., Áo nỉ nam Sandro, Cổ tṛn, Tay áo dài, Vá h́nh Square Cross`,
//         basePrice: 610000.0,
//         salePrice: 549000.0
//     },
//
//     {
//         id: "7",
//         name: `Áo sweater nam trơn basic vải da cá`,
//         categoryId: "category03",
//         color: '["#ffffff", "#000000"]',
//         imgSrc: "product7.jpg",
//         size: '["x","xl","xxl"]',
//         desc: ` Chất liệu: 100% cotton da cá dày dặn, mềm mịn co giãn 4 chiều thông thoáng, thấm hút tốt và không bị bạc màu sau thời gian dài sử dụng`,
//         basePrice: 250000.0,
//         salePrice: 200000.0
//     },
//
//     {
//         id: "8",
//         name: `Quần jean unisex nam nữ cạp cao ống suông rộng`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]', imgSrc: "product8.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: jeans (95% cotton, 5% spandex) không phai màu, thấm hút tốt và mềm mại, dễ phối đồ`,
//         basePrice: 290000.0,
//         salePrice: 260000.0
//     },
//
//     {
//         id: "9",
//         name: `Quần jean nam baggy cạp chun bản to ống rộng`,
//         categoryId: "category08",
//         color: '["#ffffff", "#000000"]', imgSrc: "product9.jpg",
//         size: '["x","xl","xxl"]',
//         desc: `Chất liệu: jeans (95% cotton, 5% spandex) không phai màu, thấm hút tốt và mềm mại, dễ phối đồ`,
//         basePrice: 290000.0,
//         salePrice: 200000.0
//     },
// ];
//
// var listUser = [
//     {
//         id: "ADMIN01",
//         username: "HieuNguyen",
//         password: "ChiHieuNguyen013",
//         fullName: "Nguyễn Chí Hiếu",
//         email: "HieuNguyen@gmail.com",
//         gender: "Nam",
//         phone: "0703637448",
//         address: "164 Ngô Tất Tố, Bình Định",
//         isVerify: "1",
//         birthDate: "1990-01-01",
//         role: "ADMIN",
//     },
//     {
//         id: "ADMIN02",
//         username: "TienDung",
//         password: "DungMai15062003",
//         fullName: "Mai Tiến Dũng",
//         email: "TienDung@gmail.com",
//         gender: "Nam",
//         phone: "0902346795",
//         address: "Xã Đức Long, Huyện Hoà An, Cao Bằng",
//         isVerify: "1",
//         birthDate: "1995-02-15",
//         role: "ADMIN",
//     },
//     {
//         id: "KH01",
//         username: "HoangVanLong",
//         password: "Long213",
//         fullName: "Hoàng Văn Long",
//         email: "longhoang@gmail.com",
//         gender: "Nam",
//         phone: "0905123456",
//         address: "Số 123 Đường Lê Lai, Phường Phạm Ngũ Lão, Quận 1, TP.HCM",
//         isVerify: "0",
//         birthDate: "1992-07-18",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH02",
//         username: "PhamThiHuyen",
//         password: "Huyen123",
//         fullName: "Phạm Thị Huyền",
//         email: "huyenpham@gmail.com",
//         gender: "Nữ",
//         phone: "0978223344",
//         address: "Số 456 Đường Lê Thánh Tôn, Phường Bến Nghé, Quận 1, TP.HCM",
//         isVerify: "0",
//         birthDate: "1987-09-30",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH03",
//         username: "NguyPhuong",
//         password: "DongPhuong909",
//         fullName: "Ngụy Ðông Phương",
//         email: "DongPhuong@gmail.com",
//         gender: "Nữ",
//         phone: "0783748159",
//         address: "Xã Nghĩa Hưng, Huyện Chư Păh, Gia Lai",
//         isVerify: "1",
//         birthDate: "1988-07-20",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH04",
//         username: "ThuyNuonglunglinh",
//         password: "ThuyNuong444",
//         fullName: "Liễu Thụy Nương",
//         email: "LieuNuong4@gmail.com",
//         gender: "Nữ",
//         phone: "0769506724",
//         address: "Xã Vĩnh Phúc, Huyện Vĩnh Lộc, Thanh Hóa",
//         isVerify: "1",
//         birthDate: "1992-12-10",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH05",
//         username: "HuuDat",
//         password: "HuuDat09700",
//         fullName: "Cáp Hữu Ðạt",
//         email: "HuuDat@gmail.com",
//         gender: "Nam",
//         phone: "0328012964",
//         address: "Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang",
//         isVerify: "1",
//         birthDate: "1999-05-30",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH06",
//         username: "CongHieu",
//         password: "CongHieu11",
//         fullName: "Nhan Công Hiếu",
//         email: "CongHieu6@gmail.com",
//         gender: "Nam",
//         phone: "0325976083",
//         address: "Xã Đắk Choong, Huyện Đắk Glei, Kon Tum",
//         isVerify: "1",
//         birthDate: "1985-03-25",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH07",
//         username: "DragonFruit",
//         password: "ThanhLongngon1",
//         fullName: "Hầu Thanh Long",
//         email: "ThanhLong@egmail.com",
//         gender: "Nam",
//         phone: "0707405391",
//         address: "Xã An Nông, Huyện Tịnh Biên, An Giang",
//         isVerify: "1",
//         birthDate: "1998-09-05",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH08",
//         username: "HamNoThi",
//         password: "HoThiNo",
//         fullName: "Thi Hồ Nam",
//         email: "HoNam8@gmail.com",
//         gender: "Nữ",
//         phone: "0886819054",
//         address: "Xã Tham Đôn, Huyện Mỹ Xuyên, Sóc Trăng",
//         isVerify: "1",
//         birthDate: "1994-04-17",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH09",
//         username: "KimLan",
//         password: "KangLimChiuChiu213",
//         fullName: "Thục Kim Lan",
//         email: "KimLan@gmail.com",
//         gender: "Nữ",
//         phone: "0797204681",
//         address: "Phường Thốt Nốt, Quận Thốt Nốt, Cần Thơ",
//         isVerify: "1",
//         birthDate: "1991-11-12",
//         role: "KHÁCH HÀNG",
//     },
//     {
//         id: "KH10",
//         username: "XuanNinh",
//         password: "XuanNinh995",
//         fullName: "Quản Xuân Ninh",
//         email: "XuanNinh@gmail.com",
//         gender: "Nam",
//         phone: "039 270 3698",
//         address: "Thị trấn Vĩnh Thạnh, Huyện Vĩnh Thạnh, Bình Định",
//         isVerify: "1",
//         birthDate: "1997-08-08",
//         role: "KHÁCH HÀNG",
//     },
// ]
//
//
// var productDetail = {
//     id: "",
//     name: `Sơ mi dài tay Café-DriS`,
//     basePrice: 499000,
//     salePrice: 429000,
//     parameter: JSON.parse(getCategory("category06").ruleSize),
//     desc: "Chất liệu: 50% S.Café + 50% Recycled PET\n" +
//         "Phù hợp với: đi làm, đi chơi\n" +
//         "Kiểu dáng: Regular fit dáng suông\n" +
//         "Người mẫu: 177 cm - 74 kg, mặc size XL\n" +
//         "Tự hào sản xuất tại Việt Nam",
//     size: ["M", "L", "XL", "XXL"],
//     reviews: [
//         {
//             "nameUser": getUser("KH01").fullName,
//             "star": 4,
//             "comment": "Sản phẩm tốt, nhưng có một chút vấn đề về kích thước. Tôi sẽ cân nhắc trong lần tiếp theo.",
//             "date": "2023-10-31",
//             avatar: "user_avatar-1.jpg"
//         }
//         , {
//             "nameUser": getUser("KH02").fullName,
//             "star": 4,
//             "comment": "Đồ áo này rất thời trang và chất lượng tốt. Chất liệu mềm mịn, cắt may tinh tế, và màu sắc tươi sáng. Tôi rất hài lòng với sản phẩm này!",
//             "date": "2023-10-30",
//             avatar: "user_avatar-2.jpg"
//         }, {
//             "nameUser": getUser("KH07").fullName,
//             "star": 1,
//             "comment": "Áo sơ mi này không đúng kích thước và chất lượng kém. Tôi thất vọng.",
//             "date": "2023-10-29",
//             avatar: "user_avatar-3.jpg"
//         }
//
//         , {
//             "nameUser": getUser("KH03").fullName,
//             "star": 4,
//             "comment": "Sản phẩm đáng giá tiền. Rất hài lòng với chất lượng và dịch vụ khách hàng.",
//             "date": "2023-10-28",
//             avatar: "user_avatar-4.jpg"
//         }
//         , {
//             "nameUser": getUser("KH04").fullName,
//             "star": 5,
//             "comment": "Bộ váy này thật sự đẹp và phù hợp cho dịp đặc biệt. Tôi rất hài lòng.",
//             "date": "2023-10-27",
//             avatar: "user_avatar-5.jpg"
//         }
//
//     ],
//     srcImg: "product25.jpg",
//     srcList: ["product25_1.jpg", "product25_2.jpg", "product25_3.jpg"],
// }
//
// function getUser(id) {
//     for (let i = 0; i < listUser.length; i++) {
//         if (listUser[i].id == id)
//             return listUser[i];
//     }
//     return null;
// }
//
// function getCategory(id) {
//     for (let i = 0; i < listCategories.length; i++) {
//         if (listCategories[i].id == id)
//             return listCategories[i];
//     }
//     return null;
// }
//
// function getProduct(id) {
//     for (let i = 0; i < listProduct.length; i++) {
//         if (listProduct[i].id == id)
//             return listProduct[i];
//     }
//     return null;
// }
//
// var productReview = {
//     productId: "21",
//     color: "#3d3d3d",
//     size: `[
//     {"name": "Dài áo", "value": 68, "unit": "cm"},
//     {"name": "Ngang ngực", "value": 45, "unit": "cm"},
//     {"name": "Dài tay", "value": 70, "unit": "cm"},
//     {"name": "Rộng vai", "value": 50, "unit": "cm"}
//     ]`,
//     quantity: "2",
// }
//
// const listOrders = [
//     {
//         id: "1",
//         userId: "5",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Ngụy Ðông Phương",
//         email: "DongPhuong@gmail.com",
//         phone: "0783748159",
//         address: "Xã Nghĩa Hưng, Huyện Chư Păh, Gia Lai",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐANG CHUẨN BỊ",
//         voucherId: "2"
//     },
//     {
//         id: "2",
//         userId: "7",
//         dateOrder: "2022-10-29",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Cáp Hữu Ðạt",
//         email: "HuuDat@gmail.com",
//         phone: "0328012964",
//         address: "Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang",
//         statusOrder: "CHƯA XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "3"
//     },
//     {
//         id: "3",
//         userId: "4",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Phạm Thị Huyền",
//         email: "huyenpham@gmail.com",
//         phone: "0978223344",
//         address: "Số 456 Đường Lê Thánh Tôn, Phường Bến Nghé, Quận 1, TP.HCM",
//         statusOrder: "CHƯA XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "4"
//     },
//     {
//         id: "4",
//         userId: "3",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Hoàng Văn Long",
//         email: "longhoang@gmail.com",
//         phone: "0905123456",
//         address: "Số 123 Đường Lê Lai, Phường Phạm Ngũ Lão, Quận 1, TP.HCM', b'0', '1992-07-18",
//         statusOrder: "HOÀN THÀNH",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "7"
//     },
//     {
//         id: "5",
//         userId: "6",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Liễu Thụy Nương",
//         email: "LieuNuong4@gmail.com",
//         phone: "0769506724",
//         address: "Xã Vĩnh Phúc, Huyện Vĩnh Lộc, Thanh Hóa",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐANG CHUẨN BỊ",
//         voucherId: "6"
//     },
//     {
//         id: "6",
//         userId: "8",
//         dateOrder: "2022-10-29",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Nhan Công Hiếu",
//         email: "CongHieu6@gmail.com",
//         phone: "0325976083",
//         address: "Xã Đắk Choong, Huyện Đắk Glei, Kon Tum",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ CHUẨN BỊ",
//         voucherId: "8"
//     },
//     {
//         id: "7",
//         userId: "10",
//         dateOrder: "2022-10-29",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Thi Hồ Nam",
//         email: "HoNam8@gmail.com",
//         phone: "0886819054",
//         address: "Xã Tham Đôn, Huyện Mỹ Xuyên, Sóc Trăng",
//         statusOrder: "HOÀN THÀNH",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "1"
//     },
//     {
//         id: "8",
//         userId: "9",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Hầu Thanh Long",
//         email: "ThanhLong@egmail.com",
//         phone: "0707405391",
//         address: "Xã An Nông, Huyện Tịnh Biên, An Giang",
//         statusOrder: "HOÀN THÀNH",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "9"
//     },
//     {
//         id: "9",
//         userId: "12",
//         dateOrder: "2022-10-29",
//         paymentMethod: "VÍ ĐIỆN TỬ",
//         fullName: "Quản Xuân Ninh",
//         email: "XuanNinh@gmail.com",
//         phone: "0392703698",
//         address: "Thị trấn Vĩnh Thạnh, Huyện Vĩnh Thạnh, Bình Định",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THÀNH TOÁN",
//         statusDelivery: "ĐANG CHUẨN BỊ",
//         voucherId: "10"
//     },
//     {
//         id: "10",
//         userId: "11",
//         dateOrder: "2022-10-29",
//         paymentMethod: "VÍ ĐIỆN TỬ",
//         fullName: "Thục Kim Lan",
//         email: "KimLan@gmail.com",
//         phone: "0797204681",
//         address: "Phường Thốt Nốt, Quận Thốt Nốt, Cần Thơ",
//         statusOrder: "HOÀN THÀNH",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "7"
//     },
//     {
//         id: "11",
//         userId: "7",
//         dateOrder: "2022-10-29",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Nguyễn Chí Hiếu",
//         email: "HieuNguyen@gmail.com",
//         phone: "0902346795",
//         address: "Xã Đức Long, Huyện Hoà An, Cao Bằng",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "0"
//     },
//     {
//         id: "12",
//         userId: "2",
//         dateOrder: "2022-10-30",
//         paymentMethod: "VÍ ĐIỆN TỬ",
//         fullName: "Trần Thị An",
//         email: "anTran@yahoo.com",
//         phone: "0901234567",
//         address: "Phường Hòa Khánh, Quận Liên Chiểu, Đà Nẵng",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐANG CHUẨN BỊ",
//         voucherId: "0"
//     },
//     {
//         id: "13",
//         userId: "3",
//         dateOrder: "2022-10-31",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Lê Văn Nam",
//         email: "NamLe@gmail.com",
//         phone: "0909876543",
//         address: "Phường Bình Hòa, Thị xã Thuận An, Bình Dương",
//         statusOrder: "CHƯA XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "2"
//     },
//     {
//         id: "14",
//         userId: "4",
//         dateOrder: "2022-11-01",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Nguyễn Thị Linh",
//         email: "linhNguyen@gmail.com",
//         phone: "0908765432",
//         address: "Phường Tây Sơn, Quận Đống Đa, Hà Nội",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "5"
//     },
//     {
//         id: "15",
//         userId: "5",
//         dateOrder: "2022-11-02",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Cáp Hữu Ðạt",
//         email: "HuuDat@gmail.com",
//         phone: "0328012964",
//         address: "Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "0"
//     },
//     {
//         id: "16",
//         userId: "6",
//         dateOrder: "2022-11-03",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Nhan Công Hiếu",
//         email: "CongHieu6@gmail.com",
//         phone: "0325976083",
//         address: "Xã Đắk Choong, Huyện Đắk Glei, Kon Tum",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐANG GIAO",
//         voucherId: "1"
//     },
//     {
//         id: "17",
//         userId: "6",
//         dateOrder: "2022-11-04",
//         paymentMethod: "TIỀN MẶT",
//         fullName: "Mai Thị Thu",
//         email: "ThuMai@gmail.com",
//         phone: "0909876123",
//         address: "Phường Hòa An, Quận Cẩm Lệ, Đà Nẵng",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐANG GIAO",
//         voucherId: "0"
//     },
//     {
//         id: "18",
//         userId: "8",
//         dateOrder: "2022-11-05",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Thi Hồ Nam",
//         email: "HoNam8@gmail.com",
//         phone: "0886819054",
//         address: "Xã Tham Đôn, Huyện Mỹ Xuyên, Sóc Trăng",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "CHƯA THANH TOÁN",
//         statusDelivery: "ĐƠN ĐÃ ĐẶT",
//         voucherId: "8"
//     },
//     {
//         id: "19",
//         userId: "8",
//         dateOrder: "2022-11-06",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Lê Thị Hà",
//         email: "HaLe@gmail.com",
//         phone: "0908887777",
//         address: "Phường Tây Sơn, Quận Thanh Khê, Đà Nẵng",
//         statusOrder: "HOÀN THÀNH",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐÃ GIAO",
//         voucherId: "0"
//     },
//     {
//         id: "20",
//         userId: "10",
//         dateOrder: "2022-11-06",
//         paymentMethod: "CHUYỂN KHOẢN NGÂN HÀNG",
//         fullName: "Phùng Thanh Nhàn",
//         email: "nhanPhung@gmail.com",
//         phone: "0907722801",
//         address: "80/50, khu phố Bình Đường 2, An Bình, Dĩ An, Bình Dương",
//         statusOrder: "ĐÃ XÁC NHẬN",
//         statusTransaction: "ĐÃ THANH TOÁN",
//         statusDelivery: "ĐANG GIAO",
//         voucherId: "0"
//     }
// ]
//
// const listVouchers = [
//     {
//         id: "1",
//         code: "MGG25",
//         description: `Bạn được giảm giá 25.000 ₫ khi áp dụng mã`,
//         minimumPrice: 200000,
//         discountPrice: 25000
//     },
//     {
//         id: "2",
//         code: "MGG35",
//         description: `Bạn được giảm giá 35.000 ₫ khi áp dụng mã`,
//         minimumPrice: 250000,
//         discountPrice: 35000
//     },
//     {
//         id: "3",
//         code: "MGG50",
//         description: `Bạn được giảm giá 50.000 ₫ khi áp dụng mã`,
//         minimumPrice: 400000,
//         discountPrice: 50000
//     },
//     {
//         id: "4",
//         code: "MGG70",
//         description: `Bạn được giảm giá 70.000 ₫ khi áp dụng mã`,
//         minimumPrice: 550000,
//         discountPrice: 70000
//     },
//     {
//         id: "5",
//         code: "MGG100",
//         description: `Bạn được giảm giá 100.000 ₫ khi áp dụng mã`,
//         minimumPrice: 700000,
//         discountPrice: 100000
//     },
//     {
//         id: "6",
//         code: "MGG150",
//         description: `Bạn được giảm giá 150.000 ₫ khi áp dụng mã`,
//         minimumPrice: 850000,
//         discountPrice: 150000
//     },
//     {
//         id: "7",
//         code: "MGG200",
//         description: `Bạn được giảm giá 200.000 ₫ khi áp dụng mã`,
//         minimumPrice: 1000000,
//         discountPrice: 200000
//     },
//     {
//         id: "8",
//         code: "MGG300",
//         description: `Bạn được giảm giá 300.000 ₫ khi áp dụng mã`,
//         minimumPrice: 1500000,
//         discountPrice: 300000
//     },
//     {
//         id: "9",
//         code: "MGG350",
//         description: `Bạn được giảm giá 350.000 ₫ khi áp dụng mã`,
//         minimumPrice: 2000000,
//         discountPrice: 350000
//     },
//     {
//         id: "10",
//         code: "MGG400",
//         description: `Bạn được giảm giá 400.000 ₫ khi áp dụng mã`,
//         minimumPrice: 2500000,
//         discountPrice: 400000
//     }
// ];
