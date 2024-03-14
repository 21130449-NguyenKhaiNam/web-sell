var  nameTop5=[];
top5NameProduct = top5NameProduct.slice(1, -1);

nameTop5 = top5NameProduct.split(',').map(function(item) {
    return item.trim();
});
console.log(nameTop5)


var quantityTop5=[];
 quantityTop5 = top5QuantityProduct
    .slice(1, -1)
    .split(',')
    .map(function(item) {
        var num = parseInt(item.trim(), 10);
        return isNaN(num) ? 0 : num;
    });

console.log(quantityTop5);


const barChartOptions = {
    series: [
        {
            data: quantityTop5,
            name: 'Đã bán',
        },
    ],
    chart: {
        type: 'bar',
        background: 'transparent',
        height: 350,
        toolbar: {
            show: false,
        },
    },
    colors: ['#2962ff', '#d50000', '#2e7d32', '#ff6d00', '#583cb3'],
    plotOptions: {
        bar: {
            distributed: true,
            borderRadius: 4,
            horizontal: false,
            columnWidth: '40%',
        },
    },
    dataLabels: {
        enabled: false,
    },
    fill: {
        opacity: 1,
    },
    grid: {
        borderColor: '#55596e',
        yaxis: {
            lines: {
                show: true,
            },
        },
        xaxis: {
            lines: {
                show: true,
            },
        },
    },
    legend: {
        labels: {
            colors: '#f5f7ff',
        },
        show: true,
        position: 'top',
    },
    stroke: {
        colors: ['transparent'],
        show: true,
        width: 2,
    },
    tooltip: {
        shared: true,
        intersect: false,
        theme: 'dark',
    },
    xaxis: {
        categories: nameTop5,
        title: {
            style: {
                color: '#f5f7ff',
            },
        },
        axisBorder: {
            show: true,
            color: '#55596e',
        },
        axisTicks: {
            show: true,
            color: '#55596e',
        },
        labels: {
            style: {
                colors: '#f5f7ff',
            },
            formatter: function (value) {
                value = value.replace("[", "").replace("]", "");

                const maxLength = 10;
                if (value.length > maxLength) {
                    return value.substring(0, maxLength - 3) + '...';
                }
                return value;
            },
        },
        tooltip: {
            enabled: false,
        },
    },
    yaxis: {
        title: {
            text: 'Số lượng đã bán',
            style: {
                color: '#f5f7ff',
            },
        },
        axisBorder: {
            color: '#55596e',
            show: true,
        },
        axisTicks: {
            color: '#55596e',
            show: true,
        },
        labels: {
            style: {
                colors: '#f5f7ff',
            },
        },
    },
};
const barChart = new ApexCharts(
    document.querySelector('#bar__chart'),
    barChartOptions
);
barChart.render();





var totalQuanByMonth=[];
totalQuanByMonth = totalQuantityByMonth
    .slice(1, -1)
    .split(',')
    .map(function(item) {
        var num = parseInt(item.trim(), 10);
        return isNaN(num) ? 0 : num;
    });

console.log(totalQuanByMonth);

var totalVenueByMonth = totalRevenueByMonth
    .toString()  // Chuyển mảng thành chuỗi
    .slice(1, -1) // Loại bỏ ký tự [ và ] từ chuỗi
    .split(',')  // Tách chuỗi thành mảng bằng dấu phẩy
    .map(function(item) {
        var num = parseFloat(item.trim().replace(/E/i, 'e'));
        return isNaN(num) ? 0 : num;
    });

console.log(totalVenueByMonth);


// AREA CHART
const areaChartOptions = {
    series: [
        {
            name: 'Tổng sản phẩm bán được',
            data: totalQuanByMonth,
        },
        {
            name: 'Tổng doanh thu',
            data: totalVenueByMonth,
        },
    ],
    chart: {
        type: 'area',
        background: 'transparent',
        height: 350,
        stacked: false,
        toolbar: {
            show: false,
        },
    },
    colors: ['#00ab57', '#d50000'],
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    dataLabels: {
        enabled: false,
    },
    fill: {
        gradient: {
            opacityFrom: 0.4,
            opacityTo: 0.1,
            shadeIntensity: 1,
            stops: [0, 100],
            type: 'vertical',
        },
        type: 'gradient',
    },
    grid: {
        borderColor: '#55596e',
        yaxis: {
            lines: {
                show: true,
            },
        },
        xaxis: {
            lines: {
                show: true,
            },
        },
    },
    legend: {
        labels: {
            colors: '#f5f7ff',
        },
        show: true,
        position: 'top',
    },
    markers: {
        size: 6,
        strokeColors: '#1b2635',
        strokeWidth: 3,
    },
    stroke: {
        curve: 'smooth',
    },
    xaxis: {
        axisBorder: {
            color: '#55596e',
            show: true,
        },
        axisTicks: {
            color: '#55596e',
            show: true,
        },
        labels: {
            offsetY: 5,
            style: {
                colors: '#f5f7ff',
            },
        },
    },
    yaxis: [
        {
            title: {
                text: 'Tổng sản phẩm bán được',
                style: {
                    color: '#f5f7ff',
                },
            },
            labels: {
                style: {
                    colors: ['#f5f7ff'],
                },
            },
        },
        {
            opposite: true,
            title: {
                text: 'Tổng doanh thu',
                style: {
                    color: '#f5f7ff',
                },
            },
            labels: {
                style: {
                    colors: ['#f5f7ff'],
                },
            },
        },
    ],
    tooltip: {
        shared: true,
        intersect: false,
        theme: 'dark',
    },
};

const areaChart = new ApexCharts(
    document.querySelector('#area__chart'),
    areaChartOptions
);
areaChart.render();