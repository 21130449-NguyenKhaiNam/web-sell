$(document).ready(function () {
    var URL = "https://online-gateway.ghn.vn/shiip/public-api/master-data/"
    let provinceId, districtId;
    const inputProvince = $("#inputProvince");
    const inputDistrict = $("#inputDistrict");
    const inputWard = $("#inputWard");

    async function callAjax(param) {
        try {
            const response = await fetch(`${URL}${param}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'token': '015940b9-e810-11ee-b290-0e922fc774da'
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

    setupSelect2();

    function setupSelect2() {
        inputProvince.select2({
            placeholder: 'Chọn tỉnh/thành phố',
            data: [],
        });

        inputDistrict.select2({
            placeholder: 'Chọn quận/huyện',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn tỉnh/thành phố trước";
                }
            },
        });

        inputWard.select2({
            placeholder: 'Chọn phường/xã',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn quận/huyện trước";
                }
            },
        });
        getProvinces();
        addEvent();
    }

    function addEvent() {
        inputProvince.on('select2:select', (e) => {
            provinceId = e.params.data.id;
            loadData();
        });
        inputDistrict.on('select2:select', (e) => {
            districtId = e.params.data.id;
            loadData()
        })
    }

    function loadData() {
        if (provinceId) {
            getDistricts(provinceId);
        }
        if (districtId) {
            getWards(districtId);
        }
        if (provinceId && districtId) {
            getWards(districtId);
        }
    }

    function getProvinces() {
        callAjax("province").then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.ProvinceID,
                    text: item.ProvinceName
                }
            });
            inputProvince.empty()
            inputProvince.select2({
                placeholder: 'Chọn tỉnh/thành phố',
                data: data,
            });
        });
    }


    function getDistricts(provinceId) {
        callAjax(`district?province_id=${provinceId}`).then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.DistrictID,
                    text: item.DistrictName
                }
            });
            inputDistrict.empty();
            inputDistrict.select2({
                placeholder: 'Chọn quận/huyện',
                data: data,
                language: {
                    "noResults": function () {
                        return "Vui lòng chọn tỉnh/thành phố trước";
                    }
                },
            });
        });
    }


    function getWards(districtId) {
        callAjax(`ward?district_id=${districtId}`).then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.WardCode,
                    text: item.WardName,
                }
            });
            inputWard.empty();
            inputWard.select2({
                placeholder: 'Chọn phường/xã',
                data: data,
                language: {
                    "noResults": function () {
                        return "Vui lòng chọn quận/huyện trước";
                    }
                },
            });
        });
    }

    $("#inputDate").datepicker({
        dateFormat: 'dd-mm-yy', // Set the date format as needed
        onSelect: function (dateText) {
            // Optionally, you can perform some action when a date is selected
        }
    });

})