const URL_ROOT = "https://online-gateway.ghn.vn/shiip/public-api/";
const URL_PROVINCE = URL_ROOT + "master-data/province";
const URL_DISTRICT = URL_ROOT + "master-data/district";
const URL_WARD = URL_ROOT + "master-data/ward";
const CALCULATE_LEAD_DAY = URL_ROOT + "v2/shipping-order/leadtime";
const CALCULATE_SHIPPING = URL_ROOT + "v2/shipping-order/fee";
const TOKEN = '015940b9-e810-11ee-b290-0e922fc774da';
const SHOP_ID = '4969655';
// Chuyển phát thương mại điện tử
const SERVICE_ID = 53320;
const SERVICE_TYPE_ID = 2;
const addressShop = {
    province: "Hồ Chí Minh",
    district: "Quận 1",
    ward: "Phường Bến Nghé",
    detail: "123 Nguyễn Huệ"
}

async function getAddressCode(address) {
    let province, district;
    return getProvinceId(address.province)
        .then(provinceId => {
            province = provinceId;
            console.log(provinceId, address.district)
            return getDistrictId(provinceId, address.district)
        })
        .then(districtId => {
            district = districtId;
            console.log(districtId, address.ward)
            return getWardCode(districtId, address.ward)
        })
        .then(wardCode => {
            console.log(wardCode)
            console.log(province, district, wardCode)
            return {
                provinceId: province,
                districtId: district,
                wardCode: wardCode
            };
        });
}

async function getFeeAndLeadTime(addressCustomer) {
    // try {
    const addressShopAPI = await getAddressCode(addressShop);
    const addressCustomerAPI = await getAddressCode(addressCustomer);

    const feeShipping = await getFeeShipping(addressShopAPI, addressCustomerAPI);
    const leadDate = await getLeadDate(addressShopAPI, addressCustomerAPI); // Assuming this function returns a value synchronously

    return {
        feeShipping: feeShipping,
        leadDate: leadDate
    };
    // } catch (error) {
    //     console.error(error);
    //     throw error;
    // }
}


async function callAPI(url, param) {
    const queryParams = new URLSearchParams(param);
    const apiUrl = `${url}?${queryParams}`;
    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'token': TOKEN,
                'shop_id': SHOP_ID,
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

function getProvinceId(provinceName) {
    return callAPI(URL_PROVINCE).then(data => {
        return data.data.find(province => province.NameExtension.includes(provinceName)).ProvinceID;
    });
}

function getDistrictId(provinceId, districtName) {
    return callAPI(URL_DISTRICT, {
        province_id: provinceId
    }).then(data => {
        return data.data.find(district => district.NameExtension.includes(districtName)).DistrictID;
    });
}

function getWardCode(districtId, wardName) {
    return callAPI(URL_WARD, {
        district_id: districtId
    }).then(data => {
        return data.data.find(ward => ward.NameExtension.includes(wardName)).WardCode;
    });
}

async function getFeeShipping(addressFrom, addressTo) {
    return callAPI(CALCULATE_SHIPPING, {
        from_province_id: addressFrom.provinceId,
        from_district_id: addressFrom.districtId,
        from_ward_code: addressFrom.wardCode,
        to_province_id: addressTo.provinceId,
        to_district_id: addressTo.districtId,
        to_ward_code: addressTo.wardCode,
        service_type_id: SERVICE_TYPE_ID,
        weight: 500,
    }).then(data => {
        return data.data.total;
    });
}

async function getLeadDate(addressFrom, addressTo) {
    return callAPI(CALCULATE_LEAD_DAY, {
        from_province_id: addressFrom.provinceId,
        from_district_id: addressFrom.districtId,
        from_ward_code: addressFrom.wardCode,
        to_province_id: addressTo.provinceId,
        to_district_id: addressTo.districtId,
        to_ward_code: addressTo.wardCode,
        service_id: SERVICE_ID,
    }).then(data => {
        return data.data.leadtime;
    });
}

async function getProvince() {
    return callAPI(URL_PROVINCE,).then(res => {
        return res.data.map(item => {
            return {
                id: item.ProvinceID,
                text: item.ProvinceName
            }
        });
    });
}

async function getDistrict(provinceId) {
    return callAPI(URL_DISTRICT, {
        province_id: provinceId
    }).then(res => {
        return res.data.map(item => {
            return {
                id: item.DistrictID,
                text: item.DistrictName
            }
        });
    });
}

async function getWard(districtId) {
    return callAPI(URL_WARD, {
        district_id: districtId
    }).then(res => {
        return res.data.map(item => {
            return {
                id: item.WardCode,
                text: item.WardName
            }
        });
    });
}

export {
    getProvinceId,
    getDistrictId,
    getWardCode,
    getFeeAndLeadTime,
    getProvince,
    getDistrict,
    getWard,
}