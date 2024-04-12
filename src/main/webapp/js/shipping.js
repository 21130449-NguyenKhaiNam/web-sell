const URL_ROOT = "https://online-gateway.ghn.vn/shiip/public-api/";
const URL_PROVINCE = URL_ROOT + "master-data/province";
const URL_DISTRICT = URL_ROOT + "master-data/district";
const URL_WARD = URL_ROOT + "master-data/ward";
const CALCULATE_LEAD_DAY = URL_ROOT + "v2/shipping-order/leadtime";
const CALCULATE_SHIPPING = URL_ROOT + "v2/shipping-order/fee";
const TOKEN = '015940b9-e810-11ee-b290-0e922fc774da';
// Chuyển phát thương mại điện tử
const SERVICE_ID = 53320;
const SERVICE_TYPE_ID = 2;
const addressShop = {
    province: "Hồ Chí Minh",
    district: "Quận 1",
    ward: "Phường Bến Nghé",
    detail: "123 Nguyễn Huệ"
}
let addressShopAPI, addressCustomerAPI;

async function getAddressShopAPI() {
    const provinceId = await getProvinceId(addressShop.province);
    const districtId = await getDistrictId(provinceId, addressShop.district);
    const wardCode = await getWardCode(districtId, addressShop.ward);
    addressShopAPI = {
        provinceId: provinceId,
        districtId: districtId,
        wardCode: wardCode
    };
}

async function getAddressCustomerAPI(address) {
    const provinceId = await getProvinceId(address.province);
    const districtId = await getDistrictId(provinceId, address.district);
    const wardCode = await getWardCode(districtId, address.ward);
    addressCustomerAPI = {
        provinceId: provinceId,
        districtId: districtId,
        wardCode: wardCode
    };
}

export async function getFeeAndLeadTime(addressCustomer) {
    try {
        const addressShopAPI = await getAddressShopAPI();
        const addressCustomerAPI = await getAddressCustomerAPI(addressCustomer);

        const feeShipping = await getFeeShipping(); // Assuming this function returns a value synchronously
        const leadDate = await getLeadDate(); // Assuming this function returns a value synchronously

        return {
            feeShipping: feeShipping,
            leadDate: leadDate
        };
    } catch (error) {
        console.error(error);
        throw error;
    }
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
        return data.data.find(province => province.NameExtension.includes(provinceName) ).ProvinceID;
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

function getFeeShipping(province, district, ward, detail) {
    return callAPI(CALCULATE_SHIPPING, {
        from_province_id: addressShopAPI.provinceId,
        from_district_id: addressShopAPI.districtId,
        from_ward_code: addressShopAPI.wardCode,
        to_province_id: addressCustomerAPI.provinceId,
        to_district_id: addressCustomerAPI.districtId,
        to_ward_code: addressCustomerAPI.wardCode,
        service_type_id: SERVICE_TYPE_ID,
        weight: 500,
    }).then(data => {
        return data.data.total;
    });
}

function getLeadDate(province, district, ward, detail) {
    return callAPI(CALCULATE_LEAD_DAY, {
        from_province_id: addressShopAPI.provinceId,
        from_district_id: addressShopAPI.districtId,
        from_ward_code: addressShopAPI.wardCode,
        to_province_id: addressCustomerAPI.provinceId,
        to_district_id: addressCustomerAPI.districtId,
        to_ward_code: addressCustomerAPI.wardCode,
        service_id: SERVICE_ID,
    }).then(data => {
        return data.data.leadtime;
    });
}