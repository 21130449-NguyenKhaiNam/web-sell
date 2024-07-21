// var nameTop5 = [];
// top5NameProduct = top5NameProduct.slice(1, -1);
//
// nameTop5 = top5NameProduct.split(',').map(function (item) {
//     return item.trim();
// });
//
//
// var quantityTop5 = [];
// quantityTop5 = top5QuantityProduct
//     .slice(1, -1)
//     .split(',')
//     .map(function (item) {
//         var num = parseInt(item.trim(), 10);
//         return isNaN(num) ? 0 : num;
//     });
//
// console.log(quantityTop5);
//
//
// const barChartOptions = {
//     series: [
//         {
//             data: quantityTop5,
//             name: 'Đã bán',
//         },
//     ],
//     chart: {
//         type: 'bar',
//         background: 'transparent',
//         height: 350,
//         toolbar: {
//             show: false,
//         },
//     },
//     colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3'],
//     plotOptions: {
//         bar: {
//             distributed: true,
//             borderRadius: 4,
//             horizontal: false,
//             columnWidth: '40%',
//         },
//     },
//     dataLabels: {
//         enabled: false,
//     },
//     fill: {
//         opacity: 1,
//     },
//     grid: {
//         borderColor: '#55596e',
//         yaxis: {
//             lines: {
//                 show: true,
//             },
//         },
//         xaxis: {
//             lines: {
//                 show: true,
//             },
//         },
//     },
//     legend: {
//         labels: {
//             colors: '#f5f7ff',
//         },
//         show: true,
//         position: 'top',
//     },
//     stroke: {
//         colors: ['transparent'],
//         show: true,
//         width: 2,
//     },
//     tooltip: {
//         shared: true,
//         intersect: false,
//         theme: 'dark',
//     },
//     xaxis: {
//         categories: nameTop5,
//         title: {
//             style: {
//                 color: '#f5f7ff',
//             },
//         },
//         axisBorder: {
//             show: true,
//             color: '#55596e',
//         },
//         axisTicks: {
//             show: true,
//             color: '#55596e',
//         },
//         labels: {
//             style: {
//                 colors: '#f5f7ff',
//             },
//             formatter: function (value) {
//                 value = value.replace("[", "").replace("]", "");
//
//                 const maxLength = 10;
//                 if (value.length > maxLength) {
//                     return value.substring(0, maxLength - 3) + '...';
//                 }
//                 return value;
//             },
//         },
//         tooltip: {
//             enabled: false,
//         },
//     },
//     yaxis: {
//         title: {
//             text: 'Số lượng đã bán',
//             style: {
//                 color: '#f5f7ff',
//             },
//         },
//         axisBorder: {
//             color: '#55596e',
//             show: true,
//         },
//         axisTicks: {
//             color: '#55596e',
//             show: true,
//         },
//         labels: {
//             style: {
//                 colors: '#f5f7ff',
//             },
//         },
//     },
// };
// const barChart = new ApexCharts(
//     document.querySelector('#bar__chart'),
//     barChartOptions
// );
// barChart.render();
//
//
// var totalQuanByMonth = [];
// totalQuanByMonth = totalQuantityByMonth
//     .slice(1, -1)
//     .split(',')
//     .map(function (item) {
//         var num = parseInt(item.trim(), 10);
//         return isNaN(num) ? 0 : num;
//     });
//
// console.log(totalQuanByMonth);
//
// var totalVenueByMonth = totalRevenueByMonth
//     .toString()  // Chuyển mảng thành chuỗi
//     .slice(1, -1) // Loại bỏ ký tự [ và ] từ chuỗi
//     .split(',')  // Tách chuỗi thành mảng bằng dấu phẩy
//     .map(function (item) {
//         var num = parseFloat(item.trim().replace(/E/i, 'e'));
//         return isNaN(num) ? 0 : num;
//     });
//
// console.log(totalVenueByMonth);
//
//
// // AREA CHART
// const areaChartOptions = {
//     series: [
//         {
//             name: 'Tổng sản phẩm bán được',
//             data: totalQuanByMonth,
//         },
//         {
//             name: 'Tổng doanh thu',
//             data: totalVenueByMonth,
//         },
//     ],
//     chart: {
//         type: 'area',
//         background: 'transparent',
//         height: 350,
//         stacked: false,
//         toolbar: {
//             show: false,
//         },
//     },
//     colors: ['#00ab57', '#d50000'],
//     labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
//     dataLabels: {
//         enabled: false,
//     },
//     fill: {
//         gradient: {
//             opacityFrom: 0.4,
//             opacityTo: 0.1,
//             shadeIntensity: 1,
//             stops: [0, 100],
//             type: 'vertical',
//         },
//         type: 'gradient',
//     },
//     grid: {
//         borderColor: '#55596e',
//         yaxis: {
//             lines: {
//                 show: true,
//             },
//         },
//         xaxis: {
//             lines: {
//                 show: true,
//             },
//         },
//     },
//     legend: {
//         labels: {
//             colors: '#f5f7ff',
//         },
//         show: true,
//         position: 'top',
//     },
//     markers: {
//         size: 6,
//         strokeColors: '#1b2635',
//         strokeWidth: 3,
//     },
//     stroke: {
//         curve: 'smooth',
//     },
//     xaxis: {
//         axisBorder: {
//             color: '#55596e',
//             show: true,
//         },
//         axisTicks: {
//             color: '#55596e',
//             show: true,
//         },
//         labels: {
//             offsetY: 5,
//             style: {
//                 colors: '#f5f7ff',
//             },
//         },
//     },
//     yaxis: [
//         {
//             title: {
//                 text: 'Tổng sản phẩm bán được',
//                 style: {
//                     color: '#f5f7ff',
//                 },
//             },
//             labels: {
//                 style: {
//                     colors: ['#f5f7ff'],
//                 },
//             },
//         },
//         {
//             opposite: true,
//             title: {
//                 text: 'Tổng doanh thu',
//                 style: {
//                     color: '#f5f7ff',
//                 },
//             },
//             labels: {
//                 style: {
//                     colors: ['#f5f7ff'],
//                 },
//             },
//         },
//     ],
//     tooltip: {
//         shared: true,
//         intersect: false,
//         theme: 'dark',
//     },
// };
//
// const areaChart = new ApexCharts(
//     document.querySelector('#area__chart'),
//     areaChartOptions
// );
// areaChart.render();

import {formatCurrency, http} from "../base.js";

$(document).ready(function () {
    const monthInput = $('#month');
    const yearInput = $('#year');
    const formFilter = $("#form-filter");
    const revenue = $('#revenue');
    const orderSuccess = $('#order-success');
    const orderFailed = $('#order-failed');
    let productsPopularChart, productsNotPopularChart
    const current = new Date();
    init();

    function init() {
        initMonthAndYear();
        initChart();
        handleChartUpdate(current.getMonth() + 1, current.getFullYear());
        handleFormFilter();
    }

    function handleFormFilter() {
        formFilter.on('submit', function (e) {
            e.preventDefault();
            const month = monthInput.val();
            const year = yearInput.val();
            if (month && year) {
                handleChartUpdate(month, year);
                return;
            }
            Swal.fire({
                icon: 'warning',
                title: 'Vui lòng chọn tháng và năm',
            });
        });
    }

    function initMonthAndYear() {
        const months = [
            {value: 1, text: "Tháng 1"},
            {value: 2, text: "Tháng 2"},
            {value: 3, text: "Tháng 3"},
            {value: 4, text: "Tháng 4"},
            {value: 5, text: "Tháng 5"},
            {value: 6, text: "Tháng 6"},
            {value: 7, text: "Tháng 7"},
            {value: 8, text: "Tháng 8"},
            {value: 9, text: "Tháng 9"},
            {value: 10, text: "Tháng 10"},
            {value: 11, text: "Tháng 11"},
            {value: 12, text: "Tháng 12"},
        ];

        // Populate month dropdown
        const configSelect2 = {
            width: '100%',
            closeOnSelect: true,
            allowClear: true,
            language: 'vi',
            data: [],
        };
        monthInput.select2({
            ...configSelect2,
            placeholder: 'Chọn tháng',
        })
        yearInput.select2({
            ...configSelect2,
            placeholder: 'Chọn năm',
        })
        months.forEach((month) => {
            let option = new Option(month.text, month.value);
            if (month.value === current.getMonth() + 1)
                option.setAttribute('selected', 'selected');
            monthInput.append(option);
        });

        const currentYear = new Date().getFullYear();
        for (let year = currentYear; year >= 2014; year--) {
            let option = new Option("Năm " + year, year);
            if (year === current.getFullYear())
                option.setAttribute('selected', 'selected');
            yearInput.append(option);
        }
    }

    function initChart() {
        // Chart.js configuration
        const ctx = document.getElementById('product-popular-charts').getContext('2d');
        const baseConfig = {
            type: 'bar',
            data: {
                labels: [],
                datasets: [{
                    label: 'Số lượng',
                    data: [],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                plugins: {
                    title: {
                        display: true,
                        text: '5 sản phẩm bán chạy nhất (phổ biến)',
                        font: {
                            size: 24,
                            weight: 'bold'
                        },
                        padding: {
                            top: 30,
                            bottom: 20
                        }
                    }
                },
                scales: {
                    x: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Sản phẩm',
                            color: '#911',
                            font: {
                                size: 18,
                            },
                            padding: {top: 10, left: 0, right: 0, bottom: 0}
                        },
                        ticks: {
                            autoSkip: false, // Ensure no labels are skipped
                            maxRotation: 0,
                            minRotation: 0,
                            font: {
                                size: 17,
                                lineHeight: 1.67,
                            }
                        }
                    },
                    y: {
                        beginAtZero: true,
                        display: true,
                        title: {
                            display: true,
                            text: 'Số luợng bán',
                            color: '#911',
                            font: {
                                size: 18,
                            },
                            padding: {top: 0, left: 0, right: 0, bottom: 20}
                        },
                        ticks: {
                            stepSize: 2,
                        },
                    }
                }
            }
        }

        productsPopularChart = new Chart(ctx, {...baseConfig,});

        productsNotPopularChart = new Chart(document.getElementById('product-not-popular-charts').getContext('2d'), {
            ...baseConfig,
            options: {
                ...baseConfig.options,
                plugins: {
                    ...baseConfig.options.plugins,
                    title: {
                        ...baseConfig.options.plugins.title,
                        text: '5 sản phẩm ít bán chạy nhất'
                    }
                }
            }
        });

    }

    function handleChartUpdate(month, year) {
        http({
            url: "/api/admin/dashboard/detail",
            method: "GET",
            data: {
                month: month,
                year: year,
            },
        }).then(function (response) {
            revenue.text(formatCurrency((response.result.revenue || "0")));
            orderSuccess.text((response.result.orderSuccess || "0") + " đơn hàng");
            orderFailed.text((response.result.orderFailed || "0") + " đơn hàng");
            {
                const labels = response.result.popular.map(product => {
                    const words = product.name.split(" ");
                    const mergedWords = [];
                    for (let i = 0; i < words.length; i += 3) {
                        let merged = words[i];
                        if (i + 1 < words.length) {
                            merged += " " + words[i + 1];
                        }
                        if (i + 2 < words.length) {
                            merged += " " + words[i + 2];
                        }
                        mergedWords.push(merged);
                    }
                    return mergedWords;
                })
                const data = response.result.popular.map(product => product.quantity);
                productsPopularChart.data.labels = labels;
                productsPopularChart.data.datasets[0].data = data;
                productsPopularChart.update();
            }
            {
                const labels = response.result.notPopular.map(product => {
                    const words = product.name.split(" ");
                    const mergedWords = [];
                    for (let i = 0; i < words.length; i += 3) {
                        let merged = words[i];
                        if (i + 1 < words.length) {
                            merged += " " + words[i + 1];
                        }
                        if (i + 2 < words.length) {
                            merged += " " + words[i + 2];
                        }
                        mergedWords.push(merged);
                    }
                    return mergedWords;
                })
                const data = response.result.notPopular.map(product => product.quantity);
                productsNotPopularChart.data.labels = labels;
                productsNotPopularChart.data.datasets[0].data = data;
                productsNotPopularChart.update();
            }
        });
    }

});